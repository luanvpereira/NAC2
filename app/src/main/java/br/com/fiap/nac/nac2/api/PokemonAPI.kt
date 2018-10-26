package br.com.fiap.nac.nac2.api

import br.com.fiap.nac.nac2.model.Score
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface PokemonAPI {
    @POST("/jokenpokemon/pontuacao")
    fun sendScore(@Body score: Score):Call<Void>

    @GET("/jokenpokemon/pontuacao")
    fun loadScores():Call<List<Score>>
}