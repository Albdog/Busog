package joaquin.busog.mealPlan;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import joaquin.busog.R;

public class SummaryAdapter extends BaseAdapter {

    private Context mContext;

    public SummaryAdapter(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return MenuAdapter.orders.size();
    }

    @Override
    public Object getItem(int i) {
        return MenuAdapter.orders.get(i);
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
                    AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                    builder.setTitle("Remove item?");
                    builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            MenuAdapter.orders.remove(i);
                            notifyDataSetChanged();

                            SummaryActivity.calculate();

                            Toast.makeText(mContext, "Item removed.", Toast.LENGTH_SHORT).show();
                            dialog.dismiss();
                        }
                    });
                    builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.dismiss();
                        }
                    });
                    AlertDialog dialog = builder.create();
                    dialog.show();
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
        String itemName = MenuAdapter.orders.get(i).getItemName();
        double price = MenuAdapter.orders.get(i).getPrice();
        String mealType = MenuAdapter.orders.get(i).getMealType();
        int quantity = MenuAdapter.orders.get(i).getQuantity();
        int image = MenuAdapter.orders.get(i).getImage();

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
}
