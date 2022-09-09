package hardroid.quiz.season1;

import android.content.res.Configuration;
import android.content.res.Resources;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class Level15_Array extends AppCompatActivity {

    private Locale locale ;
    private String rus = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";
    private  String eng = "abcdefghijklmnopqrstuvwxyz";

    public Level15_Array(String str){
        if(str.toLowerCase(Locale.ROOT).equals("ru")){
            letters15 = rus;
        }else{
            letters15 = eng;
        }
    }
    final public String letters15;

}
