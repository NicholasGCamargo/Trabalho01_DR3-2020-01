package com.example.tp03dr3_firebaseauthefirestore

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tp03dr3_firebaseauthefirestore.classes.FirebaseClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_deletar_editar.*

class DeletarEditarActivity : AppCompatActivity() {

    private lateinit var db: FirebaseFirestore
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_deletar_editar)

        val firebaseClass = intent.getSerializableExtra("dado") as FirebaseClass

        editarEmailEditText.setText(firebaseClass.email)
        editarNumeroEditText.setText(firebaseClass.numero)
        editarNomeEditText.setText(firebaseClass.nome)

       db = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()

        btnEditarInfos.setOnClickListener { v ->
            val mapToPass = mapOf(
                "nome" to editarNomeEditText.text.toString(),
                "email" to editarEmailEditText.text.toString(),
                "numero" to editarNumeroEditText.text.toString()
            )
            db.collection("users/${mAuth.currentUser?.uid}/contato")
                .document(firebaseClass.callId)
                .update(mapToPass)
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Dados atualizados com sucesso.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener {
                    Log.e("Erro ao dar update", it.message!!)
                    Toast.makeText(
                        this,
                        "Ocorreu algum erro ao atualizar suas informações.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }

        btnDeletarContato.setOnClickListener {
            db.collection("users/${mAuth.currentUser?.uid}/contato")
                .document(firebaseClass.callId)
                .delete()
                .addOnFailureListener {
                    Toast.makeText(
                        this,
                        "Informações não foram deletadas",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnSuccessListener {
                    Toast.makeText(
                        this,
                        "Infos deletadas com sucesso.",
                        Toast.LENGTH_SHORT
                    ).show()
                    finish()
                }
        }

        btnVoltarEditarDeletar.setOnClickListener {
            finish()
        }
    }
}
