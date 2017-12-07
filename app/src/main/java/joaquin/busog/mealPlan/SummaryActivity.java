package joaquin.busog.mealPlan;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import joaquin.busog.R;

public class SummaryActivity extends AppCompatActivity {

    @BindView(R.id.restaurantName) TextView restaurantName;
    @BindView(R.id.restaurantImage) ImageView restaurantImage;
    @BindView(R.id.orderList) ListView orderList;
    @BindView(R.id.timeStamp) TextView timeStamp;
    @BindView(R.id.orderQuantity) TextView orderQuantity;
    @BindView(R.id.cost) TextView cost;

    private static String mRestaurant;
    private static int mImage, totalQuantity;
    private static double totalCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);
        ButterKnife.bind(this);

        restaurantName.setText(mRestaurant);
        restaurantImage.setImageResource(mImage);

        calculate();
        updateUI();

        SummaryAdapter summaryAdapter = new SummaryAdapter(this);
        orderList.setAdapter(summaryAdapter);
    }

    public static void setRestaurant(String restaurant, int image) {
        mRestaurant = restaurant;
        mImage = image;
    }

    public static void calculate() {
        totalCost = 0;
        totalQuantity = 0;

        for(int i = 0; i < MenuAdapter.ordersList.size(); i++) {
            double price = MenuAdapter.ordersList.get(i).getPrice();
            int quantity = MenuAdapter.ordersMap.get(MenuAdapter.ordersList.get(i).getItemName() + MenuAdapter.ordersList.get(i).getMealType()).getQuantity();

            totalCost += price * quantity;
            totalQuantity += quantity;
        }
    }

    public void updateUI() {
        orderQuantity.setText("Total # of Items: " + totalQuantity);
        cost.setText("Total: Php " + String.format("%.2f", totalCost));
    }
}