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


        String query_module = "create table module (id integer primary key, code_module text, nom_module text, id_niveau integer, id_filiere integer, FOREIGN KEY(id_niveau) REFERENCES niveau(id),FOREIGN KEY(id_filiere) REFERENCES filiere(id))";
        String query_niveau = "create table niveau (id integer primary key, code_niveau text, titre_niveau text)";
        String query_filiere = "create table filiere (id integer primary key, code_filiere text , nom_filiere text , id_niveau integer,  FOREIGN KEY (id_niveau) REFERENCES niveau(id))";
        String query_etudiant = "create table etudiant ( id integer primary key,code_etudiant text, nom_etudiant text, prenom_etudiant text, date_naissance text )";
        String query_evaluation = "create table evaluation (id integer primary key,id_etudiant integer, id_module integer, note real, date text,FOREIGN KEY (id_etudiant) REFERENCES etudiant(id), FOREIGN KEY(id_module) REFERENCES module(id))";
        String query_inscription = "create table inscription (id integer primary key, id_etudiant integer ,id_filiere integer, id_niveau integer, date_inscription text,FOREIGN KEY (id_etudiant) REFERENCES etudiant(id), FOREIGN KEY (id_filiere) REFERENCES filiere(id), FOREIGN KEY(id_niveau) REFERENCES niveau(id))";


        db.execSQL(query_etudiant);
        db.execSQL(query_niveau);
        db.execSQL(query_filiere);
        db.execSQL(query_module);
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
        db.execSQL("drop table if exists inscription ");


        //fait appel a la fonction oncreate pour creer les tables
        onCreate(db);

    }


    // Etudiant CRUD

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

        db.update("Etudiant",cv,"id=?", new String []{String.valueOf(e.getId())});
        db.close();

    }

    public void deleteEtudiant(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Etudiant","id=?",new String[]{String.valueOf(id)});

    }

    public Cursor getAllEtudiants(){
        SQLiteDatabase db = this.getReadableDatabase();

        //la requete retourne un resultat (cursor)
        //execSQL utilisee pour les requetes qui ne retourne pas de resultats

        Cursor c = db.rawQuery("SELECT rowid _id , * FROM ETUDIANT",null);

        return  c;
    }

    public Etudiant getSelectedEtudiant(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "Etudiant",
                new String[]{"rowid _id","code_etudiant","nom_etudiant","prenom_etudiant","date_naissance"},
                "_id=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        c.moveToFirst();

        System.out.println("id == " + c.getInt(0)
        + "\ncode == " + c.getString(1)
        + "\nnom == " + c.getString(2)
        + "\nprenom == " + c.getString(3)
        + "\ndate == " + c.getString(4));

        Etudiant E = new Etudiant(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));

        return E;
    }

    public Etudiant getEtudiantByCNE(String cne){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "Etudiant",
                new String[]{"rowid _id","code_etudiant","nom_etudiant","prenom_etudiant","date_naissance"},
                "code_etudiant=?",
                new String[]{String.valueOf(cne)},
                null,
                null,
                null
        );
        c.moveToFirst();

        System.out.println("id == " + c.getInt(0)
                + "\ncode == " + c.getString(1)
                + "\nnom == " + c.getString(2)
                + "\nprenom == " + c.getString(3)
                + "\ndate == " + c.getString(4));

        Etudiant E = new Etudiant(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4));

        return E;
    }

    // Niveau CRUD

    public void insertNiveau(Niveau n){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("code_niveau",n.getCode_niveau());
        cv.put("titre_niveau",n.getTitre_niveau());

        db.insert("Niveau",null,cv);
        db.close();
    }

    public void updateNiveau(Niveau n){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("code_niveau",n.getCode_niveau());
        cv.put("titre_niveau",n.getTitre_niveau());

        db.update("Niveau",cv,"id=?", new String []{String.valueOf(n.getId())});
        db.close();

    }

    public void deleteNiveau(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Niveau","id=?",new String[]{String.valueOf(id)});

    }

    public Cursor getAllNiveaux(){
        SQLiteDatabase db = this.getReadableDatabase();

        //la requete retourne un resultat (cursor)
        //execSQL utilisee pour les requetes qui ne retourne pas de resultats

        Cursor c = db.rawQuery("SELECT rowid _id , * FROM Niveau",null);

        return  c;
    }

    public Niveau getSelectedNiveau(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "Niveau",
                new String[]{"rowid _id","code_niveau","titre_niveau"},
                "_id=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );

        c.moveToFirst();
        Niveau N = new Niveau(c.getInt(0),c.getString(1),c.getString(2));

        return N;
    }


    // Filiere CRUD


    public void insertFiliere(Filiere f){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("code_filiere",f.getCode_filiere());
        cv.put("nom_filiere",f.getNom_filiere());
        cv.put("id_niveau",f.getNiveau().getId());

        db.insert("Filiere",null,cv);
        db.close();
    }

    public void updateFiliere(Filiere f){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("code_filiere",f.getCode_filiere());
        cv.put("nom_filiere",f.getNom_filiere());
        cv.put("id_niveau",f.getNiveau().getId());

        db.update("Filiere",cv,"id=?", new String []{String.valueOf(f.getId())});
        db.close();

    }

    public void deleteFiliere(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Filiere","id=?",new String[]{String.valueOf(id)});

    }

    public Cursor getAllFilieres(){
        SQLiteDatabase db = this.getReadableDatabase();

        //la requete retourne un resultat (cursor)
        //execSQL utilisee pour les requetes qui ne retourne pas de resultats

        Cursor c = db.rawQuery("SELECT rowid _id , * FROM Filiere",null);

        return  c;
    }

    public Filiere getSelectedFiliere(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "Filiere",
                new String[]{"rowid _id","code_filiere","nom_filiere","id_niveau"},
                "_id=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        Niveau n = new Niveau();
        c.moveToFirst();
        n.setId(c.getInt(3));
        Filiere F = new Filiere(c.getInt(0),c.getString(1),c.getString(2),n);

        return F;
    }


    // Module CRUD

    public void insertModule(Module m){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("code_module",m.getCode_module());
        cv.put("nom_module",m.getTitre_module());
        cv.put("id_niveau",m.getNiveau().getId());
        cv.put("id_filiere",m.getFiliere().getId());


        db.insert("Module",null,cv);
        db.close();
    }

    public void updateModule(Module m){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("code_module",m.getCode_module());
        cv.put("nom_module",m.getTitre_module());
        cv.put("id_niveau",m.getNiveau().getId());
        cv.put("id_filiere",m.getFiliere().getId());

        db.update("Module",cv,"id=?", new String []{String.valueOf(m.getId())});
        db.close();

    }

    public void deleteModule(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Module","id=?",new String[]{String.valueOf(id)});

    }

    public Cursor getAllModules(){
        SQLiteDatabase db = this.getReadableDatabase();

        //la requete retourne un resultat (cursor)
        //execSQL utilisee pour les requetes qui ne retourne pas de resultats

        Cursor c = db.rawQuery("SELECT rowid _id , * FROM Module",null);

        return  c;
    }

    public Module getSelectedModule(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "Module",
                new String[]{"rowid _id","code_module","nom_module","id_niveau","id_filiere"},
                "_id=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        Niveau n = new Niveau();
        Filiere f = new Filiere();
//        System.out.println("selected_id == " + c.getString(4));
//        n.setId(c.getInt(4));
        c.moveToFirst();
        n.setId(c.getInt(3));
        f.setId(c.getInt(4));
        Module M = new Module(c.getInt(0),c.getString(1),c.getString(2),n,f);

        return M;
    }


    // Evaluation CRUD

    public void insertInscription(Inscription i){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("id_niveau",i.getNiveau().getId());
        cv.put("id_filiere",i.getFiliere().getId());
        cv.put("id_etudiant",i.getEtudiant().getId());
        cv.put("date_inscription",i.getDate());

        db.insert("Inscription",null,cv);
        db.close();

    }

    public void updateInscription(Inscription i){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("id_niveau",i.getNiveau().getId());
        cv.put("id_filiere",i.getFiliere().getId());
        cv.put("id_etudiant",i.getEtudiant().getId());
        cv.put("date_inscription",i.getDate());


        db.update("Inscription",cv,"id=?", new String []{String.valueOf(i.getId())});
        db.close();


    }

    public void deleteInscription(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Inscription","id=?",new String[]{String.valueOf(id)});

    }

    public Cursor getAllInscriptions(){
        SQLiteDatabase db = this.getReadableDatabase();

        //la requete retourne un resultat (cursor)
        //execSQL utilisee pour les requetes qui ne retourne pas de resultats

        Cursor c = db.rawQuery("SELECT rowid _id , * FROM Inscription",null);

        return  c;
    }

    public Inscription getSelectedInscription(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "Inscription",
                new String[]{"rowid _id","id_etudiant","id_filiere","id_niveau","date_inscription"},
                "_id=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        Etudiant e = new Etudiant();
        Filiere f = new Filiere();
        Niveau n = new Niveau();
//        System.out.println("selected_id == " + c.getString(4));
//        n.setId(c.getInt(4));
        c.moveToFirst();
        e.setId(c.getInt(1));
        f.setId(c.getInt(2));
        n.setId(c.getInt(3));

        Inscription I = new Inscription(c.getInt(0),n,f,e,c.getString(4));

        return I;
    }


    public Inscription getInscriptionByEtudiant(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "Inscription",
                new String[]{"rowid _id","id_etudiant","id_filiere","id_niveau","date_inscription"},
                "id_etudiant=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        Etudiant e = new Etudiant();
        Filiere f = new Filiere();
        Niveau n = new Niveau();
//        System.out.println("selected_id == " + c.getString(4));
//        n.setId(c.getInt(4));
        c.moveToFirst();
        e.setId(c.getInt(1));
        f.setId(c.getInt(2));
        n.setId(c.getInt(3));

        Inscription I = new Inscription(c.getInt(0),n,f,e,c.getString(4));

        return I;
    }


    // Inscription CRUD

    public void insertEvaluation(Evaluation e){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("id_etudiant",e.getEtudiant().getId());
        cv.put("id_module",e.getModule().getId());
        cv.put("note",e.getNote());
        cv.put("date",e.getDate_evaluation());

        db.insert("Evaluation",null,cv);
        db.close();

    }

    public void updateEvaluation(Evaluation e){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues cv = new ContentValues();

        cv.put("id_etudiant",e.getEtudiant().getId());
        cv.put("id_module",e.getModule().getId());
        cv.put("note",e.getNote());
        cv.put("date",e.getDate_evaluation());


        db.update("Evaluation",cv,"id=?", new String []{String.valueOf(e.getId())});
        db.close();

    }

    public void deleteEvaluation(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete("Evaluation","id=?",new String[]{String.valueOf(id)});

    }

    public Cursor getAllEvaluations(){
        SQLiteDatabase db = this.getReadableDatabase();

        //la requete retourne un resultat (cursor)
        //execSQL utilisee pour les requetes qui ne retourne pas de resultats

        Cursor c = db.rawQuery("SELECT rowid _id , * FROM Evaluation",null);

        return  c;
    }

    public Evaluation getSelectedEvaluation(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.query(
                "Evaluation",
                new String[]{"rowid _id","id_etudiant","id_module","note","date"},
                "_id=?",
                new String[]{String.valueOf(id)},
                null,
                null,
                null
        );
        Etudiant e = new Etudiant();
        Module m = new Module();
//        System.out.println("selected_id == " + c.getString(4));
//        n.setId(c.getInt(4));
        c.moveToFirst();
        e.setId(c.getInt(1));
        m.setId(c.getInt(2));

        Evaluation E = new Evaluation(c.getInt(0),e,m,c.getFloat(3),c.getString(4));

        return E;
    }

    public Cursor getEvaluationsByEtudiant(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT rowid _id , * FROM Evaluation WHERE id_etudiant = ?",new String[]{String.valueOf(id)});

        return c;
    }


}
