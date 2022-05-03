package kr.owens.android.study.ui.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kr.owens.android.study.databinding.ActivityGameBinding

class GameActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGameBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(binding.container.id, GameFragment.newInstance()).addToBackStack(null)
                .commit()
        }
    }

    fun replaceFragment(fragment: Fragment) =
        supportFragmentManager.beginTransaction().replace(binding.container.id, fragment).commit()

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount == 1) {
            finish()
        }

        super.onBackPressed()
    }
}
