package com.lukyanova.leotest.LeoTools;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

public class CustomUtils {

    private static final String TAG = "myLogs";

    public static void showToast(Context context, String txt)
    {
        Toast toast = Toast.makeText(context,
                txt,
                Toast.LENGTH_SHORT );
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    public static String changeCorrectNumber(String number)
    {
        if (number.length() == 12) {
            String country = number.substring(0, 2);
            String region = number.substring(2, 5);
            String number_first_part = number.substring(5, 8);
            String number_secondory_part = number.substring(8, 10);
            String number_third_part = number.substring(10, 12);
            number = country+" "+region+" "+number_first_part+" "+number_secondory_part
                    +" "+number_third_part;
        }
        return number;
    }

    public static String getNumberBeforPoint(String balance_value)
    {
        int index = getIndexPoint(balance_value);

        if (index > 0)
                balance_value.substring(0, index);
        return balance_value;
    }

    public static String getNumberAfterPoint(String balance_value)
    {
        int index = getIndexPoint(balance_value);

        if (index > 0)
            balance_value.substring(index, balance_value.length());
        else
            balance_value = "00";
        return balance_value;
    }

    public static int getIndexPoint(String balance_value)
    {
        int index = -1;

        if (balance_value == null)
            return index;

        if (balance_value.length() > 0)
            index = balance_value.indexOf(".");

        return index;
    }

    public static String changeCorrectCard(String card)
    {
        if (card.length() > 11) {
            String card_new = "";
            for (int i = 0; i < card.length(); i = i + 4) {
                if (i == 0)
                    card_new = card.substring(i, i+4);
                else
                    card_new = card_new +" "+ card.substring(i, i+4);
            }
            card = card_new;
        }
        return card;
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }
}
