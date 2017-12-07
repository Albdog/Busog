package joaquin.busog.mealPlan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import butterknife.BindView;
import butterknife.ButterKnife;
import joaquin.busog.R;

public class RestaurantActivity extends AppCompatActivity {

    @BindView(R.id.restaurantGrid) GridView grid;
    private DataStore mDataStore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        ButterKnife.bind(this);

        mDataStore = new DataStore(this);
        mDataStore.setString(MenuActivity.KEY_EDITTEXT, "");

        String next[];
        ArrayList<String[]> list = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open("CSV/Restaurants.csv")));
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

        String[] restaurants = new String[list.size()];

        for(int i = 0; i < list.size(); i++) {
            restaurants[i] = list.get(i)[1];
        }

        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this, restaurants, list);
        grid.setAdapter(restaurantAdapter);
    }
}
