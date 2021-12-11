package com.example.viratest.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.viratest.api.model.GameBean
import com.example.viratest.databinding.GamesItemBinding
import org.greenrobot.eventbus.EventBus

class GameAdapter(private var games: List<GameBean>): RecyclerView.Adapter<GameAdapter.GamesViewHolder>() {


    inner class GamesViewHolder(
        private val binding: GamesItemBinding
    ):RecyclerView.ViewHolder(binding.root){
        fun bind(item: GameBean){
            binding.data = item
            binding.rateText.rating =item.rate.toFloat()
            binding.groupText.text = item.playerCont.toString()
            binding.games.setOnClickListener { EventBus.getDefault().post(item) }

        }

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GamesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =GamesItemBinding.inflate(inflater)
        return GamesViewHolder(binding)
    }

    override fun getItemCount(): Int = games.size
    override fun getItemViewType(position: Int): Int =position
    override fun getItemId(position: Int): Long = games[position].id.toLong()


    override fun onBindViewHolder(holder: GamesViewHolder, position: Int) {
                holder.bind(games[position])
    }
}
