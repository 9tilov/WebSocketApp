package com.d9tilov.android.websockettestapp.base.ui

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.viewbinding.ViewBinding
import com.d9tilov.android.websockettestapp.R
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity<T : ViewBinding> : AppCompatActivity() {

    private lateinit var viewBinding: T
    private val contentLayout: ViewGroup by lazy { findViewById<ViewGroup>(android.R.id.content) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = performDataBinding()
        setContentView(viewBinding.root)
    }

    abstract fun performDataBinding(): T

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item.itemId == android.R.id.home) {
            onBackPressed()
            true
        } else {
            super.onOptionsItemSelected(item)
        }
    }

    fun showSnackBar(
        text: String,
        @ColorInt backgroundTint: Int = 0,
        anchorView: View? = null,
        gravityCenter: Boolean = false,
        actionTitle: String? = null,
        action: View.OnClickListener? = null,
    ) {
        val snackBar = Snackbar.make(contentLayout, text, Snackbar.LENGTH_INDEFINITE)
        if (backgroundTint != 0) {
            snackBar.setBackgroundTint(backgroundTint)
        }
        snackBar.anchorView = anchorView
        val view: View = snackBar.view
        val tv = view.findViewById<View>(R.id.snackbar_text) as TextView
        if (gravityCenter) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                tv.textAlignment = View.TEXT_ALIGNMENT_CENTER
            } else {
                tv.gravity = Gravity.CENTER_HORIZONTAL
            }
        }
        snackBar.setAction(actionTitle, action)
        snackBar.show()
    }

    companion object {
        init {
            AppCompatDelegate.setCompatVectorFromResourcesEnabled(true)
        }
    }
}
