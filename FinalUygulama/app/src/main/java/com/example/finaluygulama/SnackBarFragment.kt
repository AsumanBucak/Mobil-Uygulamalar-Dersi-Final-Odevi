package com.example.finaluygulama

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import com.google.android.material.snackbar.Snackbar

class SnackBarFragment : Fragment() {

    lateinit var editTextMesaj: EditText
    lateinit var editTextAction: EditText
    lateinit var spinner: Spinner

    val spinnerSure = arrayOf("Süre seçiniz","2 sn","2.5 sn","3 sn","3.5 sn","4 sn")
    val spinnerSureDeger = arrayOf("Süre seçiniz",2000,2500,300,3500,4000)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        editTextMesaj = requireView().findViewById(R.id.editTextMesaj)
        editTextAction = requireView().findViewById(R.id.editTextAction)
        spinner = requireView().findViewById(R.id.spinner)

        val arrayAdapter = ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,spinnerSure)
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = arrayAdapter

        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                val secim = spinnerSure[p2]
                if (secim != "Süre seçiniz") {
                    val secimDeger = spinnerSureDeger[p2]
                    snackBarOlustur(secimDeger.toString().toInt())
                }
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }




    }

    private fun snackBarOlustur(secimDeger: Int) {
        Snackbar.make(requireView(),editTextMesaj.text.toString(),secimDeger).setAction(editTextAction.text.toString()
        ) {
            Toast.makeText(requireContext(), editTextAction.text.toString(), Toast.LENGTH_SHORT).show()
        }.show()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_snack_bar, container, false)
    }


}