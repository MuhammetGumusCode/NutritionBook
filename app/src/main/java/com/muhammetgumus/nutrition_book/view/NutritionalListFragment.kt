package com.muhammetgumus.nutrition_book.view


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.muhammetgumus.nutrition_book.ViewModel.NutritionalListViewModel
import com.muhammetgumus.nutrition_book.adapter.NutritionalRecyclerAdapter
import com.muhammetgumus.nutrition_book.databinding.FragmentNutritionalListBinding
import com.muhammetgumus.nutrition_book.roomdb.Nutritional

class
NutritionalListFragment : Fragment() {

    private var _binding: FragmentNutritionalListBinding? = null

    private val binding get() = _binding!!

    private lateinit var viewModel: NutritionalListViewModel

    private  var nutritionaladapter  = NutritionalRecyclerAdapter(arrayListOf())




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNutritionalListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(NutritionalListViewModel::class.java)
        viewModel.refreshData()


        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = nutritionaladapter



        binding.swipeRefreshLayout.setOnRefreshListener {
            binding.recyclerView.visibility = View.GONE
            binding.ErrorTextview.visibility = View.GONE
            binding.uploadingprogressBar.visibility = View.VISIBLE
            viewModel.getTheDataFromTheInternet()
            binding.swipeRefreshLayout.isRefreshing = false
          }


      observeLiveData()



    }



   private fun observeLiveData() {

       viewModel._nutritionalLiveData.observe(viewLifecycleOwner) {
         //adapter
           nutritionaladapter.updateNutritionalList(it as ArrayList<Nutritional>)
        binding.recyclerView.visibility = View.VISIBLE


       }


       viewModel._errorMessage.observe(viewLifecycleOwner) {

       if (it) {

           binding.ErrorTextview.visibility = View.VISIBLE
           binding.recyclerView.visibility = View.GONE
           binding.uploadingprogressBar.visibility = View.GONE


       } else {
           binding.ErrorTextview.visibility = View.GONE
           binding.recyclerView.visibility = View.VISIBLE
           binding.uploadingprogressBar.visibility = View.GONE

       }
       }

       viewModel._loading.observe(viewLifecycleOwner) {

           if(it){

               binding.ErrorTextview.visibility = View.GONE
               binding.recyclerView.visibility = View.GONE
               binding.uploadingprogressBar.visibility = View.VISIBLE

           }
           else {

               binding.uploadingprogressBar.visibility = View.GONE
               binding.recyclerView.visibility = View.VISIBLE
               binding.ErrorTextview.visibility = View.GONE


           }
       }


   }




    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }



}