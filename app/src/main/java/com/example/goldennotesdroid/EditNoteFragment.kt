package com.example.goldennotesdroid

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.goldennotesdroid.databinding.FragmentEditNoteBinding


class EditNoteFragment : Fragment() {
    private val viewModel: EditNoteViewModel by viewModels()

    // TODO: Rename and change types of parameters
    private var titleText: String = ""
    private var bodyText: String = ""

    private var _binding: FragmentEditNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
//            Log.d("GNReceivingFragment", it.toString())
            viewModel.getArgumentsAndSetData(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEditNoteBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.submitNoteButton.setOnClickListener {
            Log.i("GoldenNotes", "Submit button clicked")
            Log.i("GoldenNotes", viewModel.toString())
            viewModel.submitData(requireContext())
            viewModel.submitStatus.observe(viewLifecycleOwner) {
                if(it == EditNoteApiStatus.SUCCESS) {
                    findNavController().navigate(R.id.action_editNoteFragment_to_notesFragment)
                }
            }
        }
        binding.deleteNoteButton.setOnClickListener {
            Log.i("GoldenNotes", "Delete button clicked")
            viewModel.deleteNote(requireContext())
            viewModel.submitStatus.observe(viewLifecycleOwner) {
                if(it == EditNoteApiStatus.SUCCESS) {
                    findNavController().navigate(R.id.action_editNoteFragment_to_notesFragment)
                }
            }
        }

        // Inflate the layout for this fragment
        return binding.root
    }
}