package eduardo.example.com.quizdroid.Niveis;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Nivel;
import eduardo.example.com.database.GerirNiveis;
import eduardo.example.com.database.GerirPerguntas;
import eduardo.example.com.database.MyListAdapterNiveis;
import eduardo.example.com.quizdroid.R;


public class ListarNivel extends ListActivity {
    private ArrayList<Nivel> nivelArrayList;
    private MyListAdapterNiveis mAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_manager);
        ((TextView) findViewById(R.id.txtListView)).setText(R.string.txtListNivel);
        nivelArrayList = new ArrayList<Nivel>();
        mAdapter = new MyListAdapterNiveis(ListarNivel.this, nivelArrayList);
        setListAdapter(mAdapter);

    }

    public void onResume() {
        super.onResume();
        GerirNiveis.listNiveis(ListarNivel.this, nivelArrayList);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onListItemClick(ListView l, View v, final int position, long id) {
        super.onListItemClick(l, v, position, id);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setMessage("O que deseja fazer com o nível: " + nivelArrayList.get(position).getNome());
        builder.setTitle("Opção");


        //ALTERADO -> Agora apagamos os niveis e as perguntas associadas ao mesmo, as estatisticas ficam na mesma.
        builder.setPositiveButton("Remover", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AlertDialog.Builder builder = new AlertDialog.Builder(ListarNivel.this);
                builder.setMessage("Ao apagar o nível " + nivelArrayList.get(position).getNome() + " irá apagar todas as perguntas que estejam associadas ao mesmo. O que deseja fazer?");
                builder.setTitle("Alerta");

                //Apaga as perguntas e os niveis associados
                builder.setPositiveButton("Apagar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int id = nivelArrayList.get(position).getId();

                        GerirPerguntas.delPerguntaByIdNivel(ListarNivel.this, nivelArrayList.get(position).getId());
                        boolean del = GerirNiveis.delNivel(ListarNivel.this, id);

                        if (del == true) {


                            String text = "O nível " + nivelArrayList.get(position).getNome() + " foi eliminada com sucesso!";
                            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();

                            nivelArrayList.remove(position);
                            mAdapter.notifyDataSetChanged();


                        } else {

                            String text = "Pedimos Desculpa, mas um imprevisto não nos deixou eliminar o nível " + nivelArrayList.get(position).getNome();
                            Toast.makeText(getApplicationContext(), text, Toast.LENGTH_LONG).show();
                        }

                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                AlertDialog mDialog = builder.create();
                mDialog.show();
            }
        });
        builder.setNegativeButton("Editar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent myIntent = new Intent(ListarNivel.this, AdicionarNivel.class);
                Nivel newNivel = new Nivel();


                newNivel.setId(nivelArrayList.get(position).getId());
                newNivel.setNome(nivelArrayList.get(position).getNome());
                newNivel.setDescricao(nivelArrayList.get(position).getDescricao());
                newNivel.setPontuacao(nivelArrayList.get(position).getPontuacao());


                //Manda-se a pergunta selecionada para a Activity Adicionar
                myIntent.putExtra("nivelEdit", newNivel);
                startActivity(myIntent);

            }
        });
        AlertDialog mDialog = builder.create();
        mDialog.show();
    }

}
