package inventory.realm.app.jsmtech.inventoryrealm;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Sukriti on 6/22/16.
 */

public class SplashStarterActivity extends AppCompatActivity{

        Thread sP;

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);

            TextView heading = (TextView) findViewById(R.id.heading);

            Typeface fontForHeading = Typeface.createFromAsset(getAssets(), "fonts/headline_1.ttf");
            heading.setTypeface(fontForHeading);




            sP = new Thread() {

                @Override
                public void run() {
                    try {
                        int wait = 0;
                        while (wait < 3000) { // In Milliseconds
                            sleep(100);
                            wait += 100;
                        }
                        // So this while loop will be called for 3 seconds or 3000 milliseoncds.

                        // Now we go to Main Activity
                        Intent intent = new Intent(SplashStarterActivity.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                        SplashStarterActivity.this.finish();


                    } catch (Exception e) {

                    } finally {
                        SplashStarterActivity.this.finish();
                    }
                }
            };

            sP.start();


        }

    }


