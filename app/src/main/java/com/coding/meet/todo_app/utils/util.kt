package com.coding.meet.todo_app.utils

import android.app.Dialog
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import com.google.android.material.textfield.TextInputLayout

// Enum class representing different status types
enum class Status{
    SUCCESS,
    ERROR,
    LOADING
}

// Enum class representing different result types for operations
enum class StatusResult{
    Added,
    Updated,
    Deleted
}

// Extension function to hide the soft keyboard
fun Context.hideKeyBoard(view : View){
    try {
        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken,0)
    }catch (e: Exception){
        e.printStackTrace()
    }
}

// Extension function to show a long toast message
fun Context.longToastShow(msg:String){
    Toast.makeText(this,msg,Toast.LENGTH_LONG).show()
}

// Extension function to set up a custom dialog
fun Dialog.setupDialog(layoutResId: Int){
    setContentView(layoutResId)
    window!!.setLayout(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    setCancelable(false)
}

// Function to validate an EditText field with TextInputLayout
fun validateEditText(editText: EditText, textTextInputLayout: TextInputLayout): Boolean {
    return when {
        editText.text.toString().trim().isEmpty() -> {
            textTextInputLayout.error = "Required"
            false
        }
        else -> {
            textTextInputLayout.error = null
            true
        }
    }
}

// Function to clear the content of an EditText field and reset TextInputLayout error
fun clearEditText(editText: EditText, textTextInputLayout: TextInputLayout) {
    editText.text = null
    textTextInputLayout.error = null
}