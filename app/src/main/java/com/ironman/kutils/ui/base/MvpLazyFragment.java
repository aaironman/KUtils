package com.ironman.kutils.ui.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import com.ironman.kutils.ui.base.delegate.FragmentMvpDelegate;
import com.ironman.kutils.ui.base.delegate.FragmentMvpDelegateImpl;
import com.ironman.kutils.ui.base.delegate.MvpDelegateCallback;


/**
 * 作者: 冯浩
 * 时间: 2017/9/7
 * 描述: mvp 懒加载fragment
 */
public abstract class MvpLazyFragment<V extends MvpView, P extends MvpPresenter<V>>
        extends LazyFragment implements MvpDelegateCallback<V, P> {

    protected FragmentMvpDelegate<V, P> mvpDelegate;

    protected P presenter;

    public abstract P createPresenter();


    @NonNull
    protected FragmentMvpDelegate<V, P> getMvpDelegate() {
        if (mvpDelegate == null) {
            mvpDelegate = new FragmentMvpDelegateImpl<>(this);
        }

        return mvpDelegate;
    }

    @NonNull
    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public void setPresenter(@NonNull P presenter) {
        this.presenter = presenter;
    }


    @NonNull
    @Override
    public V getMvpView() {
        return (V) this;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getMvpDelegate().onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getMvpDelegate().onDestroyView();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getMvpDelegate().onCreate(savedInstanceState);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getMvpDelegate().onDestroy();
    }

    @Override
    public void onPause() {
        super.onPause();
        getMvpDelegate().onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        getMvpDelegate().onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        getMvpDelegate().onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        getMvpDelegate().onStop();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getMvpDelegate().onActivityCreated(savedInstanceState);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        getMvpDelegate().onAttach(context);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        getMvpDelegate().onDetach();
    }
}
