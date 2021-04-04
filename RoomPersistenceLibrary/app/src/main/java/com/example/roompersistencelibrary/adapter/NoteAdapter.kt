package com.example.roompersistencelibrary.adapter

import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.example.roompersistencelibrary.R
import com.example.roompersistencelibrary.db.Note
import com.example.roompersistencelibrary.ui.HomeFragmentDirections

class NoteAdapter(
        private val notes: List<Note>
) : RecyclerView.Adapter<NoteAdapter.NotesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesViewHolder {
        return NotesViewHolder(
                LayoutInflater.from(parent.context).inflate(R.layout.note_layout, parent, false)
        )
    }

    override fun onBindViewHolder(holder: NotesViewHolder, position: Int) {
        holder.title.text = notes[position].title
        holder.detail.text = notes[position].note

        holder.view.setOnClickListener {
            val action = HomeFragmentDirections.actionAddNote(notes[position])
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount() = notes.size

    class NotesViewHolder(val view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.findViewById<TextView>(R.id.title)
        val detail: TextView = view.findViewById<TextView>(R.id.detail)

    }
}