package com.zipoapps.android.features

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import by.kirich1409.viewbindingdelegate.viewBinding
import com.zipoapps.android.R
import com.zipoapps.android.common.base.BaseActivity
import com.zipoapps.android.databinding.ActivityMainBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity(R.layout.activity_main) {

    private val viewModel: MainVM by viewModel()
    private val binding: ActivityMainBinding by viewBinding(ActivityMainBinding::bind)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initViews()
    }

    private fun initViews() {
        findNavController(R.id.mainNavHostFragment).apply {
            navController = this
            NavigationUI.setupWithNavController(binding.mainBottomNavigationView, this)
        }
    }
}