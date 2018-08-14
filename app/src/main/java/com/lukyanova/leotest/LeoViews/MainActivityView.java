package com.lukyanova.leotest.LeoViews;

import com.lukyanova.leotest.LeoModels.ContactInfo;

import java.util.List;

public interface MainActivityView {

    void showAccountInfo(String number_value, String balance_value, String balance_dec_value);

    void showListInfo(List<ContactInfo> infoList);
}
