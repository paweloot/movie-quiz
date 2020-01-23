package com.paweloot.quiz.questions

import com.paweloot.quiz.entity.PhotoAnswer
import com.paweloot.quiz.entity.PhotoQuestion

object PhotoDataProvider {

    val photoAnswers: List<PhotoAnswer> = listOf(
        PhotoAnswer(
            "https://storage.googleapis.com/afs-prod/media/media:e3be851cc3124c5f81f1489db36191fc/800.jpeg",
            "Henry Cavill"
        ),
        PhotoAnswer(
            "https://image.insider.com/5dcc3df979d7570d633e10ea?width=1100&format=jpeg&auto=webp",
            "Scarlett Johansson"
        ),
        PhotoAnswer(
            "https://media-manager.starsinsider.com/1920/na_5dc05f1c9c55b.jpg",
            "Adam Driver"
        ),
        PhotoAnswer(
            "https://www.cheatsheet.com/wp-content/uploads/2019/11/daisy-ridley-in-a-black-dress-1024x763.jpg",
            "Daisy Ridley"
        ),
        PhotoAnswer(
            "https://guinnessworldrecords.com/Images/jennifer-aniston_tcm25-595487.jpg",
            "Jennifer Aniston"
        ),
        PhotoAnswer(
            "https://storage.googleapis.com/afs-prod/media/media:a1cafc3b81904cbc8b0df9b2b0f033eb/3000.jpeg",
            "Emma Stone"
        ),
        PhotoAnswer(
            "https://dynaimage.cdn.cnn.com/cnn/c_fill,g_auto,w_1200,h_675,ar_16:9/https%3A%2F%2Fcdn.cnn.com%2Fcnnnext%2Fdam%2Fassets%2F191203095415-brad-pitt.jpg",
            "Brad Pitt"
        ),
        PhotoAnswer(
            "https://ocdn.eu/pulscms-transforms/1/bM0k9kqTURBXy9kNDdkYTIwMTVhODVmOGQxNDViYWNmZjFlZGI4NjQwMi5qcGVnk5UDAEjNDQHNB1CTBc0DFM0BvJMJpjc1OGY5MAaBoTAB/tom-hanks.jpg",
            "Tom Hanks"
        )
    )

    private val questions: List<PhotoQuestion> = listOf(
        PhotoQuestion(
            photoAnswers[3],
            listOf("Scarlett Johansson", "Jennifer Aniston", "Emma Stone")
        ),
        PhotoQuestion(
            photoAnswers[2],
            listOf("Brad Pitt", "Henry Cavill", "Tom Hanks")
        )
    )


    fun getRandomQuestion(): PhotoQuestion = questions.random()
}