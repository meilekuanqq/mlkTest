package com.meilekuan.zhushou_1514.other.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import com.meilekuan.zhushou_1514.other.utils.LogUtil;

/**
 * function ：
 * author：Meilekuan
 * date: 2016/1/15 11:09
 */

public class LoadMoreListView extends ListView {
    private float lastY;
    private loadMoreListener loadMoreListener;

    public LoadMoreListView(Context context) {
        super(context);
    }

    public LoadMoreListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        LoadMoreListView();

    }

    private void LoadMoreListView() {
        setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView parentView, int scrollState) {

                //如果状态不是停止的，那么不作下面的操作了
                if (scrollState != SCROLL_STATE_IDLE) {
                    return;
                }
                //获取listview中所有item的总个数
                int count = parentView.getCount();
                //最后显示的那个position
                int lastVisiblePosition = parentView.getLastVisiblePosition();

                int childConut = parentView.getChildCount();
                //如果当前可见的最后一项position等于最后一个item position，表示显示的是最后一项
                if (lastVisiblePosition == count - 1) {
                    LogUtil.w("mlk", "表示显示的是最后一项");

                    View lastChild = parentView.getChildAt(childConut - 1);

                    float y = lastChild.getY();
                    if (lastY == y) {
                        if (loadMoreListener != null) {
                            loadMoreListener.onLoadMore();
                        }
                    } else {
                        lastY = y;
                    }
                    //
                    //如果第一次随意停下来的位置和第二次随意停下来的位置相同，表示已经滑倒底部了
                }
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

            }
        });
    }

    /**
     * 设置一个加载更多的监听
     *
     * @param listener 加载更多的监听
     */
    public void setLoadMoreListener(loadMoreListener listener) {
        loadMoreListener = listener;
    }

    public interface loadMoreListener {
        void onLoadMore();
    }
}
