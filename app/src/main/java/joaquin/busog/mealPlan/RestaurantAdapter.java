package joaquin.busog.mealPlan;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import joaquin.busog.R;

public class RestaurantAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mRestaurants;
    private ArrayList<String[]> mList;

    public RestaurantAdapter(Context context, String[] restaurants, ArrayList<String[]> list) {
        mContext = context;
        mRestaurants = restaurants;
        mList = list;
    }

    @Override
    public int getCount() {
        return mRestaurants.length;
    }

    @Override
    public Object getItem(int i) {
        return mRestaurants[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final RestaurantView restaurantView;

        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.restaurant_item, null);
            restaurantView = new RestaurantView();

            restaurantView.restaurantName = view.findViewById(R.id.restaurantName);
            restaurantView.restaurantImage = view.findViewById(R.id.restaurantImage);

            view.setTag(restaurantView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    MenuActivity.viewMenu(mContext, (String) restaurantView.restaurantName.getText(), (Integer) restaurantView.restaurantImage.getTag());
                }
            });
        }
        else {
            restaurantView = (RestaurantView) view.getTag();
        }

        updateUI(i, restaurantView);

        return view;
    }

    private void updateUI(int i, RestaurantView view) {
        String name = mRestaurants[i];
        String imageString = mList.get(i)[2];

        Resources resources = mContext.getResources();
        int mImage = resources.getIdentifier(imageString, "drawable", mContext.getPackageName());

        view.restaurantName.setText(name);
        view.restaurantImage.setImageResource(mImage);
        view.restaurantImage.setTag(mImage);
    }

    private static class RestaurantView extends AppCompatActivity {
        ImageView restaurantImage;
        TextView restaurantName;
    }
}
