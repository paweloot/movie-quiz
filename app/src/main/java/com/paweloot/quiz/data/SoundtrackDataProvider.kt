package com.paweloot.quiz.data

import com.paweloot.quiz.entity.SoundtrackData
import com.paweloot.quiz.entity.SoundtrackQuestion

object SoundtrackDataProvider {

    val soundtrackData: List<SoundtrackData> = mutableListOf(
        SoundtrackData(
            "City of Stars",
            "city_of_stars.mp3",
            "La La Land",
            "https://images-na.ssl-images-amazon.com/images/I/71-u32-oOaL._SY679_.jpg",
            2016
        ),
        SoundtrackData(
            "Let It Go",
            "let_it_go.mp3",
            "Frozen",
            "https://images-na.ssl-images-amazon.com/images/I/51vgOCNPn3L.jpg",
            2013
        ),
        SoundtrackData(
            "The Time Of My Life",
            "time_of_my_life.mp3",
            "Dirty Dancing",
            "https://i.wpimg.pl/0/429x600/i.wp.pl/a/f/film/008/01/51/0025101.jpg",
            1987
        ),
        SoundtrackData(
            "Shallow",
            "shallow.mp3",
            "A Star Is Born",
            "https://image.ceneostatic.pl/data/products/69463052/i-a-star-is-born-soundtrack-pl-cd-lady-gaga-bradley-cooper.jpg",
            2018
        ),
        SoundtrackData(
            "Toss A Coin To Your Witcher",
            "toss_a_coin.mp3",
            "The Witcher",
            "https://m.media-amazon.com/images/M/MV5BOGE4MmVjMDgtMzIzYy00NjEwLWJlODMtMDI1MGY2ZDlhMzE2XkEyXkFqcGdeQXVyMzY0MTE3NzU@._V1_.jpg",
            2019
        )
    )

    private val questions: List<SoundtrackQuestion> = listOf(
        SoundtrackQuestion(
            soundtrackData[4],
            listOf("Shallow", "City of Stars", "Let It Go")
        ),
        SoundtrackQuestion(
            soundtrackData[0],
            listOf("The Time Of My Life", "Shallow", "Let It Go")
        )
    )

    fun getRandomQuestion(): SoundtrackQuestion = questions.random()
}