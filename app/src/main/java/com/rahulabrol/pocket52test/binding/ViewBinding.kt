package com.rahulabrol.pocket52test.binding

import android.view.View
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData


/**
 * Created by Rahul Abrol on 24/2/21.
 *
 * Handling all the binding adapters for views here.
 */

@BindingAdapter("toast")
fun bindToast(view: View, text: LiveData<String>) {
    text.value?.let {
        Toast.makeText(view.context, it, Toast.LENGTH_SHORT).show()
    }
}

@BindingAdapter("gone")
fun bindGone(view: View, shouldBeGone: Boolean) {
    if (shouldBeGone) {
        view.visibility = View.GONE
    } else {
        view.visibility = View.VISIBLE
    }
}