package br.com.fiap.nac.nac2.api

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ClientAPI<T> {
    fun getClient(c: Class<T>): T {
        val retrofit = Retrofit.Builder()
            .client(getOkHttpClientAuth().build())
            .baseUrl("https://gamestjd.herokuapp.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        return retrofit.create(c);
    }
}

fun getOkHttpClientAuth(): OkHttpClient.Builder {
    return OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
}

fun getPokemonApi(): PokemonAPI {
    return ClientAPI<PokemonAPI>().getClient(PokemonAPI::class.java)
}