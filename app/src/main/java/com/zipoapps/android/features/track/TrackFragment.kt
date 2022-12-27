package com.zipoapps.android.features.track

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.zipoapps.android.R
import com.zipoapps.android.common.base.BaseFragment
import com.zipoapps.android.common.extensions.IN_DEVELOPMENT
import com.zipoapps.android.databinding.FragmentTrackBinding
import com.zipoapps.android.features.MainVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
class TrackFragment : BaseFragment(R.layout.fragment_track) {

    private val binding: FragmentTrackBinding by viewBinding(FragmentTrackBinding::bind)
    private val viewModel: TrackVM by viewModel()
    private val sharedViewModel: MainVM by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()

        observeLoading(viewModel.isLoading)
        observeErrorMessage(binding.errorItem, viewModel.errorMessage)
        observeConnectionError(viewModel.connectionError, binding.root) {
            viewModel.retry()
        }
    }

    private fun initViews() {
        loadingContainer = binding.loadingContainer.root

        binding.trackEmptyAddMeasurementBtn.setOnClickListener {
            requireContext().IN_DEVELOPMENT()
        }
    }
}