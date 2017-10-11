package eduardo.example.com.database;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Estatistica;

public class MyListAdapterEstatistica extends ArrayAdapter<Estatistica> {
    private ArrayList<Estatistica> mList = new ArrayList<Estatistica>();
    private Context context;

    public MyListAdapterEstatistica(Context context, ArrayList<Estatistica> arrayList) {
        super(context, R.layout.estatisticas_list, arrayList);
        this.mList = arrayList;
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.estatisticas_list, null);
        }

        // Inicialização da interface e atribuição da valores aos campos
        TextView utilizadorEstatistica = (TextView) v.findViewById(R.id.utilizadorEstatistica);
        TextView nivelEstatistica = (TextView) v.findViewById(R.id.nivelEstatistica);
        TextView pontuacaoEstatistica = (TextView) v.findViewById(R.id.pontuacaoEstatistica);

        utilizadorEstatistica.setText(mList.get(position).getNomeUtilizador());
        nivelEstatistica.setText(mList.get(position).getNomeNivel());
        pontuacaoEstatistica.setText(String.valueOf(mList.get(position).getPontuacaoFinal()));

        return v;
    }
}
