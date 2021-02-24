package com.rahulabrol.pocket52test.ui.post

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.VisibleForTesting
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rahulabrol.pocket52test.HomeViewModel
import com.rahulabrol.pocket52test.R
import com.rahulabrol.pocket52test.base.DataBindingFragment
import com.rahulabrol.pocket52test.databinding.FragmentUserPostsBinding
import com.rahulabrol.pocket52test.model.Post
import com.rahulabrol.pocket52test.ui.adapter.PostAdapter
import com.rahulabrol.pocket52test.ui.postdetail.UserPostDetailFragment
import dagger.hilt.android.AndroidEntryPoint

/**
 * Created by Rahul Abrol on 24/2/21.
 */
@AndroidEntryPoint
class UserPostsFragment : DataBindingFragment() {

    @VisibleForTesting
    val homeViewModel: HomeViewModel by viewModels()
    lateinit var binding: FragmentUserPostsBinding

    private val postAdapter by lazy(LazyThreadSafetyMode.PUBLICATION) {
        PostAdapter { post ->
            val bundle = UserPostDetailFragment.getDataBundle(post)
            findNavController().navigate(
                R.id.action_userPostsFragment_to_userPostDetailFragment, bundle
            )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        retainInstance = true
        homeViewModel.getPostList()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = binding<FragmentUserPostsBinding>(
            inflater,
            R.layout.fragment_user_posts,
            container
        ).apply {
            lifecycleOwner = this@UserPostsFragment
            adapter = postAdapter
            viewModel = homeViewModel
            edSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    s?.let { filter(it.toString()) }
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }
        return binding.root
    }

    private fun filter(text: String) {
        val temp = arrayListOf<Post>()
        for (user in homeViewModel.postsLiveData.value!!) {
            //or use .equal(text) with you want equal match
            //use .toLowerCase() for better matches
            if (user.id?.contains(text) == true) {
                temp.add(user)
            }
        }
        //update recyclerview
        postAdapter.addPostList(temp)
    }

    override fun observeViewModel() {
        super.observeViewModel()
        homeViewModel.apply {
            postsLiveData.observe(viewLifecycleOwner, {
                if (binding.rvPost.adapter == null) {
                    binding.rvPost.layoutManager = LinearLayoutManager(requireContext())
                    binding.rvPost.adapter = postAdapter
                }
                //update list
                postAdapter.addPostList(it)
            })
        }
    }
}