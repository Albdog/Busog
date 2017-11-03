package joaquin.busog;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import au.com.bytecode.opencsv.CSVReader;
import butterknife.BindView;
import butterknife.ButterKnife;

public class RestaurantActivity extends AppCompatActivity {

    @BindView(R.id.restaurantGrid) GridView grid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurant);
        ButterKnife.bind(this);

        String next[];
        ArrayList<String[]> list = new ArrayList<>();
        try {
            Log.e("supot", "pakyu");
            CSVReader reader = new CSVReader(new InputStreamReader(getAssets().open("CSV/Restaurants.csv")));
            Log.e("supot", "supot");
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
        Log.e("supot", restaurants.length + "");

        RestaurantAdapter restaurantAdapter = new RestaurantAdapter(this, restaurants);
        grid.setAdapter(restaurantAdapter);
    }
}
