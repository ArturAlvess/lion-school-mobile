package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
import br.senai.sp.jandira.lionschool.components.BottomLine
import br.senai.sp.jandira.lionschool.components.TopLine
import br.senai.sp.jandira.lionschool.model.Students
import br.senai.sp.jandira.lionschool.model.StudentsList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StudentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dados = intent.getStringExtra("sigla")
        setContent {
            LionSchoolTheme {
                StudentScreen(dados.toString())
            }
        }
    }
}

@Composable
fun StudentScreen(sigla: String) {


    val context = LocalContext.current

    var listStudents by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Students>())
    }
    var listStudents2 by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Students>())
    }

    var list: List<Students>
    // variáveis de estado:

    var inputState by remember() {
        mutableStateOf("")
    }

    var nomeCurso by remember {
        mutableStateOf("")
    }

    // chamada para a api
    val callAlunosCurso = RetrofitFactory().getAllStudents().getStudentsBySiglaCurso(sigla)

    // executando a chamada
    callAlunosCurso.enqueue(object : Callback<StudentsList>{
        override fun onResponse(
            call: Call<StudentsList>,
            response: Response<StudentsList>
        ){
            nomeCurso = response.body()!!.nomeCurso
            listStudents = response.body()!!.informacoes
            listStudents2 = response.body()!!.informacoes
        }

        override fun onFailure(call: Call<StudentsList>, t: Throwable) {
            Log.i(
                "ds2t",
                "onFailure: ${t.message}"
            )
        }
    })

//    callAlunosCurso.enqueue(object : Callback<StudentsList>{
//
//    })

    LionSchoolTheme {
        Surface(modifier = Modifier.fillMaxSize()) {

            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 25.dp, end = 25.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier =
                        Modifier.size(120.dp)
                        ,painter = painterResource(id = R.drawable.logo),
                        contentDescription = null)

                    OutlinedTextField(
                        value =
                        inputState,
                        label = { Text(text = stringResource(id = R.string.search_student)) },
                        onValueChange = {
                            inputState = it
                        },
                        colors = TextFieldDefaults
                            .outlinedTextFieldColors
                                (backgroundColor = Color(239, 239, 239),
                                focusedBorderColor = Color(51, 71, 176),
                                focusedLabelColor = Color(51, 71, 176),
                                cursorColor = Color(51, 71, 176),
                                unfocusedBorderColor = Color(51, 71, 176, 0)
                            ),
                        shape = RoundedCornerShape(15.dp),
                        leadingIcon = { Icon(painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = null, tint = Color(157, 157, 157)) }
                    )
                }
                TopLine()
                Column(modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 20.dp)) {
                    Row(modifier = Modifier
                        .fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Button(
                            modifier = Modifier
                                .width(100.dp)
                                .padding(horizontal = 5.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults
                                .buttonColors(
                                    Color(223, 223, 223)
                                )
                            ,onClick = {
                                list = listStudents
                                listStudents2 = list
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.button_all),
                                color = Color(51, 71, 176))
                        }
                        Button(
                            modifier = Modifier
                                .width(100.dp)
                                .padding(0.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults
                                .buttonColors(
                                    Color(223, 223, 223)
                                )
                            ,onClick = {
                                listStudents2 = listStudents.filter { it.status == "Finalizado" }
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.button_finished),
                                color = Color(51, 71, 176))
                        }
                        Button(
                            modifier = Modifier
                                .width(120.dp)
                                .padding(horizontal = 5.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults
                                .buttonColors(
                                    Color(223, 223, 223)
                                )
                            ,onClick = {
                                listStudents2 = listStudents.filter { it.status == "Cursando" }
                            }
                        ) {
                            Text(
                                text = stringResource(id = R.string.button_progress),
                                color = Color(51, 71, 176),
                                fontSize = 10.sp
                            )
                        }
                    }
                    Text(modifier = Modifier.padding(start = 30.dp, bottom = 10.dp) ,text = nomeCurso, color = Color(51, 71, 176), letterSpacing = 1.sp)
                    LazyColumn(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally){
                        items(listStudents2){
                            Button(modifier = Modifier
                                .height(270.dp)
                                .width(290.dp)
                                .padding(15.dp),
                                colors = ButtonDefaults.buttonColors(changeColorbyStatus(it.status)),
                                onClick = {
                                    var openInfoStudentActivity = Intent(context, InfoStudentActivity::class.java)
                                    openInfoStudentActivity.putExtra("matricula", it.matricula)
                                    context.startActivity(openInfoStudentActivity)
                                }
                            ) {
                                Row() {
                                    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {

                                        AsyncImage(model = it.foto, contentDescription = null)
                                        Text(text = it.nome.toUpperCase(), fontSize = 15.sp, color = Color.White, textAlign = TextAlign.Center)
                                        Text(text = it.matricula, fontSize = 15.sp, color = Color.White)
                                        Text(text = it.status, fontSize = 12.sp, color = Color.White)

                                    }
                                }


                            }
                        }
                    }
                    BottomLine()
                    Text(modifier = Modifier.padding(start = 100.dp, top = 20.dp)
                        ,text = "Redes de Computadores",
                        color = Color(51, 71, 176),
                    )
                    Row(modifier = Modifier.fillMaxWidth()) {
                        Image(
                            modifier =
                            Modifier
                                .size(18.dp)
                                .padding(start = 30.dp, top = 60.dp),
                            painter = painterResource(id = R.drawable.student),
                            contentDescription = null
                        )
                        Text(
                            modifier = Modifier
                                .padding(start = 5.dp),
                            text = "Student",
                            color = Color(51, 71, 176),
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

            }

        }
    }
}

fun changeColorbyStatus(status: String): Color{
    return if (status == "Finalizado"){
        Color(229, 182, 87)
    } else{
        Color(51, 71, 176)
    }
}