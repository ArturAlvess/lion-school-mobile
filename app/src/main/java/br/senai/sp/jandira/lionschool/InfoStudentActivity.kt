package br.senai.sp.jandira.lionschool

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.TopLine
import br.senai.sp.jandira.lionschool.model.Disciplina
import br.senai.sp.jandira.lionschool.model.StudentGrade
import br.senai.sp.jandira.lionschool.model.StudentsList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class InfoStudentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dados = intent.getStringExtra("matricula")
        setContent {
            LionSchoolTheme {
                InfoStudentScreen(dados.toString())
                }
            }
        }
    }



@Composable
fun InfoStudentScreen(matricula : String) {

    var inputState by remember() {
        mutableStateOf("")
    }

    var nomeAluno by remember() {
        mutableStateOf("")
    }
    var fotoAluno by remember() {
        mutableStateOf("")
    }

    var matriculaAluno by remember() {
        mutableStateOf("")
    }
    var listaDisciplinas by remember() {
        mutableStateOf(listOf<Disciplina>())
    }
    var nomeCurso by remember() {
        mutableStateOf("")
    }

    // Chamada para a API
    val callAlunosByMatricula = RetrofitFactory().getAllStudents().getStudentByMatricula(matricula)

    // Executando a chamada da API
    callAlunosByMatricula.enqueue(object : retrofit2.Callback<StudentGrade>{
        override fun onResponse(
            call: Call<br.senai.sp.jandira.lionschool.model.StudentGrade>,
            response: Response<br.senai.sp.jandira.lionschool.model.StudentGrade>
        ){
            nomeAluno = response.body()!!.nome
            fotoAluno = response.body()!!.foto
            matriculaAluno = response.body()!!.matricula
            listaDisciplinas = response.body()!!.disciplinas
            nomeCurso = response.body()!!.nomeCurso
        }

        override fun onFailure(call: Call<br.senai.sp.jandira.lionschool.model.StudentGrade>, t: Throwable){
            Log.i(
                "ds2t",
                "onFailure: ${t.message}"
            )
        }
    })

    LionSchoolTheme {

        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier =
                        Modifier.size(120.dp), painter = painterResource(id = R.drawable.logo),
                        contentDescription = null
                    )

                    OutlinedTextField(
                        value =
                        inputState,
                        label = { Text(text = stringResource(id = R.string.search_student)) },
                        onValueChange = {
                            inputState = it
                        },
                        colors = TextFieldDefaults
                            .outlinedTextFieldColors
                                (
                                backgroundColor = Color(239, 239, 239),
                                focusedBorderColor = Color(51, 71, 176),
                                focusedLabelColor = Color(51, 71, 176),
                                cursorColor = Color(51, 71, 176),
                                unfocusedBorderColor = Color(51, 71, 176, 0)
                            ),
                        shape = RoundedCornerShape(15.dp),
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_search_24),
                                contentDescription = null,
                                tint = Color(157, 157, 157)
                            )
                        }
                    )
                }
                TopLine()
                Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center) {
                    Text(modifier = Modifier.padding(bottom = 15.dp),text = nomeCurso, fontSize = 20.sp, color = Color(51, 71, 176))
                    Card(modifier = Modifier
                        .height(530.dp)
                        .width(350.dp), backgroundColor = Color(51, 71, 176)) {

                            Column(modifier = Modifier.padding(top = 20.dp), horizontalAlignment = Alignment.CenterHorizontally) {
                                AsyncImage(model = fotoAluno, contentDescription = null, modifier = Modifier.size(115.dp))
                                Text(text = nomeAluno.toUpperCase(), color = Color.White, fontSize = 16.sp)
                                Text(text = matriculaAluno, color = Color.White, fontSize = 20.sp, textAlign = TextAlign.End)

                                Card(modifier = Modifier
                                    .height(280.dp)
                                    .width(297.dp)
                                    .padding(top = 25.dp)){

                                    LazyColumn(modifier = Modifier.fillMaxWidth(),verticalArrangement = Arrangement.Center){
                                        items(listaDisciplinas){
                                            Row(modifier = Modifier.padding(horizontal = 10.dp, vertical = 10.dp) ,horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
                                                Text(text = it.sigla, fontSize = 18.sp, color = Color(51, 71, 176))
                                                Spacer(modifier = Modifier.size(15.dp))
                                                Text(text = it.media)

                                                Surface(
                                                    modifier = Modifier
                                                        .width(113.dp)
                                                        .height(15.dp)
                                                        .background(
                                                            Color(223, 223, 223)
                                                        ).padding(start = 10.dp)
                                                ){
                                                    Box(modifier = Modifier.width(it.media.toDouble().dp).background(
                                                        changeGraphicColor(it.media.toDouble())
                                                    ).height(15.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                            }

                    }
                }

            }

        }
    }
}
fun changeGraphicColor(media : Double): Color{

    return if(media <= 100 && media >= 80){
        Color(51, 71, 176)
    } else if(media < 80 && media >= 50){
        Color(252, 194, 95, 255)
    } else{
        Color(204, 81, 81)
    }
}