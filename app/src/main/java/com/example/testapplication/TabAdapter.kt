package com.example.testapplication

import android.content.Context
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class TabAdapter (private val myContext: Context, fm: FragmentManager, private var totalTabs: Int): FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return TabOneFragment()
            }
            1 -> {
                return TabTwoFragment(myContext)
            }
            else -> {
                return TabThreeFragment()
            }
        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}