package com.example.googleauth.presentation.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.example.googleauth.BuildConfig
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions

class Home: Screen{


    @Composable
    override fun Content() {
        val context = LocalContext.current
        val navigator = LocalNavigator.current

        fun SignOut(context: Context,onComplete:() -> Unit){
            val GoogleSignInOptions =
                GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                    .requestIdToken(BuildConfig.web_token)
                    .requestEmail()
                    .build()

            val googleSignInClient =
                GoogleSignIn.getClient(context,GoogleSignInOptions)

            googleSignInClient.signOut().addOnCompleteListener { task ->
                if (task.isSuccessful){

                    Toast.makeText(context,"Signed out successfully!", Toast.LENGTH_SHORT).show()
                    onComplete()
                }

            }

        }




        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxSize()
        ) {
            Text("Welcome home",
                color = MaterialTheme.colorScheme.surface,
                modifier = Modifier.clickable(onClick = {navigator?.popUntilRoot()})
            )
            Spacer(Modifier.height(10.dp))
            Button(
                onClick = {
                    SignOut(context) {
                        navigator?.replace(LoginScreen())
                    }
                }

            ) {
                Text("SIGN OUT")
            }


        }
    }
}