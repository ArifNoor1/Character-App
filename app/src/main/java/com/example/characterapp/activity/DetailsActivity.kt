package com.example.characterapp.activity

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ReportFragment.Companion.reportFragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.example.characterapp.databinding.ActivityDetailsBinding
import com.example.characterapp.model.Result
import com.example.characterapp.rommdb.CharacterDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlin.random.Random

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding
    private lateinit var characterId : String
    private lateinit var characterName : String
    private lateinit var characterImage : String
    private lateinit var characterStatus : String




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getCharacterInformationFromCharacter()
        setCharacterInformationInViews()

        binding.btnAddFavorite.setOnClickListener {
            addData()
        }
        binding.btnRemove.setOnClickListener{
            val database = CharacterDatabase.getDatabase(this)
            lifecycleScope.launch(Dispatchers.Main){
                database.characterDao().delete(character = Result(characterId,characterName,characterImage,characterStatus))
                Toast.makeText(this@DetailsActivity,"Deleted favorite"
                    ,Toast.LENGTH_SHORT).show()
                binding.ivAddFavorite.visibility = View.GONE
            }
        }
    }



    @SuppressLint("SuspiciousIndentation")
    private fun addData() {
            val database = CharacterDatabase.getDatabase(this)
                lifecycleScope.launch(Dispatchers.Main){
                        database.characterDao().upsert(
                            character = Result(characterId,characterName,characterImage,characterStatus) )
                        Toast.makeText(this@DetailsActivity,"Data added to favorite"
                            ,Toast.LENGTH_SHORT).show()
                        binding.ivAddFavorite.visibility = View.VISIBLE



        }

    }


    private fun setCharacterInformationInViews() {
        Glide.with(applicationContext)
            .load(characterImage)
            .into(binding.imageDetails)
        binding.tvName.text = characterName
        binding.tvStatus.text = characterStatus
    }

    private fun getCharacterInformationFromCharacter() {
        val intent = intent
        characterId = intent.getStringExtra(MainActivity.CHARACTER_ID).toString()
        characterName= intent.getStringExtra(MainActivity.CHARACTER_NAME).toString()
        characterImage = intent.getStringExtra(MainActivity.CHARACTER_IMAGE).toString()
        characterStatus = intent.getStringExtra(MainActivity.CHARACTER_STATUS).toString()


    }
}