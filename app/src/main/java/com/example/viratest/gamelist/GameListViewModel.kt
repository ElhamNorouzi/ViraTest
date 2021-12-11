package com.example.viratest.gamelist

import android.content.Context
import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.viratest.api.RetrofitCallback
import com.example.viratest.api.RetrofitClient
import com.example.viratest.api.RetrofitObject
import com.example.viratest.api.model.GameBean
import com.example.viratest.database.AppDataBase
import com.example.viratest.utils.CheckNetwork

class GameListViewModel : ViewModel() {
    private var gamesData: MutableLiveData<List<GameBean>> = MutableLiveData()

    fun getGamesData(loading: View?, context: Context): MutableLiveData<List<GameBean>> {
        if (CheckNetwork.isNetworkAvailable(context)) {
            RetrofitCallback.callRetrofit(RetrofitClient.getService().getGames(),
                loading,
                object : RetrofitObject<List<GameBean>> {
                    override fun onSuccess(body: List<GameBean>) {
                        gamesData.value = body
                        if (AppDataBase.getInstance(context).gameListDao().getGameList().isNullOrEmpty()) {
                            AppDataBase.getInstance(context).gameListDao().insertAllGames(body)
                        }
                    }

                    override fun onFailure(message: String) {
                        //TODO
                        Log.v("failedGames", message)
                    }


                })
        } else {
            gamesData.value = GameListRepository.getLocalData(context)
        }
        return gamesData

    }
}