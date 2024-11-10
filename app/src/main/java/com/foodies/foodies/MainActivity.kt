package com.foodies.foodies

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.foodies.core.di.app.App
import com.foodies.foodies.di.DaggerApplicationComponent
import com.foodies.foodies.di.MainActivityComponent
import com.foodies.foodies.ui.theme.FoodiesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inject((application as App))
        setContent {

        }
    }
    private fun inject(app: App) {
        MainActivityComponent.init(app.getApplicationProvider())
            .inject(this) // Здесь происходит инъекция
    }
}

