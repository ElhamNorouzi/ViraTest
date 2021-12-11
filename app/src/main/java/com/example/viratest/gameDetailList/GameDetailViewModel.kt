package com.example.viratest.gameDetailList

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
import com.example.viratest.gamelist.GameListRepository
import com.example.viratest.utils.CheckNetwork

class GameDetailViewModel : ViewModel() {
    private var gameList: MutableLiveData<GameBean> = MutableLiveData()

    fun getLiveDetailList(context: Context, loading: View?): MutableLiveData<GameBean> {
            if (CheckNetwork.isNetworkAvailable(context)){
                RetrofitCallback.callRetrofit(RetrofitClient.getService().getGameDetails(),
                    loading,
                    object : RetrofitObject<GameBean>{
                        override fun onSuccess(body: GameBean) {
                            gameList.value = body
                            AppDataBase.getInstance(context).getDetailList().insertDetailGames(body)
                        }

                        override fun onFailure(message: String) {
                            //TODO
                            Log.v("failedGames",message)
                        }

                    })

            }else{
                gameList.value = GameDetailrepository.getLocalDetails(context)
            }
        return gameList
    }

}