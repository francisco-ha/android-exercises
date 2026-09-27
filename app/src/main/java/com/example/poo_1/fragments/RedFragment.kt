package com.example.poo_1.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.poo_1.R
import com.example.poo_1.databinding.FragmentRedBinding

class RedFragment : Fragment() {

    //[Fragment is added] -> onAttach() -> onCreate() -> onCreateView() -> onViewCreated() -> onStart() -> onResume() -> [Fragmento Activo]
    //Salida con addToBackStack() (reemplazado pero guardado en historial): onPause() -> onStop() -> onDestroyView()
    //Regreso desde el BackStack (al presionar "Atrás"): onCreateView() -> onViewCreated() -> onStart() -> onResume() -> [Fragmento Activo]
    //Destrucción definitiva (sin BackStack o al cerrar la Activity): onPause() -> onStop() -> onDestroyView() -> onDestroy() -> onDetach()
    private var listener: OnFragmentActionsListener? = null

    private var _binding: FragmentRedBinding? = null
    private val binding get() = _binding!!

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentActionsListener) {
            listener = context
        }
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentRedBinding.inflate(layoutInflater,container,false)
        //inflater.inflate(R.layout.fragment_red, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnPlus.setOnClickListener { listener?.onClickFragmentButton() }

    }
    //onActivityCreated()
    //onStart()
    //onResume()

    // ESTE ES EL MÉTODO QUE DEBES AGREGAR
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null // Libera la referencia a la vista aquí
    }
    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}