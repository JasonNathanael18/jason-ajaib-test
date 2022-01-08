package com.astrapay.jason_ajaib_test

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.astrapay.jason_ajaib_test.helper.DefaultConstants

open class MainFragment(layoutId: Int) : Fragment(layoutId) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initComponent()
        initEventListener()
        initObserver()
    }

    protected fun navigate(id: NavDirections) {
        findNavController().navigate(id)
    }

    protected open fun initComponent(){
        Log.d(DefaultConstants.logTag, "init component")
    }

    protected open fun initEventListener(){
        Log.d(DefaultConstants.logTag, "init eventlistener")
    }

    protected open fun initObserver(){
        Log.d(DefaultConstants.logTag, "init observer")
    }
}