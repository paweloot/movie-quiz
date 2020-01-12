package com.paweloot.quiz.ui.main

import androidx.lifecycle.ViewModel
import com.paweloot.quiz.entity.ClipQuestion
import com.paweloot.quiz.entity.PhotoQuestion
import com.paweloot.quiz.entity.SoundtrackQuestion

class MainViewModel : ViewModel() {

    val photoQuestions: List<PhotoQuestion> = mutableListOf(
        PhotoQuestion(
            "https://storage.googleapis.com/afs-prod/media/media:e3be851cc3124c5f81f1489db36191fc/800.jpeg",
            "Henry Cavill"
        ),
        PhotoQuestion(
            "https://media-manager.starsinsider.com/1920/na_5dc05f1c9c55b.jpg",
            "Adam Driver"
        ),
        PhotoQuestion(
            "https://www.cheatsheet.com/wp-content/uploads/2019/11/daisy-ridley-in-a-black-dress-1024x763.jpg",
            "Daisy Ridley"
        )
    )

    val soundtrackQuestions: List<SoundtrackQuestion> = mutableListOf(
        SoundtrackQuestion(
            "City of Stars",
            "city_of_stars.mp3",
            "La La Land",
            "https://images-na.ssl-images-amazon.com/images/I/71-u32-oOaL._SY679_.jpg",
            2016
        ),
        SoundtrackQuestion(
            "Let It Go",
            "let_it_go.mp3",
            "Frozen",
            "https://images-na.ssl-images-amazon.com/images/I/51vgOCNPn3L.jpg",
            2013
        ),
        SoundtrackQuestion(
            "The Time Of My Life",
            "time_of_my_life.mp3",
            "Dirty Dancing",
            "https://i.wpimg.pl/0/429x600/i.wp.pl/a/f/film/008/01/51/0025101.jpg",
            1987
        ),
        SoundtrackQuestion(
            "Shallow",
            "shallow.mp3",
            "A Star Is Born",
            "https://image.ceneostatic.pl/data/products/69463052/i-a-star-is-born-soundtrack-pl-cd-lady-gaga-bradley-cooper.jpg",
            2018
        ),
        SoundtrackQuestion(
            "Toss A Coin To Your Witcher",
            "toss_a_coin.mp3",
            "The Witcher",
            "https://m.media-amazon.com/images/M/MV5BOGE4MmVjMDgtMzIzYy00NjEwLWJlODMtMDI1MGY2ZDlhMzE2XkEyXkFqcGdeQXVyMzY0MTE3NzU@._V1_.jpg",
            2019
        )
    )

    val clipQuestions: List<ClipQuestion> = mutableListOf(
        ClipQuestion(
            "Whiplash",
            "Damien Chazelle",
            "whiplash.mp4",
            "https://images-na.ssl-images-amazon.com/images/I/516noX3aE9L.jpg",
            2014
        ),
        ClipQuestion(
            "Forrest Gump",
            "Robert Zemeckis",
            "forrest_gump.mp4",
            "https://i.etsystatic.com/15375993/r/il/cbafe5/1303342253/il_570xN.1303342253_qphh.jpg",
            1994
        ),
        ClipQuestion(
            "Intouchables",
            "Olivier Nakache, Éric Toledano",
            "intouchables.mp4",
            "https://images-na.ssl-images-amazon.com/images/I/51WbHjvEceL.jpg",
            2011
        ),
        ClipQuestion(
            "Gladiator",
            "Ridley Scott",
            "gladiator.mp4",
            "https://images-na.ssl-images-amazon.com/images/I/51o9U06EV8L._AC_.jpg",
            2000
        ),
        ClipQuestion(
            "The Shawshank Redemption",
            "Frank Darabont",
            "shawshank.mp4",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSkmMH-bEDUS2TmK8amBqgIMgrfzN1_mImChPuMrunA1XjNTSKm",
            1994
        )
    )
}
