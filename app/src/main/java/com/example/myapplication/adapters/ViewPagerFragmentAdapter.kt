package com.example.myapplication.adapters

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.myapplication.fragments.BlankFragment
import com.example.myapplication.fragments.EditFragment
import com.example.myapplication.fragments.ImageAddFragment
import com.example.myapplication.fragments.ResetPasswordFragment

class ViewPagerFragmentAdapter(activity: BlankFragment) : FragmentStateAdapter(activity) {
    override fun getItemCount() = 2

    override fun createFragment(position: Int): Fragment {
        if(position == 0){
            return ImageAddFragment()
        }else{
            return EditFragment()
        }
    }
}