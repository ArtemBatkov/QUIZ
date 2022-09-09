package hardroid.quiz.season1;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.ProgressBar;
import android.widget.TextView;


import com.airbnb.lottie.Lottie;
import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.util.Random;

import hardroid.quiz.Options;
import hardroid.quiz.R;
import hardroid.quiz.Timer;

public class Level_1 extends AppCompatActivity {



    private void MakeRoundImgCorners(ImageView img){
        img.setClipToOutline(true);
    }
    private ProgressBar timer_level1;
    private TextView text_levels,timer_txt_level1,button_close;
    private Button Back, Continue, NextLevel;
    private Timer timer;

    private int numLeft,numRight;
    private Level1_Array array = new Level1_Array();
    private Random random = new Random();
    private int count  = 0; // good answers count

    //TextViews
    private TextView txt_lft;
    private  TextView txt_rht;

    //Include animation
    private  Animation a;
    private  Dialog dialog_nextlevel;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoGameLevelSeason1();
    }


    private void setLevelName(TextView name){
        //set the name of the current level
        name.setText(R.string.level1);
    }



    private void MakeRandom(){
        numLeft = random.nextInt(10);
        numRight = random.nextInt(10);
        //while cycle controls equality of two values
        while(numRight == numLeft){
            numRight = random.nextInt(10);
        }
    }

    private void SetImageAndText(boolean animation){
        a = AnimationUtils.loadAnimation(Level_1.this,R.anim.alpha);
        img_left.setImageResource(array.images1[numLeft]);
        if(animation)img_left.startAnimation(a);
        txt_lft.setText(array.text1[numLeft]);

        a = AnimationUtils.loadAnimation(Level_1.this,R.anim.alpha);
        img_right.setImageResource(array.images1[numRight]);
        if(animation)img_right.startAnimation(a);
        txt_rht.setText(array.text1[numRight]);
    }

    private  ImageView img_left ;
    private  ImageView img_right ;

    //Array of progress
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

    private TextView btn_close2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //dialog window
        Dialog dialog;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.season1_level1);
        Level_1.Season1_options options = new Level_1.Season1_options();
        options.setOptions(this);

        txt_lft = (TextView) findViewById(R.id.txt_left);
        txt_rht = (TextView) findViewById(R.id.txt_right);

        img_left = (ImageView) findViewById(R.id.imgleft);
        img_right = (ImageView) findViewById(R.id.imgright);

        //make round corners
        MakeRoundImgCorners(img_left);MakeRoundImgCorners(img_right);

        //Animation
        a = AnimationUtils.loadAnimation(Level_1.this,R.anim.alpha);



        //Timers
        timer_level1 = (ProgressBar) findViewById(R.id.timer);
        timer_txt_level1 = (TextView) findViewById(R.id.timer_text);
        timer = new Timer(timer_level1,timer_txt_level1);
        timer_level1.setClickable(false);

        timer_level1.setOnClickListener(new View.OnClickListener() { // when timer is over
            @Override
            public void onClick(View v) {
                timer_level1.setClickable(false);
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

        NextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    gotoLevel2();
            }
        });

        btn_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGameLevelSeason1();
            }
        });






        Back = (Button) findViewById(R.id.btn_back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGameLevelSeason1();
            }
        });

        //Show that is level 1
        TextView text_levels = (TextView) findViewById(R.id.text_levels);
        setLevelName(text_levels);

        //Call dialog in the beginning of the game
        dialog  = new Dialog(this);//Create new dialog window
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);//hide tittle
        dialog.setContentView(R.layout.preview_level);//path to dialog content
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//
        //transparent background of the dialog window
        dialog.setCancelable(false);//window CAN NOT be closed by back button

        //Button close dialog
        button_close = (TextView) dialog.findViewById(R.id.btn_close);
        button_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss();
                    gotoGameLevelSeason1();
                }
                catch (Exception ex){
                }
            }
        });

        Continue = (Button) dialog.findViewById(R.id.btn_continue);
        Continue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    dialog.dismiss();
                    Thread.sleep(500);
                    ExecuteTimer();
                }
                catch (Exception exp){
                }
            }
        });
        dialog.show();

        a = AnimationUtils.loadAnimation(Level_1.this,R.anim.alpha);
        //Start Random
        MakeRandom();
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
                        if (level > 1){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",2);
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
                        if (level > 1){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",2);
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

        LottieAnimationView NewAnime = (LottieAnimationView) dialog_nextlevel.findViewById(R.id.snow_dude);
        NewAnime.setAnimation(anime[anime_pick++]);

        NewAnime.setRepeatMode(LottieDrawable.RESTART);
        LottieAnimationView snow = (LottieAnimationView) dialog_nextlevel.findViewById(R.id.snow_anime);
        snow.setVisibility(View.GONE);




        NewAnime.addAnimatorListener(new Animator.AnimatorListener() {


            @Override
            public void onAnimationStart(Animator animation, boolean isReverse) {
                Animator.AnimatorListener.super.onAnimationStart(animation, isReverse);
                int a = 5;
            }

            @Override
            public void onAnimationEnd(Animator animation, boolean isReverse) {
                Animator.AnimatorListener.super.onAnimationEnd(animation, isReverse);
                int a = 5;
            }

            @Override
            public void onAnimationStart(Animator animation) {
                int a = 5;
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                int a = 5;

            }

            @Override
            public void onAnimationCancel(Animator animation) {
                int a = 5;
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
                int a = 5;
                NewAnime.setAnimation(anime[anime_pick]);
                NewAnime.playAnimation();
                if(anime_pick == anime.length-1){
                    anime_pick = 0;
                }else{
                    anime_pick+=1;
                }
            }

        });



    }
    private int anime_pick = 0;

    private final int[] anime = {
      R.raw.zero_anim,
      R.raw.one_anim,
      R.raw.two_anim,
      R.raw.three_anim,
      R.raw.four_anim,
      R.raw.five_anim,
      R.raw.six_anim,
      R.raw.seven_anim,
      R.raw.eight_anim,
      R.raw.nine_anim
    };



    private void ExecuteTimer(){
        //Execute timer!
        final  Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                timer.TimerInitial();
                timer.TimerStart();
            }
        },1); //200
        }


    private void  gotoGameLevelSeason1(){
        if (timer != null) timer.TimerStop(); // we need to stop it first
        try {
            PlayMusic.Continue();
            Intent intent = new Intent(Level_1.this, GameLevels_S1.class);
            startActivity(intent); finish();
        }
        catch (Exception exp){}
    }

    private  class  Season1_options extends Options {
        private void setOptions(Activity activity){
            HideAndroidConditionBar(activity);
        }
    }


    private  void gotoLevel2(){
        try {
            Intent intent = new Intent(Level_1.this,Level_2.class);
            startActivity(intent);finish();
            dialog_nextlevel.dismiss();
        }catch (Exception exp){

        }
    }
}