package com.example.roompersistencelibrary

import android.text.Editable

fun String.toEditable() = Editable.Factory.getInstance().newEditable(this)