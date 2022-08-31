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
fun Calc(){

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ){
        Text(text = "Compound Interest Calculator!\nWatch how your investment can grow.")

        //principal
        val principalState = remember{ mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = principalState.value,
            onValueChange = {principalState.value = it},
            label = {Text(text= "Initial Amount")},
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number))

        //years
        val yearState = remember{ mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = yearState.value,
            onValueChange = {yearState.value = it},
            label = {Text(text= "Investment Years")},
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number))

        //interest rate
        val interestState = remember{ mutableStateOf(TextFieldValue()) }
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = interestState.value,
            onValueChange = {interestState.value = it},
            label = {Text(text= "Estimated Interest Rate")},
            colors = TextFieldDefaults.textFieldColors(
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            shape = RoundedCornerShape(8.dp),
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),)

        Text("Compound Frequency")
//        Row {
//            val checkedStateDaily = remember { mutableStateOf(false) }
//            Text("Daily", modifier = Modifier.padding(2.dp), fontSize = 14.sp)
//
//            Checkbox(
//                checked = checkedStateDaily.value,
//                onCheckedChange = { checkedStateDaily.value = it },
//                modifier = Modifier.padding(2.dp),
//                colors = CheckboxDefaults.colors(Color.Blue)
//            )
//
//            val checkedStateWeekly = remember { mutableStateOf(false) }
//            Text("Weekly", modifier = Modifier.padding(2.dp), fontSize = 14.sp)
//
//            Checkbox(
//                checked = checkedStateWeekly.value,
//                onCheckedChange = { checkedStateWeekly.value = it },
//                modifier = Modifier.padding(2.dp),
//                colors = CheckboxDefaults.colors(Color.Blue)
//            )
//
//            val checkedStateMonthly = remember { mutableStateOf(false) }
//            Text("Monthly", modifier = Modifier.padding(2.dp), fontSize = 14.sp)
//
//            Checkbox(
//                checked = checkedStateMonthly.value,
//                onCheckedChange = { checkedStateMonthly.value = it },
//                modifier = Modifier.padding(2.dp),
//                colors = CheckboxDefaults.colors(Color.Blue)
//            )
//
//            val checkedStateAnnually = remember { mutableStateOf(false) }
//            Text("Yearly", modifier = Modifier.padding(2.dp), fontSize = 14.sp)
//
//            Checkbox(
//                checked = checkedStateAnnually.value,
//                onCheckedChange = { checkedStateAnnually.value = it },
//                modifier = Modifier.padding(4.dp),
//                colors = CheckboxDefaults.colors(Color.Blue)
//            )
//        }
//
        val radioOptions = listOf("Daily", "Weekly", "Monthly", "Yearly")
        val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[3]) }

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
                                // below method is called on
                                // clicking of radio button.
                                onClick = { onOptionSelected(text) }
                            )


                    ) {
                        // below line is use to get context.
                        val context = LocalContext.current

                        // below line is use to
                        // generate radio button
                        RadioButton(
                            // inside this method we are
                            // adding selected with a option.
                            selected = (text == selectedOption),

                            onClick = {
                                // inside on click method we are setting a
                                // selected option of our radio buttons.
                                onOptionSelected(text)

                            }
                        )
                        // below line is use to add
                        // text to our radio buttons.
                        Text(
                            text = text,
                            fontSize = 12.sp
                            //modifier = Modifier.padding(start = 16.dp)
                        )
                    }}
        Button(
            onClick = {
                },
            modifier = Modifier
                .fillMaxWidth(),
            contentPadding = PaddingValues(16.dp),
            colors = ButtonDefaults.buttonColors(
                backgroundColor = Color( 0xFF4974a5),
                contentColor = Color.White
            ) ) {
            Text(
                text = "Calculate"
            )

        }

    }//end columnscope

    fun Calculate(){
        val principal: Int
        val years: Int
        val rates: Float
        val compound: Boolean
    }
}}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    CompoundInterestCalculatorTheme {
        Calc()
    }
}