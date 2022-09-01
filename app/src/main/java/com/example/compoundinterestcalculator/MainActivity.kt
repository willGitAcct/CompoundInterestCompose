package com.example.compoundinterestcalculator

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.compoundinterestcalculator.ui.theme.CompoundInterestCalculatorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompoundInterestCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    Calc()
                }
            }
        }
    }
}

@Composable
fun Calc() {

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        Alignment.CenterHorizontally
    ) {
        Text(text = "Compound Interest Calculator!\nWatch how your investment can grow.")

        //principal
        val principalState = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = principalState.value,
            onValueChange = { principalState.value = it },
            label = { Text(text = "Initial Amount") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

        //years
        val yearState = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = yearState.value,
            onValueChange = { yearState.value = it },
            label = { Text(text = "Investment Years") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )

        //interest rate
        val interestState = remember { mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = interestState.value,
            onValueChange = { interestState.value = it },
            label = { Text(text = "Estimated Interest Rate") },
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
        )

        Text("Compound Frequency")

        val radioOptions = listOf("Daily", "Weekly", "Monthly", "Quarterly", "Yearly")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[3]) }
        var compoundPeriod = 0
        if (selectedOption.equals("Daily"))
            compoundPeriod = 365
        else if (selectedOption.equals("Weekly"))
            compoundPeriod = 52
        else if (selectedOption.equals("Monthly"))
            compoundPeriod = 12
        else if (selectedOption.equals("Quarterly"))
            compoundPeriod = 4
        else
            compoundPeriod = 1

        var finalAmt: Double = 0.0

        Row {
            // below line is use to set data to
            // each radio button in columns.
            radioOptions.forEach { text ->
                Column(
                    Modifier
                        .selectable(
                            // this method is called when
                            // radio button is selected.
                            selected = (text == selectedOption),

                            onClick = { onOptionSelected(text) }
                        )


                ) {

                    //generate Radio buttons
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                        })

                    Text(
                        text = text,
                        fontSize = 12.sp
                        //modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
        Text(text = "Your Projected total is: ")
        var total = remember { mutableStateOf(0.0) }

        Text(text = "$" + "%.2f".format(total.value), fontSize = 28.sp)
        Button(
            onClick = {
                var principal: Int
                var years: Int
                var rates: Double
                //var compound: Int

                principal = Integer.parseInt(principalState.value.text)
                years = Integer.parseInt(yearState.value.text)
                rates = interestState.value.text.toDouble()
                //compound = Integer.parseInt(selectedOption)


                //*** calculation***
                finalAmt = principal * (Math.pow(
                    (1 + ((rates / 100) / compoundPeriod)),
                    (compoundPeriod * years).toDouble()
                ))
                total.value = finalAmt
                println("%.2f".format(finalAmt))
                println(rates)
                println(years)
                println(principal)
                println(compoundPeriod)


            },
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color(0xFF4974a5),
                contentColor = Color.White
            )
        ) {
            Text(
                text = "Calculate"
            )

        }


    }//end col


}

    @Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompoundInterestCalculatorTheme {
        Calc()
    }
}