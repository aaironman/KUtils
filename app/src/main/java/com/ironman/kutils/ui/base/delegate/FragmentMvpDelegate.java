package com.ironman.kutils.ui.base.delegate;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.ironman.kutils.ui.base.MvpPresenter;
import com.ironman.kutils.ui.base.MvpView;


/**
 * 作者: miaocong
 * 时间: 2017/9/20
 * 描述: 
 */
public interface FragmentMvpDelegate<V extends MvpView, P extends MvpPresenter<V>> {

    public void onCreate(Bundle saved);


    public void onDestroy();


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState);


    public void onDestroyView();


    public void onPause();


    public void onResume();


    public void onStart();


    public void onStop();


    public void onActivityCreated(Bundle savedInstanceState);


    public void onAttach(Context context);


    public void onDetach();


}
