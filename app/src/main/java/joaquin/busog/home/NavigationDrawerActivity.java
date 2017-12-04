package joaquin.busog.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import joaquin.busog.R;

/**
 * Created by Migue909 on 04/11/2017.
 */

public class NavigationDrawerActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private BottomNavigationView bottomNavigationView;
    private int pageSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigation_drawer);

        bottomNavigationView = findViewById(R.id.bottomNav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

        HomeScreenTabFragment fragment = new HomeScreenTabFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.add(R.id.fragmentContainer,fragment);
        fragmentTransaction.commit();
        pageSelected = 0;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Fragment selectedFragment = null;
        switch(item.getItemId()){
            case R.id.action_home:
                if(pageSelected != 0) {
                    pageSelected = 0;
                    selectedFragment = new HomeScreenTabFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer,selectedFragment);
                    fragmentTransaction.commit();
                }
                break;
            case R.id.action_map:
                if(pageSelected != 1) {
                    pageSelected = 1;
                    selectedFragment = new MapTabFragment();
                    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.fragmentContainer,selectedFragment);
                    fragmentTransaction.commit();
                }
                break;
            default:
                break;
        }
        return true;
    }
}
