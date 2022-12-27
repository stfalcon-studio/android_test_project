package com.zipoapps.android.features.info

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.zipoapps.android.R
import com.zipoapps.android.common.base.BaseFragment
import com.zipoapps.android.common.extensions.IN_DEVELOPMENT
import com.zipoapps.android.common.models.UserModel
import com.zipoapps.android.databinding.FragmentInfoBinding
import com.zipoapps.android.features.MainVM
import com.zipoapps.domain.models.User
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
class InfoFragment : BaseFragment(R.layout.fragment_info) {

    private val binding: FragmentInfoBinding by viewBinding(FragmentInfoBinding::bind)
    private val viewModel: InfoVM by viewModel()
    private val sharedViewModel: MainVM by sharedViewModel()

    private lateinit var usersAdapter: InfoUsersAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initAdapter()
        initViews()

        observeLoading(viewModel.isLoading)
        observeErrorMessage(binding.errorItem, viewModel.errorMessage)
        observeConnectionError(viewModel.connectionError, binding.root) {
            viewModel.retry()
        }
        observeData()
    }

    private fun initAdapter() {
        InfoUsersAdapter().apply {
            usersAdapter = this
            onUserClicked = { user ->
                onUserClicked(user)
            }
            setHasStableIds(true)
        }
    }

    private fun initViews() {
        loadingContainer = binding.loadingContainer.root

        val layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

        binding.infoRv.apply {
            adapter = usersAdapter
            this.layoutManager = layoutManager
        }
    }

    private fun observeData() {
        viewModel.userList.observe(viewLifecycleOwner) {
            usersAdapter.items = it
        }
    }

    private fun onUserClicked(user: User) {
        findNavController().navigate(InfoFragmentDirections.actionNavInfoToPostsFragment(user = UserModel.mapTo(user)))
    }
}