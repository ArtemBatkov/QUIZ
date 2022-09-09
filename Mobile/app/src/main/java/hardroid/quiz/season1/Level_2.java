package hardroid.quiz.season1;

import android.animation.Animator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.util.Random;

import hardroid.quiz.MainActivity;
import hardroid.quiz.Options;
import hardroid.quiz.R;
import hardroid.quiz.Timer;

public class Level_2 extends AppCompatActivity {

    private ProgressBar timer_level2;
    private TextView text_levels,timer_txt_level2;
    private Timer timer;

    private void MakeRoundImgCorners(ImageView img){
        img.setClipToOutline(true);
    }

    private  class  Season1_options extends Options {
        private void setOptions(Activity activity){
            HideAndroidConditionBar(activity);
        }
    }

    private Dialog dialog_preview,dialog_nextlevel;
    private int numLeft, numRight;
    private Random random = new Random();

    private TextView button_close,btn_close2;
    private Button Continue,NextLevel,Back;
    private  ImageView img_left ;
    private  ImageView img_right ;
    private TextView txt_lft;
    private  TextView txt_rht;

    private void MakeRandom(){
        numLeft = random.nextInt(10);
        numRight = random.nextInt(10);
        //while cycle controls equality of two values
        while(numRight == numLeft){
            numRight = random.nextInt(10);
        }
    }

   private Animation a;
    private Level2_Array array = new Level2_Array();

    private void SetImageAndText(boolean animation){
        a = AnimationUtils.loadAnimation(Level_2.this,R.anim.alpha);
        img_left.setImageResource(array.images2[numLeft]);
        if(animation)img_left.startAnimation(a);
        txt_lft.setText(array.text2[numLeft]);

        a = AnimationUtils.loadAnimation(Level_2.this,R.anim.alpha);
        img_right.setImageResource(array.images2[numRight]);
        if(animation)img_right.startAnimation(a);
        txt_rht.setText(array.text2[numRight]);
    }
    private  int count = 0;

    private void UpdateScore(int points){
        //the points can be add here or be removed
        count = count + points;
        if(count < 0) count = 0;
        if (count >= 10) {
            count = 10;
        }
    }

    private  void MakePointsGray(){
        //Paint points to gray
        for(int i = 0; i < 9; i++){
            TextView tv = findViewById(progress[i]);
            tv.setBackgroundResource(R.drawable.style_points);
        }
    }

    private void MakePointsGreen(){
        //Make progress green
        for(int i = 0; i < count; i++){
            TextView tv = findViewById(progress[i]);
            tv.setBackgroundResource(R.drawable.style_points_green);
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
        setContentView(R.layout.season1_level1);//extends from level 1
        Level_2.Season1_options options = new Level_2.Season1_options();
        options.setOptions(this);
        txt_lft = (TextView) findViewById(R.id.txt_left);
        txt_rht = (TextView) findViewById(R.id.txt_right);

        img_left = (ImageView) findViewById(R.id.imgleft);
        img_right = (ImageView) findViewById(R.id.imgright);

        TextView lvl = (TextView) findViewById(R.id.text_levels);
        lvl.setText(R.string.level2);

        //make round corners
        MakeRoundImgCorners(img_left);MakeRoundImgCorners(img_right);


        //Change Main Background
        ImageView img_bgnd = (ImageView) findViewById(R.id.s1_level1_background);
        img_bgnd.setImageResource(R.drawable.s1_level2_background);

        //Make Random
        MakeRandom();
        a = AnimationUtils.loadAnimation(Level_2.this,R.anim.alpha);
        SetImageAndText(false);

        //Processing touch of the picture

        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Touching (pressing)
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);//blocking right
                    if(numLeft>numRight){
                        img_left.setImageResource(R.drawable.true_answer);
                    }
                    else{
                        img_left.setImageResource(R.drawable.false_answer);
                    }
                }
                //untouching (release)
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    if(numLeft>numRight){
                        //right answer
                        UpdateScore(1); //add +1 right answer
                    }else{
                        //left image < right image (wrong)
                        UpdateScore(-2);
                    }
                    if (count == 10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 2){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",3);
                            editor.commit();
                        }
                        timer.TimerStop();
                        img_left.setEnabled(false);
                        img_right.setEnabled(false);
                        dialog_nextlevel.show();
                    }else{
                        MakePointsGray();
                        MakePointsGreen();
                        MakeRandom();//make new random
                        img_right.setEnabled(true);
                        SetImageAndText(true);//set text and image AND animate them
                        System.out.println(count);

                        System.out.println("image left:");
                        timer.TimerStop();
                        System.out.println("alive: "+timer.ThreadAlive());
                        timer.TimerInitial();
                        timer.TimerStart();
                        System.out.println("(reload) alive: "+timer.ThreadAlive());
                    }
                }
                return true;
            }
        });

        img_right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Touching (pressing)
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    img_left.setEnabled(false);//blocking right
                    if(numRight>numLeft){
                        img_right.setImageResource(R.drawable.true_answer);
                    }
                    else{
                        img_right.setImageResource(R.drawable.false_answer);
                    }
                }
                //untouching (release)
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    if(numRight>numLeft){
                        //right answer
                        UpdateScore(1); //add +1 right answer
                    }else{
                        //left image < right image (wrong)
                        UpdateScore(-2);
                    }
                    if(count==10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 2){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",3);
                            editor.commit();
                        }
                        timer.TimerStop();
                        img_left.setEnabled(false);
                        img_right.setEnabled(false);
                        dialog_nextlevel.show();
                    }else {
                        MakePointsGray();
                        MakePointsGreen();
                        MakeRandom();//make new random
                        img_left.setEnabled(true);
                        SetImageAndText(true);//set text and image AND animate them
                        System.out.println(count);

                        System.out.println("image right");
                        timer.TimerStop();
                        System.out.println("alive: " + timer.ThreadAlive());
                        timer.TimerInitial();
                        timer.TimerStart();
                        System.out.println("(reload) alive: " + timer.ThreadAlive());
                    }
                }
                return true;
            }
        });

        Back = (Button) findViewById(R.id.btn_back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGameLevelSeason1();
            }
        });

        //Timers
        timer_level2 = (ProgressBar) findViewById(R.id.timer);
        timer_txt_level2 = (TextView) findViewById(R.id.timer_text);
        timer = new Timer(timer_level2,timer_txt_level2);
        timer_level2.setClickable(false);
        timer_level2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer_level2.setClickable(false);
                MotionEvent down = MotionEvent.obtain(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_DOWN,
                        0,0,0
                );
                MotionEvent up = MotionEvent.obtain(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_UP,
                        0,0,0
                );
                if(numLeft>numRight){
                    img_right.dispatchTouchEvent(down);
                    img_right.dispatchTouchEvent(up);

                }
                else{
                    img_left.dispatchTouchEvent(down);
                    img_left.dispatchTouchEvent(up);
                }
            }
        });


        //Preview Task
        dialog_preview  = new Dialog(this);//Create new dialog window
        dialog_preview.requestWindowFeature(Window.FEATURE_NO_TITLE);//hide tittle
        dialog_preview.setContentView(R.layout.preview_level);//path to dialog content
        dialog_preview.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//
        //transparent background of the dialog window
        dialog_preview.setCancelable(false);//window CAN NOT be closed by back button

        //Set new image to preview
        ImageView previewimg = (ImageView) dialog_preview.findViewById(R.id.img_preview_level);
        previewimg.setImageResource(R.drawable.s1_level2_nums_preview);

        //Set new text to preview
        TextView text_preview = (TextView) dialog_preview.findViewById(R.id.text_preview_description);
        text_preview.setText(R.string.text_s1_level2_description);

        dialog_preview.show();

        //Finish level Preview
        dialog_nextlevel  = new Dialog(this);//Create new dialog window
        dialog_nextlevel.requestWindowFeature(Window.FEATURE_NO_TITLE);//hide tittle
        dialog_nextlevel.setContentView(R.layout.end_level_1);//path to dialog content
        dialog_nextlevel.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//
        //transparent background of the dialog window
        dialog_nextlevel.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT,
                WindowManager.LayoutParams.MATCH_PARENT); // expends dialog for all window
        dialog_nextlevel.setCancelable(false);//window CAN NOT be closed by back button
        NextLevel = (Button)dialog_nextlevel.findViewById(R.id.btn_nextlevel);
        btn_close2 = (TextView) dialog_nextlevel.findViewById(R.id.btn_close);
        TextView newtext = (TextView) dialog_nextlevel.findViewById(R.id.text_endlevel1);
        newtext.setText(R.string.text_preview_level2_end);



        NextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLevel3();
            }
        });

        btn_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_nextlevel.dismiss();
                gotoGameLevelSeason1();
            }
        });


        button_close = (TextView) dialog_preview.findViewById(R.id.btn_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog_preview.dismiss();
                    gotoGameLevelSeason1();
                }
                catch (Exception ex){
                }
            }
        });

        Continue = (Button) dialog_preview.findViewById(R.id.btn_continue);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog_preview.dismiss();
                    Thread.sleep(500);
                    ExecuteTimer();
                }
                catch (Exception exp){
                }
            }
        });

        LottieAnimationView NewAnime = (LottieAnimationView) dialog_nextlevel.findViewById(R.id.snow_dude);
        NewAnime.setAnimation(R.raw.l2);
//        dialog_nextlevel.show();
        NewAnime.setRepeatMode(LottieDrawable.RESTART);
        LottieAnimationView snow = (LottieAnimationView) dialog_nextlevel.findViewById(R.id.snow_anime);
        snow.setVisibility(View.GONE);


        dialog_nextlevel.findViewById(R.id.layout_preview_endlevel1).setBackgroundResource(R.color.black95);
        newtext.setTextColor(getResources().getColor(R.color.white));



    };

    //=====================Extra Methods========================//
    private void  gotoGameLevelSeason1(){
        if (timer != null) timer.TimerStop(); // we need to stop it first
        try {
            PlayMusic.Continue();
            Intent intent = new Intent(Level_2.this, GameLevels_S1.class);
            startActivity(intent); finish();
        }
        catch (Exception exp){}
    }

    private void ExecuteTimer(){
        //Execute timer!
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timer.TimerInitial();
                timer.TimerStart();
            }
        },1); //200
    }


    private  void gotoLevel3(){
        try {
            Intent intent = new Intent(Level_2.this,Level_3.class);
            startActivity(intent);finish();
            dialog_nextlevel.dismiss();
        }catch (Exception exp){

        }
    }

    private final int[] progress = {
            R.id.point1,
            R.id.point2,
            R.id.point3,
            R.id.point4,
            R.id.point5,
            R.id.point6,
            R.id.point7,
            R.id.point8,
            R.id.point9,
            R.id.point10
    };
}

