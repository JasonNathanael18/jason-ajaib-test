package com.astrapay.jason_ajaib_test.ui.detail.component

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.astrapay.jason_ajaib_test.R
import com.astrapay.jason_ajaib_test.databinding.ReposInfoitemBinding
import com.astrapay.jason_ajaib_test.ui.detail.viewdata.RepositoriesViewData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class RepositoriesAdapter @Inject constructor(@ActivityContext private val context: Context) :
    RecyclerView.Adapter<RepositoriesAdapter.ReposViewHolder>() {

    inner class ReposViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ReposInfoitemBinding.bind(view)
    }

    var datas = ArrayList<RepositoriesViewData>()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RepositoriesAdapter.ReposViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.repos_infoitem,
            parent, false
        )
        return ReposViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: RepositoriesAdapter.ReposViewHolder, position: Int) {
        val data = datas[position]
        val glideOpt = RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background)
        Glide.with(context)
            .load(data.thumbnail)
            .apply(glideOpt)
            .thumbnail(0.1f)
            .transform(CircleCrop())
            .into(holder.binding.ivThumbnail)

        holder.binding.labelRepoName.text = data.reposName
        holder.binding.labelRepoDetail.text = data.reposDetail
        holder.binding.labelStarAmount.text = data.stars.toString()
        holder.binding.labelLastUpdated.text = data.updatedAt
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    fun reloadData(paramDatas: List<RepositoriesViewData>) {
        datas.clear()
        datas.addAll(paramDatas)
        notifyDataSetChanged()
    }

    fun addData(newData: List<RepositoriesViewData>) {
        val previousCount = datas.size
        datas.addAll(newData)
        notifyItemRangeInserted(previousCount, datas.size)
    }

    fun clearData() {
        datas.clear()
        notifyDataSetChanged()
    }

}