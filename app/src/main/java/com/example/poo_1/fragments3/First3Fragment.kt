package com.example.poo_1.fragments3

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import com.example.poo_1.R

class First3Fragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val root = inflater.inflate(R.layout.fragment_first3, container, false)

        val btnNavigate = root.findViewById<Button>(R.id.btnNavigate)
        btnNavigate.setOnClickListener {
            //busca el navigationControllor y ahi are lo que quiero que es navegar
            //se ejecuta una accion
            //findNavController().navigate(R.id.action_first3Fragment_to_second3Fragment)
            findNavController().navigate(
                First3FragmentDirections
                    .actionFirst3FragmentToSecond3Fragment(name = "Francisco"))
        }

        return root
    }
}