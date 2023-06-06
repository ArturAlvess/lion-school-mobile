package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.BottomLine
import br.senai.sp.jandira.lionschool.components.CardLine
import br.senai.sp.jandira.lionschool.components.TopLine
import br.senai.sp.jandira.lionschool.model.CourseList
import br.senai.sp.jandira.lionschool.service.RetrofitFactory
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme
import coil.compose.AsyncImage
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CourseActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                CourseScreen()
            }
        }
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun CourseScreen() {


    val context = LocalContext.current

    var listCourses by remember {
        mutableStateOf(listOf<br.senai.sp.jandira.lionschool.model.Course>())
    }

    // variáveis de estado:

    var inputState by remember() {
        mutableStateOf("")
    }

    // Criando a chamada da api
    val call = RetrofitFactory().getAllCourses().getCourses()

    // Executando a chamada
    call.enqueue(object : Callback<CourseList>{
        override fun onResponse(
            call: Call<CourseList>,
            response: Response<CourseList>
        ){
            listCourses = response.body()!!.cursos
        }

        override fun onFailure(call: Call<CourseList>, t: Throwable) {
            Log.i(
                "ds2t",
                "onFailure: ${t.message}"
            )
        }
    })

    LionSchoolTheme {

        Surface(
            modifier =
            Modifier.fillMaxSize()
        ) {
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
                        label = { Text(text = stringResource(id = R.string.search))},
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
                        leadingIcon = { Icon(painter = painterResource(id = R.drawable.baseline_search_24), contentDescription = null, tint = Color(157, 157, 157))}
                    )
                }
                TopLine()
                
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 30.dp, top = 60.dp),
                    verticalAlignment = Alignment.CenterVertically) {
                    Image(
                        modifier =
                        Modifier.size(18.dp),
                        painter = painterResource(id = R.drawable.agenda),
                        contentDescription = null
                    )
                    Text(
                        modifier = Modifier
                        .padding(start = 5.dp),
                        text = stringResource(id = R.string.courses),
                        color = Color(51, 71, 176),
                        fontWeight = FontWeight.Bold
                    )
                }

                LazyColumn(){
                    items(listCourses){

                        Button(
                            modifier = Modifier
                                .width(400.dp)
                                .height(200.dp)
                                .padding(horizontal = 24.dp, vertical = 10.dp),
                            shape = RoundedCornerShape(25.dp),
                            colors = ButtonDefaults.buttonColors(Color(51, 71, 176))
                            ,onClick = {
                                var openStudentActivity = Intent(context, StudentActivity::class.java)
                                openStudentActivity.putExtra("sigla", it.sigla)
                                context.startActivity(openStudentActivity)
                            }
                        ) {
                            Row(
                                modifier = Modifier.fillMaxSize(),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                AsyncImage(
                                    model = it.icone,
                                    colorFilter = ColorFilter.tint(Color.White),
                                    contentDescription = "Icone do curso",
                                    modifier = Modifier
                                        .clip(shape = CircleShape)
                                        .size(110.dp)
                                        .padding(start = 10.dp)
                                )
                                Column(modifier = Modifier.padding(start = 15.dp, top = 0.dp)) {
                                    Text(
                                        modifier = Modifier
                                        ,text = it.sigla,
                                        fontSize = 40.sp,
                                        fontWeight = FontWeight.Bold,
                                        color = Color.White
                                    )
                                    CardLine()
                                    Spacer(modifier = Modifier.size(10.dp))
                                    Text(
                                        modifier = Modifier
                                        ,text = it.nome,
                                        fontSize = 12.sp,
                                        fontWeight = FontWeight.Medium,
                                        color = Color.White
                                    )
                                    Row() {
                                        Image(painter = painterResource(id = R.drawable.carga_horaria), contentDescription = null)
                                        Text(text = "${it.carga.toString()}h", color = Color.White, fontSize = 14.sp)

                                    }
                                }

                            }
                        }

                    }
                }
                Spacer(modifier = Modifier.size(80.dp))
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    BottomLine()
                    Image(modifier = Modifier.size(150.dp),painter = painterResource(id = R.drawable.social), contentDescription = null)
                }


            }
        }
    }
}