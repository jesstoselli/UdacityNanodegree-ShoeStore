package com.udacity.shoestore.ui.instruction

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentInstructionBinding
import com.udacity.shoestore.ui.welcome.WelcomeFragmentDirections

class InstructionFragment : Fragment() {

    private lateinit var viewModel: InstructionViewModel

    private lateinit var binding: FragmentInstructionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            layoutInflater,
            R.layout.fragment_instruction,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(InstructionViewModel::class.java)
        binding.instructionViewModel = viewModel

        binding.goToShoeListButton.setOnClickListener {
            val action = InstructionFragmentDirections.actionInstructionFragmentToShoeListFragment()
            NavHostFragment.findNavController(this).navigate(action)
        }

        // Inflate the layout for this fragment
        return binding.root
    }

}