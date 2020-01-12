package com.paweloot.quiz.ui.quiz

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.paweloot.quiz.R

class AnswerAdapter(
    private val answers: List<String>,
    private val callback: (answer: String) -> Unit
) :
    RecyclerView.Adapter<AnswerAdapter.AnswerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnswerHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.list_item_answer, parent, false)

        return AnswerHolder(view)
    }

    override fun onBindViewHolder(holder: AnswerHolder, position: Int) {
        holder.bindAnswer(answers[position])
    }

    override fun getItemCount() = answers.size

    inner class AnswerHolder(val view: View) :
        RecyclerView.ViewHolder(view) {

        private val answerTextView: TextView = view.findViewById(R.id.answer)

        fun bindAnswer(answer: String) {
            answerTextView.text = answer

            view.setOnClickListener {
                callback(answer)
            }
        }
    }
}