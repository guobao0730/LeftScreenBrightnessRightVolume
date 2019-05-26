package com.casic.leftscreenbrightnessrightvolume;

import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;

import com.blankj.utilcode.util.BrightnessUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ScreenUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";
    private float startY = 0;//手指按下时的Y坐标
    private float startX = 0;//手指按下时的Y坐标


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        LogUtils.i(TAG,"onTouch事件被触发了");
        final int screenWidth =ScreenUtils.getScreenWidth();
        LogUtils.i(TAG,"screenWidth:"+screenWidth);
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float endY = event.getY();
                float distanceY = startY - endY;
                if (startX > screenWidth / 2) {
                    //右边
                    //在这里处理音量
                    LogUtils.i(TAG,"音量+-");
                    final double FLING_MIN_DISTANCE = 0.5;
                    final double FLING_MIN_VELOCITY = 0.5;
                    AudioManager am=(AudioManager)getSystemService(Context.AUDIO_SERVICE);
                    if (distanceY > FLING_MIN_DISTANCE && Math.abs(distanceY) > FLING_MIN_VELOCITY) {
                        LogUtils.i(TAG,"音量++");
                        am.adjustStreamVolume (AudioManager.STREAM_MUSIC, AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI);
                    }
                    if (distanceY < FLING_MIN_DISTANCE && Math.abs(distanceY) > FLING_MIN_VELOCITY) {
                        LogUtils.i(TAG,"音量--");
                        am.adjustStreamVolume (AudioManager.STREAM_MUSIC, AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI);
                    }
                } else {
                    LogUtils.i(TAG,"屏幕亮度+-");
                    //屏幕左半部分上滑，亮度变大，下滑，亮度变小
                    final double FLING_MIN_DISTANCE = 0.5;
                    final double FLING_MIN_VELOCITY = 0.5;


                    if (distanceY > FLING_MIN_DISTANCE && Math.abs(distanceY) > FLING_MIN_VELOCITY) {

                        int brightness = BrightnessUtils.getWindowBrightness(getWindow());
                        LogUtils.i(TAG,"屏幕亮度++,brightness:"+brightness);
                        if (brightness<255){
                            BrightnessUtils.setWindowBrightness(getWindow(),++brightness);
                        }

                    }
                    if (distanceY < FLING_MIN_DISTANCE && Math.abs(distanceY) > FLING_MIN_VELOCITY) {
                        //BrightnessUtils.setBrightness(BrightnessUtils.getBrightness()+10);
                        int brightness = BrightnessUtils.getWindowBrightness(getWindow());
                        LogUtils.i(TAG,"屏幕亮度--,brightness:"+brightness);
                        if (brightness>0){
                            BrightnessUtils.setWindowBrightness(getWindow(),--brightness);
                        }
                    }
                }
                break;
        }

        return super.onTouchEvent(event);
    }
}
