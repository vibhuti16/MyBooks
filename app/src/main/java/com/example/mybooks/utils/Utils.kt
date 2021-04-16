package com.example.mybooks.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.TypedValue
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.fragment.app.FragmentActivity

object Utils {
    /**
     * OPEN URL
     */
    fun openUrl(context: Context, url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        context.startActivity(browserIntent)
    }

}