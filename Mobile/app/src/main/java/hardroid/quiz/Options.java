package hardroid.quiz;

import android.app.Activity;
import android.view.Window;
import android.view.WindowManager;

public abstract  class Options {
    public  void HideAndroidConditionBar(Activity CurrentActivity) {
        CurrentActivity.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
    };




}
