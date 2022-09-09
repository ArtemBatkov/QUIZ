package hardroid.quiz.season1;

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

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.util.Random;

import hardroid.quiz.Options;
import hardroid.quiz.R;
import hardroid.quiz.Timer;

public class Level_7 extends AppCompatActivity {
    private Dialog dialog_preview,dialog_nextlevel;
    private ProgressBar timer_level7;
    private TextView timer_txt_level7;
    private Timer timer;
    private Level7_Array array = new Level7_Array();
    private TextView txt_lft;
    private  TextView txt_rht;
    private  ImageView img_left ;
    private  ImageView img_right ;
    private void MakeRoundImgCorners(ImageView img){
        img.setClipToOutline(true);
    }
    private Random random = new Random();
    private int numLeft, numRight;
    private Animation a;
    private  int count = 0;
    private boolean Eatable;

    private  class  Season1_options extends Options {
        private void setOptions(Activity activity){
            HideAndroidConditionBar(activity);
        }
    }

    private void MakeRandom(){
        numLeft = random.nextInt(30);
        Eatable = array.values7[numLeft];

        numRight = random.nextInt(30);
        //while cycle controls equality of two values
        while(array.values7[numRight] == Eatable){
            numRight = random.nextInt(30);
        }
        System.out.println("numLeft = " + numLeft + "; "+array.values7[numLeft]+" numRight = "+numRight+"; "+array.values7[numRight]);
    }

    private void SetImageAndText(boolean animation){
        a = AnimationUtils.loadAnimation(Level_7.this,R.anim.alpha);
        img_left.setImageResource(array.images7[numLeft]);
        if(animation)img_left.startAnimation(a);
        txt_lft.setText(array.text7[numLeft]);

        a = AnimationUtils.loadAnimation(Level_7.this,R.anim.alpha);
        img_right.setImageResource(array.images7[numRight]);
        if(animation)img_right.startAnimation(a);
        txt_rht.setText(array.text7[numRight]);
    }

    private void UpdateScore(int points){
        //the points can be add here or be removed
        count = count + points;
        if(count < 0) count = 0;
        if (count >= 10) {
            count = 10;
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

    private void doShadow(TextView tv,int color){
        tv.setShadowLayer(1.6f,1.5f,1.3f,getResources().getColor(color));
    }

    private TextView button_close,btn_close2;
    private Button Continue,NextLevel,Back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.season1_level1);//extends from level 1
        Level_7.Season1_options options = new Level_7.Season1_options();
        options.setOptions(this);

        txt_lft = (TextView) findViewById(R.id.txt_left);
        txt_rht = (TextView) findViewById(R.id.txt_right);

        txt_lft.setTextColor(getResources().getColor(R.color.white));
        doShadow(txt_lft,R.color.black);
        txt_rht.setTextColor(getResources().getColor(R.color.white));
        doShadow(txt_rht,R.color.black);


        img_left = (ImageView) findViewById(R.id.imgleft);
        img_right = (ImageView) findViewById(R.id.imgright);

        TextView lvl = (TextView) findViewById(R.id.text_levels);
        lvl.setText(R.string.level7);
        lvl.setTextColor(getResources().getColor(R.color.white));
        doShadow(lvl,R.color.black);

        //make round corners
        MakeRoundImgCorners(img_left);MakeRoundImgCorners(img_right);


        //Change Main Background
        ImageView img_bgnd = (ImageView) findViewById(R.id.s1_level1_background);
        img_bgnd.setImageResource(R.drawable.s1_level7_background);

        //Make Random
        MakeRandom();
        a = AnimationUtils.loadAnimation(Level_7.this,R.anim.alpha);
        SetImageAndText(false);


        //Processing touch of the picture

        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Touching (pressing)
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);//blocking right
                    if(Eatable){
                        img_left.setImageResource(R.drawable.true_answer);
                    }
                    else{
                        img_left.setImageResource(R.drawable.false_answer);
                    }
                }
                //untouching (release)
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    if(Eatable){
                        //right answer
                        UpdateScore(1); //add +1 right answer
                    }else{
                        //left image < right image (wrong)
                        UpdateScore(-2);
                    }
                    if (count == 10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 7){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",8);
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



                        timer.TimerStop();

                        timer.TimerInitial();
                        timer.TimerStart();

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
                    if(!Eatable){
                        img_right.setImageResource(R.drawable.true_answer);
                    }
                    else{
                        img_right.setImageResource(R.drawable.false_answer);
                    }
                }
                //untouching (release)
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    if(!Eatable){
                        //right answer
                        UpdateScore(1); //add +1 right answer
                    }else{
                        //left image < right image (wrong)
                        UpdateScore(-2);
                    }
                    if(count==10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 7){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",8);
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
        timer_level7 = (ProgressBar) findViewById(R.id.timer);
        timer_txt_level7 = (TextView) findViewById(R.id.timer_text);
        timer = new Timer(timer_level7,timer_txt_level7);
        timer_level7.setClickable(false);
        timer_txt_level7.setTextColor(getResources().getColor(R.color.level4_color_txt_false));
        doShadow(timer_txt_level7,R.color.black);

        timer_level7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer_level7.setClickable(false);
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
                if(Eatable){
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
        previewimg.setImageResource(R.drawable.s1_level7_preview);

        //Set new text to preview
        TextView text_preview = (TextView) dialog_preview.findViewById(R.id.text_preview_description);
        text_preview.setText(R.string.text_s1_level7_description);

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
        newtext.setText(R.string.text_s1_level7_description_end);



        NextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_nextlevel.dismiss();
                gotoLevel8();
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
        NewAnime.setAnimation(R.raw.s7);
//        dialog_nextlevel.show();
        NewAnime.setRepeatMode(LottieDrawable.RESTART);
        LottieAnimationView snow = (LottieAnimationView) dialog_nextlevel.findViewById(R.id.snow_anime);
        snow.setVisibility(View.GONE);
        newtext.setTextColor(getResources().getColor(R.color.black95));


    }




    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoGameLevelSeason1();
    }
    private void  gotoGameLevelSeason1(){
        if (timer != null) timer.TimerStop(); // we need to stop it first !!!!!!!!!!!!!!!!
        try {
            PlayMusic.Continue();
            Intent intent = new Intent(Level_7.this, GameLevels_S1.class);
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

    private void gotoLevel8(){
        try {
            Intent intent = new Intent(Level_7.this,Level_8.class);
            startActivity(intent);finish();
        }catch (Exception exp){

        }
    }


}
