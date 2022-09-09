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

import java.util.Arrays;
import java.util.Random;

import hardroid.quiz.Options;
import hardroid.quiz.R;
import hardroid.quiz.Timer;


public class Level_3 extends AppCompatActivity {

    private  class  Season1_options extends Options {
        private void setOptions(Activity activity){
            HideAndroidConditionBar(activity);
        }
    }
    private Dialog dialog_preview,dialog_nextlevel;
    private ProgressBar timer_level3;
    private Timer timer;
    private Button Continue,Back,NextLevel;
    private Animation a;
    private ImageView MainBg,imgleft_high,imgright_high,imgleft_low,imgright_low;
    private Random random = new Random();
    private int[] PickedValues = {-1,-1,-1,-1};
    private Level3_Array array = new Level3_Array();
    private int value1,value2,value3,value4;
    private int count = 0;

    private TextView txt_left_high, txt_left_low, txt_right_high, txt_right_low,timer_txt_level3,button_close,btn_close2;

    private void UpdateScore(int points){
        //the points can be add here or be removed
        count = count + points;
        if(count < 0) count = 0;
        if (count >= 10) {
            count = 10;
        }
    }


    private void setImageAndAnimation4(boolean animation){
        //Left high
        a = AnimationUtils.loadAnimation(Level_3.this,R.anim.alpha);
        imgleft_high.setImageResource(array.images3[value1]);
        if(animation)imgleft_high.startAnimation(a);
        txt_left_high.setText(array.text3[value1]);

        //Right High
        a = AnimationUtils.loadAnimation(Level_3.this,R.anim.alpha);
        imgright_high.setImageResource(array.images3[value2]);
        if(animation)imgright_high.startAnimation(a);
        txt_right_high.setText(array.text3[value2]);

        //Left low
        a = AnimationUtils.loadAnimation(Level_3.this,R.anim.alpha);
        imgleft_low.setImageResource(array.images3[value3]);
        if(animation)imgleft_low.startAnimation(a);
        txt_left_low.setText(array.text3[value3]);

        //Right low
        a = AnimationUtils.loadAnimation(Level_3.this,R.anim.alpha);
        imgright_low.setImageResource(array.images3[value4]);
        if(animation)imgright_low.startAnimation(a);
        txt_right_low.setText(array.text3[value4]);
    }

    private int FindMaxPickedValues(){
        int maxNum = PickedValues[0];
        for (int j : PickedValues) {
            if (j > maxNum)
                maxNum = j;
        }
        return maxNum;
    }


    private boolean HasValueArray(int ValueToCheck){
        boolean has = false;
        for (int i = 0; i < PickedValues.length; i++){
            if (ValueToCheck == PickedValues[i]) {has = true;break;}
        }
        return  has;
    }

    private void ShowArray4(){
        for(int i = 0; i < PickedValues.length; i++){
            System.out.println("PickedValues["+i+"] = "+PickedValues[i]);
        }
    }

    private void ShuffleArray4()
    {
        int j;
        int temp;
        for (int i = PickedValues.length-1; i>0;i--){
            j = random.nextInt(i+1);
            temp = PickedValues[i];
            PickedValues[i]=PickedValues[j];
            PickedValues[j]=temp;
        }
        value1 = PickedValues[0];
        value2 = PickedValues[1];
        value3 = PickedValues[2];
        value4 = PickedValues[3];
    }

    private void setEnableAll(){
        boolean status = true;
        imgleft_high.setEnabled(status);
        imgright_high.setEnabled(status);
        imgleft_low.setEnabled(status);
        imgright_low.setEnabled(status);
    }
    private void setDisableAll(){
        boolean status = false;
        imgleft_high.setEnabled(status);
        imgright_high.setEnabled(status);
        imgleft_low.setEnabled(status);
        imgright_low.setEnabled(status);
    }

    private void MakeRandom4(){
        int TempValue;
        int index = 0;
        int maxindex = 3;
        TempValue = random.nextInt(10);
        PickedValues[index] = TempValue; //index = 0

        while (index < maxindex){
            TempValue = random.nextInt(10);
            if (HasValueArray(TempValue)){//if we have
                continue;
            }else{
                index+=1;
                PickedValues[index] = TempValue;
            }
        }
        //Debug only
//        ShowArray4();
        ShuffleArray4();
//        ShowArray4();
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

    private void setNewColorText(TextView tv){

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        gotoGameLevelSeason1();
    }

    private void MakeRoundImgCorners(ImageView img){
        img.setClipToOutline(true);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //BINDING
        /*
        value 1 -> img_left_high
        value 2 -> img_right_high
        value 3 -> img_left_low
        value 4 -> img_right_low
         */

        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_imgs_universal);//extends from level 1
        Level_3.Season1_options options = new Level_3.Season1_options();
        options.setOptions(this);
        a = AnimationUtils.loadAnimation(Level_3.this,R.anim.alpha);

        TextView lvl = (TextView) findViewById(R.id.text_level);
        lvl.setText(R.string.level3);

        //Preview Task
        dialog_preview  = new Dialog(this);//Create new dialog window
        dialog_preview.requestWindowFeature(Window.FEATURE_NO_TITLE);//hide tittle
        dialog_preview.setContentView(R.layout.preview_level);//path to dialog content
        dialog_preview.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//
        //transparent background of the dialog window
        dialog_preview.setCancelable(false);//window CAN NOT be closed by back button

        //Set new image to preview
        ImageView previewimg = (ImageView) dialog_preview.findViewById(R.id.img_preview_level);
        previewimg.setImageResource(R.drawable.s1_level_3_preview);

        //Set new text to preview
        TextView text_preview = (TextView) dialog_preview.findViewById(R.id.text_preview_description);
        text_preview.setText(R.string.text_s1_level3_description);
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

        MainBg = (ImageView) findViewById(R.id.four_imgs_background);
        MainBg.setImageResource(R.drawable.level_3_background);

        imgleft_high = (ImageView) findViewById(R.id.imgleft_high);
        imgleft_low = (ImageView) findViewById(R.id.imgleft_low);
        imgright_high = (ImageView) findViewById(R.id.imgright_high);
        imgright_low = (ImageView) findViewById(R.id.imgright_low);

        ImageView.ScaleType scale = ImageView.ScaleType.CENTER_CROP;
        imgleft_high.setScaleType(scale);
        imgleft_low.setScaleType(scale);
        imgright_high.setScaleType(scale);
        imgright_low.setScaleType(scale);

        txt_left_high = (TextView) findViewById(R.id.txt_left_high);
        txt_left_low = (TextView) findViewById(R.id.txt_left_low);
        txt_right_high = (TextView) findViewById(R.id.txt_right_high);
        txt_right_low = (TextView) findViewById(R.id.txt_right_low);

        txt_left_high.setTextColor(getResources().getColor(R.color.level3_color_text));
        txt_left_high.setShadowLayer(1.6f,1.5f,1.3f,R.color.black);
        txt_right_high.setTextColor(getResources().getColor(R.color.level3_color_text));
        txt_right_high.setShadowLayer(1.6f,1.5f,1.3f,R.color.black);
        txt_left_low.setTextColor(getResources().getColor(R.color.level3_color_text));
        txt_left_low.setShadowLayer(1.6f,1.5f,1.3f,R.color.black);
        txt_right_low.setTextColor(getResources().getColor(R.color.level3_color_text));
        txt_right_low.setShadowLayer(1.6f,1.5f,1.3f,R.color.black);




        MakeRoundImgCorners(imgleft_high);
        MakeRoundImgCorners(imgleft_low);
        MakeRoundImgCorners(imgright_high);
        MakeRoundImgCorners(imgright_low);

        MakeRandom4();
        setImageAndAnimation4(true);

        Back = (Button) findViewById(R.id.btn_back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGameLevelSeason1();
            }
        });

        //Timer
        timer_level3 = (ProgressBar) findViewById(R.id.timer);
        timer_txt_level3 = (TextView) findViewById(R.id.timer_text);
        timer = new Timer(timer_level3,timer_txt_level3);
        timer_level3.setClickable(false);
        timer_txt_level3.setTextColor(Color.parseColor("#FFFF0049"));
        timer_txt_level3.setShadowLayer(1.6f,1.5f,1.3f,R.color.black);

        timer_level3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer_level3.setClickable(false);
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
                int Max = FindMaxPickedValues();
                //LETS SHUFFLE
                if(value1 == Max){
                    imgright_high.dispatchTouchEvent(down);
                    imgright_high.dispatchTouchEvent(up);
                }
                else if (value2 == Max){
                    imgleft_high.dispatchTouchEvent(down);
                    imgleft_high.dispatchTouchEvent(up);
                }
                else if (value3 == Max){
                    imgright_low.dispatchTouchEvent(down);
                    imgright_low.dispatchTouchEvent(up);
                }
                else if (value4 == Max){
                    imgleft_low.dispatchTouchEvent(down);
                    imgleft_low.dispatchTouchEvent(up);
                }
            }
        });


        //=================== IMAGES TRIGGER =======================//
        imgleft_high.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    int Max = FindMaxPickedValues();
                    //block another 3 - start
                    imgright_high.setEnabled(false);
                    imgleft_low.setEnabled(false);
                    imgright_low.setEnabled(false);
                    //block another 3 - end
                    if (value1 == Max){
                        imgleft_high.setImageResource(R.drawable.true_answer);
                        imgright_high.setImageResource(R.drawable.false_answer);
                        imgleft_low.setImageResource(R.drawable.false_answer);
                        imgright_low.setImageResource(R.drawable.false_answer);

                    }
                    else{
                        imgleft_high.setImageResource(R.drawable.false_answer);
                        if(value2 == Max) imgright_high.setImageResource(R.drawable.true_answer);
                        else imgright_high.setImageResource(R.drawable.false_answer);
                        if(value3 == Max) imgleft_low.setImageResource(R.drawable.true_answer);
                        else imgleft_low.setImageResource(R.drawable.false_answer);
                        if(value4 == Max) imgright_low.setImageResource(R.drawable.true_answer);
                        else imgright_low.setImageResource(R.drawable.false_answer);
                    }

                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    int Max = FindMaxPickedValues();
                    if(value1 == Max)
                        UpdateScore(1);
                    else
                        UpdateScore(-2);

                    if(count == 10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 3){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",4);
                            editor.commit();
                        }
                        timer.TimerStop();
                        setDisableAll();
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!
                    }else{
                        MakePointsGray();
                        MakePointsGreen();
                        MakeRandom4();
                        setImageAndAnimation4(true);//set text and image AND animate them
                        timer.TimerStop();
                        timer.TimerInitial();
                        timer.TimerStart();
                        setEnableAll();
                    }
                }
                return true;
            }
        });

        imgright_high.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    int Max = FindMaxPickedValues();
                    imgleft_high.setEnabled(false);
                    imgleft_low.setEnabled(false);
                    imgright_low.setEnabled(false);
                    if (value2 == FindMaxPickedValues()){
                        imgright_high.setImageResource(R.drawable.true_answer);
                        imgleft_high.setImageResource(R.drawable.false_answer);
                        imgleft_low.setImageResource(R.drawable.false_answer);
                        imgright_low.setImageResource(R.drawable.false_answer);
                    }
                    else{
                        imgright_high.setImageResource(R.drawable.false_answer);
                        if(value1 == Max) imgleft_high.setImageResource(R.drawable.true_answer);
                        else imgleft_high.setImageResource(R.drawable.false_answer);
                        if(value3 == Max) imgleft_low.setImageResource(R.drawable.true_answer);
                        else imgleft_low.setImageResource(R.drawable.false_answer);
                        if(value4 == Max) imgright_low.setImageResource(R.drawable.true_answer);
                        else imgright_low.setImageResource(R.drawable.false_answer);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    int Max = FindMaxPickedValues();
                    if(value2 == Max)
                        UpdateScore(1);
                    else
                        UpdateScore(-2);

                    if(count == 10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 3){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",4);
                            editor.commit();
                        }
                        timer.TimerStop();
                        setDisableAll();
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!
                    }else{
                        MakePointsGray();
                        MakePointsGreen();
                        MakeRandom4();
                        setImageAndAnimation4(true);//set text and image AND animate them
                        timer.TimerStop();
                        timer.TimerInitial();
                        timer.TimerStart();
                        setEnableAll();
                    }
                }
                return true;
            }
        });

        imgleft_low.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    int Max = FindMaxPickedValues();
                    imgright_high.setEnabled(false);
                    imgright_low.setEnabled(false);
                    imgleft_high.setEnabled(false);
                    if (value3 == FindMaxPickedValues()){
                        imgleft_low.setImageResource(R.drawable.true_answer);
                        imgright_high.setImageResource(R.drawable.false_answer);
                        imgleft_high.setImageResource(R.drawable.false_answer);
                        imgright_low.setImageResource(R.drawable.false_answer);
                    }
                    else{
                        imgleft_low.setImageResource(R.drawable.false_answer);
                        if(value1 == Max) imgleft_high.setImageResource(R.drawable.true_answer);
                        else imgleft_high.setImageResource(R.drawable.false_answer);
                        if(value2 == Max) imgright_high.setImageResource(R.drawable.true_answer);
                        else imgright_high.setImageResource(R.drawable.false_answer);
                        if(value4 == Max) imgright_low.setImageResource(R.drawable.true_answer);
                        else imgright_low.setImageResource(R.drawable.false_answer);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    int Max = FindMaxPickedValues();
                    if(value3 == Max)
                        UpdateScore(1);
                    else
                        UpdateScore(-2);

                    if(count == 10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 3){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",4);
                            editor.commit();
                        }
                        timer.TimerStop();
                        setDisableAll();
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!
                    }else{
                        MakePointsGray();
                        MakePointsGreen();
                        MakeRandom4();
                        setImageAndAnimation4(true);//set text and image AND animate them
                        timer.TimerStop();
                        timer.TimerInitial();
                        timer.TimerStart();
                        setEnableAll();
                    }
                }
                return true;
            }
        });


        imgright_low.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    int Max = FindMaxPickedValues();
                    imgright_high.setEnabled(false);
                    imgleft_low.setEnabled(false);
                    imgleft_high.setEnabled(false);
                    if (value4 == FindMaxPickedValues()){
                        imgright_low.setImageResource(R.drawable.true_answer);
                        imgleft_low.setImageResource(R.drawable.false_answer);
                        imgright_high.setImageResource(R.drawable.false_answer);
                        imgleft_high.setImageResource(R.drawable.false_answer);
                    }
                    else{
                        imgright_low.setImageResource(R.drawable.false_answer);
                        if(value1 == Max) imgleft_high.setImageResource(R.drawable.true_answer);
                        else imgleft_high.setImageResource(R.drawable.false_answer);
                        if(value2 == Max) imgright_high.setImageResource(R.drawable.true_answer);
                        else imgright_high.setImageResource(R.drawable.false_answer);
                        if(value3 == Max) imgleft_low.setImageResource(R.drawable.true_answer);
                        else imgleft_low.setImageResource(R.drawable.false_answer);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    int Max = FindMaxPickedValues();
                    if(value4 == Max)
                        UpdateScore(1);
                    else
                        UpdateScore(-2);

                    if(count == 10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 3){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",4);
                            editor.commit();
                        }
                        timer.TimerStop();
                        setDisableAll();
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!
                    }else{
                        MakePointsGray();
                        MakePointsGreen();
                        MakeRandom4();
                        setImageAndAnimation4(true);//set text and image AND animate them
                        timer.TimerStop();
                        timer.TimerInitial();
                        timer.TimerStart();
                        setEnableAll();
                    }
                }
                return true;
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
        newtext.setText(R.string.text_preview_level3_end);

        NextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_nextlevel.dismiss();
                gotoLevel4();
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
        NewAnime.setAnimation(R.raw.l3);
//        dialog_nextlevel.show();
        NewAnime.setRepeatMode(LottieDrawable.RESTART);
        LottieAnimationView snow = (LottieAnimationView) dialog_nextlevel.findViewById(R.id.snow_anime);
        snow.setVisibility(View.GONE);
        newtext.setTextColor(getResources().getColor(R.color.black95));


    }
    //======================EXTRA FUNCTIONS=======================//
    private void  gotoGameLevelSeason1(){
        if (timer != null) timer.TimerStop(); // we need to stop it first !!!!!!!!!!!!!!!!
        try {
            PlayMusic.Continue();
            Intent intent = new Intent(Level_3.this, GameLevels_S1.class);
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

    private void gotoLevel4(){
        try {
            Intent intent = new Intent(Level_3.this,Level_4.class);
            startActivity(intent);finish();
        }catch (Exception exp){

        }
    }


}

