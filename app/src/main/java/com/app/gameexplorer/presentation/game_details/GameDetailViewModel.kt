package com.app.gameexplorer.presentation.game_details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.gameexplorer.domain.use_case.GetGameDetailUseCase
import com.app.gameexplorer.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GameDetailViewModel @Inject constructor(
    private val getGameDetailUseCase: GetGameDetailUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _state = mutableStateOf<GameDetailState>(GameDetailState.Loading)
    val state: State<GameDetailState> = _state

    init {
        savedStateHandle.get<Int>("gameId")?.let { id ->
            getGameById(id)
        }
    }

    private fun getGameById(id: Int) {
        viewModelScope.launch {
            _state.value = GameDetailState.Loading

            try {
                val resource = getGameDetailUseCase(id)

                when (resource) {
                    is Resource.Success -> {
                        resource.data?.let { game ->
                            _state.value = GameDetailState.Success(game)
                        } ?: run {
                            _state.value = GameDetailState.Error("No data found")
                        }
                    }
                    is Resource.Error -> {
                        _state.value = GameDetailState.Error(resource.message ?: "An unexpected error occurred")
                    }
                    is Resource.Loading -> {
                        _state.value = GameDetailState.Loading
                    }
                }
            } catch (e: Exception) {
                _state.value = GameDetailState.Error(e.message ?: "Unknown Error")
            }
        }
    }
}

