package com.example.tp03dr3_firebaseauthefirestore

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mAuth = FirebaseAuth.getInstance()

    }

    override fun onStart() {
        super.onStart()

        if(mAuth.currentUser != null){
            //Usuario ja conectado
        }else{
            //Usuario não conectado
            criarContaBtnHome.setOnClickListener {
                val email = emailEditTextHome.text.toString()
                val senha = passwordEditTextHome.text.toString()

                passwordEditTextHome.setText("")

                mAuth.createUserWithEmailAndPassword(email,senha)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(
                                this,
                                "Conta Criada",
                                Toast.LENGTH_SHORT
                            ).show()
                        }else {
                            Log.w("ERROR CREATE USER", "Failure", it.exception)
                            Toast.makeText(
                                this,
                                "sua conta não foi criada",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }

            logarContaBtnHome.setOnClickListener {

                val email = emailEditTextHome.text.toString()
                val senha = passwordEditTextHome.text.toString()

                mAuth.signInWithEmailAndPassword(email,senha)
                    .addOnCompleteListener {
                        if(it.isSuccessful){
                            Toast.makeText(
                                this,
                                "Bem Vindo.",
                                Toast.LENGTH_SHORT
                            ).show()
//                            startActivity()
                        }else{
                            Toast.makeText(
                                this,
                                "Bem Vindo.",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
            }
        }
    }
}
