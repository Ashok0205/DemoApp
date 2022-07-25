package co.uk.mvvmsample.base

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment
import java.text.SimpleDateFormat
import java.util.*

@Suppress("UNCHECKED_CAST")
fun <F : Fragment> AppCompatActivity.getCurrentFragment(fragmentClass: Class<F>): F? {
    if (this.supportFragmentManager.fragments.isEmpty()) return null
    val navHostFragment = this.supportFragmentManager.fragments.first() as? NavHostFragment

    navHostFragment?.childFragmentManager?.fragments?.forEach {
        if (fragmentClass.isAssignableFrom(it.javaClass)) {
            return it as F
        }
    }
    return null
}

fun Date.format(format: String): String {
    if (format.isBlank()) return ""
    var result: String
    try {
        val sdf = SimpleDateFormat(format, Locale.getDefault())
        result = sdf.format(this)
    } catch (e: Exception) {
        result = ""
        e.printStackTrace()
    }

    return result
}