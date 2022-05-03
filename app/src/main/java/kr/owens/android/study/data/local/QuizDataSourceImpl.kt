package kr.owens.android.study.data.local

import kr.owens.android.study.data.model.Quiz
import kotlin.random.Random
import kotlin.random.nextInt

class QuizDataSourceImpl : QuizDataSource {
    override fun getQuiz(digit: Int) = runCatching {
        var calculateMax = 1
        repeat(digit) { calculateMax *= 10 }

        val min = calculateMax / 10
        val max = calculateMax - 1

        val lhs = Random.nextInt(min..max)
        val rhs = Random.nextInt(min..max)
        val answer = lhs + rhs

        Quiz("$lhs + $rhs", answer)
    }
}
