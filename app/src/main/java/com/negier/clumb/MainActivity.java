package com.negier.clumb;

import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TabHost;

import com.jaeger.library.StatusBarUtil;
import com.negier.clumb.fragments.AddFragment;
import com.negier.clumb.fragments.HomeFragment;
import com.negier.clumb.fragments.PlaceFragment;


public class MainActivity extends AppCompatActivity implements TabHost.OnTabChangeListener {
    public static final String TAG_HOME = "home";
    public static final String TAG_ADD = "add";
    public static final String TAG_PLACE = "place";
    private TabIndicatorView homeIndicatorView;
    private TabIndicatorView addIndicatorView;
    private TabIndicatorView placeIndicatorView;
    private FragmentTabHost tabHost;
    private Toolbar toolbar;
    private DrawerLayout mDrawerLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        // FIXME: 2017/8/2 最后一个参数用于控制状态栏的透明度 0~255
        StatusBarUtil.setColorForDrawerLayout(this, mDrawerLayout, getResources().getColor(R.color.colorPrimary));
        //初始化toolbar
        initToolbar();

        // 初始化TabHost
        tabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        tabHost.setup(this, getSupportFragmentManager(), R.id.fl_container);

        initTab();
    }

    private void initToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        mDrawerLayout.setDrawerListener(toggle);
        toggle.syncState();
    }

    private void initTab() {
        // 新建TabSpec
        TabHost.TabSpec homeSpec = tabHost.newTabSpec(TAG_HOME);
        homeIndicatorView = new TabIndicatorView(this);
        homeIndicatorView.setTabHint("首页");
        homeIndicatorView.setTabIcon(R.mipmap.bottom_home);
        homeIndicatorView.setTabUnread(0);
        homeSpec.setIndicator(homeIndicatorView);
        // 添加TabSpec
        tabHost.addTab(homeSpec, HomeFragment.class, null);

        // 新建TabSpec
        TabHost.TabSpec addSpec = tabHost.newTabSpec(TAG_ADD);
        addIndicatorView = new TabIndicatorView(this);
        addIndicatorView.setTabHint("");
        addIndicatorView.setTabIcon(R.mipmap.bottom_add);
        addIndicatorView.setTabUnread(0);
        addSpec.setIndicator(addIndicatorView);
        // 添加TabSpec
        tabHost.addTab(addSpec, AddFragment.class, null);

        // 新建TabSpec
        TabHost.TabSpec lessonSpec = tabHost.newTabSpec(TAG_PLACE);
        placeIndicatorView = new TabIndicatorView(this);
        placeIndicatorView.setTabHint("课表");
        placeIndicatorView.setTabIcon(R.mipmap.bottom_lesson);
        placeIndicatorView.setTabUnread(0);
        lessonSpec.setIndicator(placeIndicatorView);
        // 添加TabSpec
        tabHost.addTab(lessonSpec, PlaceFragment.class, null);

        tabHost.setOnTabChangedListener(this);

        //去掉那条竖线
        tabHost.getTabWidget().setDividerDrawable(android.R.color.white);

        homeIndicatorView.setTabSelected(true);
    }


    @Override
    public void onTabChanged(String tabId) {
        homeIndicatorView.setTabSelected(false);
        addIndicatorView.setTabSelected(false);
        placeIndicatorView.setTabSelected(false);
        switch (tabId) {
            case TAG_HOME:
                homeIndicatorView.setTabSelected(true);
                break;
            case TAG_ADD:
                addIndicatorView.setTabSelected(true);
                break;
            case TAG_PLACE:
                placeIndicatorView.setTabSelected(true);
                break;
            default:
                break;
        }
    }

}
