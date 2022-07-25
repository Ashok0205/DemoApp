package co.uk.mvvmsample.base

import android.view.View
import androidx.appcompat.widget.Toolbar
import co.uk.mvvmsample.databinding.LayoutToolbarBinding

interface CommonCallbacks : AsyncViewController {

    fun setToolBar(toolbarbinding: LayoutToolbarBinding?,showBack: Boolean, title: String?)

    fun isConnectedToNetwork(): Boolean
}