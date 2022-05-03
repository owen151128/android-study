package kr.owens.android.study.data.repository

import kr.owens.android.study.data.model.Quiz

interface QuizRepository {
    fun getQuiz(digit: Int = 2): Result<Quiz>
}
