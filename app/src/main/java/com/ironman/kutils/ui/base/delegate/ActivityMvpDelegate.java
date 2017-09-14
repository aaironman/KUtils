package com.ironman.kutils.ui.base.delegate;

import android.os.Bundle;

import com.ironman.kutils.ui.base.MvpPresenter;
import com.ironman.kutils.ui.base.MvpView;


/**
 * Created by 冯浩 on 2016/12/6.
 */
public interface ActivityMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {


    void onCreate(Bundle bundle);


    void onDestroy();


    void onPause();


    void onResume();


    void onStart();


    void onStop();


    void onRestart();

}
