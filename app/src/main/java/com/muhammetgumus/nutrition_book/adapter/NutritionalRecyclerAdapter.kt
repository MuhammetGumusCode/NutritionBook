package com.muhammetgumus.nutrition_book.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.muhammetgumus.nutrition_book.databinding.NutritionRecyclerRowBinding
import com.muhammetgumus.nutrition_book.roomdb.Nutritional
import com.muhammetgumus.nutrition_book.util.dowloandurl
import com.muhammetgumus.nutrition_book.util.placeholderr
import com.muhammetgumus.nutrition_book.view.NutritionalListFragmentDirections

class NutritionalRecyclerAdapter(val NutritionalList : ArrayList<Nutritional>) : RecyclerView.Adapter<NutritionalRecyclerAdapter.NutritionalViewHolder>() {

    class NutritionalViewHolder(val binding: NutritionRecyclerRowBinding) : RecyclerView.ViewHolder(binding.root) {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionalViewHolder {

        val binding = NutritionRecyclerRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NutritionalViewHolder(binding)

    }


    fun  updateNutritionalList(newNutritionalList : ArrayList<Nutritional>){
           NutritionalList.clear()
           NutritionalList.addAll(newNutritionalList)
           notifyDataSetChanged()



    }


    override fun onBindViewHolder(holder: NutritionalViewHolder, position: Int) {


            holder.binding.textViewName.text = NutritionalList[position].name
            holder.binding.textViewCalories.text = NutritionalList[position].calories
            holder.binding.ListimageView.dowloandurl(NutritionalList[position].imageUrl, placeholderr(holder.itemView.context))

        holder.itemView.setOnClickListener {
            val action = NutritionalListFragmentDirections.actionNutritionalListFragmentToNutritionalDetailsFragment(NutritionalList[position].uuid)
            Navigation.findNavController(it).navigate(action)


        }




    }


    override fun getItemCount(): Int {
        return NutritionalList.size

    }





}