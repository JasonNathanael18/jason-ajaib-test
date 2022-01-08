package com.astrapay.jason_ajaib_test.ui.search

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.astrapay.jason_ajaib_test.MainFragment
import com.astrapay.jason_ajaib_test.R
import com.astrapay.jason_ajaib_test.databinding.SearchFragmentBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : MainFragment(R.layout.search_fragment){

    private lateinit var binding: SearchFragmentBinding

    override fun initComponent() {
        super.initComponent()
        binding = SearchFragmentBinding.bind(requireView())
    }

    override fun initEventListener() {
        super.initEventListener()

        binding.compSearchBox.onSearchPerformed {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

            val imm: InputMethodManager =
                activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view?.windowToken, 0)
        }
    }

    override fun initObserver() {
        super.initObserver()
    }
}