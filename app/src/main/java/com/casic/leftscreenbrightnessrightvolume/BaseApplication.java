package com.casic.leftscreenbrightnessrightvolume;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 * @author 郭宝
 * @project： LeftScreenBrightnessRightVolume
 * @package： com.casic.leftscreenbrightnessrightvolume
 * @date： 2019/5/26 0026 14:46
 * @brief:
 */
public class BaseApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
        //初始化 blankj:utilcode 依赖库
        Utils.init(this);
    }

}
