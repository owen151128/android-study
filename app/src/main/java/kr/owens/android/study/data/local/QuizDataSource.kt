package kr.owens.android.study.data.local

import kr.owens.android.study.data.model.Quiz

interface QuizDataSource {
    fun getQuiz(digit: Int = 2): Result<Quiz>
}
