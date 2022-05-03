package kr.owens.android.study.ui.main

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kr.owens.android.study.databinding.ActivityMainBinding
import kr.owens.android.study.ui.game.GameActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also {
            setContentView(it.root)
        }

        initListener()
    }

    private fun initListener() {
        binding.mainStartButton.setOnClickListener {
            startActivity(Intent(applicationContext, GameActivity::class.java))
        }
    }
}
