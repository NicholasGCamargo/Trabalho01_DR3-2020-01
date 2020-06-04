package com.example.tp03dr3_firebaseauthefirestore.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.tp03dr3_firebaseauthefirestore.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment() {

    private lateinit var mAuth: FirebaseAuth
    private lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        mAuth = FirebaseAuth.getInstance()
        db = FirebaseFirestore.getInstance()

        val root = inflater.inflate(R.layout.fragment_home, container, false)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        btnCriarDadoHome.setOnClickListener {
            val nome = editTextNomeFragmentHome.text.toString()
            val email = editTextEmailFragmentHome.text.toString()
            val numero = editTextTelFragmentHome.text.toString()


            editTextNomeFragmentHome.setText("")
            editTextEmailFragmentHome.setText("")
            editTextTelFragmentHome.setText("")

            if(nome.isNullOrEmpty() || email.isNullOrEmpty() || numero.isNullOrEmpty()){
                Toast.makeText(
                    context,
                    "Por favor preencha todos os campos",
                    Toast.LENGTH_SHORT
                ).show()
            }else{
                //inserir os dados no firestore
                val dados = mapOf("nome" to nome, "email" to email, "numero" to numero)
                db.collection("users/${mAuth.currentUser?.uid}/contato")
                    .add(dados)
                    .addOnSuccessListener {
                        Log.d("Funcionou", "foi.")
                        Toast.makeText(
                            context,
                            "Tudo certo :^)",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    .addOnFailureListener {
                        Log.d("Deu ruim", "Ocorreu algum erro. ${it.message!!}")
                        Toast.makeText(
                            context,
                            "Seus dados não foram inseridos",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
            }
        }
    }
}
