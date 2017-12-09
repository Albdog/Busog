package joaquin.busog.mealPlan;

import android.content.Context;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import joaquin.busog.R;

public class SummaryAdapter extends BaseAdapter {

    private Context mContext;
    private DataStore mDataStore;

    public SummaryAdapter(Context context) {
        mContext = context;
        mDataStore = new DataStore(mContext);
    }

    @Override
    public int getCount() {
        return MenuAdapter.ordersMap.size();
    }

    @Override
    public Object getItem(int i) {
        return MenuAdapter.ordersMap.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        final OrderView orderView;

        if(view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.summary_meal_item, null);
            orderView = new OrderView();

            orderView.itemImage = view.findViewById(R.id.itemImage);
            orderView.itemName = view.findViewById(R.id.itemName);
            orderView.quantity = view.findViewById(R.id.quantity);
            orderView.price = view.findViewById(R.id.price);
            orderView.mealType = view.findViewById(R.id.mealType);
            orderView.removeButton = view.findViewById(R.id.removeButton);

            view.setTag(orderView);

            orderView.removeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final RemoveView removeView;

                    AlertDialog.Builder removeBuilder = new AlertDialog.Builder(mContext);
                    view = LayoutInflater.from(mContext).inflate(R.layout.remove_dialog, null);
                    removeView = new RemoveView();
                    removeView.quantitySpinner = view.findViewById(R.id.quantitySpinner);
                    removeView.cancelButton = view.findViewById(R.id.cancelButton);
                    removeView.removeButton = view.findViewById(R.id.removeButton);

                    removeView.cancelButton.setText("Cancel");
                    removeView.removeButton.setText("Remove");

                    updateRemoveUI((String) orderView.quantity.getText(), removeView);

                    removeBuilder.setView(view);
                    final AlertDialog dialog = removeBuilder.create();

                    dialog.show();

                    removeView.cancelButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.hide();
                        }
                    });

                    removeView.removeButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            String[] mealType = ((String) orderView.mealType.getText()).split(" ");
                            try {
                                mealType[2] += " " + mealType[3];
                            }
                            catch (ArrayIndexOutOfBoundsException e) {
                                e.printStackTrace();
                            }
                            String key = orderView.itemName.getText() + mealType[2];
                            MenuAdapter.ordersMap.get(key).decQuantity(Integer.parseInt(removeView.quantitySpinner.getSelectedItem().toString()));

                            if(!mDataStore.getString(MenuActivity.KEY_EDITTEXT).equals("")) {
                                double budgetInc = MenuAdapter.ordersMap.get(key).getPrice() * Integer.parseInt(removeView.quantitySpinner.getSelectedItem().toString());
                                double budget = Double.parseDouble(mDataStore.getString(MenuActivity.KEY_EDITTEXT));
                                mDataStore.setString(MenuActivity.KEY_EDITTEXT, String.format("%.2f", (budget + budgetInc)));
                            }

                            if(MenuAdapter.ordersMap.get(key).getQuantity() == 0) {
                                MenuAdapter.ordersMap.remove(key);
                                MenuAdapter.ordersList.remove(i);
                                Toast.makeText(mContext, "Item removed.", Toast.LENGTH_SHORT).show();
                                notifyDataSetChanged();
                            }
                            else {
                                Toast.makeText(mContext, "Removed " + removeView.quantitySpinner.getSelectedItem().toString() + " item(s) from order.", Toast.LENGTH_SHORT).show();
                                updateOrderUI(i, orderView);
                            }

                            SummaryActivity.calculate();
                            ((SummaryActivity)mContext).updateUI();

                            dialog.hide();
                        }
                    });
                }
            });
        }
        else {
            orderView = (OrderView) view.getTag();
        }

        updateOrderUI(i, orderView);

        return view;
    }

    private void updateOrderUI(int i, OrderView view) {
        String itemName = MenuAdapter.ordersList.get(i).getItemName();
        double price = MenuAdapter.ordersList.get(i).getPrice();
        String mealType = MenuAdapter.ordersList.get(i).getMealType();
        int quantity = MenuAdapter.ordersMap.get(itemName + mealType).getQuantity();
        int image = MenuAdapter.ordersList.get(i).getImage();

        view.itemName.setText(itemName);
        view.mealType.setText("Meal Type: " + mealType);
        view.quantity.setText("Quantity: " + quantity);
        view.price.setText("Price: Php " + String.format("%.2f", price));
        view.itemImage.setImageResource(image);
    }

    private static class OrderView extends AppCompatActivity {
        ImageView itemImage;
        TextView itemName, quantity, price, mealType;
        Button removeButton;
    }

    private static class RemoveView extends AppCompatActivity {
        Spinner quantitySpinner;
        Button cancelButton, removeButton;
    }

    private void updateRemoveUI(String quantity, RemoveView view) {
        ArrayList<String> quantityList = new ArrayList<>();
        String[] split = quantity.split(" ");

        for(int i = 1; i <= Integer.parseInt(split[1]); i++) {
            quantityList.add(Integer.toString(i));
        }
        ArrayAdapter<String> adapter = new ArrayAdapter<>(mContext, android.R.layout.simple_spinner_item, quantityList);
        view.quantitySpinner.setAdapter(adapter);
    }
}
