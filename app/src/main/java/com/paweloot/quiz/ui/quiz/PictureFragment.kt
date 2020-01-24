package com.paweloot.quiz.ui.quiz

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.paweloot.quiz.databinding.FragmentPictureBinding

private const val PHOTO_URL = "photoUrl"

class PictureFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding =
            FragmentPictureBinding.inflate(inflater, container, false)

        binding.photoUrl = arguments?.getString(PHOTO_URL) ?: ""

        return binding.root
    }

    companion object {
        fun newInstance(photoUrl: String): PictureFragment {
            val fragment = PictureFragment()
            val args = Bundle()

            args.putString(PHOTO_URL, photoUrl)
            fragment.arguments = args

            return fragment
        }
    }
}