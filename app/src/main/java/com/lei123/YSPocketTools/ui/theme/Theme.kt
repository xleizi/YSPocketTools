package com.lei123.YSPocketTools

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

private val LightColorPalette = YSComposeColors(
    bottomBar = BottomBar1,
    background = background1,
    icon = iconCurrentColor1,
    iconCurrent = iconColor1,
    topBottomText = topBottomText1,
    textSecondary = textcolorsecond1,
    textPrimary = textcolorPrimary1,
    textPrimaryMe = textcolorPrimaryMe1,
    textEmpty = textEmpty1,
    ProgressBarFull = ProgressBarFull1,
    TextBold = TextBold1,
    simpleBlack = simpleBlack1,
    simplegray = simplegray1,
    simpleblue = simpleblue1,
    settingBackground = settingBackground1,

    listItem = white,
    divider = white3,
    chatPage = white2,
    onBackground = grey3,

    badge = red1,
    onBadge = white,
    bubbleMe = green1,
    bubbleOthers = white,
    textFieldBackground = white,
    more = grey4,

    chatPageBgAlpha = 0f,
)
/*private val DarkColorPalette = YSComposeColors(
    bottomBar = black1,
    background = black2,
    listItem = black3,
    divider = black4,
    chatPage = black2,
    textPrimary = white4,
    textPrimaryMe = black6,
    textSecondary = grey1,
    onBackground = grey1,
    icon = white5,
    iconCurrent = green3,
    badge = red1,
    onBadge = white,
    bubbleMe = green2,
    bubbleOthers = black5,
    textFieldBackground = black7,
    more = grey5,
    chatPageBgAlpha = 0f,
)
private val NewYearColorPalette = YSComposeColors(
    bottomBar = red4,
    background = red5,
    listItem = red2,
    divider = red3,
    chatPage = red5,
    textPrimary = white,
    textPrimaryMe = black6,
    textSecondary = grey2,
    onBackground = grey2,
    icon = white5,
    iconCurrent = green3,
    badge = yellow1,
    onBadge = black3,
    bubbleMe = green2,
    bubbleOthers = red6,
    textFieldBackground = red7,
    more = red8,
    chatPageBgAlpha = 1f,
)*/

private val LocalYSComposeColors = compositionLocalOf {
    LightColorPalette
}

object YSComposeTheme {
    val colors: YSComposeColors
        @Composable
        get() = LocalYSComposeColors.current

    enum class Theme {
        Light/*, Dark, NewYear*/
    }
}

@Stable
class YSComposeColors(
    bottomBar: Color,
    background: Color,
    listItem: Color,
    divider: Color,
    chatPage: Color,
    textPrimary: Color,
    textPrimaryMe: Color,
    textSecondary: Color,
    onBackground: Color,
    icon: Color,
    iconCurrent: Color,
    badge: Color,
    onBadge: Color,
    bubbleMe: Color,
    bubbleOthers: Color,
    textFieldBackground: Color,
    more: Color,
    topBottomText: Color,
    textEmpty: Color,
    ProgressBarFull: Color,
    TextBold: Color,
    simpleBlack: Color,
    simplegray: Color,
    simpleblue: Color,
    settingBackground: Color,

    chatPageBgAlpha: Float,
) {
    var bottomBar: Color by mutableStateOf(bottomBar)
        private set
    var background: Color by mutableStateOf(background)
        private set
    var listItem: Color by mutableStateOf(listItem)
        private set
    var chatListDivider: Color by mutableStateOf(divider)
        private set
    var chatPage: Color by mutableStateOf(chatPage)
        private set
    var textPrimary: Color by mutableStateOf(textPrimary)
        private set
    var textPrimaryMe: Color by mutableStateOf(textPrimaryMe)
        private set
    var textSecondary: Color by mutableStateOf(textSecondary)
        private set
    var onBackground: Color by mutableStateOf(onBackground)
        private set
    var icon: Color by mutableStateOf(icon)
        private set
    var iconCurrent: Color by mutableStateOf(iconCurrent)
        private set
    var badge: Color by mutableStateOf(badge)
        private set
    var onBadge: Color by mutableStateOf(onBadge)
        private set
    var bubbleMe: Color by mutableStateOf(bubbleMe)
        private set
    var bubbleOthers: Color by mutableStateOf(bubbleOthers)
        private set
    var textFieldBackground: Color by mutableStateOf(textFieldBackground)
        private set
    var more: Color by mutableStateOf(more)
        private set
    var topBottomText: Color by mutableStateOf(topBottomText)
        private set
    var textEmpty: Color by mutableStateOf(textEmpty)
        private set
    var ProgressBarFull: Color by mutableStateOf(ProgressBarFull)
        private set
    var TextBold: Color by mutableStateOf(TextBold)
        private set
    var simpleBlack: Color by mutableStateOf(simpleBlack)
        private set
    var simplegray: Color by mutableStateOf(simplegray)
        private set
    var simpleblue: Color by mutableStateOf(simpleblue)
        private set
    var settingBackground: Color by mutableStateOf(settingBackground)
        private set


    var chatPageBgAlpha: Float by mutableStateOf(chatPageBgAlpha)
        private set
}

@Composable
fun YSComposeTheme(
    theme: YSComposeTheme.Theme = YSComposeTheme.Theme.Light,
    content: @Composable() () -> Unit
) {
    val targetColors = when (theme) {
        YSComposeTheme.Theme.Light -> LightColorPalette
/*        YSComposeTheme.Theme.Dark -> DarkColorPalette
        YSComposeTheme.Theme.NewYear -> NewYearColorPalette*/
    }

    val bottomBar = animateColorAsState(targetColors.bottomBar, TweenSpec(600))
    val background = animateColorAsState(targetColors.background, TweenSpec(600))
    val listItem = animateColorAsState(targetColors.listItem, TweenSpec(600))
    val chatListDivider = animateColorAsState(targetColors.chatListDivider, TweenSpec(600))
    val chatPage = animateColorAsState(targetColors.chatPage, TweenSpec(600))
    val textPrimary = animateColorAsState(targetColors.textPrimary, TweenSpec(600))
    val textPrimaryMe = animateColorAsState(targetColors.textPrimaryMe, TweenSpec(600))
    val textSecondary = animateColorAsState(targetColors.textSecondary, TweenSpec(600))
    val onBackground = animateColorAsState(targetColors.onBackground, TweenSpec(600))
    val icon = animateColorAsState(targetColors.icon, TweenSpec(100))
    val iconCurrent = animateColorAsState(targetColors.iconCurrent, TweenSpec(100))
    val badge = animateColorAsState(targetColors.badge, TweenSpec(600))
    val onBadge = animateColorAsState(targetColors.onBadge, TweenSpec(600))
    val bubbleMe = animateColorAsState(targetColors.bubbleMe, TweenSpec(600))
    val bubbleOthers = animateColorAsState(targetColors.bubbleOthers, TweenSpec(600))
    val textFieldBackground = animateColorAsState(targetColors.textFieldBackground, TweenSpec(600))
    val more = animateColorAsState(targetColors.more, TweenSpec(600))
    val topBottomText = animateColorAsState(targetColors.topBottomText, TweenSpec(600))
    val textEmpty = animateColorAsState(targetColors.textEmpty, TweenSpec(600))
    val ProgressBarFull = animateColorAsState(targetColors.ProgressBarFull, TweenSpec(600))
    val TextBold = animateColorAsState(targetColors.TextBold, TweenSpec(600))
    val simpleBlack = animateColorAsState(targetColors.simpleBlack, TweenSpec(600))
    val simplegray = animateColorAsState(targetColors.simplegray, TweenSpec(600))
    val simpleblue = animateColorAsState(targetColors.simpleblue, TweenSpec(600))
    val settingBackground = animateColorAsState(targetColors.settingBackground, TweenSpec(600))


    val chatPageBgAlpha = animateFloatAsState(targetColors.chatPageBgAlpha, TweenSpec(600))

    val colors = YSComposeColors(
        bottomBar = bottomBar.value,
        background = background.value,
        listItem = listItem.value,
        divider = chatListDivider.value,
        chatPage = chatPage.value,
        textPrimary = textPrimary.value,
        textPrimaryMe = textPrimaryMe.value,
        textSecondary = textSecondary.value,
        onBackground = onBackground.value,
        icon = icon.value,
        iconCurrent = iconCurrent.value,
        badge = badge.value,
        onBadge = onBadge.value,
        bubbleMe = bubbleMe.value,
        bubbleOthers = bubbleOthers.value,
        textFieldBackground = textFieldBackground.value,
        more = more.value,
        topBottomText = topBottomText.value,
        textEmpty = textEmpty.value,
        ProgressBarFull = ProgressBarFull.value,
        TextBold= TextBold.value,
        simpleBlack= simpleBlack.value,
        simplegray= simplegray.value,
        simpleblue= simpleblue.value,
        settingBackground= settingBackground.value,

        chatPageBgAlpha = chatPageBgAlpha.value,
    )

    CompositionLocalProvider(LocalYSComposeColors provides colors) {
        MaterialTheme(
            shapes = MaterialTheme.shapes,
            content = content
        )
    }
}