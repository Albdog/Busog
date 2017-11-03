package joaquin.busog;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class RestaurantAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mRestaurants;

    public RestaurantAdapter(Context context, String[] restaurants) {
        mContext = context;
        mRestaurants = restaurants;
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
        Log.e("nsnsy mo", mRestaurants.length + "");
        final RestaurantView restaurantView;

        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.restaurant_item, null);
            restaurantView = new RestaurantView();

            restaurantView.restaurantName = view.findViewById(R.id.restaurantName);
            restaurantView.restaurantImage = view.findViewById(R.id.restaurantImage);

            view.setTag(restaurantView);
        }
        else {
            restaurantView = (RestaurantView) view.getTag();
        }

        updateUI(i, restaurantView);

        return view;
    }

    private void updateUI(int i, RestaurantView view) {
        String name = mRestaurants[i];

        view.restaurantName.setText(name);
    }

    private static class RestaurantView extends AppCompatActivity {
        ImageView restaurantImage;
        TextView restaurantName;
    }
}
