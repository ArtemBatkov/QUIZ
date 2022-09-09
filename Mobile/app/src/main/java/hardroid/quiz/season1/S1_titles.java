package hardroid.quiz.season1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import hardroid.quiz.Options;
import hardroid.quiz.R;

public class S1_titles extends AppCompatActivity {

    private  class  Season1_options extends Options {
        private void setOptions(Activity activity){
            HideAndroidConditionBar(activity);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoGameLevelSeason1();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season1_titles);
        S1_titles.Season1_options options = new S1_titles.Season1_options();
        options.setOptions(this);

        Button MainMenu = findViewById(R.id.gotomainmenu);
        MainMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGameLevelSeason1();
            }
        });


    }


    private void  gotoGameLevelSeason1(){
        try {
            PlayMusic.Continue();
            Intent intent = new Intent(S1_titles.this, GameLevels_S1.class);
            startActivity(intent); finish();
        }
        catch (Exception exp){}
    }

}
