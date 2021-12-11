package com.example.viratest.gamelist

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.example.viratest.R
import com.example.viratest.adapters.GameAdapter
import com.example.viratest.api.model.GameBean
import com.example.viratest.databinding.ActivityGamelistBinding
import com.example.viratest.gameDetailList.GamesDetailActivity
import com.example.viratest.utils.BaseActivity
import kotlinx.android.synthetic.main.activity_gamelist.*
import org.greenrobot.eventbus.EventBus
import org.greenrobot.eventbus.Subscribe
import org.greenrobot.eventbus.ThreadMode

class GameListActivity : BaseActivity() {
    private lateinit var gameListViewModel: GameListViewModel
    private var gameList = mutableListOf<GameBean>()
    private lateinit var binding: ActivityGamelistBinding
    private lateinit var gamesAdapter: RecyclerView.Adapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_gamelist)
        gameListViewModel = ViewModelProviders.of(this).get(GameListViewModel::class.java)

        EventBus.getDefault().register(this)

        initGamesadapter()
        getData()

    }

    private fun getData() {
        gameListViewModel.getGamesData(loading, this).observe(this, Observer {
            gameList.addAll(it)
            if (gameList.isNullOrEmpty())
                    ShowLongToast("برای گرفتن دیتا باید یکبار به نت وصل شوید")
            else
                binding.gameLisRecycler.adapter!!.notifyDataSetChanged()
        })
    }

        private fun initGamesadapter() {
            gamesAdapter = GameAdapter(gameList)
            binding.gameLisRecycler.adapter = gamesAdapter
            binding.gameLisRecycler.adapter!!.notifyDataSetChanged()
        }

    @Subscribe(threadMode = ThreadMode.MAIN)
    fun gameClicked(game: GameBean){
        val intent = Intent(this, GamesDetailActivity::class.java)
        startActivity(intent)
    }

    override fun onStart() {
        super.onStart()
        EventBus.getDefault().unregister(theme)
    }



}
