package joaquin.busog;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MenuAdapter extends BaseAdapter {

    private Context mContext;
    private String[] mMenuItems;
    private ArrayList<String[]> mList;
    private String mCategory;

    public MenuAdapter(Context context, String[] menuItems, ArrayList<String[]> list, String category) {
        mContext = context;
        mMenuItems = menuItems;
        mList = list;
        mCategory = category;
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
        String alaCarte = mList.get(i)[2];
        String small = mList.get(i)[3];
        String medium = mList.get(i)[4];
        String large = mList.get(i)[5];
        String imageString = mList.get(i)[6];

        view.itemName.setText(name);

        if(alaCarte.equals("N/A")) {}
        else view.alaCartePrice.setText(Html.fromHtml("<b>AC</b> " + alaCarte + ".00"));

        if(small.equals("N/A")) {}
        else view.smallPrice.setText(Html.fromHtml("<b>S</b> " + small + ".00"));

        if(medium.equals("N/A")) {}
        else view.mediumPrice.setText(Html.fromHtml("<b>M</b> " + medium + ".00"));

        if(large.equals("N/A")) {}
        else view.largePrice.setText(Html.fromHtml("<b>L</b> " + large + ".00"));

        switch (mCategory) {
            case "burger":
                view.itemImage.setImageResource(R.drawable.burgers);
                break;
            case "riceMeal":
                view.itemImage.setImageResource(R.drawable.rice_meals);
                break;
//            case "breakfast":
//                view.itemImage.setImageResource(R.drawable.breakfast);
//                break;
            case "dessert":
                view.itemImage.setImageResource(R.drawable.dessert);
                break;
            case "other":
                view.itemImage.setImageResource(R.drawable.others);
                break;
        }

        if(imageString.equals("N/A")) {}
        else {
            Resources resources = mContext.getResources();
            int image = resources.getIdentifier(imageString, "drawable", mContext.getPackageName());
            view.itemImage.setImageResource(image);
        }
    }

    private static class MenuView extends AppCompatActivity {
        ImageView itemImage;
        TextView itemName, alaCartePrice, smallPrice, mediumPrice, largePrice;
    }
}
