package com.chanothai.assignment.common

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseActivity: AppCompatActivity() {
    private lateinit var toolbar: Toolbar

    override fun onResume() {
        super.onResume()

        this.getToolbarInstance()?.let {
            this.initView(it)
        }
    }

    private fun initView(toolbar: Toolbar) {
        this.toolbar = toolbar

        title()?.let {
//            toolbar.title_toolbar.text = it
        }

//        toolbar.setNavigationIcon(R.drawable.ic_back_arrow_white)
        setSupportActionBar(toolbar)

        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(isDisplayHomeEnable()!!)

            it.setDisplayShowTitleEnabled(false)
        }
    }

    fun updateTitleToolbar(title: String) {
//        toolbar.title_toolbar.text = title
    }

    abstract fun isDisplayHomeEnable(): Boolean?
    abstract fun getToolbarInstance(): Toolbar?
    abstract fun title(): String?

    fun <T : ViewModel> createViewModel(model: T): ViewModelProvider.Factory =
            object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    if (modelClass.isAssignableFrom(model.javaClass)) {
                        return model as T
                    }
                    throw IllegalArgumentException("Unexpected model class $modelClass")
                }
            }
}