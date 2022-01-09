package com.astrapay.jason_ajaib_test.ui.detail

import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.astrapay.jason_ajaib_test.MainFragment
import com.astrapay.jason_ajaib_test.R
import com.astrapay.jason_ajaib_test.component.CompRecyclerView
import com.astrapay.jason_ajaib_test.databinding.DetailFragmentBinding
import com.astrapay.jason_ajaib_test.helper.NumberFormatter.getFormatedNumber
import com.astrapay.jason_ajaib_test.helper.data.DataConvert
import com.astrapay.jason_ajaib_test.helper.data.EventObserver
import com.astrapay.jason_ajaib_test.ui.detail.component.RepositoriesAdapter
import com.astrapay.jason_ajaib_test.ui.search.viewdata.SearchViewData
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class DetailFragment : MainFragment(R.layout.detail_fragment),
    CompRecyclerView.LoadMoreListener{

    @Inject
    lateinit var adapter: RepositoriesAdapter

    @Inject
    lateinit var dataConvert: DataConvert

    private lateinit var binding: DetailFragmentBinding
    private val viewModel: DetailViewModel by viewModels()

    private val args: DetailFragmentArgs by navArgs()
    private lateinit var userData: SearchViewData

    override fun initComponent() {
        super.initComponent()
        binding = DetailFragmentBinding.bind(requireView())
        userData = dataConvert.toData(args.userData)!!
        initialViewData()

        val layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvRepo.setLayoutManager(layoutManager)
        binding.rvRepo.setAdapter(adapter)
    }

    override fun initEventListener() {
        super.initEventListener()
        binding.rvRepo.listener = this
    }

    override fun initObserver() {
        super.initObserver()

        viewModel.liveReposList.observe(viewLifecycleOwner, EventObserver { data ->
            data.content?.let {
                adapter.addData(it)
                binding.rvRepo.hideWait()
                binding.rvRepo.showData()
            }
        })

        viewModel.liveError.observe(viewLifecycleOwner, EventObserver { data ->
            binding.rvRepo.hideWait()
            Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun loadData() {
        super.loadData()
        binding.rvRepo.showWait()
        viewModel.requestReposList(userData.userId)
    }

    override fun onMoreRequest() {
        viewModel.requestReposList(userData.userId)
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