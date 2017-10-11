package eduardo.example.com.quizdroid;

import android.os.Bundle;
import android.preference.ListPreference;
import android.preference.PreferenceFragment;

import java.util.ArrayList;

import eduardo.example.com.database.GerirNiveis;


public class PreferencesFragment extends PreferenceFragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.preferences);

        ListPreference nivel_widget = (ListPreference)
                findPreference("widget_nivel");


        ArrayList<String> tmpNiveis = GerirNiveis.listNameNivel(getActivity());
        if(tmpNiveis.size() > 0) {
            String[] tmpString = new String[tmpNiveis.size()];
            int i = 0;
            while (tmpNiveis.size() > 0) {
                tmpString[i++] = tmpNiveis.remove(0);
            }
            nivel_widget.setEntries(tmpString);
            nivel_widget.setEntryValues(tmpString);
            nivel_widget.setValue(tmpString[0]);
        }

    }
}
