package com.turing.base.activity.fragment.dynamicload;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.turing.base.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class FragementFirst extends Fragment {

    private EditText editText;
    private  View view ;


    public FragementFirst() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_fragement_first, container, false);
        return view;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 在Fragment中获取Activity的组件
        TextView textView = (TextView) getActivity().findViewById(R.id.id_tv_actUI);
        textView.setText("FFFF");


        // 接收Activity传递过来的数据
        Bundle bundle = getArguments();
        Toast.makeText(getActivity(), bundle.getString("key"), Toast.LENGTH_SHORT).show();



    }



    /**
     * 定义一个回调接口:(Fragment中)
     */
    public interface FragmentCallBack {

        //定义一个接口方法
        void getResult(String result);
    }


    /**
     * 接口回调（Fragment中）
     */
    public void getData(FragmentCallBack callBack){
        // 模拟获取的数据
        String msg = "小工匠";
        // 接口回调
        callBack.getResult(msg);
    }
}
