package com.meilekuan.zhushou_1514.exchange.ui;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.exchange.adapter.ExchangeListAdapter;
import com.meilekuan.zhushou_1514.exchange.bean.Exchange;
import com.meilekuan.zhushou_1514.exchange.util.ExchangeHttpUtil;
import com.meilekuan.zhushou_1514.gift.adapter.GiftListAdapter;
import com.meilekuan.zhushou_1514.gift.bean.Gift;
import com.meilekuan.zhushou_1514.gift.ui.GiftInfoActivity;
import com.meilekuan.zhushou_1514.other.ui.BaseFragment;
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
 * date: 2016/1/14 19:21
 */

public class ExchangeListFragment extends BaseFragment {
    private LoadMoreListView listView;
    private int type;
    private List<Exchange> list = new ArrayList<>();
    private int page;
    private ExchangeListAdapter adapter;

    public ExchangeListFragment(int type) {
        this.type = type;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_exchange_type_list;
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
                loadListview();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Exchange exchange = list.get(position);
                String gameId = exchange.getId();
                Intent intent = new Intent(getActivity(),ExchangeInfoActivity.class);
                intent.putExtra("id",gameId);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        //不管有没有内容都显示
        adapter = new ExchangeListAdapter(getActivity(), list);
        listView.setAdapter(adapter);
        loadListview();
    }

    private void loadListview() {
        ExchangeHttpUtil.requestExchangeList(type, page, new ZhuShouTask.RequestCallback() {
            @Override
            public void success(Object result) {
                if (result == null) {
                    return;
                }
                try {
                    JSONObject jsonObject = new JSONObject(result.toString());
                    String state = jsonObject.getString("state");
                    if ("success".equals(state)) {
                        JSONArray info = jsonObject.getJSONArray("info");
                        List<Exchange> exchanges = Exchange.arrayExchangeFromData(info.toString());
                        list.addAll(exchanges);
                        Log.w("mlk", "list.size=" + list.size());
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
