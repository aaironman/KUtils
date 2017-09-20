package com.ironman.kutils.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.IdRes;

import com.ironman.kutils.R;
import com.ironman.kutils.ui.base.BaseActivity;
import com.ironman.kutils.widget.NoTouchViewPager;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.OnTabSelectListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 作者: miaocong
 * 时间: 2017/9/19
 * 描述:
 */
public class MainActivity extends BaseActivity {

    @BindView(R.id.bottomBar)
    BottomBar bottomBar;
    @BindView(R.id.pager)
    NoTouchViewPager pager;

    /**
     * 知乎
     */
    public static final int TAB_POSITION_HOME = 0;
    /**
     * 微信精选
     */
    public static final int TAB_POSITION_WECHAT = 1;
    /**
     * 干货集中营
     */
    public static final int TAB_POSITION_GANK = 2;
    /**
     * 稀土掘金
     */
    public static final int TAB_POSITION_GOLD = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setUnBinder(ButterKnife.bind(this));
        SwipeBackHelper.getCurrentPage(this).setSwipeBackEnable(false);
        initView();
    }

    private void initView() {
        pager.setAdapter(new MainPagerAdapter(getSupportFragmentManager()));
        pager.setOffscreenPageLimit(2);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                switch (tabId) {
                    case R.id.tab_home:
                        pager.setCurrentItem(TAB_POSITION_HOME, false);
                        break;
                    case R.id.tab_wechat:
                        pager.setCurrentItem(TAB_POSITION_WECHAT, false);
                        break;
                    case R.id.tab_gank:
                        pager.setCurrentItem(TAB_POSITION_GANK, false);
                        break;
                    case R.id.tab_gold:
                        pager.setCurrentItem(TAB_POSITION_GOLD, false);
                        break;
                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        handleIntent();
    }

    private void handleIntent() {
    }

}
