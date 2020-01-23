package com.paweloot.quiz.ui.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.card.MaterialCardView
import com.paweloot.quiz.R

class AnswerAdapter(
    private val answers: List<String>,
    private val callback: (answer: String) -> Unit
) :
    RecyclerView.Adapter<AnswerAdapter.AnswerHolder>() {

    private var selectedAnswerPosition: Int = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_answer, parent, false)

        return AnswerHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
        if (selectedAnswerPosition == position) {
            (holder.view as MaterialCardView).setCardBackgroundColor(
                holder.view.resources.getColor(R.color.color_accent)
            )
        } else {
            (holder.view as MaterialCardView).setCardBackgroundColor(
                holder.view.resources.getColor(R.color.color_cardview_background)
            )
        }

        holder.bindAnswer(answers[position])
        holder.view.setOnClickListener {
            selectedAnswerPosition = position
            notifyDataSetChanged()
            callback(answers[position])
        }
    }

    override fun getItemCount() = answers.size

    private fun changeHighlightedAnswer(newAnswer: String) {
        val newPosition = answers.indexOf(newAnswer)
    }

    inner class AnswerHolder(val view: View) :
        RecyclerView.ViewHolder(view) {

        private val answerTextView: TextView = view.findViewById(R.id.answer)

        fun bindAnswer(answer: String) {
            answerTextView.text = answer

            view.setOnClickListener {
                changeHighlightedAnswer(answer)
                callback(answer)
            }
        }
    }
}