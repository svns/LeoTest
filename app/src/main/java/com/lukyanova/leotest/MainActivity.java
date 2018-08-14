package com.lukyanova.leotest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lukyanova.leotest.LeoAdapters.MainActivityAdapter;
import com.lukyanova.leotest.LeoModels.ContactInfo;
import com.lukyanova.leotest.LeoPresenters.MainActivityPresenter;
import com.lukyanova.leotest.LeoPresenters.MainActivityPresenterImp;
import com.lukyanova.leotest.LeoTools.BottomViews;
import com.lukyanova.leotest.LeoTools.CustomUtils;
import com.lukyanova.leotest.LeoViews.MainActivityView;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MainActivityView,
        View.OnClickListener {

    private BottomViews mBottomViews;

    private TextView wallet_number, balance, balance_dec;
    private TextView replenish_button, withdraw_button;
    private ImageView search_image;
    private EditText search_text;
    private LinearLayout ll_transfer_money, ll_pay_services, ll_bill;
    private RecyclerView rv_dashboard;

    private MainActivityPresenter presenter;
    private MainActivityAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
    }

    private void initViews() {
        if (mBottomViews == null)
            mBottomViews = new BottomViews(this);
        View view = getWindow().getDecorView();
        mBottomViews.initViews(view);

        wallet_number = (TextView) findViewById(R.id.wallet_number);
        balance = (TextView) findViewById(R.id.balance);
        balance_dec = (TextView) findViewById(R.id.balance_dec);

        replenish_button = (TextView) findViewById(R.id.replenish_button);
        replenish_button.setOnClickListener(this);
        withdraw_button = (TextView) findViewById(R.id.withdraw_button);
        withdraw_button.setOnClickListener(this);

        search_image = (ImageView) findViewById(R.id.search_image);
        search_image.setOnClickListener(this);

        search_text = (EditText) findViewById(R.id.search_text);

        ll_transfer_money = (LinearLayout) findViewById(R.id.ll_transfer_money);
        ll_transfer_money.setOnClickListener(this);
        ll_pay_services = (LinearLayout) findViewById(R.id.ll_pay_services);
        ll_pay_services.setOnClickListener(this);
        ll_bill = (LinearLayout) findViewById(R.id.ll_bill);
        ll_bill.setOnClickListener(this);

        rv_dashboard = (RecyclerView)findViewById(R.id.rv_dashboard);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv_dashboard.setLayoutManager(llm);
        rv_dashboard.setNestedScrollingEnabled(false);

        presenter = new MainActivityPresenterImp(this,this);
        presenter.getInfoFromJson();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBottomViews = null;
        if (adapter != null)
            adapter = null;
        presenter.onDestroy();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.replenish_button:
                CustomUtils.showToast(this, getResources().getString(R.string.toast_text)+
                    " "+replenish_button.getText().toString());
                break;
            case R.id.withdraw_button:
                CustomUtils.showToast(this, getResources().getString(R.string.toast_text)+
                        " "+withdraw_button.getText().toString());
                break;
            case R.id.search_image:
                String search_value = search_text.getText().toString().trim();
                if (search_value.isEmpty())
                    CustomUtils.showToast(this, getResources().getString(R.string.search_isEmpty));
                else {
                    search_text.setText("");
                    CustomUtils.hideKeyboard(this);
                    CustomUtils.showToast(this, getResources().getString(R.string.toast_search)+
                            " '"+search_value+"'");
                }
                break;
            case R.id.ll_transfer_money:
                CustomUtils.showToast(this, getResources().getString(R.string.toast_text)+
                        " "+getResources().getString(R.string.toast_transfer_money_text));
                break;
            case R.id.ll_pay_services:
                CustomUtils.showToast(this, getResources().getString(R.string.toast_text)+
                        " "+getResources().getString(R.string.toast_pay_service_text));
                break;
            case R.id.ll_bill:
                CustomUtils.showToast(this, getResources().getString(R.string.toast_text)+
                        " "+getResources().getString(R.string.toast_bill_text));
                break;
        }
    }

    @Override
    public void showAccountInfo(String number_value, String balance_value
            , String balance_dec_value) {
        wallet_number.setText(number_value);
        balance.setText(balance_value);
        balance_dec.setText(balance_dec_value);
    }

    @Override
    public void showListInfo(List<ContactInfo> infoList) {
        if (infoList != null) {
            if (adapter == null) {
                adapter = new MainActivityAdapter(this, infoList);
                rv_dashboard.setAdapter(adapter);
            } else {
                adapter.setMainActivity(infoList);
                adapter.notifyDataSetChanged();
            }
        }
    }
}
