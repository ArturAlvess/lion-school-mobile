package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.CourseList
import retrofit2.Call
import retrofit2.http.GET

interface CourseService {

    // https://projeto-lion-school.cyclic.app/v1/lion-school/

    @GET("cursos")
    fun getCourses(): Call<CourseList>

    @GET("cursos/")
    fun getCourseByName()
}