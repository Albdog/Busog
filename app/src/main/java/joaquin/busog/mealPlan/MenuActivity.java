package joaquin.busog.mealPlan;

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
import joaquin.busog.R;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.menuGrid) GridView menu;
    @BindView(R.id.budgetInput) EditText budgetInput;

    private static String mRestaurant;
    private static int mImage;

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

    @OnClick (R.id.drinkButton)
    public void drink() {
        loadItems("Drinks");
    }

    @OnClick (R.id.otherButton)
    public void other() {
        loadItems("Others");
    }

    @OnClick (R.id.summaryButton)
    public void cart() {
        SummaryActivity.setRestaurant(mRestaurant, mImage);

        Intent intent = new Intent(this, SummaryActivity.class);
        startActivity(intent);
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

    public static void viewMenu(Context context, String restaurant, int image) {
        mRestaurant = restaurant;
        mImage = image;

        Intent intent = new Intent(context, MenuActivity.class);
        context.startActivity(intent);
    }

    public void updateBudget(double price) {
        double budget = getBudget();
        budget -= price;
        if(budget < 0) budget = 0;

        budgetInput.setText(String.format("%.2f", budget));
    }

    public double getBudget() {
        try {
            return Double.parseDouble(budgetInput.getText().toString());
        }
        catch (NumberFormatException nfe) {
            return 0;
        }
    }
}
