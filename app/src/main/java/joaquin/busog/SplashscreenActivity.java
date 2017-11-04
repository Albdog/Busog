package joaquin.busog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Migue909 on 04/11/2017.
 */

public class SplashscreenActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_activity);

        Thread t = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                    Intent jumpToHome = new Intent(SplashscreenActivity.this, NavigationDrawerActivity.class);
                    startActivity(jumpToHome);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        t.start();
    }
}
