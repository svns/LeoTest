package com.lukyanova.leotest.LeoAdapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lukyanova.leotest.LeoModels.ContactInfo;
import com.lukyanova.leotest.LeoTools.CustomUtils;
import com.lukyanova.leotest.R;

import java.util.List;
import java.util.Random;

public class MainActivityAdapter extends RecyclerView.Adapter<MainActivityAdapter.ViewHolder> {

    private List<ContactInfo> data;
    static final String TAG = "myLogs";
    Context context;

    public MainActivityAdapter(Context cont, List<ContactInfo> dat)
    {
        context = cont;
        data = dat;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        LinearLayout ll_item_contectt;
        ImageView service_icon, star_icon, line_image;
        TextView title_text, name_text, number_text;
        ContactInfo contactInfo;
        int[] res_mobile = {R.drawable.kyivstar, R.drawable.lifecell};
        int[] res_other = {R.drawable.visa, R.drawable.triolan, R.drawable.mastercard};

        public ViewHolder(View itemView)
        {
            super(itemView);
            service_icon = (ImageView)itemView.findViewById(R.id.service_icon);
            ll_item_contectt  = (LinearLayout)itemView.findViewById(R.id.ll_item_contectt);
            ll_item_contectt.setOnClickListener(this);
            title_text = (TextView)itemView.findViewById(R.id.title_text);
            name_text = (TextView)itemView.findViewById(R.id.name_text);
            number_text = (TextView)itemView.findViewById(R.id.number_text);
            star_icon = (ImageView)itemView.findViewById(R.id.star_icon);
            line_image = (ImageView)itemView.findViewById(R.id.line_image);

        }

        public void bindCrime(ContactInfo contact_Info, int new_type, int position) {

            contactInfo = contact_Info;
            int type = contact_Info.getType();

            if (new_type == 0) {
                title_text.setVisibility(View.VISIBLE);
                if (type == 2)
                    title_text.setText(context.getResources().getString(R.string.favorites_text));
                else if (type == 3)
                    title_text.setText(context.getResources().getString(R.string.last_text));
            } else
                title_text.setVisibility(View.GONE);
            if (position == 0)
                line_image.setVisibility(View.GONE);

            if (type == 1)
                star_icon.setBackground(context.getResources().getDrawable(R.drawable.star_grey));
            else if (type == 2)
                star_icon.setBackground(context.getResources().getDrawable(R.drawable.star));
            else if (type == 3)
                star_icon.setBackground(context.getResources().getDrawable(R.drawable.star_grey));

            if (!contact_Info.getName().toString().trim().isEmpty()) {
                name_text.setVisibility(View.VISIBLE);
                name_text.setText(contact_Info.getName().toString());
                number_text.setTextColor(context.getResources().getColor(R.color.color_grey));
                number_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,12);
            } else {
                name_text.setVisibility(View.GONE);
                number_text.setTextColor(context.getResources().getColor(R.color.color_black));
                number_text.setTextSize(TypedValue.COMPLEX_UNIT_SP,16);
            }

            String account_value = contact_Info.getAccount().toString().trim();
            Random rand_mobile = new Random();
            int res_mobile_int = rand_mobile.nextInt(res_mobile.length);
            Random rand_other = new Random();
            int res_other_int = rand_other.nextInt(res_other.length);
            if(!account_value.contains("***")) {
                account_value = CustomUtils.changeCorrectNumber(account_value);
                service_icon.setImageResource(res_mobile[res_mobile_int]);
            } else {
                account_value = CustomUtils.changeCorrectCard(account_value);
                service_icon.setImageResource(res_other[res_other_int]);
            }
            number_text.setText(account_value);

        }

        @Override
        public void onClick(View v) {
            CustomUtils.showToast(context, context.getResources().getString(R.string.toast_item_text)
                    +" "+contactInfo.getAccount().toString());
        }

    }

    @Override
    public long getItemId (int position)
    {
        return super.getItemId(position);
    }

    @Override
    public ViewHolder onCreateViewHolder (ViewGroup viewGroup, int i)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.activity_main_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder (ViewHolder ssViewHolder, int position)
    {
        ContactInfo contInfo = data.get(position);
        int new_type = 0;
        if (position != 0) {
            int type1 = data.get(position).getType();
            int type2 = data.get(position - 1).getType();
            if (type1 == type2)
                new_type = 1;
        }
        ssViewHolder.bindCrime(contInfo, new_type, position);

    }

    @Override
    public void onViewRecycled (ViewHolder holder)
    {
        super.onViewRecycled(holder);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void setMainActivity(List<ContactInfo> ci) {
        data = ci;
    }
}
