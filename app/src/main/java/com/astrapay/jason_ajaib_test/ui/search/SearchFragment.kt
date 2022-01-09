package com.astrapay.jason_ajaib_test.ui.search

import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.astrapay.jason_ajaib_test.MainFragment
import com.astrapay.jason_ajaib_test.R
import com.astrapay.jason_ajaib_test.component.CompRecyclerView
import com.astrapay.jason_ajaib_test.databinding.SearchFragmentBinding
import com.astrapay.jason_ajaib_test.helper.data.DataConvert
import com.astrapay.jason_ajaib_test.helper.data.EventObserver
import com.astrapay.jason_ajaib_test.ui.search.component.SearchAdapter
import com.astrapay.jason_ajaib_test.ui.search.viewdata.SearchViewData
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : MainFragment(R.layout.search_fragment),
    CompRecyclerView.LoadMoreListener,
    SearchAdapter.OnItemClickListener {

    @Inject
    lateinit var adapter: SearchAdapter

    @Inject
    lateinit var dataConvert: DataConvert

    private lateinit var binding: SearchFragmentBinding
    private val viewModel: SearchViewModel by viewModels()

    private var lastQuery = ""
    private var isNewQuery = true

    override fun initComponent() {
        super.initComponent()
        binding = SearchFragmentBinding.bind(requireView())

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.rvSearch.setLayoutManager(layoutManager)
        binding.rvSearch.setAdapter(adapter)
        binding.rvSearch.initialHideList()
    }

    override fun initEventListener() {
        super.initEventListener()

        binding.rvSearch.listener = this
        adapter.setOnItemClickListener(this)

        binding.compSearchBox.onSearchPerformed { query ->
            hideKeyboard()
            binding.rvSearch.showWait()
            lastQuery = query
            isNewQuery = true
            viewModel.requestSearch(query, isNewQuery)
        }
    }

    override fun initObserver() {
        super.initObserver()

        viewModel.liveSearch.observe(viewLifecycleOwner, EventObserver { data ->
            data.content?.let {
                if (isNewQuery) adapter.reloadData(it)
                else adapter.addData(it)
                binding.rvSearch.hideWait()
                binding.rvSearch.showData()
            }
        })

        viewModel.liveErrorEmptyList.observe(viewLifecycleOwner, EventObserver { data ->
                binding.rvSearch.hideWait()
                binding.rvSearch.showEmpty("No Data Found")
        })

        viewModel.liveError.observe(viewLifecycleOwner, EventObserver { data ->
            binding.rvSearch.hideWait()
            Toast.makeText(context, data.message, Toast.LENGTH_SHORT).show()
        })
    }

    override fun onMoreRequest() {
        isNewQuery = false
        viewModel.requestSearch(lastQuery, isNewQuery)
    }

    override fun onItemClick(view: View, item: SearchViewData) {
        val userJsonData = dataConvert.toJson(item) ?: ""
        navigate(
            SearchFragmentDirections.actionSearchFragmentToDetailFragment(userJsonData)
        )
    }

    private fun hideKeyboard() {
        val imm: InputMethodManager =
            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view?.windowToken, 0)
    }
}