package hardroid.quiz;
import android.animation.ObjectAnimator;
import android.os.CountDownTimer;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class Timer extends Thread {
    private ProgressBar timer;
    private  TextView text;

    private long TimeWindow = 5000; // 5sec
    private long TimeInterval = 1000;
    private int initial_value = (int) ((float)TimeWindow/1000);
    private  int current_value;
    private boolean ticking = false;

    public boolean TimerIsTicking(){
        return ticking;
    }

    private Thread timer_thread;
    private CountDownTimer time;
    public boolean ThreadAlive(){return  timer_thread.isAlive();};


    public void ChangeTimer(long TimeWindow,long TimeInterval){
        //Change Timer params
        //!INTERRUPT TIMER!
        TimerStop();
        this.TimeWindow  = TimeWindow;
        this.TimeInterval= TimeInterval;
        initial_value = (int) ((float)TimeWindow/1000);
        this.timer.setMax(initial_value);
    }

    public Timer(ProgressBar timer, TextView text){
        //begin condition of the timer
        this.timer = timer;
        this.text = text;
        timer_thread = new Thread();
        this.timer.setMax(initial_value);

    }

    public void TimerInitial(){
        //set timer to initial state
        current_value = initial_value;
        this.timer.setProgress(100);
        this.text.setText(String.valueOf(initial_value));
    }

    public void TimerStart(){
            run();
        }

    public void TimerStop(){
        if(this.time!=null) {
            time.cancel();
            timer_thread.interrupt();
        }
    }

    private void UpdateTimer(){
        this.text.setText(String.valueOf(current_value));
        this.timer.setProgress(current_value);
        //Debug only
//        System.out.println(current_value);
//        System.out.println("timer is ticking: "+TimerIsTicking());
    }

    private void AlarmEndTime(){
        //when time is end, on finish calls this method
        //click on timer in Main Thread
//        timer.setClickable(true);
        timer.performClick();
    }


    public void run() {
        this.time = new CountDownTimer(TimeWindow,TimeInterval) {
            @Override
            public void onTick(long millisUntilFinished) {
                current_value -= 1;
                UpdateTimer();
            }

            @Override
            public void onFinish() {
                text.setText("0");
                timer.setProgress(0);
                AlarmEndTime();
            }
        }.start();
    }




}
