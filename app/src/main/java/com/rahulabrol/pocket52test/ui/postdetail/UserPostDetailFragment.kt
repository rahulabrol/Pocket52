package com.rahulabrol.pocket52test.ui.postdetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahulabrol.pocket52test.HomeViewModel
import com.rahulabrol.pocket52test.R
import com.rahulabrol.pocket52test.base.DataBindingFragment
import com.rahulabrol.pocket52test.databinding.FragmentUserPostDetailBinding
import com.rahulabrol.pocket52test.model.OriginalPost
import com.rahulabrol.pocket52test.model.Post
import com.rahulabrol.pocket52test.ui.adapter.PostDetailAdapter
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Rahul Abrol on 24/2/21.
 */
@AndroidEntryPoint
class UserPostDetailFragment : DataBindingFragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    lateinit var binding: FragmentUserPostDetailBinding
    private var userId: String? = null
    private var postList: ArrayList<OriginalPost>? = null

    private val postDetailAdapter by lazy(LazyThreadSafetyMode.PUBLICATION) {
        PostDetailAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        val post = arguments?.getParcelable<Post>(POST)
        userId = post?.id
        postList = post?.postList
        homeViewModel.getUserDetail(userId)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = binding<FragmentUserPostDetailBinding>(
            inflater,
            R.layout.fragment_user_post_detail,
            container
        ).apply {
            lifecycleOwner = this@UserPostDetailFragment
            adapter = postDetailAdapter
            viewModel = homeViewModel
        }
        return binding.root
    }

    override fun observeViewModel() {
        super.observeViewModel()
        homeViewModel.apply {
            postDetailLiveData.observe(this@UserPostDetailFragment, Observer {
                it?.let {
                    if (binding.rvPostDetail.adapter == null) {
                        binding.rvPostDetail.layoutManager = LinearLayoutManager(requireContext())
                        binding.rvPostDetail.adapter = postDetailAdapter
                    }
                    //update list
                    postDetailAdapter.addPostList(postList)

                    binding.name = it.name
                    binding.email = it.email
                }
            })
        }
    }

    companion object {
        private const val POST = "post"
        fun getDataBundle(post: Post) = Bundle().apply {
            putParcelable(POST, post)
        }
    }
}