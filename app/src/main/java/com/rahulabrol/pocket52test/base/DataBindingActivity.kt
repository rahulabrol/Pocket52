package com.rahulabrol.pocket52test.base

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding

/**
 * Created by Rahul Abrol on 24/2/21.
 */
abstract class DataBindingActivity : AppCompatActivity() {

    protected inline fun <reified T : ViewDataBinding> binding(
        @LayoutRes resId: Int
    ): Lazy<T> = lazy { DataBindingUtil.setContentView<T>(this, resId) }

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeViewModel()
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

    open fun observeNetworkChange() {

    }
}
