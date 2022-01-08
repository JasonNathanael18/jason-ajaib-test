package com.astrapay.jason_ajaib_test.ui.detail

import androidx.navigation.fragment.navArgs
import com.astrapay.jason_ajaib_test.MainFragment
import com.astrapay.jason_ajaib_test.R
import com.astrapay.jason_ajaib_test.databinding.DetailFragmentBinding
import com.astrapay.jason_ajaib_test.helper.NumberFormatter.getFormatedNumber
import com.astrapay.jason_ajaib_test.helper.data.DataConvert
import com.astrapay.jason_ajaib_test.ui.search.viewdata.SearchViewData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : MainFragment(R.layout.detail_fragment) {

    @Inject
    lateinit var dataConvert: DataConvert

    private lateinit var binding: DetailFragmentBinding
    private val args: DetailFragmentArgs by navArgs()
    private lateinit var userData: SearchViewData

    override fun initComponent() {
        super.initComponent()
        binding = DetailFragmentBinding.bind(requireView())
        userData = dataConvert.toData(args.userData)!!
        initialViewData()
    }

    private fun initialViewData() {
        val glideOpt = RequestOptions().diskCacheStrategy(DiskCacheStrategy.RESOURCE).fitCenter()
            .placeholder(R.drawable.ic_launcher_foreground).error(R.drawable.ic_launcher_background)
        Glide.with(this)
            .load(userData.thumbnail)
            .apply(glideOpt)
            .thumbnail(0.1f)
            .transform(CircleCrop())
            .into(binding.ivThumbnail)

        binding.labelUserName.text = userData.name
        binding.labelUserId.text = "@${userData.userId}"
        binding.labelBio.text = userData.bio
        val userFollower = if (userData.followers >= 1000) getFormatedNumber(userData.followers.toLong()) else userData.followers.toString()
        binding.labelFollower.text = userFollower
        val userFollowing = if (userData.following >= 1000) getFormatedNumber(userData.following.toLong()) else userData.following.toString()
        binding.labelFollowing.text = userFollowing
        binding.labelLocation.text = userData.location
        binding.labelEmail.text = userData.email
    }
}