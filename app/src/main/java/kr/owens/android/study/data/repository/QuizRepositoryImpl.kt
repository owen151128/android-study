package kr.owens.android.study.data.repository

import kr.owens.android.study.data.local.QuizDataSourceImpl

class QuizRepositoryImpl : QuizRepository {
    private val quizDataSource = QuizDataSourceImpl()

    override fun getQuiz(digit: Int) = quizDataSource.getQuiz(digit)
}
