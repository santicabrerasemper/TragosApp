package com.example.tragosapp.ui

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.tragosapp.AppDataBase
import com.example.tragosapp.data.datasource.DataSourceImpl
import com.example.tragosapp.data.model.Drink
import com.example.tragosapp.data.model.DrinkEntity
import com.example.tragosapp.databinding.FragmentDetallesTragosBinding
import com.example.tragosapp.domain.RepoImpl
import com.example.tragosapp.ui.viewmodel.MainViewModel
import com.example.tragosapp.ui.viewmodel.VMFactory

class DetallesTragosFragment : Fragment() {

    private var _binding: FragmentDetallesTragosBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<MainViewModel> { VMFactory(RepoImpl(DataSourceImpl(AppDataBase.getDataBase(requireActivity().applicationContext)))) }

    inline fun <reified T : Parcelable> Bundle.getParcelableOrNull(key: String): Drink? {
        return getParcelable(key)
    }

    private lateinit var drink: Drink

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requireArguments().let { arguments ->
            drink = arguments?.getParcelableOrNull<Drink>("drink") ?: error("Invalid argument")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetallesTragosBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Glide.with(requireContext())
            .load(drink.image)
            .centerCrop()
            .into(binding.imgDrink)
        binding.drinkTitle.text = drink.name
        binding.drinkDescription.text = drink.description
        if (drink.hasAlcohol == "Non_Alcoholic"){
            binding.drinkAlcohol.text = "Non Alcoholic Drink"
        }else{
            binding.drinkAlcohol.text = "Alcoholic Drink"
        }
        binding.btnSaveDrink.setOnClickListener{
            viewModel.SaveDrink(DrinkEntity(drink.drinkId,drink.image,drink.name,drink.description,drink.hasAlcohol))
            Toast.makeText(requireContext(), "The drink has been saved in favourites", Toast.LENGTH_LONG)
                .show()
        }
    }
}