package eduardo.example.com.database;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Pergunta;

public class GerirPerguntas {
    public static boolean addPergunta(Context context, Pergunta pergunta) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "SELECT * FROM tblPerguntas WHERE pergunta = '" + pergunta.getPergunta() + "'";
        Cursor c = db.rawQuery(sql, null);

        if (c != null && !c.moveToFirst()) {
            db.execSQL("INSERT INTO tblPerguntas(idNivel, pergunta , opcaoCerta , opcaoErrada1, opcaoErrada2 , opcaoErrada3 ) VALUES ('" + pergunta.getIdNivel() + "','" + pergunta.getPergunta() + "','" + pergunta.getRespostaCerta() + "','" + pergunta.getRespostasErradas().get(0) + "','" + pergunta.getRespostasErradas().get(1) + "','" + pergunta.getRespostasErradas().get(2) + "')");

            return true;
        }

        c.close();
        db.close();

        return false;
    }

    public static boolean editPergunta(Context context, Pergunta newPergunta, int oldIdPergunta) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "SELECT * FROM tblPerguntas WHERE id = '" + oldIdPergunta + "'";
        Cursor c = db.rawQuery(sql, null);

        if (c != null && c.moveToFirst()) {

            ContentValues contentValues = new ContentValues();
            contentValues.put("idNivel", newPergunta.getIdNivel());
            contentValues.put("pergunta", newPergunta.getPergunta());
            contentValues.put("opcaoCerta", newPergunta.getRespostaCerta());
            contentValues.put("opcaoErrada1", newPergunta.getRespostasErradas().get(0));
            contentValues.put("opcaoErrada2", newPergunta.getRespostasErradas().get(1));
            contentValues.put("opcaoErrada3", newPergunta.getRespostasErradas().get(2));

            db.update("tblPerguntas", contentValues, "id=" + c.getInt(0), null);

            c.close();
            db.close();

            return true;
        }

        c.close();
        db.close();

        return false;

    }

    public static ArrayList<Pergunta> searchByNivel(Context context, int id) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ArrayList<Pergunta> perguntas = new ArrayList<>();

        Cursor c = db.rawQuery("SELECT * FROM tblPerguntas WHERE idNivel=?", new String[]{String.valueOf(id)});

        if (c != null && c.moveToFirst()) {
            ;
            do {
                Pergunta p = new Pergunta();

                p.setId(c.getInt(0));
                p.setIdNivel(c.getInt(1));
                p.setPergunta(c.getString(2));
                p.setRespostaCerta(c.getString(3));
                ArrayList<String> tmp = new ArrayList<>(3);
                for (int i = 4; i < 7; i++) {
                    tmp.add(c.getString(i));
                }
                p.setRespostasErradas(tmp);

                perguntas.add(p);
            } while (c.moveToNext());


        }

        c.close();
        db.close();
        return perguntas;

    }

    public static boolean delPergunta(Context context, int id) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "SELECT * FROM tblPerguntas WHERE id = '" + id + "'";
        Cursor c = db.rawQuery(sql, null);

        if (c != null) {
            db.delete("tblPerguntas", "id=" + id, null);

            return true;
        }

        c.close();
        db.close();

        return false;
    }

    public static void listPerguntas(Context context, ArrayList<Pergunta> perguntaArrayList) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        perguntaArrayList.clear();

        Cursor c = db.rawQuery("SELECT * FROM tblPerguntas ORDER BY idNivel", null);

        if (c != null && c.moveToFirst()) {
            do {
                Pergunta pergunta = new Pergunta();
                ArrayList<String> respostasErradas = new ArrayList<>();
                pergunta.setId(c.getInt(0));
                pergunta.setIdNivel(c.getInt(1));
                pergunta.setPergunta(c.getString(2));
                pergunta.setRespostaCerta(c.getString(3));
                respostasErradas.add(0, c.getString(4));
                respostasErradas.add(1, c.getString(5));
                respostasErradas.add(2, c.getString(6));
                pergunta.setRespostasErradas(respostasErradas);

                perguntaArrayList.add(pergunta);
            } while (c.moveToNext());
        }

        c.close();
        db.close();
    }

    public static boolean delPerguntaByIdNivel(Context context, int idNivel) {
        MyDbHelper dbHelper = new MyDbHelper(context);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        String sql = "SELECT * FROM tblPerguntas WHERE idNivel = '" + idNivel + "'";
        Cursor c = db.rawQuery(sql, null);

        if (c != null && c.moveToFirst()) {
            db.delete("tblPerguntas", "idNivel=" + idNivel, null);

            return true;
        }

        c.close();
        db.close();

        return false;
    }

    public static int gerarNumeros(int maximo) {
        return (int) (0 + (Math.random() * (maximo)));
    }
}
