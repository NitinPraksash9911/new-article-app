package `in`.nitin.newsapp.ui.utils

import android.content.Context
import android.graphics.Color
import android.view.View
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar


fun String.snack(color: Int, view: View) {
    val snackbar = Snackbar.make(view, this, Snackbar.LENGTH_LONG)
    snackbar.view.setBackgroundColor(color)
    snackbar.setTextColor(Color.WHITE)
    snackbar.show()
}

fun String.showToast(context: Context) {

    Toast.makeText(context, this, Toast.LENGTH_LONG).show()
}

fun View.show() {
    this.visibility = View.VISIBLE
}

fun View.hide() {
    this.visibility = View.GONE
}
