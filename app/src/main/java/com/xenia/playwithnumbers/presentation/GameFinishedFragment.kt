package com.xenia.playwithnumbers.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.FragmentManager
import com.xenia.playwithnumbers.R
import com.xenia.playwithnumbers.databinding.FragmentGameFinishedBinding
import com.xenia.playwithnumbers.domain.entity.GameResult


class GameFinishedFragment : Fragment() {

    private lateinit var gameResult: GameResult

    private var _binding: FragmentGameFinishedBinding? = null
    private val binding: FragmentGameFinishedBinding
        get() = _binding ?: throw RuntimeException("GameFinishedFragment == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseArgs()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(
                true
            ) {
                override fun handleOnBackPressed() {
                    retryGame()
                }
            })

        binding.buttonRetry.setOnClickListener {
            retryGame()
        }

        binding.emojiResult.setImageResource(getSmileResID())

        binding.tvRequiredAnswers.text = setRequiredAnswers()
        binding.tvScoreAnswers.text = setScoreAnswers()
        binding.tvRequiredPercentage.text = setRequiredPercentage()
        binding.tvScorePercentage.text = setScorePercentage()
    }

    private fun getSmileResID(): Int {
        return if (gameResult.winner) {
            R.drawable.ic_smile
        } else R.drawable.ic_sad
    }

    private fun setScorePercentage(): String {
        val rightAnswersPercent = if (gameResult.countOfQuestions == 0) {
            0
        } else {
            ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
        }

        return String.format(
            context?.resources?.getString(R.string.score_percentage)
                ?: throw RuntimeException("Does not have this resource"),
            rightAnswersPercent.toString()
        )
    }

    private fun setRequiredPercentage(): String {
        return String.format(
            context?.resources?.getString(R.string.required_percentage)
                ?: throw RuntimeException("Does not have this resource"),
            gameResult.gameSettings.minPercentOfRightAnswers
        )
    }

    private fun setScoreAnswers(): String {
        return String.format(
            context?.resources?.getString(R.string.score_answers)
                ?: throw RuntimeException("Does not have this resource"),
            gameResult.countOfRightAnswers
        )
    }

    private fun setRequiredAnswers(): String {

        return String.format(
            context?.resources?.getString(R.string.required_score)
                ?: throw RuntimeException("Does not have this resource"),
            gameResult.gameSettings.minCountOfRightAnswers.toString()
        )
    }

    private fun parseArgs() {
        requireArguments().getParcelable<GameResult>(GAME_RESULT)?.let {
            gameResult = it
        }
        Log.d("GameFinishedFragment", gameResult.toString())
    }

    private fun retryGame() {
        requireActivity().supportFragmentManager.popBackStack(GameFragment.NAME, FragmentManager.POP_BACK_STACK_INCLUSIVE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentGameFinishedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        private const val GAME_RESULT = "result"

        @JvmStatic
        fun newInstance(gameResult: GameResult) : GameFinishedFragment{
            return GameFinishedFragment().apply {
                arguments = Bundle().apply {
                    putParcelable(GAME_RESULT, gameResult)
                }
            }
        }
    }
}