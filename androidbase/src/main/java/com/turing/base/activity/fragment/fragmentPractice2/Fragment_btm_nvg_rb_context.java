package com.turing.base.activity.fragment.fragmentPractice2;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.turing.base.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Fragment_btm_nvg_rb_context extends Fragment {

    private String content;

    /**
     * 无参构造函数
     */
    public Fragment_btm_nvg_rb_context() {

    }

    /**
     * 带有参数的构造函数
     *
     * @param content
     */
    public Fragment_btm_nvg_rb_context(String content) {
        this.content = content;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_btm_nvg_tv_context, container, false);

        TextView txt_content = (TextView) view.findViewById(R.id.txt_content);
        txt_content.setText(content);

        return view;
    }

}
