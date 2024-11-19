package ro.simona.userstestproject.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ro.simona.userstestproject.R

@Composable
fun ErrorView(errorMessage: String, onRetry: () -> Unit) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = errorMessage,
            style = TextStyle(color = Color.Red, fontWeight = FontWeight.Bold, fontSize = 18.sp),
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Button(
            onClick = onRetry,
            colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(id = R.color.app_red),
                contentColor = colorResource(id = R.color.white)
            ),
        ) {
            Text(text = stringResource(id = R.string.retry))
        }
    }
}