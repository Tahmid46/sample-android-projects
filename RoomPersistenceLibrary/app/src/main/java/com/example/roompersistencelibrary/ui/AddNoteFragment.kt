package com.example.roompersistencelibrary.ui

import android.os.Bundle
import android.text.Editable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.roompersistencelibrary.R
import com.example.roompersistencelibrary.databinding.FragmentAddNoteBinding
import com.example.roompersistencelibrary.db.Note
import com.example.roompersistencelibrary.db.NoteDb
import com.example.roompersistencelibrary.toEditable
import kotlinx.coroutines.launch

class AddNoteFragment : BaseFragment() {

    private var note: Note? = null
    private var _binding: FragmentAddNoteBinding? = null

    private val binding: FragmentAddNoteBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddNoteBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        arguments?.let {
            note = AddNoteFragmentArgs.fromBundle(it).Note
            binding.noteTitle.text = note?.title?.toEditable()
            binding.noteDetail.text = note?.note?.toEditable()
        }

        binding.done.setOnClickListener {
            if (isTitleEmpty() || isNoteEmpty()) {
                binding.noteTitle.error = "Title or note can not be empty"
                return@setOnClickListener
            } else {
                launch {
                    val mNote = Note(getNoteTitle(), getNoteDetail())
                    if (note == null) {
                        NoteDb(requireContext()).getDao().addNote(mNote)
                    } else {
                        mNote.id = note?.id!!
                        NoteDb(requireContext()).getDao().updateNote(mNote)
                    }
                }
                Toast.makeText(requireContext(), "Saved", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun isTitleEmpty() = binding.noteTitle.text.isEmpty()
    private fun isNoteEmpty() = binding.noteDetail.text.isEmpty()

    private fun getNoteTitle(): String = binding.noteTitle.text.toString().trim()
    private fun getNoteDetail(): String = binding.noteDetail.text.toString().trim()
}