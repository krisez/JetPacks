package cn.krisez.behavior.media

import android.content.ComponentName
import android.media.AudioManager
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import android.view.View
import cn.krisez.behavior.R
import cn.krisez.common.CommonBaseActivity
import kotlinx.android.synthetic.main.activity_media_video.*

class MediaAudioActivity : CommonBaseActivity() {

    private lateinit var mediaBrowser: MediaBrowserCompat

    override fun init() {
        setTitle("Audio")
        mediaBrowser = MediaBrowserCompat(this, ComponentName(this, MediaPlaybackService::class.java), //连接到对应App的服务
                                          connectionCallbacks, null // optional Bundle
                                         )
    }

    override fun onStart() {
        super.onStart()
        mediaBrowser.connect()
    }

    public override fun onResume() {
        super.onResume()
        volumeControlStream = AudioManager.STREAM_MUSIC
    }

    public override fun onStop() {
        super.onStop()
        // (see "stay in sync with the MediaSession")
        MediaControllerCompat.getMediaController(this)?.unregisterCallback(controllerCallback)
        mediaBrowser.disconnect()
    }

    //    自定义 MediaBrowserCompat.ConnectionCallback
    private val connectionCallbacks = object : MediaBrowserCompat.ConnectionCallback() {
        override fun onConnected() {

            // Get the token for the MediaSession
            mediaBrowser.sessionToken.also { token ->
                // Create a MediaControllerCompat
                val mediaController = MediaControllerCompat(this@MediaAudioActivity, // Context
                                                            token)
                // Save the controller
                MediaControllerCompat.setMediaController(this@MediaAudioActivity, mediaController)
            }

            // Finish building the UI
            buildTransportControls()
        }

        override fun onConnectionSuspended() {
            // The Service has crashed. Disable transport controls until it automatically reconnects
        }

        override fun onConnectionFailed() {
            // The Service has refused our connection
        }
    }

    //将您的界面连接到媒体控制器
    fun buildTransportControls() {
        val mediaController = MediaControllerCompat.getMediaController(this@MediaAudioActivity)
        // Grab the view for the play/pause button
        play_pause.setOnClickListener {
            // Since this is a play/pause button, you'll need to test the current state
            // and choose the action accordingly

            val pbState = mediaController.playbackState.state
            if (pbState == PlaybackStateCompat.STATE_PLAYING) {
                mediaController.transportControls.pause()
            } else {
                mediaController.transportControls.play()
            }
        }

        // Display the initial state
        val metadata = mediaController.metadata
        val pbState = mediaController.playbackState
        Log.d("MediaAudioActivity.kt", "buildTransportControls: ${metadata?.title}-${metadata?.date}")
        Log.d("MediaAudioActivity.kt", "buildTransportControls: ${pbState?.state}")
        // Register a Callback to stay in sync
        mediaController.registerCallback(controllerCallback)
    }

    //与媒体会话保持同步
    private var controllerCallback = object : MediaControllerCompat.Callback() {

        override fun onMetadataChanged(metadata: MediaMetadataCompat?) {}

        override fun onPlaybackStateChanged(state: PlaybackStateCompat?) {}
    }

    override fun view(): View? = View.inflate(this, R.layout.activity_media_audio, null)

}