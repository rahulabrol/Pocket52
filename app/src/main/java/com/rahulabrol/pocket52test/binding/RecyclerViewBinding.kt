package com.rahulabrol.pocket52test.binding

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by Rahul Abrol on 24/2/21.
 */
@BindingAdapter("adapter")
fun bindAdapter(view: RecyclerView, adapter: RecyclerView.Adapter<*>) {
    view.apply {
        layoutManager = LinearLayoutManager(view.context)
        this.adapter = adapter
    }
}