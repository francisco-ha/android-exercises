package com.example.poo_1.fragments2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.poo_1.R


/*  La Activity crea los datos y decide qué fragmento mostrar.
    El Fragment recibe esos datos, los lee para mostrar el Toast
    y dibuja la interfaz final en la pantalla.
 */
class First2Fragment : Fragment() {

    private var name: String? = null
    private var address: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            name = it.getString(NAME_BUNDLE)
            address = it.getString(ADDRESS_BUNDLE)
            Log.i("Francisco",name.orEmpty())
            Toast.makeText(requireContext(), "Hola, $name", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first2, container, false)
    }

    companion object {
        const val NAME_BUNDLE = "name_bundle"
        const val ADDRESS_BUNDLE = "address_bundle"
        @JvmStatic
        fun newInstance(name: String, address: String) =
            First2Fragment().apply {
                arguments = Bundle().apply { //un bundle es donde va toda la informacion que le queremos pasar al fragment
                    putString(NAME_BUNDLE, name)
                    putString(ADDRESS_BUNDLE, address)
                }
            }
    }
}