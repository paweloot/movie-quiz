package com.paweloot.quiz.questions

import com.paweloot.quiz.entity.ClipData
import com.paweloot.quiz.entity.ClipQuestion

object ClipDataProvider {

    val clipData: List<ClipData> = mutableListOf(
        ClipData(
            "Whiplash",
            "Damien Chazelle",
            "whiplash.mp4",
            "https://images-na.ssl-images-amazon.com/images/I/516noX3aE9L.jpg",
            2014
        ),
        ClipData(
            "Forrest Gump",
            "Robert Zemeckis",
            "forrest_gump.mp4",
            "https://i.etsystatic.com/15375993/r/il/cbafe5/1303342253/il_570xN.1303342253_qphh.jpg",
            1994
        ),
        ClipData(
            "Intouchables",
            "Olivier Nakache, Éric Toledano",
            "intouchables.mp4",
            "https://images-na.ssl-images-amazon.com/images/I/51WbHjvEceL.jpg",
            2011
        ),
        ClipData(
            "Gladiator",
            "Ridley Scott",
            "gladiator.mp4",
            "https://images-na.ssl-images-amazon.com/images/I/51o9U06EV8L._AC_.jpg",
            2000
        ),
        ClipData(
            "The Shawshank Redemption",
            "Frank Darabont",
            "shawshank.mp4",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkmMH-bEDUS2TmK8amBqgIMgrfzN1_mImChPuMrunA1XjNTSKm",
            1994
        )
    )

    private val questions = listOf(
        ClipQuestion(
            clipData[2],
            listOf("Forrest Gump", "Gladiator", "Whiplash")
        ),
        ClipQuestion(
            clipData[0],
            listOf("Forrest Gump", "Intouchables", "The Shawshank Redemption")
        )
    )

    fun getRandomQuestion(): ClipQuestion = questions.random()
}