package splashscreen.com.splashscreen;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

public class SplashScreenActivity extends AppCompatActivity {
    ProgressBar progressBar;
    Intent MainActivityGo;
    private int progressStatus = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        progressBar = findViewById(R.id.progressBar);
        progressBarLoading();
    }
    //function for load progress bar and go to next activity
    private void progressBarLoading() {
        new Thread(new Runnable() {
            public void run() {
                while (progressStatus < 100) {
                    progressStatus += 1;
                    handler.post(new Runnable() {
                        public void run() {
                            progressBar.setProgress(progressStatus);
                        }
                    });
                    try {
                        Thread.sleep(30);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                if (progressStatus == 100) {
                    //intent for go next page
                    MainActivityGo = new Intent(getApplicationContext(),MainActivity.class);
                    startActivity(MainActivityGo);
                }
            }
        }).start();
    }
}
