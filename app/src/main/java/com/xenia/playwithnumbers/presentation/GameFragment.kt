package com.xenia.playwithnumbers.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xenia.playwithnumbers.R
import com.xenia.playwithnumbers.databinding.FragmentGameBinding
import com.xenia.playwithnumbers.domain.entity.GameResult
import com.xenia.playwithnumbers.domain.entity.GameSettings
import com.xenia.playwithnumbers.domain.entity.Level


class GameFragment : Fragment() {

    private lateinit var level: Level

    private var _binding: FragmentGameBinding? = null
    private val binding: FragmentGameBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameSettings = GameSettings (
            50,
            3,
            80,
            90
        )

        val gameResult = GameResult(
            true,
            5,
            10,
            gameSettings
        )
        binding.tvOption1.setOnClickListener {
            launchGameFinishedFragment(gameResult)
        }
    }

    private fun launchGameFinishedFragment(gameResult: GameResult) {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, GameFinishedFragment.newInstance(gameResult))
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun parseArgs() {
        requireArguments().getParcelable<Level>(KEY_LEVEL)?.let {
            level = it
        }
        Log.d("GameFragment", level.toString())
    }

    companion object {

        private const val KEY_LEVEL = "level"
        const val NAME = "GameFragment"
        fun newInstance(level: Level) : GameFragment {
            return GameFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(KEY_LEVEL, level)
                }
            }
        }
    }
}