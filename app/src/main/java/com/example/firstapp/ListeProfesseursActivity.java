package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.firstapp.model.Professeur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListeProfesseursActivity extends AppCompatActivity {

    LinkedList<Professeur> profs;
    EditText search;
    ListView list_prof;
    FloatingActionButton fab;

    public ProgressDialog mProgressDialog;

    FirebaseFirestore db;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_professeurs);
         db = FirebaseFirestore.getInstance();

        search=findViewById(R.id.search);
        list_prof=findViewById((R.id.list_prof));
        fab=findViewById(R.id.add_prof);

        profs=new LinkedList<Professeur>();
        getallProfesseurs();

    }

    void getallProfesseurs(){

        showProgressDialog();
        db.collection("professeur")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Professeur p= new Professeur(document.getString("nom"),
                                        document.getString("prenom"),
                                        document.getString("tel"),
                                        document.getString("photo"),
                                        document.getString("departement"));
                                profs.add(p);
                            }
                            System.out.println(profs);
                        } else {
                            System.out.println("Erreur");
                        }

                        hideProgressDialog();
                        ArrayList<String> noms_prof = new ArrayList<String>();
                        for (Professeur prof: profs){
                            noms_prof.add(prof.getNom());
                        }

                        // This is the array adapter, it takes the context of the activity as a
                        // first parameter, the type of list view as a second parameter and your
                        // array as a third parameter.
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListeProfesseursActivity.this,android.R.layout.simple_list_item_1,noms_prof );
                        list_prof.setAdapter(arrayAdapter);


                    }
                });




    }




    public void showProgressDialog() {
        if (mProgressDialog == null) {
            mProgressDialog = new ProgressDialog(this);
            mProgressDialog.setMessage(getString(R.string.loading));
            mProgressDialog.setIndeterminate(true);
        }

        mProgressDialog.show();
    }

    public void hideProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.dismiss();
        }
    }
}