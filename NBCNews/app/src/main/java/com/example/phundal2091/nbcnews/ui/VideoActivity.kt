package com.example.phundal2091.nbcnews.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.phundal2091.nbcnews.R
import com.example.phundal2091.nbcnews.constants.Constants.Companion.url_key_extra
import com.example.phundal2091.nbcnews.constants.Constants.Companion.video_summary_key_extra
import com.example.phundal2091.nbcnews.media_control.IMediaController
import com.example.phundal2091.nbcnews.media_control.MediaController
import kotlinx.android.synthetic.main.activity_video.*

class VideoActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video)
        val mediaController : IMediaController = MediaController(this)
        if (intent != null
                && intent.hasExtra(url_key_extra)
                && intent.hasExtra(video_summary_key_extra)) {
            val url = intent.getStringExtra(url_key_extra)
            val summaryOfVideo = intent.getStringExtra(video_summary_key_extra)
            summary.text = summaryOfVideo
            mediaController.playVideo(url, videoView)
        }
    }
}
