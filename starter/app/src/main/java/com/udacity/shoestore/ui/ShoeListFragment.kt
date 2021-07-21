package com.udacity.shoestore.ui

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentShoeListBinding
import com.udacity.shoestore.viewmodels.ShoeListViewModel


class ShoeListFragment : Fragment() {

    private lateinit var viewModel: ShoeListViewModel

    private lateinit var binding: FragmentShoeListBinding

    private lateinit var viewGroup: ViewGroup

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

        viewModel = ViewModelProvider(this).get(ShoeListViewModel::class.java)
        binding.shoeListViewModel = viewModel

        setHasOptionsMenu(true)

        if (view != null) {
            viewGroup = view as ViewGroup
            viewGroup.let {
                View.inflate(context, R.layout.shoe_list_item, it)
            }
        }

        viewModel.shoeList.observe(viewLifecycleOwner, Observer { listOfShoes ->
            listOfShoes.forEach {
//                val linearLayout = LinearLayout(context)
//                linearLayout.orientation = LinearLayout.VERTICAL
//
//                val layoutParams =
//                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
//                layoutParams.setMargins(0, R.dimen.standard_margin, 0, 0)


                val newShoeItem = ShoeItem(requireContext())

                newShoeItem.setShoeCompany(it.company)
                newShoeItem.setShoeName(it.name)
                newShoeItem.setShoeSize(it.size.toString())
                newShoeItem.setShoeDescription(it.description)
                binding.shoeListLinearLayout.addView(newShoeItem)
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
        Toast.makeText(context, "Come back soon!", Toast.LENGTH_SHORT).show()
    }
}