package joaquin.busog;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import butterknife.BindView;

public class MenuActivity extends ListActivity {

    //@BindView(R.id.categorySpinner) Spinner mCategorySpinner;
    private String csv = "Burgers";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Spinner mCategorySpinner = (Spinner) findViewById(R.id.categorySpinner);

        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String category = (String) parent.getItemAtPosition(pos);
                if(category.equals("Add-Ons")) {
                    csv = category;
                    createMenu();
                }
                else if(category.equals("Breakfast")) {
                    csv = category;
                    createMenu();
                }
                else if(category.equals("Burgers")) {
                    csv = category;
                    createMenu();
                }
                else if(category.equals("Combos")) {
                    csv = category;
                    createMenu();
                }
                else if(category.equals("Desserts")) {
                    csv = category;
                    createMenu();
                }
                else if(category.equals("Drinks")) {
                    csv = category;
                    createMenu();
                }
                else if(category.equals("Chicken & Spaghetti")) {
                    csv = "Chicken-Spaghetti-1";
                    createMenu();
                }
            }
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void createMenu() {
        String next[];
        ArrayList<String[]> list = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open("McDo-" + csv + ".csv")));
            for(;;) {
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

        String[] menu = new String[list.size()];

        for(int i = 0; i < list.size(); i++) {
            menu[i] = list.get(i)[0];
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menu);
        setListAdapter(adapter);
    }
}
