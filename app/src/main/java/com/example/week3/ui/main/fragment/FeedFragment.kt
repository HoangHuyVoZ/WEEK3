package com.example.week3.ui.main.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week3.R
import com.example.week3.model.feed.FeedData
import com.example.week3.model.interest.InterestData
import com.example.week3.ui.auth.AuthActivity
import com.example.week3.ui.auth.apdapter.FollowAdapter
import com.example.week3.ui.intro.IntroActivity
import com.example.week3.ui.main.adapter.FeedAdapter
import com.example.week3.utils.Constants
import com.example.week3.utils.View.Companion.invisible
import com.example.week3.utils.View.Companion.visible
import com.example.week3.viewmodel.AuthViewModel
import com.example.week3.viewmodel.FeedViewModel
import kotlinx.android.synthetic.main.fragment_feed.*
import kotlinx.android.synthetic.main.fragment_follow.*
import kotlinx.android.synthetic.main.fragment_follow.recyclerViewFollow


class FeedFragment : Fragment() {
    lateinit var feedViewModel: FeedViewModel
    lateinit var layoutManager: LinearLayoutManager
    lateinit var adapter: FeedAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        feedViewModel = ViewModelProvider(this).get(FeedViewModel::class.java)
        return inflater.inflate(R.layout.fragment_feed, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        init()
        addScrolListener()
        notifyerr_feed.invisible()
        feedViewModel.dataFeed.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                if (it.status) {
                    feedViewModel.dataFeedList.observe(viewLifecycleOwner, Observer {
                        if (it != null) {
                            adapter.addList(it as MutableList<FeedData>)
                            adapter.notifyDataSetChanged()
                        }
                    })
                }

            } else {
                progress_feed.invisible()
                notifyerr_feed.visible()
                notifyerr_feed.setOnClickListener {
                    val intent = Intent(activity, AuthActivity::class.java)
                    activity?.startActivity(intent)
                }
            }
        })
    }


    private fun init() {
        layoutManager = LinearLayoutManager(context)
        recyclerView_feed.setHasFixedSize(true)
        recyclerView_feed.layoutManager = layoutManager
        adapter = FeedAdapter()
        recyclerView_feed.addItemDecoration(
            DividerItemDecoration(
                context,
                LinearLayoutManager.VERTICAL
            )
        )
        recyclerView_feed.adapter = adapter
        val pref = requireContext().getSharedPreferences(
            Constants.PREFS_NAME,
            AppCompatActivity.MODE_PRIVATE
        )
        val accessToken = pref.getString(Constants.ACCESS_TOKEN, "")
        if (accessToken != null) {
//            feedViewModel.current_pageCount.observe(viewLifecycleOwner, Observer {
                feedViewModel.getFeed(
                    1,
                    Constants.AUTHORIZATION + accessToken,
                    10
                )
//            })


        }
    }
    fun addScrolListener() {
        recyclerView_feed.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val pastVisibleItem = layoutManager.findLastVisibleItemPosition()

                val total = adapter.itemCount
                Log.d("a", (pastVisibleItem).toString())
                val pref = requireContext().getSharedPreferences(
                    Constants.PREFS_NAME,
                    AppCompatActivity.MODE_PRIVATE
                )
                val accessToken = pref.getString(Constants.ACCESS_TOKEN, "")
                if ( pastVisibleItem == total - 1) {
                    feedViewModel.current_pageCount.value = ++feedViewModel.CountPage
                    feedViewModel.current_pageCount.observe(viewLifecycleOwner, Observer {
                        feedViewModel.getFeed(
                            it,
                            Constants.AUTHORIZATION + accessToken,
                            10
                        )
                    })
                }
                super.onScrolled(recyclerView   , dx, dy)
            }

        })
    }

}
