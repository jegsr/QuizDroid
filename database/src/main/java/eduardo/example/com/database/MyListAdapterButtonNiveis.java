package eduardo.example.com.database;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MyListAdapterButtonNiveis extends ArrayAdapter<String> {

    private OnButtonListener onButtonListener;
    private ArrayList<String> mList = new ArrayList<>();
    private Context context;

    public MyListAdapterButtonNiveis(Context context, ArrayList<String> arrayList) {
        super(context, R.layout.button_niveis, arrayList);
        this.mList = arrayList;
        this.context = context;
        onButtonListener = (OnButtonListener) context;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.button_niveis, null);
        }

        // Inicialização da interface e atribuição da valores aos campos
        Button a = (Button) convertView.findViewById(R.id.btn_Niveis);
        a.setHint(mList.get(position));
        a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onButtonListener.buttonClick((String)((Button) view).getHint());
            }
        });

        return convertView;
    }
}
