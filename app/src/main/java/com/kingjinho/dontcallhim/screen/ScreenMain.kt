package com.kingjinho.dontcallhim.screen

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.kingjinho.dontcallhim.R
import com.kingjinho.dontcallhim.databinding.ScreenMainBinding

class ScreenMain : Fragment() {

    private lateinit var binding: ScreenMainBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ScreenMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toAddNumber.setOnClickListener {
            findNavController().navigate(R.id.screenAddRemoveNumber)
        }
    }

}