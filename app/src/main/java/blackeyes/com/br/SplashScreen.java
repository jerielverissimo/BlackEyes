package blackeyes.com.br;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;




public class SplashScreen extends AppCompatActivity implements Runnable{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        Handler SplashScreen = new Handler();
        SplashScreen.postDelayed(SplashScreen.this, 3000);

    }

    @Override
    public void run() {
        startActivity(new Intent(SplashScreen.this, MainActivity.class));
        finish();
    }


}
