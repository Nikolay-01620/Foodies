package com.foodies.foodies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import com.foodies.core.di.app.App
import com.foodies.foodies.di.MainActivityComponent
import com.foodies.foodies.navigation.NavHost
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((application as App))
        setContent {
            NavHost(
                viewModelProvider = viewModelProvider
            )
        }
    }
    private fun inject(app: App) {
        MainActivityComponent.init(app.getApplicationProvider(), application)
            .inject(this)
    }
}

