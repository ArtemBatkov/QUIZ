package hardroid.quiz.season1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.TypedValue;
import android.view.Gravity;
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
import android.widget.TextClock;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import org.w3c.dom.Text;

import java.util.Locale;
import java.util.Random;
import java.util.logging.Level;

import hardroid.quiz.Options;
import hardroid.quiz.R;
import hardroid.quiz.Timer;

public class Level_15 extends AppCompatActivity {


    private  class  Season1_options extends Options {
        private void setOptions(Activity activity){
            HideAndroidConditionBar(activity);
        }
    }
    private Timer timer;

    private TextView left_high, right_high, left_low, right_low;

    private LinearLayout.LayoutParams params;

    private Dialog dialog_preview,dialog_nextlevel;
    private ProgressBar timer_level15;
    private Button Continue,Back,NextLevel;
    private Animation a;
    private ImageView MainBg,imgleft_high,imgright_high,imgleft_low,imgright_low;
    private Random random = new Random();
    private int[] PickedValues = {-1,-1,-1,-1};
    private Level15_Array array;
    private int value1,value2,value3,value4;
    private int count = 0;

    private TextView txt_left_high, txt_left_low, txt_right_high, txt_right_low,timer_txt_level15,button_close,btn_close2;

    private void UpdateScore(int points){
        //the points can be add here or be removed
        count = count + points;
        if(count < 0) count = 0;
        if (count >= 10) {
            count = 10;
        }
    }


    private void setImageAndAnimation4(){
        //Left high
        a = AnimationUtils.loadAnimation(Level_15.this,R.anim.alpha);
        left_high.setText(Character.toString(array.letters15.charAt(value1)));
        left_high.startAnimation(a);

        //Right High
        a = AnimationUtils.loadAnimation(Level_15.this,R.anim.alpha);
        right_high.setText(Character.toString(array.letters15.charAt(value2)));
        right_high.startAnimation(a);

        //Left low
        a = AnimationUtils.loadAnimation(Level_15.this,R.anim.alpha);
        left_low.setText(Character.toString(array.letters15.charAt(value3)));
        left_low.startAnimation(a);

        //Right low
        a = AnimationUtils.loadAnimation(Level_15.this,R.anim.alpha);
        right_low.setText(Character.toString(array.letters15.charAt(value4)));
        right_low.startAnimation(a);
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
        left_high.setEnabled(status);
        right_high.setEnabled(status);
        left_low.setEnabled(status);
        right_low.setEnabled(status);
    }
    private void setDisableAll(){
        boolean status = false;
        left_high.setEnabled(status);
        right_high.setEnabled(status);
        left_low.setEnabled(status);
        right_low.setEnabled(status);
    }

    private int CurrentIndex;
    private String CurrentLetter;

    private void MakeRandom4(){
        int TempValue;
        int index = 0;
        int maxindex = 3;

        TempValue = random.nextInt(array.letters15.length());
        CurrentLetter = Character.toString(array.letters15.charAt(TempValue));

        PickedValues[index] = TempValue; //index = 0
        //0
        //1
        //2


        while (index < maxindex){
            TempValue = random.nextInt(array.letters15.length());
            if (HasValueArray(TempValue)){//if we have
                continue;
            }else{
                index+=1;
                PickedValues[index] = TempValue;
            }
        }
        //Debug only
        ShowArray4();
        ShuffleArray4();
        ShowArray4();

        FindMin();



    }

    private  void FindMin(){
        int min = PickedValues[0];
        for(int i = 0; i < PickedValues.length; i++){
            if(min>PickedValues[i]) min = PickedValues[i];
        }
        CurrentIndex = min;
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


    private void MakeRoundImgCorners(TextView img){
        img.setClipToOutline(true);
    }

    private void ResetBackground(){
        left_high.setBackgroundResource(R.color.finalcards);
        right_high.setBackgroundResource(R.color.finalcards);
        left_low.setBackgroundResource(R.color.finalcards);
        right_low.setBackgroundResource(R.color.finalcards);
    }
    private Locale locale ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_imgs_universal);//extends from level 1
        Level_15.Season1_options options = new Level_15.Season1_options();
        options.setOptions(this);

        Resources res = getResources();
        Configuration conf = res.getConfiguration();
        locale = getResources().getConfiguration().locale;
        array = new Level15_Array(locale.getLanguage());



        Typeface typeface = ResourcesCompat.getFont(this, R.font.mountains_of_christmas_bold);

        TextView text_left_high = (TextView)  findViewById(R.id.txt_left_high);
        TextView text_right_high = (TextView)  findViewById(R.id.txt_right_high);
        TextView text_left_low = (TextView)  findViewById(R.id.txt_left_low);
        TextView text_right_low = (TextView)  findViewById(R.id.txt_right_low);


        LinearLayout line3 = (LinearLayout) findViewById(R.id.line3);

        ImageView left_high_img = (ImageView) findViewById(R.id.imgleft_high);
        left_high = new TextView(this);
        LinearLayout.LayoutParams params1 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,left_high_img.getHeight());
        params1.gravity = Gravity.CENTER;
        left_high.setText("A");
        left_high.setTypeface(typeface);
        left_high.setTextColor(getResources().getColor(R.color.black));
        left_high.setTextSize(TypedValue.COMPLEX_UNIT_SP,text_left_high.getTextSize());
        left_high.setLayoutParams(left_high_img.getLayoutParams());
        left_high_img.setVisibility(View.GONE);
        left_high.setVisibility(View.VISIBLE);
        left_high.setGravity(Gravity.CENTER);

        ImageView right_high_img = (ImageView) findViewById(R.id.imgright_high);
        right_high = new TextView(this);
        LinearLayout.LayoutParams params2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,right_high_img.getHeight());
        params2.gravity = Gravity.CENTER;
        right_high.setText("B");
        right_high.setTypeface(typeface);
        right_high.setTextColor(getResources().getColor(R.color.black));
        right_high.setTextSize(TypedValue.COMPLEX_UNIT_SP,text_right_high.getTextSize());
        right_high.setLayoutParams(right_high_img.getLayoutParams());
        right_high_img.setVisibility(View.GONE);
        right_high.setVisibility(View.VISIBLE);
        right_high.setGravity(Gravity.CENTER);

        line3.addView(left_high);
        line3.addView(right_high);

        LinearLayout line4 = (LinearLayout) findViewById(R.id.line4);

        ImageView left_low_img = (ImageView) findViewById(R.id.imgleft_low);
        left_low = new TextView(this);
        LinearLayout.LayoutParams params3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,left_low_img.getHeight());
        params3.gravity = Gravity.CENTER;
        left_low.setText("C");
        left_low.setTypeface(typeface);
        left_low.setTextColor(getResources().getColor(R.color.black));
        left_low.setLayoutParams( left_low_img.getLayoutParams());
        left_low.setTextSize(TypedValue.COMPLEX_UNIT_SP,text_left_low.getTextSize());
        left_low_img.setVisibility(View.GONE);
        left_low.setVisibility(View.VISIBLE);
        left_low.setGravity(Gravity.CENTER);

        ImageView right_low_img = (ImageView) findViewById(R.id.imgright_low);
        right_low = new TextView(this);
        LinearLayout.LayoutParams params4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,right_low_img.getHeight());
        params4.gravity = Gravity.CENTER;
        right_low.setText("D");
        right_low.setTypeface(typeface);
        right_low.setLayoutParams(right_low_img.getLayoutParams());
        right_low.setTextColor(getResources().getColor(R.color.black));
        right_low.setTextSize(TypedValue.COMPLEX_UNIT_SP,text_right_low.getTextSize());
        right_low_img.setVisibility(View.GONE);
        right_low.setVisibility(View.VISIBLE);
        right_low.setGravity(Gravity.CENTER);

        line4.addView(left_low);
        line4.addView(right_low);



        text_left_high.setVisibility(View.GONE);
        text_right_high.setVisibility(View.GONE);
        text_left_low.setVisibility(View.GONE);
        text_right_low.setVisibility(View.GONE);

        LinearLayout line5 = (LinearLayout) findViewById(R.id.line5);
        line5.setGravity(Gravity.CENTER);

        MakeRandom4();
        setImageAndAnimation4();

        //BINDING
        /*
        value 1 -> img_left_high
        value 2 -> img_right_high
        value 3 -> img_left_low
        value 4 -> img_right_low
         */

        TextView lvl = (TextView) findViewById(R.id.text_level);
        lvl.setText(R.string.level15);

        //Preview Task
        dialog_preview  = new Dialog(this);//Create new dialog window
        dialog_preview.requestWindowFeature(Window.FEATURE_NO_TITLE);//hide tittle
        dialog_preview.setContentView(R.layout.preview_level);//path to dialog content
        dialog_preview.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));//
        //transparent background of the dialog window
        dialog_preview.setCancelable(false);//window CAN NOT be closed by back button

        //Set new image to preview
        ImageView previewimg = (ImageView) dialog_preview.findViewById(R.id.img_preview_level);
        previewimg.setImageResource(R.drawable.s1_level15_preview);



        //Set new text to preview
        TextView text_preview = (TextView) dialog_preview.findViewById(R.id.text_preview_description);
        text_preview.setText(R.string.text_s1_level15_description);
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
        MainBg.setImageResource(R.drawable.s1_level15_background);

        MakeRoundImgCorners(left_high);
        MakeRoundImgCorners(left_low);
        MakeRoundImgCorners(right_high);
        MakeRoundImgCorners(right_low);


        Back = (Button) findViewById(R.id.btn_back);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoGameLevelSeason1();
            }
        });

        //Timer
        timer_level15 = (ProgressBar) findViewById(R.id.timer);
        timer_txt_level15 = (TextView) findViewById(R.id.timer_text);
        timer = new Timer(timer_level15,timer_txt_level15);
        timer_level15.setClickable(false);
        timer_txt_level15.setTextColor(Color.parseColor("#FFFF0049"));
        timer_txt_level15.setShadowLayer(1.6f,1.5f,1.3f,R.color.black);

        timer_level15.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer_level15.setClickable(false);
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
                if(value1 == CurrentIndex){
                    right_high.dispatchTouchEvent(down);
                    right_high.dispatchTouchEvent(up);
                }
                else if (value2 == CurrentIndex){
                    left_high.dispatchTouchEvent(down);
                    left_high.dispatchTouchEvent(up);
                }
                else if (value3 == CurrentIndex){
                    right_low.dispatchTouchEvent(down);
                    right_low.dispatchTouchEvent(up);
                }
                else if (value4 == CurrentIndex){
                    left_low.dispatchTouchEvent(down);
                    left_low.dispatchTouchEvent(up);
                }
            }
        });

        ResetBackground();

        left_high.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    //block another 3 - start
                    right_high.setEnabled(false);
                    left_low.setEnabled(false);
                    right_low.setEnabled(false);
                    //block another 3 - end
                    if (value1 == CurrentIndex){
                        left_high.setBackgroundResource(R.drawable.true_answer);
                        right_high.setBackgroundResource(R.drawable.false_answer);
                        left_low.setBackgroundResource(R.drawable.false_answer);
                        right_low.setBackgroundResource(R.drawable.false_answer);

                    }
                    else{
                        left_high.setBackgroundResource(R.drawable.false_answer);
                        if(value2 == CurrentIndex) right_high.setBackgroundResource(R.drawable.true_answer);
                        else right_high.setBackgroundResource(R.drawable.false_answer);
                        if(value3 == CurrentIndex) left_low.setBackgroundResource(R.drawable.true_answer);
                        else left_low.setBackgroundResource(R.drawable.false_answer);
                        if(value4 == CurrentIndex) right_low.setBackgroundResource(R.drawable.true_answer);
                        else right_low.setBackgroundResource(R.drawable.false_answer);
                    }

                }
                else if (event.getAction() == MotionEvent.ACTION_UP){;
                    if(value1 ==CurrentIndex)
                        UpdateScore(1);
                    else
                        UpdateScore(-2);

                    if(count == 10){
                        timer.TimerStop();
                        setDisableAll();
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!
                    }else{
                        ResetBackground();
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

        right_high.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    left_high.setEnabled(false);
                    left_low.setEnabled(false);
                    right_low.setEnabled(false);
                    if (value2 == CurrentIndex){
                        right_high.setBackgroundResource(R.drawable.true_answer);
                        left_high.setBackgroundResource(R.drawable.false_answer);
                        left_low.setBackgroundResource(R.drawable.false_answer);
                        right_low.setBackgroundResource(R.drawable.false_answer);
                    }
                    else{
                        right_high.setBackgroundResource(R.drawable.false_answer);
                        if(value1 == CurrentIndex) left_high.setBackgroundResource(R.drawable.true_answer);
                        else left_high.setBackgroundResource(R.drawable.false_answer);
                        if(value3 == CurrentIndex) left_low.setBackgroundResource(R.drawable.true_answer);
                        else left_low.setBackgroundResource(R.drawable.false_answer);
                        if(value4 == CurrentIndex) right_low.setBackgroundResource(R.drawable.true_answer);
                        else right_low.setBackgroundResource(R.drawable.false_answer);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    if(value2 == CurrentIndex)
                        UpdateScore(1);
                    else
                        UpdateScore(-2);

                    if(count == 10){
                        timer.TimerStop();
                        setDisableAll();
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!
                    }else{
                        ResetBackground();
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

        left_low.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    right_high.setEnabled(false);
                    right_low.setEnabled(false);
                    left_high.setEnabled(false);
                    if (value3 == CurrentIndex){
                        left_low.setBackgroundResource(R.drawable.true_answer);
                        right_high.setBackgroundResource(R.drawable.false_answer);
                        left_high.setBackgroundResource(R.drawable.false_answer);
                        right_low.setBackgroundResource(R.drawable.false_answer);
                    }
                    else{
                        left_low.setBackgroundResource(R.drawable.false_answer);
                        if(value1 == CurrentIndex) left_high.setBackgroundResource(R.drawable.true_answer);
                        else left_high.setBackgroundResource(R.drawable.false_answer);
                        if(value2 == CurrentIndex) right_high.setBackgroundResource(R.drawable.true_answer);
                        else right_high.setBackgroundResource(R.drawable.false_answer);
                        if(value4 == CurrentIndex) right_low.setBackgroundResource(R.drawable.true_answer);
                        else right_low.setBackgroundResource(R.drawable.false_answer);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    if(value3 == CurrentIndex)
                        UpdateScore(1);
                    else
                        UpdateScore(-2);

                    if(count == 10){
                        timer.TimerStop();
                        setDisableAll();
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!
                    }else{
                        ResetBackground();
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


        right_low.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN){
                    right_high.setEnabled(false);
                    left_low.setEnabled(false);
                    left_high.setEnabled(false);
                    if (value4 == CurrentIndex){
                        right_low.setBackgroundResource(R.drawable.true_answer);
                        left_low.setBackgroundResource(R.drawable.false_answer);
                        right_high.setBackgroundResource(R.drawable.false_answer);
                        left_high.setBackgroundResource(R.drawable.false_answer);
                    }
                    else{
                        right_low.setBackgroundResource(R.drawable.false_answer);
                        if(value1 == CurrentIndex) left_high.setBackgroundResource(R.drawable.true_answer);
                        else left_high.setBackgroundResource(R.drawable.false_answer);
                        if(value2 == CurrentIndex) right_high.setBackgroundResource(R.drawable.true_answer);
                        else right_high.setBackgroundResource(R.drawable.false_answer);
                        if(value3 == CurrentIndex) left_low.setBackgroundResource(R.drawable.true_answer);
                        else left_low.setBackgroundResource(R.drawable.false_answer);
                    }
                }
                else if (event.getAction() == MotionEvent.ACTION_UP){
                    if(value4 == CurrentIndex)
                        UpdateScore(1);
                    else
                        UpdateScore(-2);

                    if(count == 10){
                        timer.TimerStop();
                        setDisableAll();
                        dialog_nextlevel.show(); //!!!!!!!!!!!!!!!!!!!!
                    }else{
                        ResetBackground();
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
        newtext.setText(R.string.text_s1_level15_description_end);

        NextLevel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_nextlevel.dismiss();
                gotoSeason1End();
            }
        });

        btn_close2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog_nextlevel.dismiss();
                gotoGameLevelSeason1();
            }
        });


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
            Intent intent = new Intent(Level_15.this, GameLevels_S1.class);
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

    private void gotoSeason1End(){
        if (timer != null) timer.TimerStop(); // we need to stop it first !!!!!!!!!!!!!!!!
        try {
            Intent intent = new Intent(Level_15.this, S1_titles.class);
            startActivity(intent); finish();
        }
        catch (Exception exp){}
    }

}
