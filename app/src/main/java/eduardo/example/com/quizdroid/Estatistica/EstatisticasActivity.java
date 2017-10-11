package eduardo.example.com.quizdroid.Estatistica;

import android.app.ListActivity;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Estatistica;
import eduardo.example.com.database.GerirEstatistica;
import eduardo.example.com.database.MyListAdapterEstatistica;
import eduardo.example.com.quizdroid.R;

public class EstatisticasActivity extends ListActivity implements View.OnClickListener {

    private ArrayList<Estatistica> estatisticaArrayList;
    private MyListAdapterEstatistica mAdapter = null;
    private Button pontuacao, nivel, utilizador;

    private Drawable down;
    private Drawable up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estatisticas);

        estatisticaArrayList = new ArrayList<>();
        mAdapter = new MyListAdapterEstatistica(EstatisticasActivity.this, estatisticaArrayList);
        setListAdapter(mAdapter);

        pontuacao = (Button) findViewById(R.id.pontuacao);
        pontuacao.setOnClickListener(this);

        nivel = (Button) findViewById(R.id.nivel);
        nivel.setOnClickListener(this);

        utilizador = (Button) findViewById(R.id.utilizador);
        utilizador.setOnClickListener(this);

        up = ContextCompat.getDrawable(this,R.mipmap.ic_sort_up);
        down = ContextCompat.getDrawable(this,R.mipmap.ic_sort_down);
    }

    public void onResume() {
        super.onResume();
        GerirEstatistica.pontuacaoDecrescentEstatistica(EstatisticasActivity.this, estatisticaArrayList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View view) {
        if (estatisticaArrayList.size()> 0) {
            if (view.getId() == R.id.pontuacao) {
                Drawable bottom = pontuacao.getCompoundDrawables()[3];

                if (bottom.equals(up)) {
                    pontuacao.setCompoundDrawablesWithIntrinsicBounds(null,null,null,down);
                    GerirEstatistica.pontuacaoCrescenteEstatistica(this, estatisticaArrayList);
                } else {
                    pontuacao.setCompoundDrawablesWithIntrinsicBounds(null,null,null,up);
                    GerirEstatistica.pontuacaoDecrescentEstatistica(this, estatisticaArrayList);
                }
                mAdapter.notifyDataSetChanged();
            } else if (view.getId() == R.id.nivel) {
                Drawable bottom = nivel.getCompoundDrawables()[3];

                if (bottom.equals(up)) {
                    nivel.setCompoundDrawablesWithIntrinsicBounds(null,null,null,down);
                    GerirEstatistica.nivelCrescenteEstatistica(this, estatisticaArrayList);
                } else {
                    nivel.setCompoundDrawablesWithIntrinsicBounds(null,null,null,up);
                    GerirEstatistica.nivelDescrecenteEstatistica(this, estatisticaArrayList);
                }
                mAdapter.notifyDataSetChanged();
            } else if (view.getId() == R.id.utilizador) {
                Drawable bottom = utilizador.getCompoundDrawables()[3];

                if (bottom.equals(up)) {
                    utilizador.setCompoundDrawablesWithIntrinsicBounds(null,null,null,down);
                    GerirEstatistica.nomeCrescenteEstatistica(this, estatisticaArrayList);
                } else {
                    utilizador.setCompoundDrawablesWithIntrinsicBounds(null,null,null,up);

                    GerirEstatistica.nomeDescenteEstatistica(this, estatisticaArrayList);
                }
                mAdapter.notifyDataSetChanged();
            }
        }
    }
}
