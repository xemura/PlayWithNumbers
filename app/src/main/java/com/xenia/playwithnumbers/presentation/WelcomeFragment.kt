package com.xenia.playwithnumbers.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.xenia.playwithnumbers.R
import com.xenia.playwithnumbers.databinding.FragmentWelcomeBinding


class WelcomeFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private var _binding: FragmentWelcomeBinding? = null
    private val binding: FragmentWelcomeBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWelcomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonUnderstand.setOnClickListener {
            launchChooseLevelFragment()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun launchChooseLevelFragment() {
        requireActivity().supportFragmentManager.beginTransaction()
            .replace(R.id.main_container, ChooseLevelFragment.newInstance())
            .addToBackStack(ChooseLevelFragment.NAME)
            .commit()
    }
}