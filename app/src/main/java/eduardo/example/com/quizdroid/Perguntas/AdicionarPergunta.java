package eduardo.example.com.quizdroid.Perguntas;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Pergunta;
import eduardo.example.com.database.GerirNiveis;
import eduardo.example.com.database.GerirPerguntas;
import eduardo.example.com.quizdroid.AdminActivity;
import eduardo.example.com.quizdroid.R;

public class AdicionarPergunta extends AppCompatActivity implements View.OnClickListener {

    private Pergunta pergunta;
    private int idPergunta;

    private Spinner spinnerNivelPergunta;
    private EditText txtAddPergunta, txtAddOpcaoCorreta, txtAddOpcaoErrada1, txtAddOpcaoErrada2, txtAddOpcaoErrada3;
    private Button btnAddPergunta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_pergunta);
        Intent getIntent = getIntent();

        this.pergunta = (Pergunta) getIntent.getSerializableExtra("perguntaEdit");

        spinnerNivelPergunta = (Spinner) findViewById(R.id.spinnerNivelPergunta);

        //Inicializar a Spinner com os nomes dos niveis
        ArrayList<String> arrayList = new ArrayList<String>();
        arrayList = GerirNiveis.listNameNivel(this);

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, arrayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinnerNivelPergunta.setAdapter(spinnerAdapter);

        txtAddPergunta = (EditText) findViewById(R.id.txtAddPergunta);
        txtAddOpcaoCorreta = (EditText) findViewById(R.id.txtAddOpcaoCorreta);
        txtAddOpcaoErrada1 = (EditText) findViewById(R.id.txtAddOpcaoErrada1);
        txtAddOpcaoErrada2 = (EditText) findViewById(R.id.txtAddOpcaoErrada2);
        txtAddOpcaoErrada3 = (EditText) findViewById(R.id.txtAddOpcaoErrada3);

        btnAddPergunta = (Button) findViewById(R.id.btnAddPergunta);
        btnAddPergunta.setOnClickListener(this);

        if (this.pergunta != null) {

            //Obter o id desta Pergunta
            idPergunta = this.pergunta.getId();

            //Por todos os campos já preenchidos com a pergunta selecionada
            String nivelName = GerirNiveis.nameNivel(this, this.pergunta.getIdNivel());
            int position = spinnerAdapter.getPosition(nivelName);

            spinnerNivelPergunta.setSelection(position);
            txtAddPergunta.setText(this.pergunta.getPergunta());
            txtAddOpcaoCorreta.setText(this.pergunta.getRespostaCerta());
            txtAddOpcaoErrada1.setText(this.pergunta.getRespostasErradas().get(0));
            txtAddOpcaoErrada2.setText(this.pergunta.getRespostasErradas().get(1));
            txtAddOpcaoErrada3.setText(this.pergunta.getRespostasErradas().get(2));

            //Alterar o id e o text do botao
            ((TextView) findViewById(R.id.textNovoPergunta)).setText(String.valueOf(R.string.txtEditarPergunta));
            btnAddPergunta.setText(String.valueOf(R.string.txtEditarPergunta));
            btnAddPergunta.setId(R.id.btnEditPergunta);
        }
    }

    @Override
    public void onClick(View view) {
        Intent myIntent = new Intent(AdicionarPergunta.this, AdminActivity.class);
        Pergunta newPergunta = new Pergunta();
        ArrayList<String> respostaErrada = new ArrayList<String>(3);


        String selectedValue = spinnerNivelPergunta.getItemAtPosition(spinnerNivelPergunta.getSelectedItemPosition()).toString();
        int idSelectedValue = GerirNiveis.searchById(this, selectedValue);

        newPergunta.setIdNivel(idSelectedValue);
        newPergunta.setPergunta(txtAddPergunta.getText().toString());
        newPergunta.setRespostaCerta(txtAddOpcaoCorreta.getText().toString());

        String resposta1, resposta2, resposta3;

        respostaErrada.add(resposta1 = txtAddOpcaoErrada1.getText().toString());
        respostaErrada.add(resposta2 = txtAddOpcaoErrada2.getText().toString());
        respostaErrada.add(resposta3 = txtAddOpcaoErrada3.getText().toString());

        newPergunta.setRespostasErradas(respostaErrada);

        if (!newPergunta.getPergunta().trim().equals("") && !newPergunta.getRespostaCerta().trim().equals("")
                && !resposta2.trim().equals("") && !resposta3.trim().equals("")
                && !resposta1.trim().equals("")) {

            if (view.getId() == R.id.btnEditPergunta) {
                if (!(GerirPerguntas.editPergunta(this, newPergunta, idPergunta))) {
                    String text = "Não foi possivél alterar a pergunta por favor tente novamente";
                    Toast.makeText(this, text, Toast.LENGTH_LONG).show();
                } else {
                    startActivity(myIntent);
                    finish();
                }

            } else if (view.getId() == R.id.btnAddPergunta) {

                if (!(GerirPerguntas.addPergunta(this, newPergunta))) {
                    String text = "Essa pergunta já existe. Por favor adicione outra.";
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
