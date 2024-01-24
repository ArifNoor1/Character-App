package com.example.characterapp.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.characterapp.databinding.CharacterItemBinding
import com.example.characterapp.model.Result

class CharacterAdapter() :  RecyclerView.Adapter<CharacterAdapter.CharacterViewHolder>(){

    private var characterList = ArrayList<Result>()
    var onItemClick : ((Result) -> Unit)? = null


    @SuppressLint("NotifyDataSetChanged")
    fun setCharacterList(charactersList: List<Result>){
        this.characterList = charactersList as ArrayList<Result>
        notifyDataSetChanged()
    }

    inner class CharacterViewHolder(val binding: CharacterItemBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharacterViewHolder {
        return CharacterViewHolder(CharacterItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return characterList.size
    }

    override fun onBindViewHolder(holder: CharacterViewHolder, position: Int) {
        Glide.with(holder.itemView)
            .load(characterList[position].image)
            .into(holder.binding.imageViewItem)
        holder.binding.tvNameItem.text = characterList[position].name
        holder.binding.tvStatusItem.text = "Status : ${characterList[position].status}"

        holder.itemView.setOnClickListener {
            onItemClick!!.invoke(characterList[position])
        }
    }
}