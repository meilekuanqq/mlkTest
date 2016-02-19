package com.meilekuan.zhushou_1514.gift.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.gift.adapter.GiftListAdapter;
import com.meilekuan.zhushou_1514.gift.bean.Gift;
import com.meilekuan.zhushou_1514.gift.util.GiftHttpUtil;
import com.meilekuan.zhushou_1514.other.ui.BaseFragment;
import com.meilekuan.zhushou_1514.other.utils.LogUtil;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouTask;
import com.meilekuan.zhushou_1514.other.widget.LoadMoreListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/12 14:59
 */

public class GiftListFragment extends BaseFragment {

    private LoadMoreListView listView;
    private int type ;
    private List<Gift> list = new ArrayList<>();
    private float lastY ;
    private int page = 1;
    private  GiftListAdapter adapter;

    public GiftListFragment(int type) {
        this.type = type;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_type_list;
    }

    @Override
    protected void initViews() {
        listView = (LoadMoreListView) root;
    }

    @Override
    protected void initEvents() {

        listView.setLoadMoreListener(new LoadMoreListView.loadMoreListener() {
            @Override
            public void onLoadMore() {
                page ++;
                loadList();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Gift gift = list.get(position);
                String gameId = gift.getId();
                Intent intent = new Intent(getActivity(),GiftInfoActivity.class);
                intent.putExtra("id",gameId);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        //不管有没有内容都显示
        adapter = new GiftListAdapter(getActivity(), list);
        listView.setAdapter(adapter);

        loadList();

    }

    private void loadList() {
        GiftHttpUtil.requestGiftList(type, page, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {

                try {
                    if (result == null) {
                        return;
                    }
                    JSONObject json = new JSONObject(result.toString());
                    String state = json.getString("state");
                    if ("success".equals(state)) {
                        JSONArray info = json.getJSONArray("info");
                        List<Gift> gifts = Gift.arrayGiftFromData(info.toString());
                        list.addAll(gifts);
                        //更新listview的数据
                        adapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {
                Toast.makeText(getActivity(), "请求失败", Toast.LENGTH_LONG).show();
            }
        });
    }
}
