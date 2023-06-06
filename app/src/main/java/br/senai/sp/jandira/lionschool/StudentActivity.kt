package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import br.senai.sp.jandira.lionschool.components.TopLine
import br.senai.sp.jandira.lionschool.ui.theme.ui.theme.LionSchoolTheme

class StudentActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LionSchoolTheme {
                StudentScreen()
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun StudentScreen() {
    // variáveis de estado:

    var inputState by remember() {
        mutableStateOf("")
    }

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
                        label = { Text(text = stringResource(id = R.string.search)) },
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
                        .fillMaxWidth()
                        .padding(start = 40.dp)) {
                        Button(
                            modifier = Modifier
                                .width(100.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults
                                .buttonColors(
                                    Color(223, 223, 223)
                                )
                            ,onClick = { /*TODO*/ }
                        ) {
                            Text(
                                text = stringResource(id = R.string.button_all),
                                color = Color(51, 71, 176))
                        }
                        Button(
                            modifier = Modifier
                                .width(100.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults
                                .buttonColors(
                                    Color(223, 223, 223)
                                )
                            ,onClick = { /*TODO*/ }
                        ) {
                            Text(
                                text = stringResource(id = R.string.button_finished),
                                color = Color(51, 71, 176))
                        }
                        Button(
                            modifier = Modifier
                                .width(100.dp),
                            shape = RoundedCornerShape(20.dp),
                            colors = ButtonDefaults
                                .buttonColors(
                                    Color(223, 223, 223)
                                )
                            ,onClick = { /*TODO*/ }
                        ) {
                            Text(
                                text = stringResource(id = R.string.button_progress),
                                color = Color(51, 71, 176),
                                fontSize = 10.sp
                            )
                        }
                    }
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
