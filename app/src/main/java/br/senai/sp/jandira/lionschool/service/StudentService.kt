package br.senai.sp.jandira.lionschool.service

import br.senai.sp.jandira.lionschool.model.StudentsList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface StudentService {

    // https://projeto-lion-school.cyclic.app/v1/lion-school/

    @GET("alunos")
    fun getStudents(): Call<StudentsList>

    @GET("alunos/")
    fun getStudentsBySiglaCurso(
        @Query("curso") name: String
    ): Call<StudentsList>

    @GET("alunos/")
    fun getStudentsByStatus(
        @Query("status") name: String
    ): Call<StudentsList>
}