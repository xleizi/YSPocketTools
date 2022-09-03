package com.lei123.YSPocketTools.ui.widgets

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.lei123.YSPocketTools.R
import com.lei123.YSPocketTools.utils.loadBoolean
import com.lei123.YSPocketTools.utils.loadFloat
import com.lei123.YSPocketTools.utils.loadString

class widgetSettingSimpleActivityViewModel : ViewModel(){

    var id by mutableStateOf("123456789")
    var uid by mutableStateOf("123456789")
    var theme by mutableStateOf("Light")
    var whichItem by mutableStateOf("resin")
    var minickname by mutableStateOf("芭芭拉拉粑粑")
    var Zoom by mutableStateOf(1f)
    var timeShow by mutableStateOf(true)
    var nicknameShow by mutableStateOf(true)
    var iconStyle by mutableStateOf("simple")
    var widgetClass by mutableStateOf("transparencySimple22")
    var simpleitem1 by mutableStateOf("resin")
    var simpleitem2 by mutableStateOf("homecoin")
    var simpleitem3 by mutableStateOf("daily")
    var simpleitem4 by mutableStateOf("explore")
    var ifitemselect1 by mutableStateOf(false)
    var ifitemselect2 by mutableStateOf(false)
    var ifitemselect3 by mutableStateOf(false)
    var ifitemselect4 by mutableStateOf(false)
    var ifitemselect5 by mutableStateOf(false)
    var ifitemselect6 by mutableStateOf(false)

    var ifitemselecticon1 by mutableStateOf(R.drawable.w_simple_select5)
    var ifitemselecticon2 by mutableStateOf(R.drawable.w_simple_select5)
    var ifitemselecticon3 by mutableStateOf(R.drawable.w_simple_select5)
    var ifitemselecticon4 by mutableStateOf(R.drawable.w_simple_select5)
    var ifitemselecticon5 by mutableStateOf(R.drawable.w_simple_select5)
    var ifitemselecticon6 by mutableStateOf(R.drawable.w_simple_select5)


}