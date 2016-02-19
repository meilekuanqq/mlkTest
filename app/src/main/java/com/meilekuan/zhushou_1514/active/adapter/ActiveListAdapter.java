package com.meilekuan.zhushou_1514.active.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.active.bean.Active;
import com.meilekuan.zhushou_1514.other.utils.ImageLoader;
import com.meilekuan.zhushou_1514.profit.bean.Profit;

import org.w3c.dom.Text;

import java.util.List;
import java.util.zip.Inflater;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/15 16:51
 */

public class ActiveListAdapter extends BaseAdapter {
    private List<Active> list;

    private LayoutInflater inflater;
    private Context context;
    private ImageLoader imageLoader;

    public ActiveListAdapter(Context context ,List<Active> list) {
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
        ActiveItem item = null;
        if (convertView == null){
            item = new ActiveItem();
            convertView = inflater.inflate(R.layout.adapter_active_list, null);
            item.ivIcon = (ImageView) convertView.findViewById(R.id.adapter_active_header_iv);
            item.tvAname = (TextView) convertView.findViewById(R.id.adapter_active_name_tv);
            item.tvShortname = (TextView) convertView.findViewById(R.id.adapter_active_shortname_tv);
            item.tvJoin = (TextView) convertView.findViewById(R.id.adapter_active_join);

            convertView.setTag(item);
        }else
        {
            item = (ActiveItem) convertView.getTag();
        }
        Active active = list.get(position);

        item.tvAname.setText(active.getAname());
        item.tvShortname.setText(active.getShortname());
        String join = context.getString(R.string.active_list_join,active.getTotal_join_user());
        item.tvJoin.setText(join);

        imageLoader.DisplayImage(active.getHotpic(), item.ivIcon);
        return convertView;
    }

    class ActiveItem{
        ImageView ivIcon;
        TextView tvAname,tvShortname,tvJoin;
    }
}
