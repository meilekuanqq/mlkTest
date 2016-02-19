package com.meilekuan.zhushou_1514.exchange.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.exchange.bean.Exchange;
import com.meilekuan.zhushou_1514.other.utils.ImageLoader;
import com.meilekuan.zhushou_1514.other.utils.TextUtil;

import java.util.List;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/14 19:50
 */

public class ExchangeListAdapter extends BaseAdapter{
    private List<Exchange> list;

    private LayoutInflater inflater;
    private Context context;

    private ImageLoader imageLoader;

    public ExchangeListAdapter(Context context, List<Exchange> list) {
        this.list = list;
        this.context = context;
        imageLoader = new ImageLoader(context);
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ExchangeItem item = null;
        if (convertView == null){
            item = new ExchangeItem();
            convertView = inflater.inflate(R.layout.adapter_exchange_list, null);
            item.ivHeader = (ImageView) convertView.findViewById(R.id.adapter_exchange_header_iv);
            item.tvName = (TextView) convertView.findViewById(R.id.adapter_exchange_name_tv);
            item.tvRemain = (TextView) convertView.findViewById(R.id.adapter_exchange_remain_tv);
            item.tvContent = (TextView) convertView.findViewById(R.id.adapter_exchange_consume_tv);
            item.btn1 = (Button) convertView.findViewById(R.id.adapter_exchange_btn1);

            convertView.setTag(item);
        }else {
            item = (ExchangeItem) convertView.getTag();
        }

        Exchange exchange = list.get(position);
        item.tvName.setText(exchange.getName());
        String consume = exchange.getConsume();

        if (consume.length()>=4){
            consume = consume.substring(0,consume.length()-3)+","+consume.substring(consume.length()-3,consume.length());
        }

        String consumeStr = context.getString(R.string.exchang_list_consume,consume);

        String remain = context.getString(R.string.exchang_list_remain, Integer.parseInt(exchange.getRemain()));
        SpannableString remainS = TextUtil.getColorSpan(remain, 3, remain.length(), context.getResources().getColor(R.color.list_item_yellow_btn_color));

        SpannableString consumeS = TextUtil.getColorSpan(consumeStr, 3, consumeStr.length()-2, context.getResources().getColor(R.color.list_item_yellow_btn_color));
        //把加工好的字符串设置进去
        item.tvContent.setText(consumeS);

        item.tvRemain.setText(remainS);

        imageLoader.DisplayImage(exchange.getIcon(),item.ivHeader);
        return convertView;
    }
    class ExchangeItem {
        ImageView ivHeader;

        TextView tvName, tvRemain, tvContent;

        Button btn1;
    }
}
