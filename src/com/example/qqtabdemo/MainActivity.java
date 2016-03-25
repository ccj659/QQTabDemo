package com.example.qqtabdemo;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TextView;


public class MainActivity extends FragmentActivity {

    private static final String TAG = "MainActivity-->";
    private FragmentTabHost mTabHost;
    private LayoutInflater layoutInflater;
    private Class fragmentArray[] = {MassageFragment.getInstance().getClass(), UseFragment.class, PersonageFragment.class};
    private String tabHostTagArray[] = {"通知", "应用", "个人中心"};
    private int mImageViewArray[] = {R.drawable.ic_recent, R.drawable.ic_keypad, R.drawable.ic_contacts};
    private String currentTab = tabHostTagArray[0];
    private int counts=0;
    private TextView tv_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        layoutInflater = LayoutInflater.from(this);
        initView();
    }
    /**
     * Set common header
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //TODO Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    private void initView() {
        mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
        mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
        mTabHost.getTabWidget().setDividerDrawable(null);


        int count = fragmentArray.length;
        for (int i = 0; i < count; i++) {
            //为每一个Tab按钮设置图标、文字和内容
            TabHost.TabSpec tabSpec = mTabHost.newTabSpec(tabHostTagArray[i]).setIndicator(getTabItemView(i));
            //将Tab按钮添加进Tab选项卡中
            mTabHost.addTab(tabSpec, fragmentArray[i], null);
            //设置Tab按钮的背景
            mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.WHITE);
        }
    }
    /**
     * 给Tab按钮设置图标和文字
     */
    private View getTabItemView(int index) {
        View view = layoutInflater.inflate(R.layout.tabhost_item, null);
        ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
        imageView.setImageResource(mImageViewArray[index]);
        TextView main_tab_unread_tv = (TextView) view.findViewById(R.id.main_tab_unread_tv);
        TextView textView = (TextView) view.findViewById(R.id.textview);
        textView.setText(tabHostTagArray[index]);
        
        if (index == 0) {//假如仅有通知界面需要显示消息数,提取tv_count作为全局变量进行操作,隐藏或显示
            tv_count=main_tab_unread_tv;
            if (counts>0){
                tv_count.setVisibility(View.VISIBLE);
                tv_count.setText(counts+"");
            }else{
                tv_count.setVisibility(View.INVISIBLE);
            }
        }
        return view;
    }
    public void setMegWidget(int megWidget) {
        Log.e("setMegWidget~~~~~~~", "megWidget" + counts);
        if (counts>0) {
            tv_count.setVisibility(View.VISIBLE);
            tv_count.setText(counts+"");
        }else {
            tv_count.setVisibility(View.INVISIBLE);
        }
    }

    public void notifyMsgChanged(int count) {//在massagefragment中调用,或者请求网络查看 未读消息个数,
    	counts=count;
    	setMegWidget(counts);
    }

}
