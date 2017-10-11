package eduardo.example.com.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Nivel;

public class GerirNiveis {

    public static boolean addNivel(Context context, Nivel nivel) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "SELECT * FROM tblNiveis WHERE nome = ?";
        Cursor c = db.rawQuery(sql, new String[]{nivel.getNome()});


        if (c != null && !c.moveToFirst()) {

            db.execSQL("INSERT INTO tblNiveis(nome,descricao,pontuacao) VALUES ('" + nivel.getNome() + "','" + nivel.getDescricao() + "','" + nivel.getPontuacao() + "')");
            c.close();
            db.close();
            return true;

        }

        c.close();
        db.close();

        return false;

    }

    public static boolean editNivel(Context context, Nivel newNivel, int oldIdNivel) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "SELECT * FROM tblNiveis WHERE id = '" + oldIdNivel + "'";
        Cursor c = db.rawQuery(sql, null);

        if (c != null && c.moveToFirst()) {

            ContentValues contentValues = new ContentValues();
            contentValues.put("nome", newNivel.getNome());
            contentValues.put("descricao", newNivel.getDescricao());
            contentValues.put("pontuacao", newNivel.getPontuacao());

            db.update("tblNiveis", contentValues, "id=" + c.getInt(0), null);

            c.close();
            db.close();

            return true;
        } else {

            c.close();
            db.close();

            return false;
        }
    }

    public static boolean delNivel(Context context, int idNivel) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "SELECT * FROM tblNiveis WHERE id = '" + idNivel + "'";
        Cursor c = db.rawQuery(sql, null);

        if (c != null) {
            db.delete("tblNiveis", "id=" + idNivel, null);

            return true;
        }

        c.close();
        db.close();

        return false;
    }

    public static String nameNivel(Context context, int idNivel) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String name;

        String sql = "SELECT nome FROM tblNiveis WHERE id = '" + idNivel + "'";
        Cursor c = db.rawQuery(sql, null);

        if (c != null && c.moveToFirst()) {
            name = c.getString(0);
            c.close();
            db.close();
            return name;
        } else {
            db.close();
            return null;
        }
    }

    public static ArrayList<String> listNameNivel(Context context) {

        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();


        String sql = "SELECT nome FROM tblNiveis";
        Cursor c = db.rawQuery(sql, null);


        ArrayList<String> tmp = new ArrayList<>();
        if (c != null && c.moveToFirst()) {
            do {
                tmp.add(c.getString(0));

            } while (c.moveToNext());


        }
        db.close();
        c.close();
        return tmp;


    }

    public static int searchById(Context context, String nome) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT id FROM tblNiveis WHERE nome=?", new String[]{nome});

        if (c != null && c.moveToFirst()) {
            int id = c.getInt(0);
            c.close();
            db.close();
            return id;
        } else {
            db.close();
            return -1;
        }


    }

    public static int searchByPontuacao(Context context, int id) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Cursor c = db.rawQuery("SELECT pontuacao FROM tblNiveis WHERE id=?", new String[]{String.valueOf(id)});


        if (c != null && c.moveToFirst()) {
            int pontuacao = c.getInt(0);
            c.close();
            db.close();
            return pontuacao;
        } else {
            db.close();
            return -1;
        }
    }


    public static void listNiveis(Context context, ArrayList<Nivel> nivelArrayList) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        nivelArrayList.clear();

        Cursor c = db.rawQuery("SELECT * FROM tblNiveis", null);

        if (c != null && c.moveToFirst()) {
            do {
                Nivel nivel = new Nivel();
                nivel.setId(c.getInt(0));
                nivel.setNome(c.getString(1));
                nivel.setDescricao(c.getString(2));
                nivel.setPontuacao(c.getString(3));

                nivelArrayList.add(nivel);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
    }
}
