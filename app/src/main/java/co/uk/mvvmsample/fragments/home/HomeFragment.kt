package co.uk.mvvmsample.fragments.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.databinding.ObservableList
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import co.uk.mvvmsample.base.BaseAtivity
import co.uk.mvvmsample.base.BaseFragment
import co.uk.mvvmsample.dashboard.DashBoardActivity
import co.uk.mvvmsample.databinding.FragmentHomeBinding
import co.uk.mvvmsample.dashboard.DashBoardViewModel
import co.uk.mvvmsample.utills.Util
import kotlinx.android.synthetic.main.fragment_home.*
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import co.uk.mvvmsample.R
import co.uk.mvvmsample.fragments.send.SendFragment
import co.uk.mvvmsample.model.HomeData
import kotlinx.android.synthetic.main.activity_dash_board.*
import kotlinx.android.synthetic.main.activity_social_login.*


class HomeFragment : BaseFragment() {

    lateinit var homeBinding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    lateinit var navController: NavController
    var list = ArrayList<HomeData>()
    lateinit var homeAdapter: HomeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,savedInstanceState: Bundle? ): View? {

        viewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        homeBinding = FragmentHomeBinding.inflate(inflater,container,false).apply {
           homeViewModel = viewModel
        }
       viewModel.text.observe(viewLifecycleOwner, Observer {
            homeBinding.textHome.text = it
        })

        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(activity!!,
            R.id.nav_host_fragment
        )
        activity!!.drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        home_items_list.layoutManager = LinearLayoutManager(activity)
        home_items_list.setHasFixedSize(true)

        homeAdapter = HomeAdapter()
        home_items_list.setAdapter(homeAdapter)
        getData()

        viewModel.click?.observe(this, Observer {
            if(it){
                viewModel.click?.value = false
                navController.navigate(R.id.action_nav_home_to_detailfragment)
                activity!!.drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
            }
        })

        viewModel.progressBar.observe(this, Observer {
            if(it){
                homeBinding.progress?.visibility = View.GONE
            }
        })

    }

    private fun getData() {
        viewModel.getAllEmployee()?.observe(this,
            Observer<List<Any>> {
                    employees -> homeAdapter.setAppList(employees as ArrayList<HomeData>)
                homeBinding.progress?.visibility = View.GONE

            })
             }

}