package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.*
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.viewmodels.ShoeListViewModel


class ShoeListFragment : Fragment() {

    private val viewModel by activityViewModels<ShoeListViewModel>()
    private lateinit var binding: FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )

        setHasOptionsMenu(true)

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { listOfShoes ->
            listOfShoes.forEach { shoe ->
                val shoeItem = ShoeItem(requireContext(), shoe)
                binding.shoeListLinearLayout.addView(shoeItem)
            }
        })

        viewModel.isUserLoggedIn.observe(viewLifecycleOwner, Observer {
            if (it == false) {
                val action = ShoeListFragmentDirections.actionShoeListFragmentToLoginFragment()
                NavHostFragment.findNavController(this).navigate(action)
            }
        })

        binding.addShoeFab.setOnClickListener {
            val action = ShoeListFragmentDirections.actionShoeListFragmentToShoeDetailFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.toolbar_icon_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.logout -> loggingOut()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun loggingOut() {
        viewModel.onLogOut()
    }
}