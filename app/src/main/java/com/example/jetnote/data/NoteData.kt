package com.example.jetnote.data

import com.example.jetnote.model.Note

class NotesDatSource{
    fun loadNotes(): List<Note>{

        return listOf(
            Note(
                title = "Meeting Notes",
                description = "Discuss project timelines and milestones."
            ),
            Note(
                title = "Shopping List",
                description = "Buy milk, eggs, bread, and coffee."
            ),
            Note(
                title = "Workout Plan",
                description = "Morning: Jogging, Evening: Weightlifting."
            ),
            Note(
                title = "Book Ideas",
                description = "Draft concepts for a mystery novel."
            ),
            Note(
                title = "Travel Checklist",
                description = "Pack passport, clothes, charger, and toiletries."
            )
        )

    }
}