package eduardo.example.com.quizdroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Pergunta;
import eduardo.example.com.database.GerirNiveis;
import eduardo.example.com.database.GerirPerguntas;
import eduardo.example.com.quizdroid.Estatistica.EstatisticasActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static MediaPlayer mediaPlayer = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mediaPlayer = MediaPlayer.create(MainActivity.this, R.raw.music);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(100, 100);
            mediaPlayer.start();
        }


        ((Button) findViewById(R.id.btn_novoJogo)).setOnClickListener(this);

        ((Button) findViewById(R.id.btn_estatistica)).setOnClickListener(this);

        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String nome = preferences.getString("widget_nivel", "-1");


        int id_nivel = GerirNiveis.searchById(getApplicationContext(), nome);
        ArrayList<Pergunta> perguntas = GerirPerguntas.searchByNivel(getApplicationContext(), id_nivel);


        //new ServiceTask(this).execute();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuItem tmp = menu.add(0, 0, 0, "Definições");
        tmp.setIcon(R.drawable.settings_icon);
        tmp.setShowAsAction(MenuItem.SHOW_AS_ACTION_ALWAYS);


        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == 0) {
            Intent myIntent = new Intent(this, PreferenciasActivity.class);
            startActivity(myIntent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.release();
            } finally {
                mediaPlayer = null;
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();


        Boolean music = (PreferenceManager.getDefaultSharedPreferences(this)).getBoolean("app_musica", true);
        if (music) {
            mediaPlayer.start();
        } else {
            mediaPlayer.pause();
        }
    }


    @Override
    public void onClick(View view) {
        Intent myIntent;

        if (view.getId() == R.id.btn_novoJogo) {
            myIntent = new Intent(this, NovoJogoActivity.class);
            startActivity(myIntent);
        } else if (view.getId() == R.id.btn_estatistica) {
            myIntent = new Intent(this, EstatisticasActivity.class);
            startActivity(myIntent);

        }
    }
}
