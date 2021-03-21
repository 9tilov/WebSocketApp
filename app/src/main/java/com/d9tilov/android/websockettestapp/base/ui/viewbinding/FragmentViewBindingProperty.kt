package com.d9tilov.android.websockettestapp.base.ui.viewbinding

import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

private class FragmentViewBindingProperty<F : Fragment, T : ViewBinding>(
    viewBinder: (F) -> T
) : ViewBindingProperty<F, T>(viewBinder) {

    override fun getLifecycleOwner(thisRef: F) = thisRef.viewLifecycleOwner
}

/**
 * Create new [ViewBinding] associated with the [Fragment]
 */
@JvmName("viewBindingFragment")
fun <F : Fragment, T : ViewBinding> viewBinding(viewBinder: (F) -> T): ViewBindingProperty<F, T> {
    return FragmentViewBindingProperty(viewBinder)
}

/**
 * Create new [ViewBinding] associated with the [Fragment]
 *
 * @param vbFactory Function that create new instance of [ViewBinding]. `MyViewBinding::bind` can be used
 * @param viewProvider Provide a [View] from the Fragment. By default call [Fragment.requireView]
 */
@JvmName("viewBindingFragment")
inline fun <F : Fragment, T : ViewBinding> Fragment.viewBinding(
    crossinline vbFactory: (View) -> T,
    crossinline viewProvider: (F) -> View = Fragment::requireView
): ViewBindingProperty<F, T> {
    return viewBinding { fragment: F -> vbFactory(viewProvider(fragment)) }
}

