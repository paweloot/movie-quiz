package com.paweloot.quiz.ui.learning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.quiz.R
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoFragment : Fragment() {

    private lateinit var viewModel: PhotoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this).get(PhotoViewModel::class.java)

        photo_list.isNestedScrollingEnabled = false
        photo_list.layoutManager = LinearLayoutManager(context)
        photo_list.adapter = PhotoAdapter()

        next_button.setOnClickListener {
            findNavController()
                .navigate(PhotoFragmentDirections.actionPhotoFragmentToMusicFragment())
        }

        photo_list.visibility = View.GONE
        next_button.visibility = View.GONE
        progress_bar.visibility = View.VISIBLE

        observeCelebrityPhotos()
    }

    private fun observeCelebrityPhotos() {
        viewModel.celebrityPhotos.observe(viewLifecycleOwner, Observer { photos ->
            if (photos.isNullOrEmpty()) {
                viewModel.fetchPhotos()
            } else {
                (photo_list.adapter as PhotoAdapter).photoData = photos
                photo_list.visibility = View.VISIBLE
                next_button.visibility = View.VISIBLE
                progress_bar.visibility = View.GONE
            }
        })
    }
}