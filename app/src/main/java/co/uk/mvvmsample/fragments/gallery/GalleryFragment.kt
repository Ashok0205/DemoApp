package co.uk.mvvmsample.fragments.gallery

import android.os.Bundle
import android.view.*
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavController
import androidx.navigation.Navigation
import co.uk.mvvmsample.R
import co.uk.mvvmsample.base.BaseFragment
import co.uk.mvvmsample.dashboard.DashBoardActivity
import co.uk.mvvmsample.databinding.FragmentGalleryBinding
import kotlinx.android.synthetic.main.activity_dash_board.*


class GalleryFragment : BaseFragment() {

    private lateinit var viewModel: GalleryViewModel
    lateinit var galleryBinding: FragmentGalleryBinding
    lateinit var navController: NavController

    override fun onCreateView(inflater: LayoutInflater,container: ViewGroup?,savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this).get(GalleryViewModel::class.java)
        galleryBinding = FragmentGalleryBinding.inflate(inflater,container,false).apply {
            galleryViewModel = viewModel
        }

        viewModel.text.observe(this, Observer {
            galleryBinding.textGallery.text = it
        })
        return galleryBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navController = Navigation.findNavController(activity!!,
            R.id.nav_host_fragment
        )

        viewModel.click?.observe(this, Observer {
            if(it){
                viewModel.click?.value = false
                navController.navigate(R.id.action_nav_gallery_to_nav_send)
                /*activity!!.drawer_layout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)*/
            }
        })
    }

}