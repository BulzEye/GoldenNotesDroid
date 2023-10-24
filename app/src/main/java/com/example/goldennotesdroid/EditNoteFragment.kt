package com.example.goldennotesdroid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.goldennotesdroid.databinding.FragmentEditNoteBinding


class EditNoteFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var titleText: String = ""
    private var bodyText: String = ""

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            titleText = it.getString(NOTE_TITLE).toString()
            bodyText = it.getString(NOTE_BODY).toString()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.editTitle.setText(titleText)
        binding.editBody.setText(bodyText)
    }

    companion object {
        const val NOTE_TITLE = "noteTitle"
        const val NOTE_BODY = "noteBody"
    }
}