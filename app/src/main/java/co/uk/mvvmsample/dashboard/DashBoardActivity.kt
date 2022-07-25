package co.uk.mvvmsample.dashboard

import android.os.Bundle
import android.text.TextUtils.replace

import androidx.navigation.findNavController

import androidx.drawerlayout.widget.DrawerLayout
import com.google.android.material.navigation.NavigationView

import android.view.Menu
import android.view.MenuItem
import android.widget.FrameLayout
import android.widget.Switch

import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity

import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.NavController
import androidx.navigation.ui.NavigationUI.navigateUp
import co.uk.mvvmsample.R
import co.uk.mvvmsample.base.BaseAtivity
import co.uk.mvvmsample.databinding.ActivityDashBoardBinding
import co.uk.mvvmsample.databinding.ContentDashBoardBinding
import co.uk.mvvmsample.databinding.LayoutToolbarBinding
import co.uk.mvvmsample.fragments.gallery.GalleryFragment
import co.uk.mvvmsample.fragments.home.HomeFragment
import co.uk.mvvmsample.utills.Util.Companion.addFragment
import co.uk.mvvmsample.utills.Util.Companion.replaceFragment
import kotlinx.android.synthetic.main.content_dash_board.view.*
import androidx.navigation.Navigation
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import androidx.navigation.NavDestination
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import android.view.View
import androidx.core.view.GravityCompat
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.*


class DashBoardActivity : BaseAtivity(), NavigationView.OnNavigationItemSelectedListener {



    private lateinit var navController: NavController
    var dashBoardViewModel: DashBoardViewModel? = null
    lateinit var mDashBinding: ActivityDashBoardBinding
    lateinit var navView: NavigationView
    lateinit  var drawerLayout: DrawerLayout
    lateinit var toggle: ActionBarDrawerToggle
    lateinit var toolbarBinding: LayoutToolbarBinding
    lateinit var contentDashBoardBinding : ContentDashBoardBinding
    lateinit var appBarConfiguration:AppBarConfiguration


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mDashBinding = DataBindingUtil.setContentView(this,R.layout.activity_dash_board)

       mDashBinding.dashboardViewModel = dashBoardViewModel

       navController = findNavController(R.id.nav_host_fragment)

       toolbarBinding = mDashBinding.toolbarLayout
       setSupportActionBar(toolbarBinding.toolbar)
       contentDashBoardBinding = mDashBinding.contentLayout

       hideProgress()
       drawerLayout = mDashBinding.drawerLayout
       navView = mDashBinding.navView

       toggle = object : ActionBarDrawerToggle(
            this, drawerLayout, toolbarBinding.toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        ){}
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
        setupNavigationDrawer()
        navView.setNavigationItemSelectedListener(this)


    }

    private fun setupNavigationDrawer() {
       /* appBarConfiguration  = AppBarConfiguration(navController.graph)
        setupActionBarWithNavController(navController, appBarConfiguration)*/
        val appBarConfiguration = AppBarConfiguration(navController.graph, drawerLayout)
        NavigationUI.setupWithNavController(toolbarBinding.toolbar, navController, appBarConfiguration)
        NavigationUI.setupActionBarWithNavController(this, navController, drawerLayout)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_gallery -> {
                navController.navigate(R.id.action_nav_home_to_nav_gallery)
                /*drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)*/
            }
            R.id.nav_send -> {
                navController.navigate(R.id.action_nav_home_to_nav_send)
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)

        return true
    }

    override fun onSupportNavigateUp(): Boolean {

        return Navigation.findNavController(this,
            R.id.nav_host_fragment
        ).navigateUp() || super.onSupportNavigateUp()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        }else {
            super.onBackPressed()
        }
    }


}













