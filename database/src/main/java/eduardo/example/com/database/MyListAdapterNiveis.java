package eduardo.example.com.database;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Nivel;

public class MyListAdapterNiveis extends ArrayAdapter<Nivel>{

    private ArrayList<Nivel> mList = new ArrayList<Nivel>();
    private Context context;

    public MyListAdapterNiveis(Context context, ArrayList<Nivel> arrayList) {
        super(context, R.layout.niveis_list, arrayList);
        this.mList = arrayList;
        this.context = context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.niveis_list, null);
        }

        // Inicialização da interface e atribuição da valores aos campos
        TextView nomeNivelList = (TextView) v.findViewById(R.id.nomeNivelList);
        TextView descricaoNivelList = (TextView) v.findViewById(R.id.descricaoNivelList);
        TextView pontuacaoNivelList = (TextView) v.findViewById(R.id.pontuacaoNivelList);

        nomeNivelList.setText(mList.get(position).getNome());
        descricaoNivelList.setText(mList.get(position).getDescricao());
        pontuacaoNivelList.setText(mList.get(position).getPontuacao());

        return v;
    }
}
