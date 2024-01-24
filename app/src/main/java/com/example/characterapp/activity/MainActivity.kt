package com.example.characterapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.GridLayoutManager
import com.example.characterapp.R
import com.example.characterapp.adapter.CharacterAdapter
import com.example.characterapp.databinding.ActivityMainBinding
import com.example.characterapp.model.Result
import com.example.characterapp.rommdb.CharacterDatabase
import com.example.characterapp.viewmodel.CharacterViewModel

class MainActivity : AppCompatActivity() {

    private lateinit var characterAdapter: CharacterAdapter
    private lateinit var characterViewModel : CharacterViewModel
    private lateinit var binding : ActivityMainBinding

    companion object{
        const val CHARACTER_ID = "com.example.characterapp.activity.idCharacter"
        const val CHARACTER_NAME = "com.example.characterapp.activity.nameCharacter"
        const val CHARACTER_IMAGE = "com.example.characterapp.activity.imageCharacter"
        const val CHARACTER_STATUS = "com.example.characterapp.activity.statusCharacter"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val database = CharacterDatabase.getDatabase(this)
//        val factory = CharacterViewModelFactory(database)
        characterViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        characterViewModel.getCharacterList()

        prepareRecyclerView()
        observeCharacters()
       // observeCharactersdatabse()
        onCharacterClick()
    }


    private fun onCharacterClick() {
        characterAdapter.onItemClick = { character ->
            val intent = Intent(this,DetailsActivity::class.java)
            intent.putExtra(CHARACTER_ID,character.id)
            intent.putExtra(CHARACTER_NAME,character.name)
            intent.putExtra(CHARACTER_IMAGE,character.image)
            intent.putExtra(CHARACTER_STATUS,character.status)
            startActivity(intent)

        }
    }

//    private fun observeCharactersdatabse() {
//        characterViewModel.observeCharacterLiveDataDatabase().observe(this, Observer { characterList->
//            characterAdapter.setCharacterList(charactersList = characterList as ArrayList<Result>)
//        })
//    }
    private fun observeCharacters() {
        characterViewModel.observeCharacterLiveData().observe(this, Observer { characterList->
            characterAdapter.setCharacterList(charactersList = characterList as ArrayList<Result>)
        })
    }

    private fun prepareRecyclerView() {
        characterAdapter = CharacterAdapter()
        binding.characterRecyclerView.apply {
            layoutManager = GridLayoutManager(context,2, GridLayoutManager.VERTICAL,false)
            adapter = characterAdapter
        }
    }
}