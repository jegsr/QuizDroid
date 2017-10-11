package eduardo.example.com.quizdroid.Widget;


import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.preference.PreferenceManager;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Pergunta;
import eduardo.example.com.database.GerirNiveis;
import eduardo.example.com.database.GerirPerguntas;
import eduardo.example.com.quizdroid.R;

public class PerguntaService extends Service {
    private PerguntaTask task;
    private BroadcastReceiver tmp;
    private ArrayList<Pergunta> perguntas = new ArrayList<>();
    private String nome_nivel = "";

    public int onStartCommand(Intent i, int flags, int startId) {

        while (true) {

            task = new PerguntaTask(this);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            String nome = preferences.getString("widget_nivel", "-1");

            if (!nome_nivel.equals(nome)) {
                nome_nivel = nome;
                int id_nivel = GerirNiveis.searchById(getApplicationContext(), nome_nivel);
                perguntas = GerirPerguntas.searchByNivel(getApplicationContext(), id_nivel);

            }

            if (perguntas.size() > 0) {
                task.execute(perguntas.remove(GerirPerguntas.gerarNumeros(perguntas.size())));

                try {
                    task.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                Intent intent = new Intent(String.valueOf(R.string.broadcastForWidget));
                intent.putExtra(String.valueOf(R.string.SemPerguntas), "Não existe mais perguntas neste nivel, selecione outro");
                sendBroadcast(intent);
            }


        }

    }

    @Override
    public void onCreate() {
        super.onCreate();

        IntentFilter mIF = new IntentFilter();
        mIF.addAction(String.valueOf(R.string.broadcastForService));


        registerReceiver(tmp = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Boolean cancelar = intent.getExtras().getBoolean("Resposta");
                if (cancelar) {
                    task.cancel(true);
                }
            }
        }, mIF);
    }


    public IBinder onBind(Intent i) {
        return null;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        unregisterReceiver(tmp);


    }
}
