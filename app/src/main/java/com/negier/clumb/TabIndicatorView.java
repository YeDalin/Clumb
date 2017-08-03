package com.negier.clumb;

import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

/**
 * Created by NEGIER on 2017/6/6.
 */

public class TabIndicatorView extends RelativeLayout {

    private ImageView ivTabNormalIcon;
    private ImageView ivTabFocusIcon;
    private TextView tvTabHint;
    private TextView tvTabUnread;

    public TabIndicatorView(Context context) {
        this(context, null);
    }

    public TabIndicatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        View.inflate(context, R.layout.tab_indicator, this);
        ivTabNormalIcon = (ImageView) findViewById(R.id.tab_indicator_normal_icon);
        ivTabFocusIcon = (ImageView) findViewById(R.id.tab_indicator_focus_icon);

        tvTabHint = (TextView) findViewById(R.id.tab_indicator_hint);
        tvTabUnread = (TextView) findViewById(R.id.tab_indicator_unread);
    }

    public void setTabIcon(int normalIconId) {
        ivTabNormalIcon.setImageResource(normalIconId);
        ivTabFocusIcon.setImageResource(normalIconId);
    }

    public void setTabSelected(boolean selected) {
        if (selected) {
            ivTabNormalIcon.setVisibility(View.GONE);
            ivTabFocusIcon.setVisibility(View.VISIBLE);
            tvTabHint.setTextColor(Color.parseColor("#4f9ef6"));
        } else {
            ivTabNormalIcon.setVisibility(View.VISIBLE);
            ivTabFocusIcon.setVisibility(View.GONE);
            tvTabHint.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }
    public void setTabHint(String hint){
        if (hint==""){
            tvTabHint.setVisibility(View.GONE);
        }else{
            tvTabHint.setText(hint);
        }

    }
    public void setTabUnread(int unreadCount){
        if (unreadCount<1){
            tvTabUnread.setVisibility(View.GONE);
        }else{
            tvTabUnread.setText(String.valueOf(unreadCount));
            tvTabUnread.setVisibility(View.VISIBLE);
        }
    }
}
