package com.meilekuan.zhushou_1514.profit.ui;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.meilekuan.zhushou_1514.R;
import com.meilekuan.zhushou_1514.gift.bean.Gift;
import com.meilekuan.zhushou_1514.gift.ui.GiftInfoActivity;
import com.meilekuan.zhushou_1514.my.ui.MyUesrloginActivity;
import com.meilekuan.zhushou_1514.other.ui.BaseFragment;
import com.meilekuan.zhushou_1514.other.utils.ZhuShouTask;
import com.meilekuan.zhushou_1514.other.widget.LoadMoreListView;
import com.meilekuan.zhushou_1514.profit.adapter.ProfitListAdapter;
import com.meilekuan.zhushou_1514.profit.bean.Profit;
import com.meilekuan.zhushou_1514.profit.util.ProfitHttpUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * function ：赚钱界面
 * author：Meilekuan
 * date: 2016/1/12 10:39
 */

public class ProfitFragment extends BaseFragment {

    private LoadMoreListView listView;
    private TextView tvScore, tvTask;

    private List<Profit> profits = new ArrayList<>();

    private int page = 1;
    private ProfitListAdapter adapter;

    @Override
    protected int getLayout() {
        return R.layout.fragment_profit;
    }

    @Override
    protected void initViews() {
        listView = (LoadMoreListView) root.findViewById(R.id.profit_listview);
        tvScore = (TextView) root.findViewById(R.id.profit_type_score_tv);
        tvTask = (TextView) root.findViewById(R.id.profit_type_task_tv);

        tvTask.setOnClickListener(listener);
        tvScore.setOnClickListener(listener);
    }

    @Override
    protected void initEvents() {

        listView.setLoadMoreListener(new LoadMoreListView.loadMoreListener() {
            @Override
            public void onLoadMore() {
                page++;
                LoadList();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Profit profit = profits.get(position);
                String gameId = profit.getId();
                Intent intent = new Intent(getActivity(), ProfitInfoActivity.class);
                intent.putExtra("id", gameId);
                getActivity().startActivity(intent);
            }
        });
    }

    @Override
    protected void initData() {
        adapter = new ProfitListAdapter(getActivity(), profits);
        listView.setAdapter(adapter);
        LoadList();
    }

    private void LoadList() {


        ProfitHttpUtil.requestProfitList(page, new ZhuShouTask.RequestCallback() {
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
                        List<Profit> gifts = Profit.arrayprofitFromData(info.toString());
                        profits.addAll(gifts);
                        //更新listview的数据
                        adapter.notifyDataSetChanged();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void error(String msg) {
                Toast.makeText(getActivity(), msg, Toast.LENGTH_LONG).show();
            }
        });
    }
    private  View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.profit_type_score_tv:
                    Intent intent = new Intent(getActivity(), MyUesrloginActivity.class);
                    getActivity().startActivity(intent);
                    break;
                case R.id.profit_type_task_tv:
                    Intent intent2 = new Intent(getActivity(), MyUesrloginActivity.class);
                    getActivity().startActivity(intent2);
                    break;
            }
        }
    };

}
