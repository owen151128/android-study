package kr.owens.android.study.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kr.owens.android.study.data.repository.QuizRepositoryImpl
import kr.owens.android.study.databinding.FragmentGameOverBinding

class GameOverFragment : Fragment() {
    private lateinit var binding: FragmentGameOverBinding
    private lateinit var fragmentView: View
    private lateinit var gameActivity: GameActivity

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        gameActivity = activity as GameActivity
        binding = FragmentGameOverBinding.inflate(layoutInflater).apply {
            fragmentView = root
            gameViewModel = ViewModelProvider(
                requireActivity(),
                GameViewModelFactory(gameActivity.application, QuizRepositoryImpl())
            )[GameViewModel::class.java]
            lifecycleOwner = this@GameOverFragment
        }

        return fragmentView
    }

    companion object {
        @Synchronized
        fun newInstance() = GameOverFragment()
    }
}
