package com.app.gameexplorer.presentation.games_list


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.gameexplorer.domain.model.Game
import com.app.gameexplorer.domain.use_case.GetGamesUseCase
import com.app.gameexplorer.domain.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GamesViewModel @Inject constructor(
    private val getGamesUseCase: GetGamesUseCase
) : ViewModel() {

    private var currentPage = 1
    private var isEndReached = false

    private val allLoadedGames = mutableListOf<Game>()

    private val _uiState = MutableStateFlow<GamesUiState>(GamesUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadGames()
    }

    fun loadGames(isNextPage: Boolean = false) {
        if (isEndReached || (isNextPage && _uiState.value is GamesUiState.Loading)) return

        viewModelScope.launch {
            if (isNextPage) {
                val currentList = (uiState.value as? GamesUiState.Success)?.games ?: emptyList()
                _uiState.value = GamesUiState.Success(currentList, isPaginationLoading = true)
            } else {
                _uiState.value = GamesUiState.Loading
            }

            when (val result = getGamesUseCase(currentPage, "action")) {
                is Resource.Success -> {
                    val newGames = result.data ?: emptyList()

                    if (newGames.isEmpty()) {
                        isEndReached = true
                        if (allLoadedGames.isEmpty()) {
                            _uiState.value = GamesUiState.Empty
                        } else {
                            _uiState.value = GamesUiState.Success(allLoadedGames.toList())
                        }
                    } else {
                        allLoadedGames.addAll(newGames)
                        _uiState.value = GamesUiState.Success(allLoadedGames.toList())
                        currentPage++
                    }
                }

                is Resource.Error -> {
                    _uiState.value =
                        GamesUiState.Error(result.message ?: "An unexpected error occurred")
                }

                is Resource.Loading -> {
                }
            }
        }
    }

    fun onSearchQueryChanged(query: String) {
        if (query.isEmpty()) {
            _uiState.value = GamesUiState.Success(allLoadedGames.toList())
        } else {
            val filteredList = allLoadedGames.filter {
                it.name.contains(query, ignoreCase = true)
            }
            if (filteredList.isEmpty()) {
                _uiState.value = GamesUiState.Empty
            } else {
                _uiState.value = GamesUiState.Success(filteredList)
            }
        }
    }
}