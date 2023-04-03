package com.example.finaluygulama

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView


class RgbFragment : Fragment(), SeekBar.OnSeekBarChangeListener {


    var red = 0
    var green = 188
    var blue = 212

    lateinit var mainLayout : LinearLayout
    lateinit var textViewRgb : TextView
    lateinit var seekBarRed : SeekBar
    lateinit var seekBarGreen : SeekBar
    lateinit var seekBarBlue : SeekBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        seekBarRed = requireView().findViewById(R.id.seekbarRed)
        seekBarGreen = requireView().findViewById(R.id.seekbarGreen)
        seekBarBlue = requireView().findViewById(R.id.seekbarBlue)
        textViewRgb  = requireView().findViewById(R.id.textViewRgb)
        mainLayout = requireView().findViewById(R.id.mainLayout)

        seekBarRed.setOnSeekBarChangeListener(this)
        seekBarGreen.setOnSeekBarChangeListener(this)
        seekBarBlue.setOnSeekBarChangeListener(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {


        return inflater.inflate(R.layout.fragment_rgb, container, false)
    }

    override fun onProgressChanged(p0: SeekBar?, p1: Int, p2: Boolean) {
        when(p0?.id) {
            R.id.seekbarRed -> {
                red = p1
            }
            R.id.seekbarGreen -> {
                green = p1
            }
            R.id.seekbarBlue -> {
                blue = p1
            }
        }
        mainLayout.setBackgroundColor(Color.rgb(red,green, blue))
        val hex = HexCode(red,green,blue)
        textViewRgb.text = hex.lowercase()

    }

    private fun HexCode(red: Int, green: Int, blue: Int): String {
        var code = ""
        code = "#"
        code += Integer.toHexString(red)
        code += Integer.toHexString(green)
        code += Integer.toHexString(blue)
        return code
    }

    override fun onStartTrackingTouch(p0: SeekBar?) {

    }

    override fun onStopTrackingTouch(p0: SeekBar?) {

    }


}