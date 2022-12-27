package com.zipoapps.android.features.settings

import android.os.Bundle
import android.view.View
import by.kirich1409.viewbindingdelegate.viewBinding
import com.zipoapps.android.R
import com.zipoapps.android.common.base.BaseFragment
import com.zipoapps.android.databinding.FragmentSettingsBinding
import com.zipoapps.android.features.MainVM
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * @author andrii.zhumela
 * Created 27.12.2022
 */
class SettingsFragment : BaseFragment(R.layout.fragment_settings) {

    private val binding: FragmentSettingsBinding by viewBinding(FragmentSettingsBinding::bind)
    private val viewModel: SettingsVM by viewModel()
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
    }
}