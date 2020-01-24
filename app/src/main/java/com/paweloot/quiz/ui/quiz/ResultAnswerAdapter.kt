package com.paweloot.quiz.ui.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.paweloot.quiz.R
import com.paweloot.quiz.entity.Answer
import com.paweloot.quiz.entity.AnswerKind
import kotlinx.android.synthetic.main.list_item_answer.view.*

class ResultAnswerAdapter(private val answers: List<Answer>) :
    RecyclerView.Adapter<ResultAnswerAdapter.AnswerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_answer, parent, false)

        return AnswerHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
        holder.bindAnswer(answers[position])
    }

    override fun getItemCount() = answers.size

    inner class AnswerHolder(val view: View) : RecyclerView.ViewHolder(view) {

        fun bindAnswer(answer: Answer) {
            view.answer.text = answer.content

            when (answer.kind) {
                AnswerKind.CORRECT ->
                    (view as MaterialCardView).setCardBackgroundColor(view.resources.getColor(R.color.color_accent))
                AnswerKind.INCORRECT ->
                    (view as MaterialCardView).setCardBackgroundColor(view.resources.getColor(R.color.color_error))
            }
        }
    }
}