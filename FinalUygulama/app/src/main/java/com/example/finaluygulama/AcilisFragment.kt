package com.example.finaluygulama

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import android.widget.LinearLayout


class AcilisFragment : Fragment() {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val linearLayoutMenuSecim: LinearLayout = requireView().findViewById(R.id.linearLayoutMenuSecim)
        val imageViewArrow: ImageView = requireView().findViewById(R.id.imageViewArrow)


        val animation: Animation = AlphaAnimation(1F, 0F)
        animation.duration = 500
        animation.interpolator = LinearInterpolator()
        animation.repeatCount = Animation.INFINITE
        animation.repeatMode = Animation.REVERSE
        imageViewArrow.startAnimation(animation)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_acilis, container, false)
    }

}