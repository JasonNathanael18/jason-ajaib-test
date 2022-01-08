package com.astrapay.jason_ajaib_test.ui.search.component

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.astrapay.jason_ajaib_test.R
import com.astrapay.jason_ajaib_test.databinding.SearchCompInfoitemBinding
import com.astrapay.jason_ajaib_test.ui.search.viewdata.SearchViewData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.qualifiers.ActivityContext
import javax.inject.Inject

class SearchAdapter @Inject constructor(@ActivityContext private val context: Context) :
    RecyclerView.Adapter<SearchAdapter.SearchViewHolder>() {

    interface OnItemClickListener {
        fun onItemClick(view: View, item: SearchViewData)
    }

    inner class SearchViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = SearchCompInfoitemBinding.bind(view)
    }

    private var onItemClickListener: OnItemClickListener? = null
    var datas = ArrayList<SearchViewData>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SearchViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(
            R.layout.search_comp_infoitem,
            parent, false
        )
        return SearchViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: SearchViewHolder, position: Int) {
        val data = datas[position]
        val glideOpt = RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background)
        Glide.with(context)
            .load(data.thumbnail)
            .apply(glideOpt)
            .thumbnail(0.1f)
            .transform(CircleCrop())
            .into(holder.binding.ivThumbnail)

        holder.binding.labelUserId.text = "@${data.userId}"
        holder.binding.labelUserName.text = data.name
        holder.binding.labelBio.text = data.bio
        holder.binding.labelLocation.text = data.location
        holder.binding.labelEmail.text = data.email

        holder.binding.itemUser.setOnClickListener {
            onItemClickListener?.onItemClick(it, datas[position])
        }
    }

    override fun getItemCount(): Int {
        return datas.size
    }

    fun reloadData(paramDatas: List<SearchViewData>) {
        datas.clear()
        datas.addAll(paramDatas)
        notifyDataSetChanged()
    }

    fun addData(newData: List<SearchViewData>) {
        val previousCount = datas.size
        datas.addAll(newData)
        notifyItemRangeInserted(previousCount, datas.size)
    }

    fun clearData() {
        datas.clear()
        notifyDataSetChanged()
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }
}