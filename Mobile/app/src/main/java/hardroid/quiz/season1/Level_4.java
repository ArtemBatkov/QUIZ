package hardroid.quiz.season1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Gravity;
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

import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

import hardroid.quiz.Options;
import hardroid.quiz.R;
import hardroid.quiz.Timer;

public class Level_4 extends AppCompatActivity {

    private  class  Season1_options extends Options {
        private void setOptions(Activity activity){
            HideAndroidConditionBar(activity);
        }
    }

    private Level4_Array array = new Level4_Array();
    private Locale locale ;
    private ProgressBar timer_level4;
    private TextView question,timer_txt_level4,button_close,txt_left,txt_right,text_level,btn_close2;
    private Timer timer;
    private Random random = new Random();
    private int FactNum;
    private boolean FactNum_answer;
    private ImageView img_left, img_right;
    private Animation a;
    private  final boolean img_left_ans = true, img_right_ans = false;
    private int count = 0;
    private  Button Continue,NextLevel,Back;

    private ArrayList<Integer>History = new ArrayList<Integer>();

    private void MakeRandom(){
        FactNum = random.nextInt(20);
        while (History.contains(FactNum)){
            FactNum = random.nextInt(20);
        }
        History.add(FactNum);
        ShowHistory();
        FactNum_answer = array.values4[FactNum];
        question.setText(array.text4[FactNum]);
    }

    private void doShadow(TextView tv,int color){
        tv.setShadowLayer(1.6f,1.5f,1.3f,getResources().getColor(color));
    }

    private void ShowHistory(){
        System.out.println("History:" + History);
    }

    private void UpdateHistory(int points){
        if(count == 0) History.clear();
        else{
            if(points<0) {
                try {
                    History.remove(History.size() - 1);
                    History.remove(History.size() - 1);
                } catch (Exception e) {}
                ShowHistory();
            }
        }
    }

    private void UpdateScore(int points){
        //the points can be add here or be removed
        count = count + points;
        if(count < 0) count = 0;
        if (count >= 10) {
            count = 10;
        }
        UpdateHistory(points);
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
        int style = R.drawable.style_points_green;
        for(int i = 0; i < count; i++){
            TextView tv = findViewById(progress[i]);
//            tv.setBackgroundResource(R.drawable.style_points_green);
            //for level 4 I did NOT green color because of merge
            tv.setBackgroundResource(R.drawable.style_points_blue);
//            tv.setBackgroundColor(getResources().getColor(R.color.black));
        }
    }

    private void SetImageAndText(boolean animation){
        a = AnimationUtils.loadAnimation(Level_4.this,R.anim.alpha);
        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        locale = getResources().getConfiguration().locale;
        if(locale.getLanguage().toLowerCase(Locale.ROOT).equals("ru")){
            if(animation)img_right.startAnimation(a);
            img_left.setImageResource(R.drawable.s1_level4_true_ru);
            if(animation)img_right.startAnimation(a);
            img_right.setImageResource(R.drawable.s1_level4_false_ru);
        }else{
            if(animation)img_right.startAnimation(a);
            img_left.setImageResource(R.drawable.s1_level4_true);
            if(animation)img_right.startAnimation(a);
            img_right.setImageResource(R.drawable.s1_level4_false);
        }
    }

    private  Dialog dialog_preview,dialog_nextlevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.true_false_layout);//extends from level 1
        Level_4.Season1_options options = new Level_4.Season1_options();
        options.setOptions(this);
        int CommonColor = R.color.black;

        //Preview Task
        dialog_preview  = new Dialog(this);//Create new dialog window
        dialog_preview.requestWindowFeature(Window.FEATURE_NO_TITLE);//hide tittle
        dialog_preview.setContentView(R.layout.preview_level);//path to dialog content
        dialog_preview.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//
        //transparent background of the dialog window
        dialog_preview.setCancelable(false);//window CAN NOT be closed by back button

        //Set new image to preview
        ImageView previewimg = (ImageView) dialog_preview.findViewById(R.id.img_preview_level);
        previewimg.setImageResource(R.drawable.s1_level4_preview);

        //Set new text to preview
        TextView text_preview = (TextView) dialog_preview.findViewById(R.id.text_preview_description);
        text_preview.setText(R.string.text_s1_level4_description);

        dialog_preview.show();

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

        TextView lvl = (TextView) findViewById(R.id.text_level);
        lvl.setText(R.string.level4);

        ImageView imgbg = (ImageView) findViewById(R.id.true_false_bg);
        imgbg.setImageResource(R.drawable.s1_level4_bg);
        question = (TextView) findViewById(R.id.question);
        question.setTextColor(getResources().getColor(R.color.black));
        doShadow(question,R.color.white);

        MakeRandom();
        img_left = (ImageView) findViewById(R.id.imgleft);
        img_right = (ImageView) findViewById(R.id.imgright);

        txt_left = (TextView) findViewById(R.id.txt_left);
        txt_right = (TextView) findViewById(R.id.txt_right);
        txt_left.setTextColor(getResources().getColor(R.color.level4_color_txt_true));
        txt_right.setTextColor(getResources().getColor(R.color.level4_color_txt_false));

        text_level = (TextView) findViewById(R.id.text_level);
        text_level.setTextColor(getResources().getColor(R.color.white));


        txt_left.setGravity(Gravity.CENTER);txt_right.setGravity(Gravity.CENTER);

        doShadow(txt_left, CommonColor);
        doShadow(txt_right,CommonColor);
        doShadow(text_level,CommonColor);

        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        locale = getResources().getConfiguration().locale;
        if(locale.getLanguage().toLowerCase(Locale.ROOT).equals("ru")){
            img_left.setImageResource(R.drawable.s1_level4_true_ru);
            img_right.setImageResource(R.drawable.s1_level4_false_ru);
        }else{
            img_left.setImageResource(R.drawable.s1_level4_true);
            img_right.setImageResource(R.drawable.s1_level4_false);
        }

        //Timer
        timer_level4 = (ProgressBar) findViewById(R.id.timer);
        timer_txt_level4 = (TextView) findViewById(R.id.timer_text);
        timer = new Timer(timer_level4,timer_txt_level4);
        timer_level4.setClickable(false);
        timer_txt_level4.setText("10");
        timer_level4.setProgress(100);
        timer.ChangeTimer((long)10000,(long)1000);

        doShadow(timer_txt_level4,CommonColor);
        timer_level4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer_level4.setClickable(false);
                MotionEvent down = MotionEvent.obtain(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_DOWN,
                        0, 0, 0
                );
                MotionEvent up = MotionEvent.obtain(
                        SystemClock.uptimeMillis(),
                        SystemClock.uptimeMillis(),
                        MotionEvent.ACTION_UP,
                        0, 0, 0
                );

                if (FactNum_answer) {
                    img_right.dispatchTouchEvent(down);
                    img_right.dispatchTouchEvent(up);

                } else {
                    img_left.dispatchTouchEvent(down);
                    img_left.dispatchTouchEvent(up);
                }
            }
        });

        a = AnimationUtils.loadAnimation(Level_4.this,R.anim.alpha);


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
        newtext.setText(R.string.text_s1_level4_description_end);

        NextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLevel5();
            }
        });

        btn_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_nextlevel.dismiss();
                gotoGameLevelSeason1();
            }
        });


        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                //Touching (pressing)
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);//blocking right
                    if(FactNum_answer == img_left_ans){//correct
                        img_left.setImageResource(R.drawable.true_answer);
                    }
                    else{
                        img_left.setImageResource(R.drawable.false_answer);
                    }
                }
                //untouching (release)
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    if(FactNum_answer == img_left_ans){
                        //right answer
                        UpdateScore(1); //add +1 right answer
                    }else{
                        //left image < right image (wrong)
                        UpdateScore(-2);
                    }
                    if (count == 10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 4){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",5);
                            editor.commit();
                        }
                        timer.TimerStop();
                        img_left.setEnabled(false);
                        img_right.setEnabled(false);
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!!!!
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
                    if(FactNum_answer == img_right_ans){
                        img_right.setImageResource(R.drawable.true_answer);
                    }
                    else{
                        img_right.setImageResource(R.drawable.false_answer);
                    }
                }
                //untouching (release)
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    if(FactNum_answer == img_right_ans){
                        //right answer
                        UpdateScore(1); //add +1 right answer
                    }else{
                        //left image < right image (wrong)
                        UpdateScore(-2);
                    }
                    if(count==10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 4){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",5);
                            editor.commit();
                        }
                        timer.TimerStop();
                        img_left.setEnabled(false);
                        img_right.setEnabled(false);
                        dialog_nextlevel.show(); //!!!!!!!!!
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


        LottieAnimationView NewAnime = (LottieAnimationView) dialog_nextlevel.findViewById(R.id.snow_dude);
        NewAnime.setAnimation(R.raw.tf);
        NewAnime.setAlpha(0.5f);
//        dialog_nextlevel.show();
        NewAnime.setRepeatMode(LottieDrawable.RESTART);
        LottieAnimationView snow = (LottieAnimationView) dialog_nextlevel.findViewById(R.id.snow_anime);
        snow.setVisibility(View.GONE);
        newtext.setTextColor(getResources().getColor(R.color.black95));


    }




    //=================EXTRA FUNCTIONS===============//
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

    private void  gotoGameLevelSeason1(){
        if (timer != null) timer.TimerStop(); // we need to stop it first !!!!!!!!!!!!!!!!
        try {
            PlayMusic.Continue();
            Intent intent = new Intent(Level_4.this, GameLevels_S1.class);
            startActivity(intent); finish();
        }
        catch (Exception exp){}
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoGameLevelSeason1();
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

    private void gotoLevel5(){
        try {
            Intent intent = new Intent(Level_4.this,Level_5.class);
            startActivity(intent);finish();
            dialog_nextlevel.dismiss();
        }catch (Exception exp){

        }
    }
}
