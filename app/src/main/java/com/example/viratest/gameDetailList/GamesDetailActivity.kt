package com.example.viratest.gameDetailList

import android.os.Bundle
import android.widget.MediaController
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.viratest.R
import com.example.viratest.api.model.GameBean
import com.example.viratest.databinding.ActivityGamesDetailBinding
import com.example.viratest.utils.BaseActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_games_detail.*

class GamesDetailActivity : BaseActivity() {

    private lateinit var binding: ActivityGamesDetailBinding
    private lateinit var gameDetailViewModel: GameDetailViewModel
    private lateinit var gameBean: GameBean


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_games_detail)
        gameDetailViewModel = ViewModelProviders.of(this).get(GameDetailViewModel::class.java)

        getData()
    }

    private fun getData() {
        gameDetailViewModel.getLiveDetailList(this, loading).observe(this, Observer {
            gameBean = it
            binding.moviename.text = gameBean.title
            binding.desc.text = gameBean.description
            binding.rateText.rating = gameBean.rate.toFloat()
            binding.groupText.text = gameBean.playerCont.toString()
            binding.genreText.text = gameBean.genre.genreName

            Picasso.get().load(gameBean.image).into(binding.imageGame)
            Picasso.get().load(gameBean.genre.genreImage).into(binding.imageGenre)

            binding.videoView.setVideoPath(gameBean.video)
            val mediaController = MediaController(this)
            mediaController.setAnchorView(binding.videoView)
            binding.videoView.setMediaController(mediaController)
            binding.videoView.setOnPreparedListener {
                binding.videoView.start()
            }
            binding.videoView.setOnCompletionListener { binding.videoView.start() }
        })


    }
}
