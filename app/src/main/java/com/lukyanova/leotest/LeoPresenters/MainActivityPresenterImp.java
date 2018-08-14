package com.lukyanova.leotest.LeoPresenters;

import android.content.Context;
import android.util.Log;

import com.lukyanova.leotest.LeoModels.ContactInfo;
import com.lukyanova.leotest.LeoTools.CustomUtils;
import com.lukyanova.leotest.LeoViews.MainActivityView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivityPresenterImp implements MainActivityPresenter {

    static final String TAG = "myLogs";
    private MainActivityView mainView;
    private Context context;

    public MainActivityPresenterImp(MainActivityView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
    }

    @Override
    public void onDestroy() {
        mainView = null;
    }

    @Override
    public void getInfoFromJson() {
        try {
            JSONObject obj = new JSONObject(loadFromFile());

            String account_value = obj.getString("account");
            String balance_value = obj.getString("balance");

            mainView.showAccountInfo(CustomUtils.changeCorrectNumber(account_value),
                    CustomUtils.getNumberBeforPoint(balance_value),
                    CustomUtils.getNumberAfterPoint(balance_value));

            List<ContactInfo> infoList = new ArrayList<ContactInfo>();

            int type = 0;
            JSONArray invoicesArray = obj.getJSONArray("invoices");
            ContactInfo contactInfo;
            for (int i = 0; i < invoicesArray.length(); i++) {
                JSONObject jo_inside = invoicesArray.getJSONObject(i);
                type = 1;
                int id_value = jo_inside.getInt("id");
                String name_value = jo_inside.getString("name");
                String accounts_value = jo_inside.getString("account");

                contactInfo = new ContactInfo(id_value, type , name_value, accounts_value);

                infoList.add(contactInfo);
            }

            JSONArray favoritesArray = obj.getJSONArray("favorites");
            for (int i = 0; i < favoritesArray.length(); i++) {
                type = 2;
                JSONObject jo_favor = favoritesArray.getJSONObject(i);
                int service_value = jo_favor.getInt("service");
                String name_value = jo_favor.getString("name");
                String accounts_value = jo_favor.getString("account1");

                contactInfo = new ContactInfo(service_value, type , name_value, accounts_value);

                infoList.add(contactInfo);
            }

            JSONArray lastArray = obj.getJSONArray("last");
            for (int i = 0; i < lastArray.length(); i++) {
                type = 3;
                JSONObject jo_last = lastArray.getJSONObject(i);
                int service_value = jo_last.getInt("service");
                String name_value = jo_last.getString("name");
                String accounts_value = jo_last.getString("account1");

                contactInfo = new ContactInfo(service_value, type , name_value, accounts_value);

                infoList.add(contactInfo);
            }

            mainView.showListInfo(infoList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String loadFromFile() {
        String json = null;
        try {
            InputStream iputStream = context.getAssets().open("dashboard.json");
            int size = iputStream.available();
            byte[] buffer = new byte[size];
            iputStream.read(buffer);
            iputStream.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}
