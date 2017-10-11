package eduardo.example.com.database;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Estatistica;

public class GerirEstatistica {
    public static void addEstatistica(Context context, Estatistica estatistica) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        db.execSQL("INSERT INTO tblEstatistica(nomeNivel,pontuacaoFinal,nomeUtilizador) VALUES ('" + estatistica.getNomeNivel() + "','" + estatistica.getPontuacaoFinal() + "','" + estatistica.getNomeUtilizador() + "')");
        db.close();
    }

    public static void listEstatistica(Context context, ArrayList<Estatistica> estatisticaArrayList) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        estatisticaArrayList.clear();

        Cursor c = db.rawQuery("SELECT * FROM tblEstatistica", null);

        if (c != null && c.moveToFirst()) {
            do {
                Estatistica estatistica = new Estatistica();
                estatistica.setNomeNivel(c.getString(1));
                estatistica.setPontuacaoFinal(c.getInt(2));
                estatistica.setNomeUtilizador(c.getString(3));

                estatisticaArrayList.add(estatistica);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
    }

    public static void pontuacaoDecrescentEstatistica(Context context, ArrayList<Estatistica> estatisticaArrayList){
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        estatisticaArrayList.clear();

        Cursor c = db.rawQuery("SELECT * FROM tblEstatistica ORDER BY pontuacaoFinal DESC", null);

        if (c != null && c.moveToFirst()) {
            do {
                Estatistica estatistica = new Estatistica();
                estatistica.setNomeNivel(c.getString(1));
                estatistica.setPontuacaoFinal(c.getInt(2));
                estatistica.setNomeUtilizador(c.getString(3));

                estatisticaArrayList.add(estatistica);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
    }

    public static void pontuacaoCrescenteEstatistica(Context context, ArrayList<Estatistica> estatisticaArrayList){
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        estatisticaArrayList.clear();

        Cursor c = db.rawQuery("SELECT * FROM tblEstatistica ORDER BY pontuacaoFinal ASC", null);

        if (c != null && c.moveToFirst()) {
            do {
                Estatistica estatistica = new Estatistica();
                estatistica.setNomeNivel(c.getString(1));
                estatistica.setPontuacaoFinal(c.getInt(2));
                estatistica.setNomeUtilizador(c.getString(3));

                estatisticaArrayList.add(estatistica);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
    }

    public static void nivelCrescenteEstatistica(Context context, ArrayList<Estatistica> estatisticaArrayList){
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        estatisticaArrayList.clear();

        Cursor c = db.rawQuery("SELECT * FROM tblEstatistica ORDER BY nomeNivel ASC", null);

        if (c != null && c.moveToFirst()) {
            do {
                Estatistica estatistica = new Estatistica();
                estatistica.setNomeNivel(c.getString(1));
                estatistica.setPontuacaoFinal(c.getInt(2));
                estatistica.setNomeUtilizador(c.getString(3));

                estatisticaArrayList.add(estatistica);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
    }

    public static void nivelDescrecenteEstatistica(Context context, ArrayList<Estatistica> estatisticaArrayList){
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        estatisticaArrayList.clear();

        Cursor c = db.rawQuery("SELECT * FROM tblEstatistica ORDER BY nomeNivel DESC", null);

        if (c != null && c.moveToFirst()) {
            do {
                Estatistica estatistica = new Estatistica();
                estatistica.setNomeNivel(c.getString(1));
                estatistica.setPontuacaoFinal(c.getInt(2));
                estatistica.setNomeUtilizador(c.getString(3));

                estatisticaArrayList.add(estatistica);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
    }

    public static void nomeCrescenteEstatistica(Context context, ArrayList<Estatistica> estatisticaArrayList){
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        estatisticaArrayList.clear();

        Cursor c = db.rawQuery("SELECT * FROM tblEstatistica ORDER BY nomeUtilizador ASC", null);

        if (c != null && c.moveToFirst()) {
            do {
                Estatistica estatistica = new Estatistica();
                estatistica.setNomeNivel(c.getString(1));
                estatistica.setPontuacaoFinal(c.getInt(2));
                estatistica.setNomeUtilizador(c.getString(3));

                estatisticaArrayList.add(estatistica);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
    }

    public static void nomeDescenteEstatistica(Context context, ArrayList<Estatistica> estatisticaArrayList){
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        estatisticaArrayList.clear();

        Cursor c = db.rawQuery("SELECT * FROM tblEstatistica ORDER BY nomeUtilizador DESC", null);

        if (c != null && c.moveToFirst()) {
            do {
                Estatistica estatistica = new Estatistica();
                estatistica.setNomeNivel(c.getString(1));
                estatistica.setPontuacaoFinal(c.getInt(2));
                estatistica.setNomeUtilizador(c.getString(3));

                estatisticaArrayList.add(estatistica);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
    }
}
