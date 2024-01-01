package com.example.tragosapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tragosapp.AppDataBase
import com.example.tragosapp.data.datasource.DataSourceImpl
import com.example.tragosapp.data.model.Drink
import com.example.tragosapp.data.model.DrinkEntity
import com.example.tragosapp.databinding.FragmentFavouriteBinding
import com.example.tragosapp.domain.RepoImpl
import com.example.tragosapp.ui.viewmodel.MainViewModel
import com.example.tragosapp.ui.viewmodel.VMFactory
import com.example.tragosapp.vo.Resource

class FavouriteFragment : Fragment() , OnDrinkClickListener{

    private var _binding: FragmentFavouriteBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel> {
        VMFactory(
            RepoImpl(
                DataSourceImpl(
                    AppDataBase.getDataBase(
                        requireActivity().applicationContext
                    )
                )
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFavouriteBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRecyclerView()
        setUpObservers()

    }

    private fun setUpObservers() {
        viewModel.getFavouriteDrink().observe(viewLifecycleOwner, Observer { it ->
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    val drinkList = it.data.map {
                        Drink(it.drinkId, it.image, it.name,it.description, it.hasAlcohol)
                    }
                    binding.rvFavouriteDrinks.adapter = MainAdapter(requireContext(),drinkList,this)
                }
                is Resource.Failure -> {}
            }
        })
    }

    private fun setUpRecyclerView() {
        binding.rvFavouriteDrinks.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavouriteDrinks.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                DividerItemDecoration.VERTICAL
            )
        )
    }

    override fun onDrinkClick(drink: Drink, position: Int) {
        TODO("Not yet implemented")
    }

    /*  override fun onDrinkClick(drink: DrinkEntity, position: Int) {
          //Borras elemento en roon
          viewModel.deleteDrink(drink)
          //Actualizas el recyclerView
          binding.rvFavouriteDrinks.adapter?.notifyItemRemoved(position)
          //Aca se le dice que no muestre mas esa info
          binding.rvFavouriteDrinks.adapter?.notifyItemRangeRemoved(position, binding.rvFavouriteDrinks.adapter?.itemCount!!)
      }*/

}



