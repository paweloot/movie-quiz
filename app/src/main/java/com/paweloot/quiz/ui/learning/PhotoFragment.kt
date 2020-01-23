package com.paweloot.quiz.ui.learning

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.paweloot.quiz.R
import com.paweloot.quiz.ui.main.MainViewModel
import kotlinx.android.synthetic.main.fragment_photo.*

class PhotoFragment : Fragment() {

    companion object {
        fun newInstance() = PhotoFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_photo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(requireActivity()).get(MainViewModel::class.java)

        photo_list.layoutManager = LinearLayoutManager(context)
        photo_list.adapter = PhotoAdapter(viewModel.photoAnswers)

        next_button.setOnClickListener {
            findNavController()
                .navigate(PhotoFragmentDirections.actionPhotoFragmentToMusicFragment())
        }
    }
}