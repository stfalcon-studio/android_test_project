package com.zipoapps.android.features.posts

import android.os.Bundle
import androidx.navigation.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.zipoapps.android.R
import com.zipoapps.android.common.base.BaseActivity
import com.zipoapps.android.common.extensions.loadImage
import com.zipoapps.android.common.models.UserModel
import com.zipoapps.android.databinding.ActivityPostsBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
class PostsActivity : BaseActivity(R.layout.activity_posts) {

    private val args: PostsActivityArgs by navArgs()
    private val binding: ActivityPostsBinding by viewBinding(ActivityPostsBinding::bind)
    private val viewModel: PostVM by viewModel()
    private lateinit var postAdapter: PostAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        extractArgs()
        initAdapter()
        initViews()

        observeLoading(viewModel.isLoading)
        observeErrorMessage(binding.postErrorItem, viewModel.errorMessage)
        observeConnectionError(viewModel.connectionError, binding.root) {
            viewModel.retry()
        }
        observeData()
    }

    private fun extractArgs() {
        viewModel.setupData(user = UserModel.mapToDomain(args.user))
    }

    private fun initAdapter() {
        PostAdapter().apply {
            postAdapter = this
            setHasStableIds(true)
        }
    }

    private fun initViews() {
        loadingContainer = binding.loadingContainer.root

        binding.postBackBtn.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        binding.postRv.apply {
            adapter = postAdapter
            this.layoutManager = layoutManager
        }
    }

    private fun observeData() {
        viewModel.user.observe(this) { user ->
            binding.postUserNameTv.text = user.name
            binding.postImg.loadImage(user.thumbnailUrl)
        }
        viewModel.postList.observe(this) {
            postAdapter.items = it
        }
    }
}