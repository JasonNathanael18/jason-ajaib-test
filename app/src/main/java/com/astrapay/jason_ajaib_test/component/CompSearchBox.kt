package com.astrapay.jason_ajaib_test.component

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import com.astrapay.jason_ajaib_test.databinding.CompSearchBoxBinding

class CompSearchBox(context: Context, attrs: AttributeSet) : FrameLayout(context, attrs) {

    private var binding: CompSearchBoxBinding

    init {
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        binding = CompSearchBoxBinding.inflate(inflater)
        addView(binding.root)
    }
}