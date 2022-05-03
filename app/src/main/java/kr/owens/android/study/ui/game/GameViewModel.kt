package kr.owens.android.study.ui.game

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kr.owens.android.study.R
import kr.owens.android.study.data.model.GameStatus
import kr.owens.android.study.data.model.Quiz
import kr.owens.android.study.data.repository.QuizRepository

class GameViewModel(application: Application, private val quizRepository: QuizRepository) :
    AndroidViewModel(application) {
    private lateinit var currentQuiz: Quiz
    private lateinit var currentTimerJob: Job

    private var answerCount = 0
    private var level = 0

    private val timerData = MutableLiveData<String>()
    private val quizData = MutableLiveData<String>()
    private val reasonData = MutableLiveData<String>()
    private val gameStatus = MutableLiveData<GameStatus>()

    fun getTimerData(): LiveData<String> = timerData

    fun getQuizData(): LiveData<String> = quizData

    fun getReasonData(): LiveData<String> = reasonData

    fun getGameStatus(): LiveData<GameStatus> = gameStatus

    fun refreshQuiz() {
        if (answerCount % 10 == 0) {
            level++
        }

        currentQuiz = quizRepository.getQuiz(level).getOrThrow()
        quizData.value = currentQuiz.question
        currentTimerJob = startTimer()
    }

    fun checkAnswer(input: Int) {
        if (!currentTimerJob.isCompleted) {
            currentTimerJob.cancel()
        }

        if (currentQuiz.answer == input) {
            answerCount++
            refreshQuiz()
        } else {
            gameStatus.value = GameStatus.WRONG
            reasonData.value =
                getApplication<Application>().getString(R.string.game_over_reason_wrong_answer_format)
                    .format(currentQuiz.answer, answerCount)
        }
    }

    private fun startTimer() = viewModelScope.launch {
        var timerSecond = 10
        var timerMillSeconds = 0

        repeat(1000) {
            if (timerMillSeconds == 0) {
                timerSecond -= 1
                timerMillSeconds = 99
            } else {
                timerMillSeconds -= 1
            }

            delay(10)

            timerData.value = "%02d:%02d".format(timerSecond, timerMillSeconds)
        }

        gameStatus.value = GameStatus.WRONG
        reasonData.value =
            getApplication<Application>().getString(R.string.game_over_reason_time_out_text)
                .format(answerCount)
    }
}
