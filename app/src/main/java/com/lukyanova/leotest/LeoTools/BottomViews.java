package com.lukyanova.leotest.LeoTools;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lukyanova.leotest.R;

public class BottomViews implements View.OnClickListener {

    private static final String TAG = "myLogs";
    private Context context;

    private LinearLayout ll_button_purse, ll_button_bonuses, ll_button_history, ll_button_profile;
    private ImageView image_button_purse, image_button_bonuses, image_button_history,
            image_button_profile;
    private TextView text_button_purse, text_button_bonuses, text_button_history,
            text_button_profile;

    public BottomViews(Context context){
        this.context = context;
    }

    public void initViews(View inflate) {
        ll_button_purse = (LinearLayout) inflate.findViewById(R.id.ll_button_purse);
        ll_button_purse.setOnClickListener(this);
        image_button_purse = (ImageView) inflate.findViewById(R.id.image_button_purse);
        text_button_purse = (TextView) inflate.findViewById(R.id.text_button_purse);
        ll_button_bonuses = (LinearLayout) inflate.findViewById(R.id.ll_button_bonuses);
        ll_button_bonuses.setOnClickListener(this);
        image_button_bonuses = (ImageView) inflate.findViewById(R.id.image_button_bonuses);
        text_button_bonuses = (TextView) inflate.findViewById(R.id.text_button_bonuses);
        ll_button_history = (LinearLayout) inflate.findViewById(R.id.ll_button_history);
        ll_button_history.setOnClickListener(this);
        image_button_history = (ImageView) inflate.findViewById(R.id.image_button_history);
        text_button_history = (TextView) inflate.findViewById(R.id.text_button_history);
        ll_button_profile = (LinearLayout) inflate.findViewById(R.id.ll_button_profile);
        ll_button_profile.setOnClickListener(this);
        image_button_profile = (ImageView) inflate.findViewById(R.id.image_button_profile);
        text_button_profile = (TextView) inflate.findViewById(R.id.text_button_profile);

        clickOnBottom(1);

    }

    private void clickOnBottom(int bottomNumber) {
        switch (bottomNumber) {
            case 1:
                image_button_purse.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.wallet_white));
                text_button_purse.setTextColor(context.getResources().getColor(R.color.color_white));
                image_button_bonuses.setImageDrawable(context.getResources()
                        .getDrawable(R.drawable.gift_grey));
                text_button_bonuses.setTextColor(context.getResources().getColor(R.color.color_grey));
                image_button_history.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.clock_grey));
                text_button_history.setTextColor(context.getResources().getColor(R.color.color_grey));
                image_button_profile.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.profile_grey));
                text_button_profile.setTextColor(context.getResources().getColor(R.color.color_grey));
                CustomUtils.showToast(context, context.getResources().getString(R.string.toast_text)
                    +" "+text_button_purse.getText().toString());
                break;
            case 2:
                image_button_purse.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.wallet_grey));
                text_button_purse.setTextColor(context.getResources().getColor(R.color.color_grey));
                image_button_bonuses.setImageDrawable(context.getResources()
                        .getDrawable(R.drawable.gift_white));
                text_button_bonuses.setTextColor(context.getResources().getColor(R.color.color_white));
                image_button_history.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.clock_grey));
                text_button_history.setTextColor(context.getResources().getColor(R.color.color_grey));
                image_button_profile.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.profile_grey));
                text_button_profile.setTextColor(context.getResources().getColor(R.color.color_grey));
                CustomUtils.showToast(context, context.getResources().getString(R.string.toast_text)
                        +" "+text_button_bonuses.getText().toString());
                break;
            case 3:
                image_button_purse.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.wallet_grey));
                text_button_purse.setTextColor(context.getResources().getColor(R.color.color_grey));
                image_button_bonuses.setImageDrawable(context.getResources()
                        .getDrawable(R.drawable.gift_grey));
                text_button_bonuses.setTextColor(context.getResources().getColor(R.color.color_grey));
                image_button_history.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.clock_white));
                text_button_history.setTextColor(context.getResources().getColor(R.color.color_white));
                image_button_profile.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.profile_grey));
                text_button_profile.setTextColor(context.getResources().getColor(R.color.color_grey));
                CustomUtils.showToast(context, context.getResources().getString(R.string.toast_text)
                        +" "+text_button_history.getText().toString());
                break;
            case 4:
                image_button_purse.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.wallet_grey));
                text_button_purse.setTextColor(context.getResources().getColor(R.color.color_grey));
                image_button_bonuses.setImageDrawable(context.getResources()
                        .getDrawable(R.drawable.gift_grey));
                text_button_bonuses.setTextColor(context.getResources().getColor(R.color.color_grey));
                image_button_history.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.clock_grey));
                text_button_history.setTextColor(context.getResources().getColor(R.color.color_grey));
                image_button_profile.setImageDrawable(context.getResources().
                        getDrawable(R.drawable.profile_white));
                text_button_profile.setTextColor(context.getResources().getColor(R.color.color_white));
                CustomUtils.showToast(context, context.getResources().getString(R.string.toast_text)
                        +" "+text_button_profile.getText().toString());
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_button_purse:
                clickOnBottom(1);
                break;
            case R.id.ll_button_bonuses:
                clickOnBottom(2);
                break;
            case R.id.ll_button_history:
                clickOnBottom(3);
                break;
            case R.id.ll_button_profile:
                clickOnBottom(4);
                break;
        }
    }
}
