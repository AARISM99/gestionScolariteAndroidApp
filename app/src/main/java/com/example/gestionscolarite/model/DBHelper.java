package com.example.gestionscolarite.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(@Nullable Context context) {

        super(context, "gestion_scolarite", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String query_filiere = "create table filiere (id integer primary key,code_filiere text, nom_filiere text,FOREIGN KEY(id_niveau) REFERENCES niveau(id))";
        String query_module = "create table module (id integer primary key,code_module text, nom_module text, coef_module integer,FOREIGN KEY(id_niveau) REFERENCES niveau(id),FOREIGN KEY(id_filiere) REFERENCES filiere(id))";
        String query_niveau = "create table niveau (id integer primary key,code_niveau text, titre_niveau text)";
        String query_etudiant = "create table etudiant (id integer primary key,code_etudiant text, nom_etudiant text, prenom_etudiant text, date_naissance text )";
        String query_evaluation = "create table evaluation (id integer primary key,note real, date text,FOREIGN KEY(id_etudiant) REFERENCES etudiant(id), FOREIGN KEY(id_module) REFERENCES module(id))";
        String query_inscription = "create table inscription (id integer primary key,FOREIGN KEY(id_etudiant) REFERENCES etudiant(id), FOREIGN KEY(id_filiere) REFERENCES filiere(id), FOREIGN KEY(id_niveau) REFERENCES niveau(id))";


        db.execSQL(query_filiere);
        db.execSQL(query_module);
        db.execSQL(query_niveau);
        db.execSQL(query_etudiant);
        db.execSQL(query_evaluation);
        db.execSQL(query_inscription);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        //supprimer les tables s elles existenet deja
        db.execSQL("drop table if exists filiere ");
        db.execSQL("drop table if exists module ");
        db.execSQL("drop table if exists niveau ");
        db.execSQL("drop table if exists etudiant ");
        db.execSQL("drop table if exists evaluation ");
        db.execSQL("drop table if exists inscriprion ");


        //fait appel a la fonction oncreate pour creer les tables
        onCreate(db);

    }

    public void insertEtudiant(Etudiant e){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("code_etudiant",e.getCode_etudiant());
        cv.put("nom_etudiant",e.getNom_etudiant());
        cv.put("prenom_etudiant",e.getPrenom_etudiant());
        cv.put("date_naissance",e.getDate_naissance());

        db.insert("Etudiant",null,cv);
        db.close();
    }

    public void updateEtudiant(Etudiant e){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("code_etudiant",e.getCode_etudiant());
        cv.put("nom_etudiant",e.getNom_etudiant());
        cv.put("prenom_etudiant",e.getPrenom_etudiant());
        cv.put("date_naissance",e.getDate_naissance());

        db.update("Etudiant",cv,"_id=?", new String []{String.valueOf(e.getId())});
        db.close();

    }

    public void deleteEtudiant(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Etudiant","_id=?",new String[]{String.valueOf(id)});

    }

    public Cursor getAllEtudiants(){
        SQLiteDatabase db = this.getReadableDatabase();

        //la requete retourne un resultat (cursor)
        //execSQL utilisee pour les requete qui ne retourne pas de resultats

        Cursor c = db.rawQuery("SELECT * FROM ETUDIANT",null);
        return  c;
    }

    public Etudiant getSelectedEtudiant(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "Etudiant",
                new String[]{"_id","code_etudiant","nom_etudiant","prenom_etudiant","date_naissance"},
                "_id=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        c.moveToFirst();
        Etudiant E = new Etudiant(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));

        return E;
    }
}
