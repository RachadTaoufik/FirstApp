package com.example.firstapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.app.FragmentManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.firstapp.model.Professeur;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.LinkedList;

public class ListeProfesseursActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

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
        list_prof.setOnItemClickListener(this);
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


                        // Exercice: a Modifier en se basant sur BaseAdapter ==> vois la calsse MyAdapter
                        //pour recuperer la photo a partir de Firebase
                        //Ajouter les dependances Strorage: Utiliser l'Assistant Firebase de android Studio
                        //Ajouter la librairie Glide : https://guides.codepath.com/android/Displaying-Images-with-the-Glide-Library

                       /*
                        ArrayList<String> noms_prof = new ArrayList<String>();
                        for (Professeur prof: profs){
                            noms_prof.add(prof.getPrenom()+" "+prof.getNom());
                        }

                        // This is the array adapter, it takes the context of the activity as a
                        // first parameter, the type of list view as a second parameter and your
                        // array as a third parameter.
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(ListeProfesseursActivity.this,android.R.layout.simple_list_item_1,noms_prof );
                        list_prof.setAdapter(arrayAdapter);
                        */

                        ListAdapter myadapter= new MyAdapter();
                        list_prof.setAdapter(myadapter);
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent myIntent= new Intent(this, ProfDetailActivity.class);
        myIntent.putExtra("professeur",profs.get(i));
        startActivity(myIntent);
    }


    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return profs.size();
        }

        @Override
        public Professeur getItem(int i) {
            return profs.get(i);
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = getLayoutInflater().inflate(R.layout.list_item, container, false);
            }

            ((TextView) convertView.findViewById(R.id.prof_nom))
                    .setText(getItem(position).getPrenom()+" "+getItem(position).getNom());

            ((TextView) convertView.findViewById(R.id.prof_dep))
                    .setText("Departement "+getItem(position).getDepartement());



            // Reference to an image file in Cloud Storage
            //StorageReference storageReference = FirebaseStorage.getInstance().getReference(getItem(position).getPhoto());
            StorageReference storageReference = FirebaseStorage.getInstance().getReference().child(getItem(position).getPhoto());

            // Download directly from StorageReference using Glide
            // (See MyAppGlideModule for Loader registration)


            Glide.with(ListeProfesseursActivity.this)
                    .load(storageReference)
                    .override(70, 70)
                    .into(((ImageView) convertView.findViewById(R.id.prof_photo)));

            return convertView;
        }
    }





}

