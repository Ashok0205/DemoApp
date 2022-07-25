package co.uk.mvvmsample.fragments.home

import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import co.uk.mvvmsample.databinding.HomeListRowBinding
import co.uk.mvvmsample.model.HomeData
import androidx.databinding.DataBindingUtil
import co.uk.mvvmsample.R



class HomeAdapter : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var mHomeList = ArrayList<HomeData>()
    lateinit var homeListRowBinding: HomeListRowBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int):HomeViewHolder {

        homeListRowBinding  = DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.home_list_row,parent,false)

        return HomeViewHolder(homeListRowBinding)
    }


    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {

        var homeData = mHomeList.get(position)
        holder.homeListRowBinding.homedata = homeData

    }

    override fun getItemCount(): Int {
        Log.e("LIST_SIZE","" + mHomeList.size)
        return mHomeList.size
    }

    fun setAppList(mHomeList: ArrayList<HomeData>) {
        this.mHomeList = mHomeList
        notifyDataSetChanged()
    }

    class HomeViewHolder : RecyclerView.ViewHolder {
        var homeListRowBinding: HomeListRowBinding
        constructor(homeListRowBinding: HomeListRowBinding) : super(homeListRowBinding.root){
           this.homeListRowBinding = homeListRowBinding
        }

    }

}






