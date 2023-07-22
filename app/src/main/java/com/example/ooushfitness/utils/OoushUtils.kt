package com.example.ooushfitness.utils

import android.text.Editable
import android.widget.EditText

class OoushUtils {

    companion object {

        fun getTextFromEditor(editor: EditText): String {
            return editor.text.toString()
        }

    }

}