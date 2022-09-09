package hardroid.quiz.season1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

import hardroid.quiz.MainActivity;
import hardroid.quiz.MainMenu;
import hardroid.quiz.Options;
import hardroid.quiz.R;
import hardroid.quiz.Timer;

public class GameLevels_S1 extends AppCompatActivity{

    private Button Back;
    private TextView Level1,Level2,Level3,Level4,Level5,Level6,Level7,Level8,Level9,Level10,Level11,Level12,Level13,Level14,Level15,Level16;

    @Override
    public  void onConfigurationChanged(@NonNull Configuration newConfiguration){
        super.onConfigurationChanged(newConfiguration);
    }

    @Override
    public void onBackPressed(){
        gotoMainMenu();
        super.onBackPressed();
    }



    private  void gotoMainMenu(){
        try {
            Intent intent = new Intent(this, MainMenu.class);
            startActivity(intent);
            finish();
        }
        catch (Exception exp){

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gamelevels);

        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
        final int level = save.getInt("Level",1);



        GameLevels_S1_Options  options  = new GameLevels_S1_Options();
        options.setOptions(this);







        Back = findViewById(R.id.season_back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainMenu();
            }
        });

        Level1 = (TextView) findViewById(R.id.season1_level1);
        Level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=1) {
                    PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_1.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });

        Level2 = (TextView) findViewById(R.id.season1_level2);
        Level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=2) {
                    PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_2.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });

        Level3 = (TextView) findViewById(R.id.season1_level3);
        Level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=3) {
                    PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_3.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });

        Level4 = (TextView) findViewById(R.id.season1_level4);
        Level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=4) {
                    PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_4.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });

        Level5 = (TextView) findViewById(R.id.season1_level5);
        Level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=5) {
                    PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_5.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });

        Level6 = (TextView) findViewById(R.id.season1_level6);
        Level6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=6) {
                    PlayMusic.Pause();
                    Intent intent = new Intent(GameLevels_S1.this, Level_6.class);
                    startActivity(intent);
                    finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });


        Level7 = (TextView) findViewById(R.id.season1_level7);
        Level7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=7) {
                        PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_7.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });

        Level8 = (TextView) findViewById(R.id.season1_level8);
        Level8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=8) {
                        PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_8.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });

        Level9 = (TextView) findViewById(R.id.season1_level9);
        Level9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=9) {
                        PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_9.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });

        Level10 = (TextView) findViewById(R.id.season1_level10);
        Level10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=10) {
                        PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_10.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });

        Level11 = (TextView) findViewById(R.id.season1_level11);
        Level11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=11) {
                        PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_11.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });

        Level12 = (TextView) findViewById(R.id.season1_level12);
        Level12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=12) {
                        PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_12.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });



        Level13 = (TextView) findViewById(R.id.season1_level13);
        Level13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=13) {
                        PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_13.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });

        Level14 = (TextView) findViewById(R.id.season1_level14);
        Level14.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=14) {
                        PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_14.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });


        Level15 = (TextView) findViewById(R.id.season1_level15);
        Level15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if(level>=15) {
                        PlayMusic.Pause();
                        Intent intent = new Intent(GameLevels_S1.this, Level_15.class);
                        startActivity(intent);
                        finish();
                    }
                }
                catch (Exception exc){

                }
            }
        });


        final int[] x = {
                R.id.season1_level1,
                R.id.season1_level2,
                R.id.season1_level3,
                R.id.season1_level4,
                R.id.season1_level5,
                R.id.season1_level6,
                R.id.season1_level7,
                R.id.season1_level8,
                R.id.season1_level9,
                R.id.season1_level10,
                R.id.season1_level11,
                R.id.season1_level12,
                R.id.season1_level13,
                R.id.season1_level14,
                R.id.season1_level15
        };



        for(int i = 1; i < level ; i++){
            TextView tv = findViewById(x[i]);
            tv.setText(""+(i+1));
        }



    }

    private class GameLevels_S1_Options extends Options {
        private void setOptions(Activity activity){
            HideAndroidConditionBar(activity);
        }
    }
}