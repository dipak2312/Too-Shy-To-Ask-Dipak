 package com.example.tooshytoask.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager

import com.example.tooshytoask.activity.story.StoryDisplayFragment
import com.example.tooshytoask.Models.StoryDetails


class StoryPagerAdapter constructor(fragmentManager: FragmentManager, private val storyList: ArrayList<StoryDetails>)
    : FragmentStatePagerAdapter(fragmentManager, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {



    override fun getCount(): Int {
        return storyList.size
    }

    override fun getItem(position: Int): Fragment {
        return StoryDisplayFragment(storyList)
    }

    fun findFragmentByPosition(viewPager: ViewPager, position: Int): Fragment? {
        try {
            val f = instantiateItem(viewPager, position)
            return f as? Fragment
        } finally {
            finishUpdate(viewPager)
        }
    }
}