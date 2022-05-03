package kr.owens.android.study.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.owens.android.study.R
import kr.owens.android.study.data.model.GameStatus
import kr.owens.android.study.data.repository.QuizRepositoryImpl
import kr.owens.android.study.databinding.FragmentGameBinding

class GameFragment : Fragment() {
    private lateinit var binding: FragmentGameBinding
    private lateinit var gameViewModel: GameViewModel
    private lateinit var fragmentView: View
    private lateinit var gameActivity: GameActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gameActivity = activity as GameActivity
        binding = FragmentGameBinding.inflate(layoutInflater).apply {
            fragmentView = root
            this@GameFragment.gameViewModel = ViewModelProvider(
                requireActivity(),
                GameViewModelFactory(gameActivity.application, QuizRepositoryImpl())
            )[GameViewModel::class.java]
            gameViewModel = this@GameFragment.gameViewModel
            lifecycleOwner = this@GameFragment
        }

        gameViewModel.getGameStatus().observe(viewLifecycleOwner) {
            it?.let {
                if (it == GameStatus.ANSWER) {
                    gameViewModel.refreshQuiz()
                } else {
                    gameActivity.replaceFragment(GameOverFragment.newInstance())
                }
            }
        }

        binding.answerButton.setOnClickListener {
            getAnswerInput().onSuccess {
                gameViewModel.checkAnswer(binding.answerInput.text.toString().toInt())
            }.onFailure {
                Toast.makeText(
                    context,
                    getString(R.string.wrong_input_toast_message),
                    Toast.LENGTH_SHORT
                ).show()
            }

            binding.answerInput.setText("")
        }

        gameViewModel.refreshQuiz()

        return fragmentView
    }

    private fun getAnswerInput() = runCatching { binding.answerInput.text.toString().toInt() }

    companion object {
        @Synchronized
        fun newInstance() = GameFragment()
    }
}
