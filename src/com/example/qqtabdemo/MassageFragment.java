package com.example.qqtabdemo;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by Administrator on 2016/3/1.
 */
public class MassageFragment extends Fragment {
    private static final String TAG = "MassageFragment";
    private static MassageFragment mFragment = null;
    private View view;
    private int pageIndex=0;
    private MainActivity mainActivity;

    public static MassageFragment getInstance() {
        if (mFragment == null) {
            mFragment = new MassageFragment();
        }
        return mFragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_massage, null);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mainActivity = (MainActivity) getActivity();
        initView(view);
    }


    private void initView(View view) {//在massagefragment中调用,或者请求网络查看 未读消息个数,
    	////模拟调用  添加 模拟数据
    	mainActivity. notifyMsgChanged(2);//假设接收到两个未读消息
       
    }

}
