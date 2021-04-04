package com.example.roompersistencelibrary.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.roompersistencelibrary.R
import com.example.roompersistencelibrary.adapter.NoteAdapter
import com.example.roompersistencelibrary.databinding.FragmentHomeBinding
import com.example.roompersistencelibrary.db.Note
import com.example.roompersistencelibrary.db.NoteDb
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.coroutines.launch
import java.util.zip.Inflater

class HomeFragment : BaseFragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding: FragmentHomeBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.noteRecyclerView.setHasFixedSize(true)
        binding.noteRecyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        launch {
            val notes = NoteDb(requireContext()).getDao().getAllNotes()
            binding.noteRecyclerView.adapter = NoteAdapter(notes)
        }

        binding.buttonAdd.setOnClickListener {
            val action = HomeFragmentDirections.actionAddNote(Note("",""))
            Navigation.findNavController(it).navigate(action)
        }
    }
}