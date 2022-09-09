package hardroid.quiz;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Trace;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.security.spec.ECField;

import hardroid.quiz.season1.PlayMusic;


public class MainActivity extends AppCompatActivity {


    private static boolean USER_IS_GOING_TO_EXIT = true;
    private Toast backtoast;

    @Override
    public void onBackPressed(){
        if(USER_IS_GOING_TO_EXIT){
            if(backtoast!=null && backtoast.getView().getWindowToken()!=null){
                //while the toast is still visible and only if the back press
                finish();
            }
            else{
                backtoast = Toast.makeText(this,"Press back to exit",Toast.LENGTH_SHORT);
                backtoast.show();
            }
        }
        else{
            //other stuff
            super.onBackPressed();
        }

    }



    private ImageView MainThemeOnOff;
    private Button BtnPlay;


    public void gotoMainMenu() {
        try {
            Intent intent = new Intent(MainActivity.this, MainMenu.class);
            startActivity(intent);
            finish();
        } catch (Exception exp) {

        }
    }

    public  void OnOffMainTheme(){
        if(MainThemeStatus == R.drawable.sound_on){
            MainThemeStatus = R.drawable.sound_off;
            MainThemeOnOff.setImageResource(MainThemeStatus);
            PlayMusic.SetVolume(0);
        }
        else{
            MainThemeStatus = R.drawable.sound_on;
            MainThemeOnOff.setImageResource(MainThemeStatus);
            PlayMusic.SetVolume(1);
        }
    }

    private static int MainThemeStatus = R.drawable.sound_on;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        setContentView(R.layout.season1_titles);


        MainActivityOptions options = new MainActivityOptions();
        options.setOptions(this);

        BtnPlay = findViewById(R.id.btn_play);
        Window w = getWindow();
        BtnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoMainMenu();
            }
        });

//        PlayMusic.Track = MediaPlayer.create(MainActivity.this, R.raw.mywave);
//        PlayMusic.Start();//4113
//        System.out.println("TRACK:" + PlayMusic.Track);

        MainThemeOnOff = (ImageView) findViewById(R.id.volume_on_off);

        if(StartTheme){
            PlayMusic.SetAudio(MainActivity.this,R.raw.main_theme);
            PlayMusic.Start();
            PlayMusic.StartLoop(true);
            StartTheme = false;
            MainThemeOnOff.setImageResource(MainThemeStatus);
            PlayMusic.SetVolume(1);
        }

        MainThemeOnOff.setImageResource(MainThemeStatus);
        MainThemeOnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                OnOffMainTheme();
            }
        });





    }
    private class MainActivityOptions extends Options{
        private void setOptions(Activity activity){
            HideAndroidConditionBar(activity);
        }
    }
    public static boolean StartTheme = true;

}

