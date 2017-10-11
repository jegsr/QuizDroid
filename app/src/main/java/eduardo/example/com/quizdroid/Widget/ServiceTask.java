package eduardo.example.com.quizdroid.Widget;


import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;

import eduardo.example.com.database.Classes.Pergunta;

public class ServiceTask extends AsyncTask<Pergunta, Integer, Integer> {
    private Context mContext;



    public ServiceTask(Context context) {
        this.mContext = context;
    }


    protected void onPreExecute() {
        super.onPreExecute();
    }


    protected Integer doInBackground(Pergunta... pergunta) {

        Intent mIntent = new Intent(mContext, PerguntaService.class);
        mContext.startService(mIntent);
        return -1;
    }

    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);
    }
}
