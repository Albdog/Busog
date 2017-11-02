package joaquin.busog;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MenuAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mMenuItems;

    public MenuAdapter(Context context, String[] menuItems) {
        mContext = context;
        mMenuItems = menuItems;
    }

    @Override
    public int getCount() {
        return mMenuItems.length;
    }

    @Override
    public Object getItem(int i) {
        return mMenuItems[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final MenuView menuView;

        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.menu_item, null);
            menuView = new MenuView();
            menuView.itemImage = view.findViewById(R.id.itemImage);
            menuView.itemName = view.findViewById(R.id.itemName);
            menuView.alaCartePrice = view.findViewById(R.id.alaCartePrice);
            menuView.smallPrice = view.findViewById(R.id.smallPrice);
            menuView.mediumPrice = view.findViewById(R.id.mediumPrice);
            menuView.largePrice = view.findViewById(R.id.largePrice);

//            itemName.setText(mMenuItems[i]);
//            itemName.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Toast.makeText(mContext, "tests", Toast.LENGTH_SHORT).show();
//                }
//            });
            view.setTag(menuView);
        }
        else {
            menuView = (MenuView) view.getTag();
        }

        updateUI(i, menuView);

        return view;
    }

    private void updateUI(int i, MenuView view) {
        String name = mMenuItems[i];

        view.itemName.setText(name);
    }

    private static class MenuView extends AppCompatActivity {
        ImageView itemImage;
        TextView itemName, alaCartePrice, smallPrice, mediumPrice, largePrice;
    }
}
