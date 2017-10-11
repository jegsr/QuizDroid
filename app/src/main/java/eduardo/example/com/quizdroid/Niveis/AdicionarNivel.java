package eduardo.example.com.quizdroid.Niveis;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import eduardo.example.com.database.Classes.Nivel;
import eduardo.example.com.database.GerirNiveis;
import eduardo.example.com.quizdroid.AdminActivity;
import eduardo.example.com.quizdroid.R;

public class AdicionarNivel extends AppCompatActivity implements View.OnClickListener {
    private Nivel nivel;
    private int idNivel;
    private EditText txtAddNivelNome, txtAddDescricao, txtAddPontuacao;
    private Button btnAddNivel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_nivel);

        Intent getIntent = getIntent();

        this.nivel = (Nivel) getIntent.getSerializableExtra("nivelEdit");

        txtAddNivelNome = (EditText) findViewById(R.id.txtAddNivelNome);
        txtAddDescricao = (EditText) findViewById(R.id.txtAddDescricao);
        txtAddPontuacao = (EditText) findViewById(R.id.txtAddPontuacao);


        btnAddNivel = (Button) findViewById(R.id.btnAddNivel);
        btnAddNivel.setOnClickListener(this);

        if (this.nivel != null) {

            //Obter o id desta Pergunta
            idNivel = this.nivel.getId();

            //Por todos os campos já preenchidos com a pergunta selecionada

            txtAddNivelNome.setText(this.nivel.getNome());
            txtAddDescricao.setText(this.nivel.getDescricao());
            txtAddPontuacao.setText(this.nivel.getPontuacao());


            //Alterar o id e o text do botao
            ((TextView) findViewById(R.id.textNovoNivel)).setText(String.valueOf(R.string.txtEditarNivel));
            btnAddNivel.setText(String.valueOf(R.string.txtEditarNivel));
            btnAddNivel.setId(R.id.btnEditNivel);
        }
    }

    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(AdicionarNivel.this, AdminActivity.class);
        Nivel newNivel = new Nivel();


        newNivel.setNome(txtAddNivelNome.getText().toString());
        newNivel.setDescricao(txtAddDescricao.getText().toString());
        newNivel.setPontuacao(txtAddPontuacao.getText().toString());

        if (!newNivel.getDescricao().trim().equals("") && !newNivel.getPontuacao().trim().equals("")) {

            if (view.getId() == R.id.btnEditNivel) {
                if (!(GerirNiveis.editNivel(this, newNivel, idNivel))) {
                    String text = "Não foi possivél alterar o nível por favor tente novamente";
                    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
                } else {
                    startActivity(myIntent);
                    finish();
                }

            } else if (view.getId() == R.id.btnAddNivel) {
                if (!(GerirNiveis.addNivel(this, newNivel))) {
                    String text = "Esse nível já existe. Por favor adicione outro.";
                    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
                } else {
                    startActivity(myIntent);
                    finish();
                }
            }

        } else {
            String text = "Por favor preencha os campos.";
            Toast.makeText(this, text, Toast.LENGTH_LONG).show();
        }
    }
}
