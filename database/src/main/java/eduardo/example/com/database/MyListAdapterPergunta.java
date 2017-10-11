package eduardo.example.com.database;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Pergunta;

public class MyListAdapterPergunta extends ArrayAdapter<Pergunta> {
    private ArrayList<Pergunta> mList=new ArrayList<Pergunta>();
    private Context context;

    public MyListAdapterPergunta(Context context, ArrayList<Pergunta> arrayList) {
        super(context, R.layout.perguntas_list, arrayList);
        this.mList= arrayList;
        this.context= context;

    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.perguntas_list, null);
        }
// Inicialização da interface e atribuição da valores aos campos



        TextView idNivelPerguntaList = (TextView) v.findViewById(R.id.idNivelPerguntaList);
        TextView perguntaPerguntaList = (TextView) v.findViewById(R.id.perguntaPerguntaList);
        TextView opcaoCerta = (TextView) v.findViewById(R.id.corretaPerguntaList);
        TextView incorrecta1PerguntaList = (TextView) v.findViewById(R.id.incorrecta1PerguntaList);
        TextView incorrecta2PerguntaList = (TextView) v.findViewById(R.id.incorrecta2PerguntaList);
        TextView incorrecta3PerguntaList = (TextView) v.findViewById(R.id.incorrecta3PerguntaList);

        idNivelPerguntaList.setText(GerirNiveis.nameNivel(context,mList.get(position).getIdNivel()));
        perguntaPerguntaList.setText(mList.get(position).getPergunta());
        opcaoCerta.setText(mList.get(position).getRespostaCerta());
        incorrecta1PerguntaList.setText(mList.get(position).getRespostasErradas().get(0));
        incorrecta2PerguntaList.setText(mList.get(position).getRespostasErradas().get(1));
        incorrecta3PerguntaList.setText(mList.get(position).getRespostasErradas().get(2));


        return v;
    }
}
