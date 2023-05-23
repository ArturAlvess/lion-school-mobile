package br.senai.sp.jandira.lionschool

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.senai.sp.jandira.lionschool.components.TopLine
import br.senai.sp.jandira.lionschool.ui.theme.LionSchoolTheme

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

    // variáveis de estado:

    var inputState by remember() {
        mutableStateOf("")
    }

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
                        .padding(start = 30.dp, top = 80.dp),
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

                LazyColumn(){}

            }
        }
    }
}