package com.example.tipcalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.tipcalculator.ui.theme.TipCalculatorTheme
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.material3.TextField
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.KeyboardType
import java.text.NumberFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TipCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TipCalculatorApp()
                }
            }
        }
    }
}


@Composable
fun TipCalculatorApp(modifier: Modifier =Modifier){

    var tipAmount by remember {mutableStateOf("")}
    var tipPercentage by remember {
        mutableStateOf("")
    }
    val calculatedTip = tipAmount.toDoubleOrNull() ?:0.0
    val percentage =tipPercentage.toDoubleOrNull() ?:0.0
    val tip = CalculateTip(calculatedTip, percentage)

    Column (
        modifier = modifier
            .padding(40.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
            ){
            Text(
                stringResource(id = R.string.tip_amount_label, "$00.0"),
                modifier = Modifier
                    .padding(
                        bottom = 16.dp

                    )
                    .align(alignment = Alignment.Start)
            )
//        TextField(value = tipAmount, onValueChange = {tipAmount =it} )
        EditNumberField(
            tipAmount,
            onValueChange ={tipAmount=it},
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    bottom = 32.dp
                )
        )

        EditTipPercentage(
            value = tipPercentage,
            onValueChange = {tipPercentage =it},
            modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 32.dp
            ))

        Text(
            text = stringResource(id = R.string.tip_label,tip),
            style =MaterialTheme.typography.displaySmall
        )
        Spacer(modifier =Modifier.height(40.dp))

    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditNumberField(
    value:String,
    onValueChange: (String) ->Unit,
    modifier: Modifier =Modifier
) {
    /*
    * or use
    * var tip =CalculateTip(tipAmount.toDoubleOrNull())
     */
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        label ={Text(text= stringResource(id = R.string.tip_amount))},
        singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditTipPercentage(
    value:String,
    onValueChange: (String) -> Unit,
    modifier: Modifier =Modifier
){
    TextField(
        value = value,
        onValueChange = onValueChange,
        singleLine = true,
        label = { Text(text = stringResource(id = R.string.custom_tip))},
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
        modifier =Modifier
    )

}

fun CalculateTip(amount:Double, tipPercentage:Double =15.0): String{
    val tipAmount =(amount/100) * tipPercentage
    return NumberFormat.getCurrencyInstance().format(tipAmount)
}

@Preview(showBackground = true)
@Composable
fun TipCaluculatorPreview() {
    TipCalculatorTheme {
        TipCalculatorApp()
    }
}
