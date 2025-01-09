package com.android.testprojectkotlin.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.forEach
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.android.testprojectkotlin.R
import com.android.testprojectkotlin.databinding.FragmentChildBinding
import com.android.testprojectkotlin.databinding.FragmentTabBinding
import com.android.testprojectkotlin.databinding.ItemTabBinding
import com.google.android.material.tabs.TabLayoutMediator

class TabFragment : Fragment() {

    lateinit var binding: FragmentTabBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTabBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        tab()
    }

    fun tab() {
        val tabs = listOf<String>(
            "Reel",
            "Live",
            "Follow",
        )
        val adapter = PageAdapter(childFragmentManager, lifecycle, listOf(
            FragmentChild(),
            FragmentChild(),
            FragmentChild(),
        ))
        binding.viewPager.adapter= adapter
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val bindTab = ItemTabBinding.inflate(LayoutInflater.from(context), null, false)
            bindTab.text.setText(tabs[position])
            tab.setCustomView(bindTab.root)
        //            tab.text = tabs[position]
        }.attach()


//        val tabStrip = binding.tabLayout.getChildAt(0) as ViewGroup
//        for (i in 0 until tabStrip.childCount) {
//            val tabView = tabStrip.getChildAt(i)
////            tabView.setPadding(0, 0, 0, 0) // Remove padding
//
//            val params = tabView.layoutParams as ViewGroup.MarginLayoutParams
//            params.setMargins(0, 0, 0, 0) // Remove margins
//            tabView.layoutParams = params
//        }
    }
}

class PageAdapter(
    fragmentManager: FragmentManager,
    lifecycle: Lifecycle,
    val items: List<Fragment>
) :
    FragmentStateAdapter(fragmentManager, lifecycle) {
    override fun getItemCount(): Int = items.size

    override fun createFragment(position: Int): Fragment = items[position]

}

class FragmentChild: Fragment() {
    lateinit var binding: FragmentChildBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentChildBinding.inflate(inflater, container, false)
        return binding.root
    }
}