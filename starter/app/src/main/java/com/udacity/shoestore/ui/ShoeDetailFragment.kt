package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeDetailBinding
import com.udacity.shoestore.viewmodels.ShoeListViewModel

class ShoeDetailFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    private lateinit var binding: FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )

        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        binding.shoeDetailViewModel = viewModel

        binding.newShoeAddNewShoeButton.setOnClickListener {
            if (viewModel.addShoeToList()) {
                viewModel.addShoeToList()
                findNavController().navigateUp()
            } else {
                Toast.makeText(requireContext(), "All fields must be filled.", Toast.LENGTH_LONG)
                    .show()
            }
        }

        binding.newShoeCancelButton.setOnClickListener {
            findNavController().navigateUp()
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_icon_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return requireView().findNavController().navigateUp()
    }
}