package hardroid.quiz.season1;

import android.content.Context;
import android.media.MediaPlayer;

import hardroid.quiz.R;


public class PlayMusic {
    public  static MediaPlayer Track;
    private static int length = 0;
    private  static  float volume_lvl = 1;

    public static  void  SetAudio(Context context, int audio){
        Stop();
        Track = MediaPlayer.create(context, audio);
    }



    public static void Start(){
        if (Track != null) {
            Stop();
            Track.start();
        }

    }

    public static void Stop(){
        if(Track!=null){
            if(Track.isPlaying()){
                Track.stop();
            }
        }

    }
    public static void Pause(){
        if(Track!=null){
            length = Track.getCurrentPosition();
            Track.pause();
        }

    }

    public static void Continue(){
        if(Track!=null){
            Track.seekTo(length);
            Track.start();
        }

    }

    public static void StartLoop(boolean looping){
        if(Track!=null){
            Track.setLooping(looping);
        }
    }


    public static void SetVolume(float volume){
        volume_lvl = volume;
        Track.setVolume(volume,volume);
    }

     public static  boolean IsHighVolume(){
        boolean VolumeOn  = false;
        if(volume_lvl == 1.0) VolumeOn = true;
        else VolumeOn = false;
        return  VolumeOn;
     }
}
