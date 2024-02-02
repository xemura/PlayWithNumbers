package com.xenia.playwithnumbers.presentation

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.xenia.playwithnumbers.R
import com.xenia.playwithnumbers.domain.entity.GameResult

@BindingAdapter("requiredAnswers")
fun bindRequiresAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_score),
        count
    )
}

@BindingAdapter("scoreAnswers")
fun bindScoreAnswers(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.score_answers),
        count
    )
}

@BindingAdapter("requiredPercentage")
fun bindRequiredPercentage(textView: TextView, count: Int) {
    textView.text = String.format(
        textView.context.getString(R.string.required_percentage),
        count
    )
}

@BindingAdapter("scorePercentage")
fun bindScorePercentage(textView: TextView, gameResult: GameResult) {

    val rightAnswersPercent = if (gameResult.countOfQuestions == 0) {
        0
    } else {
        ((gameResult.countOfRightAnswers / gameResult.countOfQuestions.toDouble()) * 100).toInt()
    }

    textView.text =  String.format(
        textView.context.getString(R.string.score_percentage),
        rightAnswersPercent.toString()
    )
}

@BindingAdapter("emojiResult")
fun bindEmojiResult(imageView: ImageView, gameResult: GameResult) {

    val resId = if (gameResult.winner) {
        R.drawable.ic_smile
    } else R.drawable.ic_sad

    imageView.setImageResource(resId)
}