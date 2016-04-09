package com.turing.base.activity.fragment.fragmentPractice3;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.turing.base.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment3Context extends Fragment implements View.OnClickListener {

    private Context mContext;
    private Button btn_one;
    private Button btn_two;
    private Button btn_three;
    private Button btn_four;


    public Fragment3Context() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_fragment3_context, container, false);

        //UI Object
        btn_one = (Button) view.findViewById(R.id.btn_one);
        btn_two = (Button) view.findViewById(R.id.btn_two);
        btn_three = (Button) view.findViewById(R.id.btn_three);
        btn_four = (Button) view.findViewById(R.id.btn_four);
        //Bind Event
        btn_one.setOnClickListener(this);
        btn_two.setOnClickListener(this);
        btn_three.setOnClickListener(this);
        btn_four.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_one:
                // 在Fragment中获取Activity中的组件，通过getActivity().findViewById()
                TextView tab_menu_channel_num = (TextView) getActivity().findViewById(R.id.tab_menu_channel_num);
                tab_menu_channel_num.setText("11");
                tab_menu_channel_num.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_two:
                TextView tab_menu_message_num = (TextView) getActivity().findViewById(R.id.tab_menu_message_num);
                tab_menu_message_num.setText("20");
                tab_menu_message_num.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_three:
                TextView tab_menu_better_num = (TextView) getActivity().findViewById(R.id.tab_menu_better_num);
                tab_menu_better_num.setText("99+");
                tab_menu_better_num.setVisibility(View.VISIBLE);
                break;
            case R.id.btn_four:
                ImageView tab_menu_setting_partner = (ImageView) getActivity().findViewById(R.id.tab_menu_setting_partner);
                tab_menu_setting_partner.setVisibility(View.VISIBLE);
                break;
        }
    }
}
