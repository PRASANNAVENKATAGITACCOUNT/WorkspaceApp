package com.learning.auth_ui


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.learning.auth_ui.ui.theme.ComposeNewAppTheme

@Composable
fun UserAuthScreen(
    user: User = User(),
    onUserEmailChange:(String)-> Unit={},
    onSuccessfulLogin:()-> Unit={}
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {innerPadding->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Column(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Spacer(
                    Modifier.height(10.dp)
                )
                Text(
                    text = "Log In",
                    style = TextStyle(
                        fontSize = 35.sp,
                        fontWeight = FontWeight.Bold,
                        )
                )
                Spacer(
                    Modifier.height(10.dp)
                )
                AuthButton(name = "CONTINUE WITH GOOGLE") {
                    onSuccessfulLogin.invoke()
                }
                Spacer(
                    Modifier.height(25.dp)
                )
                HorizontalDivider(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(5.dp),
                    thickness = 1.5.dp
                )
                Spacer(
                    Modifier.height(25.dp)
                )
                OutlinedTextField(
                    value = user.userEmail,
                    onValueChange = onUserEmailChange,
                    label = {Text(text = "Enter Your Email")},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(65.dp)
                )
                Spacer(
                    Modifier.height(10.dp)
                )
                Button(
                    onClick = onSuccessfulLogin
                ) {
                    Text(text = "Continue With Email")
                }
                Spacer(
                    Modifier.height(8.dp)
                )

                Text(
                    text = "Forgot Password?",
                    style = TextStyle(
                        fontSize = 15.sp,
                        textDecoration = TextDecoration.Underline
                    )
                )

            }

        }
    }
}


@Composable
fun AuthButton(@DrawableRes icon:Int= R.drawable.google_square_icon,
               name: String,
               onClickListener:()-> Unit) {

        Row (modifier = Modifier
                .padding(5.dp)
                .clickable{onClickListener.invoke()}
                .fillMaxWidth(0.8f)
                .border(
                    width = 2.dp,
                    shape = RoundedCornerShape(12.dp),
                    color = Color.LightGray,
                )
                .background(color = Color.White)
                .height(55.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
        ) {
            Image(
                painter = painterResource(icon),
                modifier = Modifier
                    .size(40.dp)
                    .padding(5.dp),
                contentDescription="")
            Text(
                text = name,
                modifier = Modifier.wrapContentSize(),
                style = TextStyle(
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            )
        }

}

@Preview(showBackground = true)
@Composable
fun UserAuthPreview() {
    ComposeNewAppTheme {
        UserAuthScreen()
    }
}