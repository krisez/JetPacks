package cn.krisez.behavior.media

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import android.media.AudioAttributes
import android.media.AudioFocusRequest
import android.media.AudioManager
import android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY
import android.media.MediaDescription
import android.media.browse.MediaBrowser
import android.os.Build
import android.os.Bundle
import android.service.media.MediaBrowserService
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaDescriptionCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import androidx.annotation.RequiresApi
import androidx.core.net.toUri
import androidx.media.MediaBrowserServiceCompat

/**
 * https://developer.android.google.cn/guide/topics/media-apps/audio-app/building-a-mediabrowserservice#java
 * MediaBrowserService 有两个方法来处理客户端连接：
 * onGetRoot() 控制对服务的访问
 * onLoadChildren() 使客户端能够构建和显示 内容层次结构菜单。
 */
class MediaPlaybackService : MediaBrowserServiceCompat() {
    private val logTag = "媒体Service"
    private var mediaSession: MediaSessionCompat? = null
    private val MY_MEDIA_ROOT_ID = "media_root_id"
    private val MY_EMPTY_MEDIA_ROOT_ID = "empty_root_id"

    //播放状态管理
    private lateinit var stateBuilder: PlaybackStateCompat.Builder

    override fun onCreate() {
        super.onCreate()
        // Create a MediaSessionCompat
        mediaSession = MediaSessionCompat(baseContext, logTag).apply {

            // Enable callbacks from MediaButtons and TransportControls
            setFlags(MediaSessionCompat.FLAG_HANDLES_MEDIA_BUTTONS or MediaSessionCompat.FLAG_HANDLES_TRANSPORT_CONTROLS)

            // Set an initial PlaybackState with ACTION_PLAY, so media buttons can start the player
            stateBuilder = PlaybackStateCompat.Builder()
                    .setActions(PlaybackStateCompat.ACTION_PLAY or PlaybackStateCompat.ACTION_PLAY_PAUSE)
            setPlaybackState(stateBuilder.build())

            // MySessionCallback() has methods that handle callbacks from a media controller
            setCallback(callback)

            // Set the session's token so that client activities can communicate with it.
            setSessionToken(sessionToken)
        }
    }

    override fun onGetRoot(clientPackageName: String, clientUid: Int, rootHints: Bundle?): BrowserRoot? {
        // (Optional) Control the level of access for the specified package name.
        // You'll need to write your own logic to do this.
        return BrowserRoot(MY_MEDIA_ROOT_ID, null)
    }

    override fun onLoadChildren(parentId: String, result: Result<MutableList<MediaBrowserCompat.MediaItem>>) {
        //  Browsing not allowed
        if (MY_EMPTY_MEDIA_ROOT_ID == parentId) {
            result.sendResult(null)
            return
        }

        // Assume for example that the music catalog is already loaded/cached.
        val mediaItems = arrayListOf<MediaBrowserCompat.MediaItem>()
        val item = MediaBrowserCompat.MediaItem(MediaDescriptionCompat.Builder().setTitle("酒醉的蝴蝶")
                                                        .setMediaUri("http://172.20.141.187/media/%E5%B4%94%E4%BC%9F%E7%AB%8B%20-%20%E9%85%92%E9%86%89%E7%9A%84%E8%9D%B4%E8%9D%B6mp3%E5%85%8D%E8%B4%B9%E4%B8%8B%E8%BD%BD.mp3".toUri())
                                                        .build().mediaDescription as MediaDescriptionCompat, 0)
        mediaItems.add(item)
        // Check if this is the root menu:
        if (MY_MEDIA_ROOT_ID == parentId) {
            // Build the MediaItem objects for the top level,
            // and put them in the mediaItems list...

        } else {
            // Examine the passed parentMediaId to see which submenu we're at,
            // and put the children of that menu in the mediaItems list...
        }
        result.sendResult(mediaItems)
    }

    private val intentFilter = IntentFilter(ACTION_AUDIO_BECOMING_NOISY)

    // Defined elsewhere...
    private lateinit var afChangeListener: AudioManager.OnAudioFocusChangeListener
    private val myNoisyAudioStreamReceiver = BecomingNoisyReceiver()
    private lateinit var myPlayerNotification: MediaStyleNotification
    private lateinit var service: MediaBrowserService
    private lateinit var player: SomeKindOfPlayer
    private lateinit var audioFocusRequest: AudioFocusRequest

    private val callback = object : MediaSessionCompat.Callback() {

        override fun onPlay() {
            val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            // Request audio focus for playback, this registers the afChangeListener

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                audioFocusRequest = AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN).run {
                    setOnAudioFocusChangeListener(afChangeListener)
                    setAudioAttributes(AudioAttributes.Builder().run {
                        setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        build()
                    })
                    build()
                }
                val result = am.requestAudioFocus(audioFocusRequest)
                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    // Start the service
                    startService(Intent(baseContext, MediaBrowserService::class.java))
                    // Set the session active  (and update metadata and state)
                    mediaSession?.isActive = true
                    // start the player (custom call)
                    player.start()
                    // Register BECOME_NOISY BroadcastReceiver
                    registerReceiver(myNoisyAudioStreamReceiver, intentFilter)
                    // Put the service in the foreground, post notification
                    service.startForeground(10, myPlayerNotification)
                }
            }
        }

        override fun onStop() {
            val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            // Abandon audio focus
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                am.abandonAudioFocusRequest(audioFocusRequest)
            }else{
                am.requestAudioFocus()
            }
            unregisterReceiver(myNoisyAudioStreamReceiver)
            // Stop the service
            service.stopSelf()
            // Set the session inactive  (and update metadata and state)
            mediaSession?.isActive = false
            // stop the player (custom call)
            player.stop()
            // Take the service out of the foreground
            service.stopForeground(false)
        }

        override fun onPause() {
            val am = getSystemService(Context.AUDIO_SERVICE) as AudioManager
            // Update metadata and state
            // pause the player (custom call)
            player.pause()
            // unregister BECOME_NOISY BroadcastReceiver
            unregisterReceiver(myNoisyAudioStreamReceiver)
            // Take the service out of the foreground, retain the notification
            service.stopForeground(false)
        }
    }
}