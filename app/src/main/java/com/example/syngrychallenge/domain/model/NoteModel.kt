package com.example.syngrychallenge.domain.model
import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteModel (
    val id: Int = 0,
    val title: String,
    val note: String
): Parcelable