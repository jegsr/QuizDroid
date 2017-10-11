package eduardo.example.com.quizdroid;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import eduardo.example.com.quizdroid.Niveis.AdicionarNivel;
import eduardo.example.com.quizdroid.Niveis.ListarNivel;
import eduardo.example.com.quizdroid.Perguntas.AdicionarPergunta;
import eduardo.example.com.quizdroid.Perguntas.ListarPergunta;

public class AdminActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        ((Button) findViewById(R.id.btn_addNivel)).setOnClickListener(this);


        ((Button) findViewById(R.id.btn_addPergunta)).setOnClickListener(this);


        ((Button) findViewById(R.id.btn_listNivel)).setOnClickListener(this);


        ((Button) findViewById(R.id.btn_listPergunta)).setOnClickListener(this);


        ((Button) findViewById(R.id.homeButton)).setOnClickListener(this);



    }

    @Override
    public void onClick(View view) {
        Intent myIntent;

        if (view.getId() == R.id.btn_addNivel) {
            myIntent = new Intent(this, AdicionarNivel.class);
            startActivity(myIntent);
        } else if (view.getId() == R.id.btn_addPergunta) {
            myIntent = new Intent(this, AdicionarPergunta.class);
            startActivity(myIntent);
        } else if (view.getId() == R.id.btn_listNivel) {
            myIntent = new Intent(this, ListarNivel.class);
            startActivity(myIntent);
        } else if (view.getId() == R.id.btn_listPergunta) {
            myIntent = new Intent(this, ListarPergunta.class);
            startActivity(myIntent);
        } else if (view.getId() == R.id.homeButton) {
            MainActivity.mediaPlayer.pause();
            myIntent = new Intent(this, MainActivity.class);
            startActivity(myIntent);

        }
    }
}
