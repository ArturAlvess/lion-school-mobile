package br.senai.sp.jandira.lionschool

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.BottomLine
import br.senai.sp.jandira.lionschool.components.TopLine
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
               MainScreen()
            }
        }
    }
}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun MainScreen() {
    LionSchoolTheme {

        val context = LocalContext.current

        // SURFACE GLOBAL
        Surface(modifier = Modifier.fillMaxSize()) {

            // PRIMEIRA COLUNA
            Column(
                modifier =
                Modifier
                    .fillMaxSize()
                    .padding(top = 40.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                // Component do Shape


                Column(
                    modifier = Modifier.padding(10.dp),
                    horizontalAlignment =
                    Alignment.CenterHorizontally


                ) {
                    TopLine()

                    Image(
                        modifier = Modifier.size(150.dp)
                        ,painter =
                        painterResource(id = R.drawable.logo),
                        contentDescription = null)

                    Text(
                        text =
                        stringResource(id = R.string.title),
                        color = Color(229, 182, 87),
                        fontSize = 24.sp
                    )
                    Text(
                        text =
                        stringResource(id = R.string.second_title),
                        color = Color(229, 182, 87),
                        fontSize = 24.sp

                    )

                    Image(
                        modifier = Modifier.size(280.dp)
                        ,painter =
                        painterResource(id = R.drawable.devices),
                        contentDescription = null)

                    Text(
                        modifier = Modifier.padding(bottom = 30.dp)
                        ,text =
                        stringResource(id = R.string.description),
                        color = Color(172, 161, 161),
                        fontSize = 24.sp
                    )

                    Button(
                        modifier =
                        Modifier
                            .height(80.dp)
                            .width(230.dp),
                        colors = ButtonDefaults.buttonColors(Color(51, 71, 176))
                        ,onClick = {
                            var openCourseActivity = Intent(context, CourseActivity::class.java)
                            context.startActivity(openCourseActivity)
                        }
                    )
                    {
                        Text(text = stringResource(id = R.string.button_text),
                            color = Color.White,
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Light)
                    }
                    Spacer(modifier = Modifier.size(30.dp))
                    BottomLine()
                }
                

            }
        }
    }
}