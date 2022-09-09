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
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.airbnb.lottie.LottieDrawable;

import java.util.Random;

import hardroid.quiz.Options;
import hardroid.quiz.R;
import hardroid.quiz.Timer;

public class Level_13 extends AppCompatActivity {
    private  class  Season1_options extends Options {
        private void setOptions(Activity activity){
            HideAndroidConditionBar(activity);
        }
    }
    private Timer timer;
    private ProgressBar timer_level13;
    private TextView timer_txt_level13;
    private Animation a;
    private Dialog dialog_preview,dialog_nextlevel;
    private  TextView button_close,btn_close2;
    private Button Back,Continue,NextLevel,Answer1,Answer2,Answer3,Answer4;
    private ImageView MainBg,ImgTask;
    private Random random = new Random();
    private int[] PickedValues = {-1,-1,-1,-1};
    private int CurrentImage =  -1;
    private int CurrentImageText = -1;
    private int CurrentIndex = -1;
    private int count  = 0;
    private Level13_Array array = new Level13_Array();

    private void MakeRoundImgCorners(ImageView img){
        img.setClipToOutline(true);
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
    }

    private void MakeRandom4(){
        int TempValue;
        int index = 0;
        int maxindex = 3;

        CurrentIndex = random.nextInt(40);
        CurrentImage = array.images13[CurrentIndex];
        CurrentImageText = array.text13[CurrentIndex];
        PickedValues[index] = CurrentIndex; //index = 0
        //0
        //1
        //2


        while (index < maxindex){
            TempValue = random.nextInt(40);
            if (HasValueArray(TempValue)){//if we have
                continue;
            }else{
                index+=1;
                PickedValues[index] = TempValue;
            }
        }
        System.out.println(getText(CurrentImageText));
        //Debug only
        ShowArray4();
        ShuffleArray4();
        System.out.println("\n");
        ShowArray4();

    }

    private void setImageAndAnimation4(){
        a = AnimationUtils.loadAnimation(Level_13.this,R.anim.alpha);
        ImgTask.setImageResource(CurrentImage);
        ImgTask.startAnimation(a);

        a = AnimationUtils.loadAnimation(Level_13.this,R.anim.alpha);
        Answer1.setText(array.text13[PickedValues[0]]);
        Answer1.startAnimation(a);
        a = AnimationUtils.loadAnimation(Level_13.this,R.anim.alpha);
        Answer2.setText(array.text13[PickedValues[1]]);
        Answer2.startAnimation(a);
        a = AnimationUtils.loadAnimation(Level_13.this,R.anim.alpha);
        Answer3.setText(array.text13[PickedValues[2]]);
        Answer3.startAnimation(a);
        a = AnimationUtils.loadAnimation(Level_13.this,R.anim.alpha);
        Answer4.setText(array.text13[PickedValues[3]]);
        Answer4.startAnimation(a);
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


    private void setEnableAll(){
        boolean status = true;
        Answer1.setEnabled(status);
        Answer2.setEnabled(status);
        Answer3.setEnabled(status);
        Answer4.setEnabled(status);
    }
    private void setDisableAll(){
        boolean status = false;
        Answer1.setEnabled(status);
        Answer2.setEnabled(status);
        Answer3.setEnabled(status);
        Answer4.setEnabled(status);
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

    private void SetDefaultBackground(){
        Answer1.setBackgroundResource(R.drawable.btn_answer_unpressed);
        Answer2.setBackgroundResource(R.drawable.btn_answer_unpressed);
        Answer3.setBackgroundResource(R.drawable.btn_answer_unpressed);
        Answer4.setBackgroundResource(R.drawable.btn_answer_unpressed);
    }

    private void doShadow(TextView tv,int color){
        tv.setShadowLayer(1.6f,1.5f,1.3f,getResources().getColor(color));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_buttons_universal);//extends from level 1
        Level_13.Season1_options options = new Level_13.Season1_options();
        options.setOptions(this);

        //BINDING
        /*
        value 1 -> answer 1
        value 2 -> answer 2
        value 3 -> answer 3
        value 4 -> answer 4
         */

        a = AnimationUtils.loadAnimation(Level_13.this,R.anim.alpha);

        TextView lvl = (TextView) findViewById(R.id.text_level);
        lvl.setText(R.string.level13);

        //Preview Task
        dialog_preview  = new Dialog(this);//Create new dialog window
        dialog_preview.requestWindowFeature(Window.FEATURE_NO_TITLE);//hide tittle
        dialog_preview.setContentView(R.layout.preview_level);//path to dialog content
        dialog_preview.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//
        //transparent background of the dialog window
        dialog_preview.setCancelable(false);//window CAN NOT be closed by back button


        //Set new image to preview
        ImageView previewimg = (ImageView) dialog_preview.findViewById(R.id.img_preview_level);
        previewimg.setImageResource(R.drawable.s1_level13_preview);
        previewimg.setScaleType(ImageView.ScaleType.FIT_XY);

        //Set new text to preview
        TextView text_preview = (TextView) dialog_preview.findViewById(R.id.text_preview_description);
        text_preview.setText(R.string.text_s1_level13_description);
        text_preview.setTextColor(getResources().getColor(R.color.white));
        doShadow(text_preview,R.color.white);
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
        MainBg.setImageResource(R.drawable.s1_level13_background);

        Answer1 = (Button) findViewById(R.id.answer1);
        Answer2 = (Button) findViewById(R.id.answer2);
        Answer3 = (Button) findViewById(R.id.answer3);
        Answer4 = (Button) findViewById(R.id.answer4);

        ImgTask = (ImageView) findViewById(R.id.main_img);
        MakeRoundImgCorners(ImgTask);


        MakeRandom4();
        setImageAndAnimation4();

        Back = (Button) findViewById(R.id.btn_back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGameLevelSeason1();
            }
        });

        //Timer
        timer_level13 = (ProgressBar) findViewById(R.id.timer);
        timer_txt_level13 = (TextView) findViewById(R.id.timer_text);
        timer = new Timer(timer_level13,timer_txt_level13);
        timer_level13.setClickable(false);
        timer_txt_level13.setTextColor(Color.parseColor("#FFFF0049"));
        timer_txt_level13.setShadowLayer(1.6f,1.5f,1.3f,R.color.black);

        timer_level13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer_level13.setClickable(false);
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
                //LETS SHUFFLE
                if(CurrentIndex == PickedValues[0]){//Answer1
                    Answer2.dispatchTouchEvent(down);
                    Answer2.dispatchTouchEvent(up);
                }
                else if (CurrentIndex == PickedValues[1]){
                    Answer1.dispatchTouchEvent(down);
                    Answer1.dispatchTouchEvent(up);
                }
                else if (CurrentIndex == PickedValues[2]){
                    Answer4.dispatchTouchEvent(down);
                    Answer4.dispatchTouchEvent(up);
                }
                else if (CurrentIndex == PickedValues[3]){
                    Answer3.dispatchTouchEvent(down);
                    Answer3.dispatchTouchEvent(up);
                }
            }
        });


        //=================== BUTTONS TRIGGER =======================//
        Answer1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Answer2.setEnabled(false);
                    Answer3.setEnabled(false);
                    Answer4.setEnabled(false);
                    //block another 3 - end
                    if (CurrentIndex == PickedValues[0]){
                        Answer1.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        Answer2.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        Answer3.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        Answer4.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                    }
                    else{
                        Answer1.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        if(CurrentIndex == PickedValues[1]) Answer2.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        else Answer2.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        if(CurrentIndex == PickedValues[2]) Answer3.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        else Answer3.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        if(CurrentIndex == PickedValues[3]) Answer4.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        else Answer4.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    SetDefaultBackground();
                    if(CurrentIndex == PickedValues[0])
                        UpdateScore(1);
                    else
                        UpdateScore(-2);

                    if(count == 10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 13){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",14);
                            editor.commit();
                        }
                        timer.TimerStop();
                        setDisableAll();
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!
                    }else{
                        MakePointsGray();
                        MakePointsGreen();
                        MakeRandom4();
                        setImageAndAnimation4();//set text and image AND animate them
                        timer.TimerStop();
                        timer.TimerInitial();
                        timer.TimerStart();
                        setEnableAll();
                    }
                }
                return true;
            }
        });

        Answer2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Answer1.setEnabled(false);
                    Answer3.setEnabled(false);
                    Answer4.setEnabled(false);
                    //block another 3 - end
                    if (CurrentIndex == PickedValues[1]){
                        Answer1.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        Answer2.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        Answer3.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        Answer4.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                    }
                    else{
                        Answer2.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        if(CurrentIndex == PickedValues[0]) Answer1.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        else Answer1.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        if(CurrentIndex == PickedValues[2]) Answer3.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        else Answer3.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        if(CurrentIndex == PickedValues[3]) Answer4.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        else Answer4.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    SetDefaultBackground();
                    if(CurrentIndex == PickedValues[1])
                        UpdateScore(1);
                    else
                        UpdateScore(-2);

                    if(count == 10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 13){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",14);
                            editor.commit();
                        }
                        timer.TimerStop();
                        setDisableAll();
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!
                    }else{
                        MakePointsGray();
                        MakePointsGreen();
                        MakeRandom4();
                        setImageAndAnimation4();//set text and image AND animate them
                        timer.TimerStop();
                        timer.TimerInitial();
                        timer.TimerStart();
                        setEnableAll();
                    }
                }
                return true;
            }
        });

        Answer3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Answer1.setEnabled(false);
                    Answer2.setEnabled(false);
                    Answer4.setEnabled(false);
                    //block another 3 - end
                    if (CurrentIndex == PickedValues[2]){
                        Answer1.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        Answer2.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        Answer3.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        Answer4.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                    }
                    else{
                        Answer3.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        if(CurrentIndex == PickedValues[0]) Answer1.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        else Answer1.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        if(CurrentIndex == PickedValues[1]) Answer2.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        else Answer2.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        if(CurrentIndex == PickedValues[3]) Answer4.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        else Answer4.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    SetDefaultBackground();
                    if(CurrentIndex == PickedValues[2])
                        UpdateScore(1);
                    else
                        UpdateScore(-2);

                    if(count == 10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 13){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",14);
                            editor.commit();
                        }
                        timer.TimerStop();
                        setDisableAll();
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!
                    }else{
                        MakePointsGray();
                        MakePointsGreen();
                        MakeRandom4();
                        setImageAndAnimation4();//set text and image AND animate them
                        timer.TimerStop();
                        timer.TimerInitial();
                        timer.TimerStart();
                        setEnableAll();
                    }
                }
                return true;
            }
        });

        Answer4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    Answer1.setEnabled(false);
                    Answer2.setEnabled(false);
                    Answer3.setEnabled(false);
                    //block another 3 - end
                    if (CurrentIndex == PickedValues[3]){
                        Answer1.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        Answer2.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        Answer3.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        Answer4.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                    }
                    else{
                        Answer4.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        if(CurrentIndex == PickedValues[0]) Answer1.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        else Answer1.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        if(CurrentIndex == PickedValues[1]) Answer2.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        else Answer2.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                        if(CurrentIndex == PickedValues[2]) Answer3.setBackgroundResource(R.drawable.btn_answer_pressed_right);
                        else Answer3.setBackgroundResource(R.drawable.btn_answer_pressed_wrong);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    SetDefaultBackground();
                    if(CurrentIndex == PickedValues[3])
                        UpdateScore(1);
                    else
                        UpdateScore(-2);

                    if(count == 10){
                        SharedPreferences save = getSharedPreferences("Save",MODE_PRIVATE);
                        final int level = save.getInt("Level",1);
                        if (level > 13){

                        }
                        else{
                            SharedPreferences.Editor editor = save.edit();
                            editor.putInt("Level",14);
                            editor.commit();
                        }
                        timer.TimerStop();
                        setDisableAll();
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!
                    }else{
                        MakePointsGray();
                        MakePointsGreen();
                        MakeRandom4();
                        setImageAndAnimation4();//set text and image AND animate them
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
        newtext.setText(R.string.text_s1_level13_description_end);

        ScrollView scr = (ScrollView)  dialog_nextlevel.findViewById(R.id.scroll_end);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.weight = 0.0f;
        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        scr.setLayoutParams(params);

//        params.weight = 0.0f;
//        params.height = LinearLayout.LayoutParams.WRAP_CONTENT;
//        newtext.setLayoutParams(params);

        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        params2.height =  100;
        params2.weight = 0.0f;
        params2.topMargin = 20;
        NextLevel.setLayoutParams(params2);

        FrameLayout.LayoutParams params3 = new FrameLayout.LayoutParams(
                FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        LinearLayout layout = (LinearLayout) dialog_nextlevel.findViewById(R.id.layout_preview_endlevel1);
        params3.height = LinearLayout.LayoutParams.WRAP_CONTENT;
        params3.gravity = Gravity.CENTER;
        params3.setMargins(10,10,10,10);
        layout.setLayoutParams(params3);

        NextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_nextlevel.dismiss();
                gotoLevel14();
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
        NewAnime.setAnimation(R.raw.s13);
        NewAnime.setAlpha(1f);
        NewAnime.setRepeatMode(LottieDrawable.REVERSE);
//        dialog_nextlevel.show();


        NewAnime.setRepeatMode(LottieDrawable.RESTART);
        LottieAnimationView snow = (LottieAnimationView) dialog_nextlevel.findViewById(R.id.snow_anime);
        snow.setVisibility(View.GONE);
        newtext.setTextColor(getResources().getColor(R.color.black95));

    }


    //======EXTRA FUNCTIONS=======//

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
            Intent intent = new Intent(Level_13.this, GameLevels_S1.class);
            startActivity(intent); finish();
        }
        catch (Exception exp){}
    }

    private void gotoLevel14(){
        try {
            Intent intent = new Intent(Level_13.this,Level_14.class);
            startActivity(intent);finish();
        }catch (Exception exp){

        }
    }
}
