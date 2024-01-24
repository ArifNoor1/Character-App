package com.example.characterapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.characterapp.model.Result
import com.example.characterapp.network.Networking
import com.example.characterapp.rommdb.CharacterDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel : ViewModel(){

    private var characterMutableLiveData = MutableLiveData<List<Result>>()
   // private var characterMutableLiveDatadatabse = database.characterDao().getAllCharacters()

    fun getCharacterList(){
        viewModelScope.launch(Dispatchers.IO){
            val response = Networking.retrofit.getCharacter()
            response.body()?.let {characterList ->
                characterMutableLiveData.postValue(characterList.results)
            }

        }
    }

//    fun observeCharacterLiveDataDatabase() : LiveData<List<Result>>{
//        return characterMutableLiveDatadatabse
//    }
    fun observeCharacterLiveData() : LiveData<List<Result>>{
        return characterMutableLiveData
    }


//    fun insertCharacter(character : Result){
//        viewModelScope.launch {
//            database.characterDao().upsert(character)
//        }
//    }
//    fun deleteCharacter(character : Result){
//        viewModelScope.launch {
//            database.characterDao().delete(character)
//        }
//    }

}