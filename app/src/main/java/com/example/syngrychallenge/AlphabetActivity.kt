package com.example.syngrychallenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.syngrychallenge.databinding.ActivityAlphabetBinding

class AlphabetActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAlphabetBinding
    override fun onCreate(savedInstanceState: Bundle?
    ) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlphabetBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val fragmentManager = supportFragmentManager
//        val alphabetFragment = AlphabetFragment()
//        val fragment = fragmentManager.findFragmentByTag(alphabetFragment::class.java.simpleName)
//        if (fragment !is AlphabetFragment) {
//            fragmentManager
//                .beginTransaction()
//                .add(R.id.frame_container, alphabetFragment, alphabetFragment::class.java.simpleName)
//                .commit()
//        }

    }
}