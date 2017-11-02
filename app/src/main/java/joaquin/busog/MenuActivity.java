package joaquin.busog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.menuGrid) GridView menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

//        // my_child_toolbar is defined in the layout file
//        Toolbar mealCreationToolbar = findViewById(R.id.mealCreationToolbar);
//        setSupportActionBar(mealCreationToolbar);
//
//        // Get a support ActionBar corresponding to this toolbar
//        ActionBar ab = getSupportActionBar();
//
//        // Enable the Up button
//        ab.setDisplayHomeAsUpEnabled(true);

        loadItems("burger");
    }

    @OnClick (R.id.burgerButton)
    public void burger() {
        loadItems("burger");
    }

    @OnClick (R.id.riceMealButton)
    public void riceMeal() {
        loadItems("riceMeal");
    }

    @OnClick (R.id.breakfastButton)
    public void breakfast() {
        loadItems("breakfast");
    }

    @OnClick (R.id.dessertButton)
    public void dessert() {
        loadItems("dessert");
    }

    @OnClick (R.id.otherButton)
    public void other() {
        loadItems("other");
    }

    private void loadItems(String category) {
        String csvName = "";

        switch (category) {
            case "burger":
                csvName = "Burgers";
                break;
            case "riceMeal":
                csvName = "Rice-Meals";
                break;
            case "breakfast":
                csvName = "Breakfast";
                break;
            case "dessert":
                csvName = "Desserts";
                break;
            case "other":
                csvName = "Others";
                break;
        }

        String next[];
        ArrayList<String[]> list = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open("CSV/McDo-" + csvName + ".csv")));
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
            menuItems[i] = list.get(i)[1];
        }

        MenuAdapter menuAdapter = new MenuAdapter(this, menuItems);
        menu.setAdapter(menuAdapter);
    }
}
