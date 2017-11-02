package joaquin.busog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Joaquin on 10/23/2017.
 */

public class MenuAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mRestaurant;

    public MenuAdapter(Context context, String[] restaurant) {
        mContext = context;
        mRestaurant = restaurant;
    }

    @Override
    public int getCount() {
        return mRestaurant.length;
    }

    @Override
    public Object getItem(int i) {
        return mRestaurant[i];
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewThing thing;

        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.restaurant_entry, null);
            thing = new ViewThing();
            thing.restaurantName = (TextView) view.findViewById(R.id.restaurantName);

            final View finalView = view;
            thing.restaurantName.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(finalView.getContext(), "tests", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(finalView.getContext(), MenuActivity.class);
                    finalView.getContext().startActivity(intent);
                }
            });
        }
        else {
            thing = (ViewThing) view.getTag();
        }

        updateUI(i, thing);

        return view;
    }

    private void updateUI(int i, ViewThing thing) {
        String name = mRestaurant[i];

        thing.restaurantName.setText(name);
    }

    private static class ViewThing extends AppCompatActivity{
        TextView restaurantName;
    }
}
