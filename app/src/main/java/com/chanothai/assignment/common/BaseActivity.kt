package com.chanothai.assignment.common

import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.chanothai.assignment.R
import kotlinx.android.synthetic.main.toolbar_layout_default.view.*

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

        if (isToolbarCollapsed()) {
            toolbar.title = title() ?: ""
            toolbar.title_toolbar.visibility = View.GONE
        }else{
            toolbar.title_toolbar.text = title()
        }

        toolbar.setNavigationIcon(R.drawable.ic_back_arrow_white)
        setSupportActionBar(toolbar)

        supportActionBar?.let {
            supportActionBar?.setDisplayHomeAsUpEnabled(isDisplayHomeEnable())

            it.setDisplayShowTitleEnabled(false)
        }
    }

    fun updateTitleToolbar(title: String) {
        toolbar.title = title
    }

    abstract fun isDisplayHomeEnable(): Boolean
    abstract fun getToolbarInstance(): Toolbar?
    abstract fun isToolbarCollapsed(): Boolean
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