package joaquin.busog;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
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
    @BindView(R.id.budgetInput) EditText budget;

    private static String mRestaurant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        loadItems("Burgers");
    }

    @OnClick (R.id.burgerButton)
    public void burger() {
        loadItems("Burgers");
    }

    @OnClick (R.id.riceMealButton)
    public void riceMeal() {
        loadItems("Rice-Meals");
    }

//    @OnClick (R.id.breakfastButton)
//    public void breakfast() {
//        loadItems("Breakfast");
//    }

    @OnClick (R.id.dessertButton)
    public void dessert() {
        loadItems("Desserts");
    }

    @OnClick (R.id.otherButton)
    public void other() {
        loadItems("Others");
    }

    private void loadItems(String category) {

        String next[];
        ArrayList<String[]> list = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open("CSV/" + mRestaurant + "-" + category + ".csv")));
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

        MenuAdapter menuAdapter = new MenuAdapter(this, menuItems, list, category);
        menu.setAdapter(menuAdapter);
    }

    public static void viewMenu(Context context, String restaurant) {
        mRestaurant = restaurant;

        Intent intent = new Intent(context, MenuActivity.class);
        context.startActivity(intent);
    }
}
