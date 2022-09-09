package hardroid.quiz;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import java.util.Locale;

import hardroid.quiz.season1.GameLevels_S1;
import hardroid.quiz.season1.PlayMusic;


public class MainMenu extends AppCompatActivity {

    private CardView Season1_card;


    public void GoToMainActivity(){
        try {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        } catch (Exception exp) {

        }
    }

    @Override
    public void onBackPressed(){
        GoToMainActivity();
        super.onBackPressed();
    }

    public void MakeOutline(int ID){
        TextView TextViewShadow = (TextView) findViewById(ID);
        TextViewShadow.getPaint().setStrokeWidth(5);
        TextViewShadow.getPaint().setStyle(Paint.Style.STROKE);
    }

    public void GoToLevelChoose(){
        try {
            Intent intent = new Intent(MainMenu.this, GameLevels_S1.class);
            startActivity(intent);
            finish();
        } catch (Exception exp) {

        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);

        MainMenuOptions options = new MainMenuOptions();
        options.setOptions(this);

//        PlayMusic.Continue();

        //magic comes here (outline for season 1)
        MakeOutline(R.id.season1_txt_outline);

        Season1_card = findViewById(R.id.season1_card);

        Season1_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GoToLevelChoose();
            }
        });

        Button ChangeLanguage;
        ChangeLanguage = (Button) findViewById(R.id.btn_language);
        ChangeLanguage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String str = ChangeLanguage.getText().toString().toLowerCase(Locale.ROOT);
                Resources res = getResources();
                DisplayMetrics metrics = res.getDisplayMetrics();
                Configuration conf = res.getConfiguration();
                conf.setLocale(new Locale(str));
                res.updateConfiguration(conf, metrics);

                try {
                    Intent intent = new Intent(MainMenu.this,MainMenu.class);
                    startActivity(intent);finish();
                }
                catch (Exception e){}


            }
        });


//        System.out.println(PlayMusic.Track);
    }








    private class MainMenuOptions extends Options{
        private void setOptions(Activity activity){
            HideAndroidConditionBar(activity);
        }
    }

}

