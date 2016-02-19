package com.meilekuan.zhushou_1514.active.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.meilekuan.zhushou_1514.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * function : 顶部轮播图数据适配器.
 * <p/>
 * date 2016/1/12 10:39
 */
public class TopPicAdapter extends PagerAdapter {
    private Context mContext;
    private List<Integer> mTopPicList = new ArrayList<>();

    public TopPicAdapter(Context context, List<Integer> topPicList) {
        mContext = context;
        if (topPicList != null && topPicList.size() > 0) {
            mTopPicList.clear();
            mTopPicList.addAll(topPicList);
        }
    }

    @Override
    public int getCount() {
        return mTopPicList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView iv = new ImageView(mContext);
        iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        Picasso.with(mContext).load(mTopPicList.get(position)).into(iv);

        iv.setTag(R.id.active_vp_pic, position);
        iv.setOnClickListener(mOnClickListener);

        container.addView(iv);
        return iv;
    }

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int position = (int) v.getTag(R.id.active_vp_pic);
        }
    };
}