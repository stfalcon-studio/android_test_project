package com.zipoapps.android.common.di

import com.zipoapps.android.features.MainVM
import com.zipoapps.android.features.history.HistoryVM
import com.zipoapps.android.features.info.InfoVM
import com.zipoapps.android.features.posts.PostVM
import com.zipoapps.android.features.settings.SettingsVM
import com.zipoapps.android.features.track.TrackVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelsModule = module {
    viewModel<MainVM>()
    viewModel<PostVM>()
    viewModel<HistoryVM>()
    viewModel<TrackVM>()
    viewModel<SettingsVM>()
    viewModel<InfoVM>()
}

val appModule = arrayOf(
    viewModelsModule
)
