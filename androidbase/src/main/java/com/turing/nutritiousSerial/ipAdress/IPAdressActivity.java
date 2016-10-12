package com.turing.nutritiousSerial.ipAdress;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.turing.base.R;

public class IPAdressActivity extends AppCompatActivity {

    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ipadress);
        tv = (TextView) this.findViewById(R.id.tv);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.get_wifi_ip:
                tv.setText(GetIPAddressUtil.getWifiIP(this));
                break;
            case R.id.get_gprs_ip:
                tv.setText(GetIPAddressUtil.getMobileIP());
                break;
            case R.id.get_wifi_ssid:
                tv.setText(GetIPAddressUtil.getConnectWifiSsid(this));
                break;
            case R.id.openSetting:
                GetIPAddressUtil.openSetting(this);
                break;
            default:
                break;
        }
    }
}
