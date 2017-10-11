package eduardo.example.com.quizdroid.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import java.util.ArrayList;

import eduardo.example.com.database.Classes.Pergunta;
import eduardo.example.com.database.GerirPerguntas;
import eduardo.example.com.quizdroid.R;

public class MyBroadcastReceiverWidget extends AppWidgetProvider {

    private Pergunta pergunta;
    private Intent mIntentService = new Intent(String.valueOf(R.string.broadcastForService));



    @Override
    public void onReceive(Context context, Intent intent) {

        ComponentName thisWidget = new ComponentName(context, MyBroadcastReceiverWidget.class);
        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);

        if(intent.hasExtra("novaPergunta"))
            pergunta = (Pergunta) intent.getSerializableExtra("novaPergunta");

        if(intent.hasExtra("onClick")){
            String resposta = intent.getStringExtra("onClick");

            mIntentService.putExtra("Resposta", pergunta.getRespostaCerta().equals(resposta));
            context.sendBroadcast(mIntentService);


        }




        // executa onUpdate sempre que recebe um Intent neste caso trata-se de um Intents do tipo
        onUpdate(context, appWidgetManager, appWidgetManager.getAppWidgetIds(thisWidget));

        super.onReceive(context, intent);
    }

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {


        final int N = appWidgetIds.length;
        for (int i = 0; i < N; i++) {
            int appWidgetId = appWidgetIds[i];
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }

    }

    private void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        // cria um objecto RemoteViews com o layout da widget
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget_layout);
        views.setTextViewText(R.id.txtPergunta, pergunta.getPergunta());
        ArrayList<String> tmpRespostas = pergunta.getRespostasErradas();
        tmpRespostas.add(pergunta.getRespostaCerta());
        String btnA;
        String btnB;
        String btnC;
        String btnD;
        views.setTextViewText(R.id.btnA,btnA = tmpRespostas.remove(GerirPerguntas.gerarNumeros(tmpRespostas.size())));
        views.setTextViewText(R.id.btnB,btnB = tmpRespostas.remove(GerirPerguntas.gerarNumeros(tmpRespostas.size())));
        views.setTextViewText(R.id.btnC,btnC = tmpRespostas.remove(GerirPerguntas.gerarNumeros(tmpRespostas.size())));
        views.setTextViewText(R.id.btnD,btnD = tmpRespostas.remove(GerirPerguntas.gerarNumeros(tmpRespostas.size())));


        Intent intent = new Intent("eduardo.example.com.quizdroid.widget");
        intent.putExtra("onClick",btnA);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.btnA,pendingIntent);

        intent.putExtra("onClick",btnB);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.btnB,pendingIntent);

        intent.putExtra("onClick",btnC);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.btnC,pendingIntent);

        intent.putExtra("onClick",btnD);
        pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);
        views.setOnClickPendingIntent(R.id.btnD,pendingIntent);

        appWidgetManager.updateAppWidget(appWidgetId, views);
    }
}
