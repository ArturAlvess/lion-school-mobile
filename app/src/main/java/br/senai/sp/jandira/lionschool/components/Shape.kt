package br.senai.sp.jandira.lionschool.components

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun TopLine(){

    Card(modifier = Modifier
        .height(3.dp)
        .width(280.dp),
        backgroundColor = Color(229, 182, 87),

    ){}
}

@Preview
@Composable
fun TopLinePreview(){
    TopLine()
}

@Composable
fun BottomLine(){

    Card(modifier = Modifier
        .height(3.dp)
        .width(280.dp),
        backgroundColor = Color(229, 182, 87)
    ){}
}


@Preview
@Composable
fun BottomLinePreview(){
    BottomLine()
}