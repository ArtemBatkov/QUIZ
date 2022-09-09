package hardroid.quiz.season1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.provider.ContactsContract;
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

import java.util.ArrayList;
import java.util.Random;

import hardroid.quiz.Options;
import hardroid.quiz.R;
import hardroid.quiz.Timer;

public class Level_5 extends AppCompatActivity {

    private  class  Season1_options extends Options {
        private void setOptions(Activity activity){
            HideAndroidConditionBar(activity);
        }
    }
    private Dialog dialog_preview,dialog_nextlevel;
    private ProgressBar timer_level5;
    private Timer timer;
    private Animation a;
    private ArrayList<Integer> True = new ArrayList<Integer>();
    private ArrayList<Integer> False = new ArrayList<Integer>();
    private Level5_Array array = new Level5_Array();
    private ImageView img_left,img_right;
    private int RandTrue,RandFalse;
    private int TrueLen = 0,FalseLen = 0;
    private Random random = new Random();
    private boolean isLeft;
    private Button Back,Continue,Continue_end,NextLevel;
    private TextView text_level,timer_txt_level5,button_close,btn_close2;

    private void ResetPazzleArray(){
        True = (ArrayList<Integer>) array.img_true_5.clone();
        TrueLen = True.size();
        False = (ArrayList<Integer>) array.img_false_5.clone();
        FalseLen = False.size();
//        System.out.println("True:" + True);
    }

    private void MakeRandom(){
        RandTrue = random.nextInt(TrueLen);
        RandFalse = random.nextInt(FalseLen);
        isLeft = random.nextBoolean();
        a = AnimationUtils.loadAnimation(Level_5.this,R.anim.alpha);

        if(isLeft){//img True is left?
            img_left.setImageResource(True.get(RandTrue));
            img_left.startAnimation(a);

            img_right.setImageResource(False.get(RandFalse));
            img_right.startAnimation(a);
        }
        else{
            img_left.setImageResource(False.get(RandFalse));
            img_left.startAnimation(a);

            img_right.setImageResource(True.get(RandTrue));
            img_right.startAnimation(a);
        }
     }

    private void ClearPazzle(){
        int base [] ={
                R.drawable.el1,
                R.drawable.el2,
                R.drawable.el3,
                R.drawable.el4,
                R.drawable.el5,
                R.drawable.el6,
                R.drawable.el7,
                R.drawable.el8,
                R.drawable.el9
        };
        for (int i = 0; i<Pazzle.length;i++){
            a = AnimationUtils.loadAnimation(Level_5.this,R.anim.alpha);
            ImageView mg = (ImageView) findViewById(Pazzle[i]);
            mg.setImageResource(base[i]);
            mg.setAnimation(a);
        }
    }

    private void ShowPazzle(){

        int base [] ={
                R.drawable.s1_l5_element1,
                R.drawable.s1_l5_element2,
                R.drawable.s1_l5_element3,
                R.drawable.s1_l5_element4,
                R.drawable.s1_l5_element5,
                R.drawable.s1_l5_element6,
                R.drawable.s1_l5_element7,
                R.drawable.s1_l5_element8,
                R.drawable.s1_l5_element9
        };

        for (int i = 0; i<Pazzle.length;i++){
            a = AnimationUtils.loadAnimation(Level_5.this,R.anim.alpha);
            ImageView mg = (ImageView) findViewById(Pazzle[i]);
            mg.setImageResource(base[i]);
            a.setDuration(1500);
            mg.setAnimation(a);
        }
    }

    private void setScaleType(ImageView.ScaleType scale){
        for (int i = 0; i<Pazzle.length;i++){
             ImageView mg = (ImageView) findViewById(Pazzle[i]);
             mg.setScaleType(scale);
        }
    }

    private final int[] Pazzle = {
            R.id.one,
            R.id.two,
            R.id.three,
            R.id.four,
            R.id.five,
            (R.id.six),
            (R.id.seven),
            (R.id.eight),
            (R.id.nine)
    };

    private void ShowContinueButton(){
        LinearLayout choice_left_or_right = findViewById(R.id.choice_left_right);
        choice_left_or_right.setVisibility(View.GONE);

        LinearLayout show_img_layout = findViewById(R.id.show_img_layout);
        show_img_layout.setVisibility(View.VISIBLE);
    }

    private void doShadow(TextView tv,int color){
        tv.setShadowLayer(1.6f,1.5f,1.3f,getResources().getColor(color));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pazzle_layour);//extends from level 1
        Level_5.Season1_options options = new Level_5.Season1_options();
        options.setOptions(this);
        ImageView bg = (ImageView) findViewById(R.id.pazzle_background) ;
        bg.setImageResource(R.drawable.level5_backgrond);

        //Preview Task
        dialog_preview  = new Dialog(this);//Create new dialog window
        dialog_preview.requestWindowFeature(Window.FEATURE_NO_TITLE);//hide tittle
        dialog_preview.setContentView(R.layout.preview_level);//path to dialog content
        dialog_preview.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//
        //transparent background of the dialog window
        dialog_preview.setCancelable(false);//window CAN NOT be closed by back button

        //Set new image to preview
        ImageView previewimg = (ImageView) dialog_preview.findViewById(R.id.img_preview_level);
        previewimg.setImageResource(R.drawable.s1_l5_preview);

        //Set new text to preview
        TextView text_preview = (TextView) dialog_preview.findViewById(R.id.text_preview_description);
        text_preview.setText(R.string.text_s1_level5_description);

        dialog_preview.show();

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


        img_left = (ImageView) findViewById(R.id.imgleft);
        img_right = (ImageView) findViewById(R.id.imgright);

        ImageView.ScaleType scale = ImageView.ScaleType.FIT_XY;
        setScaleType(scale);
        ClearPazzle();
        ResetPazzleArray();
        MakeRandom();

        Back = (Button) findViewById(R.id.btn_back);
        text_level = (TextView) findViewById(R.id.text_level);
        text_level.setText(R.string.level5);
        text_level.setTextColor(getResources().getColor(R.color.white));
        doShadow(text_level,R.color.black);

        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGameLevelSeason1();
            }
        });

        //Timer
        timer_level5 = (ProgressBar) findViewById(R.id.timer);
        timer_txt_level5 = (TextView) findViewById(R.id.timer_text);
        timer = new Timer(timer_level5,timer_txt_level5);
        timer_level5.setClickable(false);
        timer_txt_level5.setTextColor(Color.parseColor("#FFFF0049"));
        doShadow(timer_txt_level5,R.color.black);


        timer_level5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer_level5.setClickable(false);
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
                if(isLeft){
                    img_right.dispatchTouchEvent(down);
                    img_right.dispatchTouchEvent(up);
                }
                else{
                    img_left.dispatchTouchEvent(down);
                    img_left.dispatchTouchEvent(up);
                }
            }
        });


        img_left.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    img_right.setEnabled(false);
                    timer.TimerStop();
                    if(isLeft){
                        img_left.setImageResource(R.drawable.true_answer);
                    }
                    else{
                        img_left.setImageResource(R.drawable.false_answer);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    ImageView mg;
                    if(isLeft){
                        //correct
                        try{
                            int index = array.img_true_5.indexOf(True.get(RandTrue));//define index of original
                            mg = (ImageView) findViewById(Pazzle[index]);//find by that index in pazzle pict
                            mg.setImageResource(True.get(RandTrue));//set img in the point spot
                            a = AnimationUtils.loadAnimation(Level_5.this,R.anim.alpha);
                            mg.startAnimation(a);
                            True.remove(RandTrue);//remove correct answer
                        }catch (Exception e){};
                    }else{
                        //incorrect
                        //if incorrect answer DELETE ALL, and restart
                        ResetPazzleArray();
                        ClearPazzle();
                    }
                    TrueLen = True.size();
                    if (TrueLen == 0){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 5){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",6);
                            editor.commit();
                        }
                        ShowContinueButton();
                        ShowPazzle();
                        System.out.println("GAME IS OVER");
                    }else{
                        img_right.setEnabled(true);
                        MakeRandom();
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
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    timer.TimerStop();
                    img_left.setEnabled(false);
                    if(!isLeft){
                        img_right.setImageResource(R.drawable.true_answer);
                    }
                    else{
                        img_right.setImageResource(R.drawable.false_answer);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    ImageView mg;
                    if(!isLeft){//right
                        //correct
                        try{
                            int index = array.img_true_5.indexOf(True.get(RandTrue));//define index of original
                            mg = (ImageView) findViewById(Pazzle[index]);//find by that index in pazzle pict
                            mg.setImageResource(True.get(RandTrue));//set img in the point spot
                            a = AnimationUtils.loadAnimation(Level_5.this,R.anim.alpha);
                            mg.startAnimation(a);
                            True.remove(RandTrue);//remove correct answer
                        }catch (Exception e){};
                    }
                    else{
                        //incorrect - Reset
                        ResetPazzleArray();
                        ClearPazzle();
                    }
                    TrueLen = True.size();
                    if (TrueLen == 0){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 5){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",6);
                            editor.commit();
                        }
                        ShowContinueButton();
                        ShowPazzle();
                        System.out.println("GAME IS OVER");
                    }else{
                        img_left.setEnabled(true);
                        MakeRandom();
                        timer.TimerInitial();
                        timer.TimerStart();
                    }
                }
                return true;
            }
        });


        //when puzzle is solved and button continue appeared
        Continue_end = (Button) findViewById(R.id.btn_confirm_img);
        Continue_end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_nextlevel.show();
            }
        });






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
        newtext.setText(R.string.text_preview_level5_end);

        NextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoLevel6();
            }
        });

        btn_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_nextlevel.dismiss();
                gotoGameLevelSeason1();
            }
        });


        LottieAnimationView NewAnime = (LottieAnimationView) dialog_nextlevel.findViewById(R.id.snow_dude);
        NewAnime.setAnimation(R.raw.s5);
//        dialog_nextlevel.show();
        NewAnime.setRepeatMode(LottieDrawable.RESTART);
        LottieAnimationView snow = (LottieAnimationView) dialog_nextlevel.findViewById(R.id.snow_anime);
        snow.setVisibility(View.GONE);
        newtext.setTextColor(getResources().getColor(R.color.black95));


    }


    //=============EXTRA FUNCTIONS============//
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoGameLevelSeason1();
    }

    private void  gotoGameLevelSeason1(){
        if (timer != null) timer.TimerStop(); // we need to stop it first !!!!!!!!!!!!!!!!
        try {
            PlayMusic.Continue();
            Intent intent = new Intent(Level_5.this, GameLevels_S1.class);
            startActivity(intent); finish();
        }
        catch (Exception exp){}
    }

    private void gotoLevel6(){
        try {
            dialog_nextlevel.dismiss();
            Intent intent = new Intent(Level_5.this,Level_6.class);
            startActivity(intent);finish();
        }catch (Exception exp){

        }
    }

}
