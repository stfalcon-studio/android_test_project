package com.zipoapps.android.features.history

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.zipoapps.android.R
import com.zipoapps.android.common.base.BaseFragment
import com.zipoapps.android.common.extensions.IN_DEVELOPMENT
import com.zipoapps.android.databinding.FragmentHistoryBinding
import com.zipoapps.android.databinding.FragmentTrackBinding
import com.zipoapps.android.features.MainVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
class HistoryFragment : BaseFragment(R.layout.fragment_history) {

    private val binding: FragmentHistoryBinding by viewBinding(FragmentHistoryBinding::bind)
    private val viewModel: HistoryVM by viewModel()
    private val sharedViewModel: MainVM by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        observeLoading(viewModel.isLoading)
        observeErrorMessage(binding.errorItem, viewModel.errorMessage)
        observeConnectionError(viewModel.connectionError, binding.root) {
            viewModel.retry()
        }
        observeData()
    }

    private fun initViews() {
        loadingContainer = binding.loadingContainer.root
    }

    private fun observeData() {
        binding.historyEmptyAddMeasurementBtn.setOnClickListener {
            requireContext().IN_DEVELOPMENT()
        }
    }
}