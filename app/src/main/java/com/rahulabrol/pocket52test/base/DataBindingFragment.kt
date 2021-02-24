package com.rahulabrol.pocket52test.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

/**
 * Created by Rahul Abrol on 24/2/21.
 */
abstract class DataBindingFragment : Fragment() {

    protected inline fun <reified T : ViewDataBinding> binding(
        inflater: LayoutInflater,
        @LayoutRes resId: Int,
        container: ViewGroup?
    ): T = DataBindingUtil.inflate(inflater, resId, container, false)

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observeViewModel()
        setUpViews()
    }

    /**
     * To observe viewModel Live Data format is:
     *
     * viewModel.liveData.observe([getViewModelStore], Observer { updateViews(it) })
     *
     * Here the fragment's view is life cycle owner to prevent LiveData Leak
     */
    open fun observeViewModel() {

    }

    /**
     * To set the view
     * */
    open fun setUpViews() {

    }
}