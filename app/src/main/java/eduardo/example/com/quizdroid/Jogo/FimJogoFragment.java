package eduardo.example.com.quizdroid.Jogo;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import eduardo.example.com.database.Classes.Estatistica;
import eduardo.example.com.database.GerirEstatistica;
import eduardo.example.com.quizdroid.MainActivity;
import eduardo.example.com.quizdroid.R;

public class FimJogoFragment extends Fragment implements View.OnClickListener {
    private Context mContext;
    private TextView pontuacao;
    private EditText username;
    private String numero;
    private String nomeNivel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }

    @Override
    public void onResume() {
        super.onResume();
        this.updatePontucao();

    }

    public void setNivelEstatistica(String nomeNivel){
        this.nomeNivel = nomeNivel;
    }

    public void setPontucao(int pontuacao){
        this.numero = String.valueOf(pontuacao);
        updatePontucao();
    }

    public void updatePontucao(){
        if (this.pontuacao != null) {
            System.out.println(numero);
            this.pontuacao.setText(numero);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mContentView = inflater.inflate(R.layout.fimjogo, container, false);
        pontuacao = (TextView) mContentView.findViewById(R.id.txtViewPontuacao);
        username = (EditText) mContentView.findViewById(R.id.username);


        ((Button) mContentView.findViewById(R.id.btnVoltarMenu)).setOnClickListener(this);
        return mContentView;
    }

    @Override
    public void onClick(View view) {
        Estatistica newEstatistica = new Estatistica();

        if(username.getText().toString().trim().equals("")){
            String text = "Não se esqueça de escrever o seu nome ;)";
            Toast.makeText(mContext, text, Toast.LENGTH_LONG).show();
        }else{
            Intent myIntent = new Intent(mContext, MainActivity.class);

            newEstatistica.setNomeNivel(nomeNivel);
            newEstatistica.setPontuacaoFinal(Integer.parseInt(pontuacao.getText().toString()));
            newEstatistica.setNomeUtilizador(username.getText().toString());

            GerirEstatistica.addEstatistica(mContext,newEstatistica);

            MainActivity.mediaPlayer.pause();
            startActivity(myIntent);
            onDestroy();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().finish();
    }
}
