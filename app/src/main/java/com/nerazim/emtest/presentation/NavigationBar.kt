package com.nerazim.emtest.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role.Companion.Tab

//нижний бар, он же навигация
@Composable
fun JobBottomBar(
    screens: List<BottomBarDestination>, //список экранов
    onTabSelected: (BottomBarDestination) -> Unit, //что делать при выборе вкладки
    currentScreen: BottomBarDestination //текущий экран
) {
    Surface(
        Modifier.fillMaxWidth()
    ) {
        //ряд, на котором размещаются все иконки
        Row(
            Modifier.selectableGroup(), //это группа предметов, из которых можно выбрать один
            horizontalArrangement = Arrangement.SpaceEvenly //равномерное распределение по всему ряду
        ) {
            //заполнение бара элементами
            screens.forEach { screen ->
                JobBottomBarItem(
                    text = screen.route, //подпись под иконкой
                    //иконка, разная в зависимости от того, выбран этот экран или нет
                    icon = painterResource (if (currentScreen == screen) screen.active else screen.inactive),
                    //что делать при выборе
                    onSelected = { onTabSelected(screen) },
                    //проверка на текущий экран
                    selected = currentScreen == screen
                )
            }
        }
    }
}

//элемент бара
@Composable
fun JobBottomBarItem(
    text: String, //подпись
    icon: Painter, //иконка
    onSelected: () -> Unit, //что делать при выборе
    selected: Boolean //флаг выбора
) {
    Column(
        modifier = Modifier.selectable(
            selected = selected,
            onClick = onSelected,
            role = Tab),
        horizontalAlignment = Alignment.CenterHorizontally //выравнивание по центру
    ) {
        Image(painter = icon, contentDescription = text) //сама иконка (не Icon, потому что иначе они черно-белые)
        //подпись, меняет цвет в зависимости от того, выбран ли этот экран
        Text(
            text = text,
            color = if (selected) Color(0xFF2B7EFE) else Color.White
        )
    }
}
