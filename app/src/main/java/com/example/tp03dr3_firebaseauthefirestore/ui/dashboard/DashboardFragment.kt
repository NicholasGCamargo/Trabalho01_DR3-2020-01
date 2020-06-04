package com.example.tp03dr3_firebaseauthefirestore.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp03dr3_firebaseauthefirestore.R
import com.example.tp03dr3_firebaseauthefirestore.adapter.MeuAdapter
import com.example.tp03dr3_firebaseauthefirestore.classes.FirebaseClass
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.fragment_dashboard.*

class DashboardFragment : Fragment() {

    lateinit var mAuth: FirebaseAuth
    lateinit var db: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_dashboard, container, false)

        db = FirebaseFirestore.getInstance()
        mAuth = FirebaseAuth.getInstance()

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db.collection("users/${mAuth.currentUser?.uid}/contato")
            .get()
            .addOnCompleteListener { task ->
                if (task.isSuccessful){
                    task.addOnSuccessListener { querySnapshot ->
                        Toast.makeText(
                            context,
                            "Seus dados foram pegos com sucesso.",
                            Toast.LENGTH_SHORT
                        ).show()

                        val passar = mutableListOf<FirebaseClass>()

                        querySnapshot.documents.forEach {
                            passar.add(FirebaseClass(it["nome"].toString(), it["email"].toString(), it["numero"].toString()))
                        }

                        rcyVwDashboard.adapter = MeuAdapter(passar as List<FirebaseClass>)
                        rcyVwDashboard.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

                    }.addOnFailureListener {
                        Toast.makeText(
                            context,
                            "Algo aconteceu de errado.",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }else{
                    Toast.makeText(
                        context,
                        "Algo aconteceu de errado.",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

    }
}
