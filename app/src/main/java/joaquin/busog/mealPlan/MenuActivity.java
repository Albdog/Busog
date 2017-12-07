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
import butterknife.OnTextChanged;
import joaquin.busog.R;

public class MenuActivity extends AppCompatActivity {

    @BindView(R.id.menuGrid) GridView menu;
    @BindView(R.id.budgetInput) EditText budgetInput;

    private static String mRestaurant;
    private static int mImage;
    private static double mBudget;
    public static final String KEY_EDITTEXT = "budget";
    private DataStore mDataStore;
    private String[] mMenuItems;
    private ArrayList<String[]> mList;
    private String mCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        ButterKnife.bind(this);

        loadItems("Burgers");
        mDataStore = new DataStore(this);

        String setBudget = mDataStore.getString(KEY_EDITTEXT);
        budgetInput.setText(setBudget);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mDataStore.setString(KEY_EDITTEXT, budgetInput.getText().toString());
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

    @OnTextChanged (R.id.budgetInput)
    public void dynamicMenu() {
        if(!budgetInput.getText().toString().equals("")) {
            ArrayList<String[]> newList = new ArrayList<>();
            for(int i = 0; i < mList.size(); i++) {
                if(!mList.get(i)[2].equals("N/A")) {
                    if(Double.parseDouble(mList.get(i)[2]) <= Double.parseDouble(budgetInput.getText().toString())) newList.add(mList.get(i));
                }
                else if(!mList.get(i)[3].equals("N/A")) {
                    if(Double.parseDouble(mList.get(i)[3]) <= Double.parseDouble(budgetInput.getText().toString())) newList.add(mList.get(i));
                }
                else {
                    if(Double.parseDouble(mList.get(i)[4]) <= Double.parseDouble(budgetInput.getText().toString()))  newList.add(mList.get(i));
                }
            }

            String[] newMenuItems = new String[newList.size()];
            for(int j = 0; j < newList.size(); j++) {
                newMenuItems[j] = newList.get(j)[1];
            }

            updateMenu(newMenuItems, newList, mCategory);
        }
        else updateMenu(mMenuItems, mList, mCategory);
    }

    private void loadItems(String category) {
        mCategory = category;
        String next[];
        mList = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open("CSV/" + mRestaurant + "-" + category + ".csv")));
            while(true) {
                next = reader.readNext();
                if(next != null) {
                    mList.add(next);
                } else {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        mMenuItems = new String[mList.size()];

        for(int i = 0; i < mList.size(); i++) {
            mMenuItems[i] = mList.get(i)[1];
        }

        updateMenu(mMenuItems, mList, mCategory);
        dynamicMenu();
    }

    public static void viewMenu(Context context, String restaurant, int image) {
        mRestaurant = restaurant;
        mImage = image;

        Intent intent = new Intent(context, MenuActivity.class);
        context.startActivity(intent);
    }

    public void updateBudget(double price) {
        mBudget = getBudget();
        mBudget -= price;
        if(mBudget < 0) mBudget = 0;

        budgetInput.setText(String.format("%.2f", mBudget));
    }

    public double getBudget() {
        try {
            return Double.parseDouble(budgetInput.getText().toString());
        }
        catch (NumberFormatException nfe) {
            return -1;
        }
    }

    private void updateMenu(String[] menuItems, ArrayList<String[]> list, String category) {
        MenuAdapter menuAdapter = new MenuAdapter(this, menuItems, list, category);
        menu.setAdapter(menuAdapter);
    }
}
