package com.udacity.shoestore.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.udacity.shoestore.R
import com.udacity.shoestore.databinding.FragmentLoginBinding

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    private lateinit var binding: FragmentLoginBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        binding.loginViewModel = viewModel

        viewModel.userHasLoggedIn.observe(viewLifecycleOwner, Observer { hasLoggedIn ->
            if (hasLoggedIn) {
                saveEmailAndPassword()
                loginDone(hasLoggedIn)
            }
        })

        return binding.root
    }

    private fun saveEmailAndPassword() {
        viewModel.login = binding.emailEditText.text.toString()
        viewModel.password = binding.passwordEditText.text.toString()
    }

    private fun loginDone(hasLoggedIn: Boolean) {
        if (hasLoggedIn) {
            val action = LoginFragmentDirections.actionLoginFragmentToWelcomeFragment()
            findNavController(this).navigate(action)
        }
    }

}

//                Toast.makeText(requireContext(), "${viewModel.userHasLoggedIn.value}", Toast.LENGTH_SHORT).show()
