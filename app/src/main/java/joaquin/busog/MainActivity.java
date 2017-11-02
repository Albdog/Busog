package joaquin.busog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.restaurantsButton)
    public void startRestaurantActivity(View view) {
        Intent intent = new Intent(this, RestaurantsActivity.class);
        startActivity(intent);
    }

    @OnClick(R.id.nearbyButton)
    public void startMenuActivity(View view) {
        Toast.makeText(this, "test", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, MenuActivity.class);
        startActivity(intent);
    }
}
