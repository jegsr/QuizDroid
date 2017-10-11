package eduardo.example.com.quizdroid.Perguntas;

import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Pergunta;
import eduardo.example.com.database.GerirPerguntas;
import eduardo.example.com.database.MyListAdapterPergunta;
import eduardo.example.com.quizdroid.R;

public class ListarPergunta extends ListActivity{

    private ArrayList<Pergunta> perguntaArrayList;
    private MyListAdapterPergunta mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_manager);
        ((TextView) findViewById(R.id.txtListView)).setText(R.string.txtListPergunta);

        perguntaArrayList = new ArrayList<Pergunta>();
        mAdapter = new MyListAdapterPergunta(ListarPergunta.this, perguntaArrayList);
        setListAdapter(mAdapter);

    }

    public void onResume() {
        super.onResume();
        GerirPerguntas.listPerguntas(ListarPergunta.this, perguntaArrayList);
        mAdapter.notifyDataSetChanged();
    }


    @Override
    public void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("O que deseja fazer com a pergunta: " + perguntaArrayList.get(position).getPergunta());
        builder.setTitle("Opção");


        builder.setPositiveButton("Remover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                int id = perguntaArrayList.get(position).getId();
                boolean del = GerirPerguntas.delPergunta(ListarPergunta.this, id);

                if (del == true) {
                    String text = "A pergunta " + perguntaArrayList.get(position).getPergunta() + " foi eliminada com sucesso!";
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

                    perguntaArrayList.remove(position);

                    mAdapter.notifyDataSetChanged();
                } else {

                    String text = "Pedimos Desculpa, mas um imprevisto não nos deixou eliminar a pergunta " + perguntaArrayList.get(position).getPergunta();
                    Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                }

            }
        });
        builder.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myIntent = new Intent(ListarPergunta.this, AdicionarPergunta.class);
                Pergunta newPergunta = new Pergunta();
                ArrayList<String> respostasErradas = new ArrayList<>(3);
                respostasErradas.clear();

                newPergunta.setId(perguntaArrayList.get(position).getId());
                newPergunta.setIdNivel(perguntaArrayList.get(position).getIdNivel());
                newPergunta.setPergunta(perguntaArrayList.get(position).getPergunta());
                newPergunta.setRespostaCerta(perguntaArrayList.get(position).getRespostaCerta());


                respostasErradas.add(0, perguntaArrayList.get(position).getRespostasErradas().get(0));
                respostasErradas.add(1, perguntaArrayList.get(position).getRespostasErradas().get(1));
                respostasErradas.add(2, perguntaArrayList.get(position).getRespostasErradas().get(2));

                newPergunta.setRespostasErradas(respostasErradas);


                //Manda-se a pergunta selecionada para a Activity Adicionar
                myIntent.putExtra("perguntaEdit", newPergunta);
                startActivity(myIntent);

            }
        });
        AlertDialog mDialog = builder.create();
        mDialog.show();

    }
}
