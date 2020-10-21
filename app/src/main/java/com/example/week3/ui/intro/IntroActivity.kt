package com.example.week3.ui.intro

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager
import com.example.week3.R
import com.example.week3.adapter.FragmentPagerAdapter
import com.example.week3.ui.auth.AuthActivity
import com.example.week3.ui.intro.fragment.intro1_fragment
import com.example.week3.ui.intro.fragment.intro2_fragment
import com.example.week3.ui.intro.fragment.intro3_fragment
import com.example.week3.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_intro.*

class IntroActivity : AppCompatActivity() {
    private var currentPage = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        addTabLayout()
        addEvent()
    }

    private fun addEvent() {
        btn_skip_intro.setOnClickListener {
            val intent = Intent(this, AuthActivity::class.java)
            startActivity(intent)
            finish()
        }
        btn_next.setOnClickListener {
            currentPage = view_pager_intro.currentItem
            currentPage++
            if (currentPage < 3)
                view_pager_intro.currentItem = currentPage
            else {
                val intent = Intent(this, AuthActivity::class.java)
                startActivity(intent)
                finish()
            }
        }

    }

    private fun addTabLayout() {
        val adapter = FragmentPagerAdapter(this.supportFragmentManager)
        adapter.addFragment(intro1_fragment(), "")
        adapter.addFragment(intro2_fragment(), "")
        adapter.addFragment(intro3_fragment(), "")
        view_pager_intro.adapter = adapter
        view_pager_intro.addOnPageChangeListener(ViewPagerListener(this::onPageSelected))
    }


    private fun onPageSelected(position: Int) {
        when (position) {
            0 -> {
                firstDotImageView.setImageResource(R.drawable.ic_dot_selected)
                secondDotImageView.setImageResource(R.drawable.ic_dot)
                thirdDotImageView.setImageResource(R.drawable.ic_dot)
            }
            1 -> {
                firstDotImageView.setImageResource(R.drawable.ic_dot)
                secondDotImageView.setImageResource(R.drawable.ic_dot_selected)
                thirdDotImageView.setImageResource(R.drawable.ic_dot)
            }
            2 -> {
                firstDotImageView.setImageResource(R.drawable.ic_dot)
                secondDotImageView.setImageResource(R.drawable.ic_dot)
                thirdDotImageView.setImageResource(R.drawable.ic_dot_selected)
            }
        }
    }
    class ViewPagerListener(private val closure: (Int) -> Unit) : ViewPager.OnPageChangeListener {
        override fun onPageScrollStateChanged(p0: Int) {
        }

        override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
        }

        override fun onPageSelected(position: Int) = closure(position)
    }
}