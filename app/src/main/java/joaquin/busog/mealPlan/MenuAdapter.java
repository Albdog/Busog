package joaquin.busog.mealPlan;

import android.content.Context;
import android.content.res.Resources;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import java.util.ArrayList;

import joaquin.busog.R;

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
    public View getView(final int i, View view, ViewGroup viewGroup) {
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

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final OrderView orderView;
                    
                    AlertDialog.Builder orderBuilder = new AlertDialog.Builder(mContext);
                    view = LayoutInflater.from(mContext).inflate(R.layout.order_dialog, null);
                    orderView = new OrderView();
                    orderView.itemName = view.findViewById(R.id.itemName);
                    orderView.alaCarteRadioButton = view.findViewById(R.id.alaCarteRadioButton);
                    orderView.smallRadioButton = view.findViewById(R.id.smallRadioButton);
                    orderView.mediumRadioButton = view.findViewById(R.id.mediumRadioButton);
                    orderView.largeRadioButton = view.findViewById(R.id.largeRadioButton);
                    orderView.cancelButton = view.findViewById(R.id.cancelButton);
                    orderView.orderButton = view.findViewById(R.id.orderButton);

                    updateDialogUI(i, orderView);
                    
                    orderBuilder.setView(view);
                    final AlertDialog dialog = orderBuilder.create();
                    dialog.show();

                    orderView.cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.hide();
                        }
                    });
                    
                    orderView.orderButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            //method to add item to cart
                            dialog.hide();
                        }
                    });
                }
            });
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

        if(alaCarte.equals("N/A")) view.alaCartePrice.setText("--");
        else view.alaCartePrice.setText(Html.fromHtml("<b>AC</b> " + alaCarte + ".00"));

        if(small.equals("N/A")) view.smallPrice.setText("--");
        else view.smallPrice.setText(Html.fromHtml("<b>S</b> " + small + ".00"));

        if(medium.equals("N/A")) view.mediumPrice.setText("--");
        else view.mediumPrice.setText(Html.fromHtml("<b>M</b> " + medium + ".00"));

        if(large.equals("N/A")) view.largePrice.setText("--");
        else view.largePrice.setText(Html.fromHtml("<b>L</b> " + large + ".00"));

        switch (mCategory) {
            case "Burgers":
                view.itemImage.setImageResource(R.drawable.burgers);
                break;
            case "Rice-Meals":
                view.itemImage.setImageResource(R.drawable.rice_meals);
                break;
//            case "Breakfast":
//                view.itemImage.setImageResource(R.drawable.breakfast);
//                break;
            case "Desserts":
                view.itemImage.setImageResource(R.drawable.dessert);
                break;
            case "Drinks":
                view.itemImage.setImageResource(R.drawable.drink);
                break;
            case "Others":
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

    private void updateDialogUI(int i, OrderView view) {
        String name = mMenuItems[i];
        String alaCarte = mList.get(i)[2];
        String small = mList.get(i)[3];
        String medium = mList.get(i)[4];
        String large = mList.get(i)[5];
        
        view.itemName.setText(name);

        if(alaCarte.equals("N/A")) view.alaCarteRadioButton.setClickable(false);
        else view.alaCarteRadioButton.setText("Ala Carte Php " + alaCarte + ".00");

        if(small.equals("N/A")) view.smallRadioButton.setClickable(false);
        else view.smallRadioButton.setText("Small Php " + small + ".00");

        if(medium.equals("N/A")) view.mediumRadioButton.setClickable(false);
        else view.mediumRadioButton.setText("Medium Php " + medium + ".00");

        if(large.equals("N/A")) view.largeRadioButton.setClickable(false);
        else view.largeRadioButton.setText("Large Php " + large + ".00");
    }
    
    private static class OrderView extends AppCompatActivity {
        TextView itemName;
        RadioButton alaCarteRadioButton, smallRadioButton, mediumRadioButton, largeRadioButton;
        Button cancelButton, orderButton;
    }
}
