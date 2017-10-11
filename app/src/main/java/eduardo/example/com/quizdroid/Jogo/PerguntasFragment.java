package eduardo.example.com.quizdroid.Jogo;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Pergunta;
import eduardo.example.com.database.GerirPerguntas;
import eduardo.example.com.quizdroid.R;

public class PerguntasFragment extends Fragment implements View.OnClickListener, View.OnLongClickListener {
    private Context mContext;
    private TextView txtViewPergunta;
    private Button[] respostas;
    private Button respostaSelecionada;
    private Pergunta pergunta;
    private OnPerguntaListener novapergunta;

    public PerguntasFragment() {
        respostas = new Button[4];
        respostaSelecionada = null;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
        novapergunta = (OnPerguntaListener) getActivity();

    }

    @Override
    public void onResume() {
        super.onResume();
        this.updatePergunta();

    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
        this.updatePergunta();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mContentView = inflater.inflate(R.layout.perguntas, container, false);
        txtViewPergunta = (TextView) mContentView.findViewById(R.id.txtViewPergunta);

        respostas[0] = (Button) mContentView.findViewById(R.id.btnPergunta1);
        respostas[0].setOnClickListener(this);
        respostas[0].setOnLongClickListener(this);

        respostas[1] = (Button) mContentView.findViewById(R.id.btnPergunta2);
        respostas[1].setOnClickListener(this);
        respostas[1].setOnLongClickListener(this);

        respostas[2] = (Button) mContentView.findViewById(R.id.btnPergunta3);
        respostas[2].setOnClickListener(this);
        respostas[2].setOnLongClickListener(this);

        respostas[3] = (Button) mContentView.findViewById(R.id.btnPergunta4);
        respostas[3].setOnClickListener(this);
        respostas[3].setOnLongClickListener(this);

        return mContentView;
    }

    public void updatePergunta() {
        if (this.txtViewPergunta != null) {
            this.txtViewPergunta.setText(this.pergunta.getPergunta());
            ArrayList<String> respostasTmp = this.pergunta.getRespostasErradas();
            respostasTmp.add(this.pergunta.getRespostaCerta());

            for (int i = 0; i < this.respostas.length; i++) {
                this.respostas[i].setText(respostasTmp.remove(GerirPerguntas.gerarNumeros(respostasTmp.size())));
            }


        }
    }


    @Override
    public void onClick(View view) {


        int i = 0;
        while (i < respostas.length && respostas[i].getId() != view.getId()) {
            i++;
        }
        if (respostaSelecionada != null) {
            respostaSelecionada.setBackgroundResource(android.R.drawable.btn_default);
        }


        respostaSelecionada = respostas[i];
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String str = "Errou na Resposta";
        if ((pergunta.getRespostaCerta()).equals((respostaSelecionada.getText()))) {
            respostaSelecionada.setBackgroundColor(Color.GREEN);
            str = "Acertou na Resposta";
            builder.setMessage("Deseja passar para a proxima?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            novapergunta.onPerguntaCerta(1);
                        }
                    })
                    .setNegativeButton("Não", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            novapergunta.onPerguntaCerta(3);
                        }
                    });
        } else {
            respostaSelecionada.setBackgroundColor(Color.RED);
            builder.setMessage("Game Over")
                    .setPositiveButton("Sair", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            novapergunta.onPerguntaCerta(2);
                        }
                    });
        }



        builder.setIcon(android.R.drawable.ic_menu_edit);
        builder.setTitle(str);

        builder.show();
    }

    @Override
    public boolean onLongClick(View view) {


        int i = 0;
        while (i < respostas.length && respostas[i].getId() != view.getId()) {
            i++;
        }

        if (respostaSelecionada != null) {
            respostaSelecionada.setBackgroundResource(android.R.drawable.btn_default);
        }

        respostas[i].setBackgroundColor(ContextCompat.getColor(mContext,android.R.color.holo_orange_dark));

        respostaSelecionada = respostas[i];

        return true;
    }
}
