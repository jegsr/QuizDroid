package eduardo.example.com.quizdroid.Widget;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.preference.PreferenceManager;

import eduardo.example.com.database.Classes.Pergunta;
import eduardo.example.com.quizdroid.R;

public class PerguntaTask extends AsyncTask<Pergunta, Integer, Integer> {
    private Context mContext;
    private Intent mIntentWidget;



    public PerguntaTask(Context context) {
        this.mContext = context;
    }


    protected void onPreExecute() {
        super.onPreExecute();
        mIntentWidget = new Intent("eduardo.example.com.quizdroid.widget");
    }


    protected Integer doInBackground(Pergunta... pergunta) {
        mIntentWidget.putExtra("novaPergunta", pergunta[0]);
        mContext.sendBroadcast(mIntentWidget);

        try {
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(mContext);
            //sleep funciona em milisegundos :: 60 000ms = 1min ::
            Thread.sleep(preferences.getInt("widget_time",60)*60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return -1;
    }



    //vem para este metodo quando o doInBackground termina
    //fazer para enviar notificaçao
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);


    }
}
