package eduardo.example.com.quizdroid;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import eduardo.example.com.database.GerirNiveis;
import eduardo.example.com.database.GerirPerguntas;
import eduardo.example.com.database.MyListAdapterButtonNiveis;
import eduardo.example.com.database.OnButtonListener;
import eduardo.example.com.quizdroid.Jogo.JogoAtivity;

public class NovoJogoActivity extends ListActivity implements OnButtonListener {


    private ArrayList<String> nivelArrayList;
    private MyListAdapterButtonNiveis mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_novo_jogo);

        nivelArrayList = GerirNiveis.listNameNivel(NovoJogoActivity.this);


        mAdapter = new MyListAdapterButtonNiveis(NovoJogoActivity.this,nivelArrayList );
        setListAdapter(mAdapter);


    }

    public void onResume() {
        super.onResume();
        nivelArrayList = GerirNiveis.listNameNivel(NovoJogoActivity.this);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void buttonClick(String nomeNivel) {

        if((GerirPerguntas.searchByNivel(this,GerirNiveis.searchById(this,nomeNivel))).size() > 0){

            Toast.makeText(this,"A iniciar o jogo com o nivel "+nomeNivel+"!",Toast.LENGTH_SHORT).show();

            try {
                Thread.sleep(Toast.LENGTH_SHORT);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Intent myIntent = new Intent(this, JogoAtivity.class);
            myIntent.putExtra(String.valueOf(R.string.txtNiveis), nomeNivel);
            startActivity(myIntent);
        }else{
            Toast.makeText(this,"O nivel selecionado nao se encontra com perguntas",Toast.LENGTH_LONG).show();
        }
    }
}
