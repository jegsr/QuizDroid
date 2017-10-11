package eduardo.example.com.quizdroid.Jogo;


import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Pergunta;
import eduardo.example.com.database.GerirNiveis;
import eduardo.example.com.database.GerirPerguntas;
import eduardo.example.com.quizdroid.R;

public class JogoAtivity extends FragmentActivity implements OnPerguntaListener {

    private ArrayList<Pergunta> perguntas = new ArrayList<>();
    private int pontuacaoNivel;
    private int perguntasCertas = 0;
    private String nivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ativity_perguntas);

        Bundle tmp = getIntent().getExtras();

        if (tmp != null) {
            nivel = tmp.getString(String.valueOf(R.string.txtNiveis));
            this.uploadPerguntas(nivel);
        }


        if (findViewById(R.id.fragment_container) != null) {
            if (savedInstanceState != null)
                return;
            PerguntasFragment fragment = new PerguntasFragment();
            fragment.setPergunta(perguntas.remove(GerirPerguntas.gerarNumeros(perguntas.size())));
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, fragment).commit();
        }
    }


    private void uploadPerguntas(String str) {


        int id_nivel = GerirNiveis.searchById(this,str);

        if(id_nivel != -1){
            this.pontuacaoNivel = GerirNiveis.searchByPontuacao(this,id_nivel);

            this.perguntas = GerirPerguntas.searchByNivel(this,id_nivel);

        }

    }


    @Override
    public void onPerguntaCerta(int acertou) {

        if (acertou != 2) {
            perguntasCertas++;
        }
        if (acertou == 1 && perguntas.size() > 0) {

            PerguntasFragment fragment = (PerguntasFragment) getSupportFragmentManager().findFragmentById(R.id.perguntas);

            if (fragment != null) {
                fragment.setPergunta(perguntas.remove(GerirPerguntas.gerarNumeros(perguntas.size())));
            } else {
                fragment = new PerguntasFragment();
                fragment.setPergunta(perguntas.remove(GerirPerguntas.gerarNumeros(perguntas.size())));

                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fragment).addToBackStack("null").commit();
            }
        } else {
            FimJogoFragment fimJogoFragment = (FimJogoFragment) getSupportFragmentManager().findFragmentById(R.id.fimJogo);

            if (fimJogoFragment == null) {
                fimJogoFragment = new FimJogoFragment();
                fimJogoFragment.setPontucao(perguntasCertas*pontuacaoNivel);
                fimJogoFragment.setNivelEstatistica(nivel);
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.replace(R.id.fragment_container, fimJogoFragment).addToBackStack("null").commit();
            }
        }


    }
}
