package br.senai.sp.jandira.lionschool.service

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitFactory {

    private val BASE_URL = "https://api-lion-school-2023.cyclic.app/v1/lion-school/"

    private val retrofitFactory = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun getAllCourses(): CourseService{
        return retrofitFactory.create(CourseService::class.java)
    }

    fun getAllStudents(): StudentService{
        return retrofitFactory.create(StudentService::class.java)
    }
}