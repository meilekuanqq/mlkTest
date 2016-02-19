package com.meilekuan.zhushou_1514.profit.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.other.utils.ImageLoader;
import com.meilekuan.zhushou_1514.other.utils.TextUtil;
import com.meilekuan.zhushou_1514.profit.bean.Profit;

import java.util.List;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/15 12:37
 */

public class ProfitListAdapter extends BaseAdapter {

    private List<Profit> profits;

    private LayoutInflater inflater;
    private Context context;
    private ImageLoader imageLoader;

    public ProfitListAdapter(Context context, List<Profit> profits) {
        this.profits = profits;
        this.context = context;
        imageLoader = new ImageLoader(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return profits.size();
    }

    @Override
    public Object getItem(int position) {
        return profits.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ProfitItem item = null;
        if (convertView == null) {
            item = new ProfitItem();
            convertView = inflater.inflate(R.layout.adapter_profit_list, null);

            item.tvName = (TextView) convertView.findViewById(R.id.adapter_prfit_name_tv);
            item.ivIcon = (ImageView) convertView.findViewById(R.id.adapter_profit_icon_iv);

            item.tvDown = (TextView) convertView.findViewById(R.id.adapter_profit_down_tv);
            item.tvMB = (TextView) convertView.findViewById(R.id.adapter_profit_mb_tv);
            item.tvUbi = (TextView) convertView.findViewById(R.id.profit_down_ubi);
            item.ratingBar = (RatingBar) convertView.findViewById(R.id.profit_ratingbar);
            convertView.setTag(item);
        } else {
            item = (ProfitItem) convertView.getTag();
        }
        Profit profit = profits.get(position);
        item.tvName.setText(profit.getName());
        imageLoader.DisplayImage(profit.getIcon(), item.ivIcon);

        item.tvDown.setText(profit.getCount_dl() + "人下载");
        item.tvMB.setText(profit.getSize());
//        String ubi = context.getString(profit.getAll_prize());
//        item.tvUbi.setText(ubi);
//        float remain = Float.parseFloat(profit.getScore());
//        float total = 1;
//        try {
//            item.ratingBar.setMax((int) total);
//            item.ratingBar.setProgress((int) remain);
//        } catch (NumberFormatException e) {
//            e.printStackTrace();
//        }

        String consume = String.valueOf(profit.getAll_prize());

        if (consume.length()>=4){
            consume = consume.substring(0,consume.length()-3)+","+consume.substring(consume.length()-3,consume.length());
        }


        String ubi = context.getString(R.string.profit_list_ubi, consume);
        String ubisss = "奖" + ubi;
        SpannableString ubiString = TextUtil.getColorSpan(ubisss, 0, ubisss.length(), context.getResources().getColor(R.color.colorAccent));
        item.tvUbi.setText(ubiString);

        float score = Float.valueOf(profit.getScore());
        item.ratingBar.setProgress(Math.round(score));

        return convertView;
    }

    class ProfitItem {
        ImageView ivIcon;
        TextView tvName, tvDown, tvMB, tvUbi;
        RatingBar ratingBar;
    }
}
