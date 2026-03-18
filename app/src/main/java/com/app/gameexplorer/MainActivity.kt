package com.app.gameexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.app.gameexplorer.presentation.navigation.GameExplorerNavGraph
import com.app.gameexplorer.ui.theme.GameExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GameExplorerTheme {
                GameExplorerNavGraph()
            }
        }
    }
}
