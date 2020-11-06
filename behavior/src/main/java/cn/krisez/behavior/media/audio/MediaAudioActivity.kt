package cn.krisez.behavior.media.audio

import android.content.ComponentName
import android.media.AudioManager
import android.support.v4.media.MediaBrowserCompat
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.PlaybackStateCompat
import android.util.Log
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import cn.krisez.behavior.R
import cn.krisez.common.CommonBaseActivity
import com.example.android.uamp.media.extensions.id
import com.example.android.uamp.media.extensions.isPlayEnabled
import com.example.android.uamp.media.extensions.isPlaying
import com.example.android.uamp.media.extensions.isPrepared
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_media_audio.*

class MediaAudioActivity : CommonBaseActivity() {

    private var musicServiceConnection: MusicServiceConnection? = null

    private val _mediaItems = MutableLiveData<List<MediaItemData>>()
    private val mediaItems: LiveData<List<MediaItemData>> = _mediaItems

    /**
     * Pass the status of the [MusicServiceConnection.networkFailure] through.
     */
    //    val networkError = Transformations.map(musicServiceConnection.networkFailure) { it }

    override fun init() {
        setTitle("Audio")
        musicServiceConnection = MusicServiceConnection.getInstance(this, ComponentName(this, MediaPlaybackService::class.java))

        play_pause.setOnClickListener {
            val mediaItem = mediaItems.value?.get(2)
            val nowPlaying = musicServiceConnection?.nowPlaying?.value
            // Grab the view for the play/pause button

            Log.d("MediaAudioActivity.kt", "click: ${Gson().toJson(mediaItem)}")
            val transportControls = musicServiceConnection?.transportControls

            val isPrepared = musicServiceConnection?.playbackState?.value?.isPrepared ?: false
            if (isPrepared && mediaItem?.mediaId == nowPlaying?.id) {
                musicServiceConnection?.playbackState?.value?.let { playbackState ->
                    when {
                        playbackState.isPlaying -> transportControls?.pause()
                        playbackState.isPlayEnabled -> transportControls?.play()
                        else -> {
                            Log.w("MediaAudioActivity", "Playable item clicked but neither play nor pause are enabled!" + " (mediaId=${mediaItem?.mediaId})")
                        }
                    }
                }
            } else {
                transportControls?.playFromMediaId(mediaItem?.mediaId, null)
            }
        }
        subscribe.setOnClickListener {
            musicServiceConnection?.subscribe("__RECOMMENDED__", subscriptionCallback)
            musicServiceConnection?.playbackState?.observeForever(playbackStateObserver)
            musicServiceConnection?.nowPlaying?.observeForever(mediaMetadataObserver)
        }
    }

    public override fun onResume() {
        super.onResume()
        volumeControlStream = AudioManager.STREAM_MUSIC
    }

    public override fun onStop() {
        super.onStop()
        // (see "stay in sync with the MediaSession")
        musicServiceConnection?.playbackState?.removeObserver(playbackStateObserver)
        musicServiceConnection?.nowPlaying?.removeObserver(mediaMetadataObserver)

        // And then, finally, unsubscribe the media ID that was being watched.
        musicServiceConnection?.unsubscribe("__RECOMMENDED__", subscriptionCallback)
    }

    private fun getResourceForMediaId(mediaId: String): Int {
        val isActive = mediaId == musicServiceConnection?.nowPlaying?.value?.id
        val isPlaying = musicServiceConnection?.playbackState?.value?.isPlaying ?: false
        return when {
            !isActive -> 0
            isPlaying -> R.drawable.ic_pause_black_24dp
            else -> R.drawable.ic_play_arrow_black_24dp
        }
    }

    private val subscriptionCallback = object : MediaBrowserCompat.SubscriptionCallback() {
        override fun onChildrenLoaded(parentId: String, children: List<MediaBrowserCompat.MediaItem>) {
            Log.d("MediaAudioActivity.kt", "onChildrenLoaded: $parentId")
            Log.d("MediaAudioActivity.kt", "onChildrenLoaded: ${children.size}")
            val itemsList = children.map { child ->
                Log.d("MediaAudioActivity.kt", "onChildrenLoaded: ${Gson().toJson(child)}")
                val subtitle = child.description.subtitle ?: ""
                MediaItemData(child.mediaId!!, child.description.title.toString(), subtitle.toString(), child.description.iconUri!!, child.isBrowsable, getResourceForMediaId(child.mediaId!!))
            }
            _mediaItems.postValue(itemsList)
        }
    }

    private val playbackStateObserver = Observer<PlaybackStateCompat> {
        val playbackState = it ?: EMPTY_PLAYBACK_STATE
        val metadata = musicServiceConnection?.nowPlaying?.value ?: NOTHING_PLAYING
        if (metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) != null) {
            _mediaItems.postValue(updateState(playbackState, metadata))
        }
    }

    private val mediaMetadataObserver = Observer<MediaMetadataCompat> {
        val playbackState = musicServiceConnection?.playbackState?.value ?: EMPTY_PLAYBACK_STATE
        val metadata = it ?: NOTHING_PLAYING
        if (metadata.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) != null) {
            _mediaItems.postValue(updateState(playbackState, metadata))
        }
    }

    private fun updateState(
            playbackState: PlaybackStateCompat,
            mediaMetadata: MediaMetadataCompat,
                           ): List<MediaItemData> {

        val newResId = when (playbackState.isPlaying) {
            true -> R.drawable.ic_pause_black_24dp
            else -> R.drawable.ic_play_arrow_black_24dp
        }

        return mediaItems.value?.map {
            val useResId = if (it.mediaId == mediaMetadata.id) newResId else 0
            it.copy(playbackRes = useResId)
        } ?: emptyList()
    }


    override fun view(): View? = View.inflate(this, R.layout.activity_media_audio, null)

}