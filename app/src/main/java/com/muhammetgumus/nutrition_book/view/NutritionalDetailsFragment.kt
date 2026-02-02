package com.muhammetgumus.nutrition_book.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import androidx.lifecycle.ViewModelProvider
import com.muhammetgumus.nutrition_book.ViewModel.NutritionalDetailsViewModel
import com.muhammetgumus.nutrition_book.databinding.FragmentNutritionalDetailsBinding
import com.muhammetgumus.nutrition_book.util.dowloandurl
import com.muhammetgumus.nutrition_book.util.placeholderr


class NutritionalDetailsFragment : Fragment() {

    private lateinit var nutritionalDetailsViewModel : NutritionalDetailsViewModel

    private var _binding: FragmentNutritionalDetailsBinding? = null

    private val binding get() = _binding!!




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNutritionalDetailsBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        nutritionalDetailsViewModel = ViewModelProvider(this).get(NutritionalDetailsViewModel::class.java)



        arguments?.let {

            val uuid = NutritionalDetailsFragmentArgs.fromBundle(it).besinid
           nutritionalDetailsViewModel.getDataFromRoom(uuid)


        }

        observeLiveData()


    }





    private fun observeLiveData() {
        nutritionalDetailsViewModel.nutritionalLiveData.observe(viewLifecycleOwner) { nutritional ->
            // Gelen 'nutritional' nesnesi null değilse bu blok çalışır.
            nutritional?.let {
                // Bu blok içinde 'it' artık null olmayan bir Nutritional nesnesidir.
                binding.textViewNutritionalName.text = it.name
                binding.textViewNutritionalCalorie.text = it.calories
                binding.textViewNutritionalCarbohydrate.text = it.carbohydrate
                binding.textViewNutritionalProtein.text = it.protein
                binding.textViewNutritionalFAT.text = it.fat

                // Bu satır da artık güvenli bir şekilde, imageUrl null olmadığında çalışacaktır.
                binding.DetailsimageView.dowloandurl(it.imageUrl, placeholderr(binding.DetailsimageView.context))
            }
        }
    }





    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}