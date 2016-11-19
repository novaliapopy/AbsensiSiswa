package id.sch.smktelkom_mlg.project.xiirpl303132333.absensisiswa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

// import android.content.Intent;

public class activity_splash_screen extends Activity {

    // Set Waktu lamanya splash screen
    private static int splashInterval = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                //TODO Auto-generated method stub
                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);

                //Jeda selesai Splash screen
                this.finish();
            }

            private void finish() {
                //TODO Auto-generated method stub
            }
        }, splashInterval);
    }





        /* Thread thread = new Thread() {
            public void run() {
                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(activity_splash_screen.this, MainActivity.class));
                    finish();
                }
            }
        };
        thread.start(); */
}
