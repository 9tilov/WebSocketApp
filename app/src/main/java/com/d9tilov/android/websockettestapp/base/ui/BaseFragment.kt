package com.d9tilov.android.websockettestapp.base.ui

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.d9tilov.android.websockettestapp.base.navigation.BaseNavigator
import com.d9tilov.android.websockettestapp.base.vm.BaseViewModel

abstract class BaseFragment<N : BaseNavigator>(@LayoutRes layoutId: Int) :
    Fragment(layoutId) {

    abstract fun getNavigator(): N

    @get:ColorRes
    protected open val snackBarBackgroundTint = 0
    protected open val snackBarAnchorView: View? = null
    protected abstract val viewModel: BaseViewModel<N>
    private var baseActivity: BaseActivity<*>? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity<*>) {
            baseActivity = context
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity?.let {
            viewModel.navigator = getNavigator()
        }
    }

    protected fun showSnackBar(text: String, gravityCenter: Boolean = false) {
        baseActivity?.let {
            val backgroundTint = if (snackBarBackgroundTint != 0)
                ContextCompat.getColor(it, snackBarBackgroundTint) else 0
            it.showSnackBar(text, backgroundTint, snackBarAnchorView, gravityCenter)
        }
    }

    fun showSnackBarWithAction(text: String, actionTitle: String, action: View.OnClickListener) {
        baseActivity?.let {
            val backgroundTint = if (snackBarBackgroundTint != 0)
                ContextCompat.getColor(it, snackBarBackgroundTint) else 0
            it.showSnackBar(text, backgroundTint, snackBarAnchorView, false, actionTitle, action)
        }
    }

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }
}
