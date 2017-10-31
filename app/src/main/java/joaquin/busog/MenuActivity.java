package joaquin.busog;

import android.app.ListActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;

public class MenuActivity extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    private void loadItems(String category) {
        String next[];
        ArrayList<String[]> list = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open("CSV/McDo-Burgers.csv")));
            while(true) {
                next = reader.readNext();
                if(next != null) {
                    list.add(next);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] menuItems = new String[list.size()];

        for(int i = 0; i < list.size(); i++) {
            menuItems[i] = list.get(i)[0];
        }

        MenuAdapter menuAdapter = new MenuAdapter(this, menuItems);
        setListAdapter(menuAdapter);
    }
}
