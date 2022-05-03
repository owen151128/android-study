package kr.owens.android.study.ui.game

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import kr.owens.android.study.data.repository.QuizRepository

class GameViewModelFactory(
    private val application: Application,
    private val quizRepository: QuizRepository
) :
    ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(GameViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            GameViewModel(application, quizRepository) as T
        } else {
            throw IllegalArgumentException("Unknown ViewModel.")
        }
    }
}
