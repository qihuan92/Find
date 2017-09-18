package com.qihuan.find.view;


import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.blankj.utilcode.util.NetworkUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qihuan.find.App;
import com.qihuan.find.R;
import com.qihuan.find.kit.DateKit;
import com.qihuan.find.kit.ToastKit;
import com.qihuan.find.model.bean.zhihu.DailyItemBean;
import com.qihuan.find.model.bean.zhihu.StoryBean;
import com.qihuan.find.model.bean.zhihu.TopStoryBean;
import com.qihuan.find.view.adapter.DailyAdapter;
import com.qihuan.find.view.base.BaseFragment;
import com.qihuan.find.viewmodel.DailyViewModel;
import com.qihuan.imageloader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * DailyFragment
 */
public class DailyFragment extends BaseFragment implements
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        BGABanner.Adapter<RelativeLayout, TopStoryBean>,
        BGABanner.Delegate<RelativeLayout, TopStoryBean> {

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView rvList;
    private List<DailyItemBean> stories = new ArrayList<>();
    private DailyAdapter dailyAdapter;
    private String date = DateKit.getNowDate();
    private BGABanner bannerView;
    private DailyViewModel dailyViewModel;

    public static DailyFragment newInstance() {
        return new DailyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dailyViewModel = ViewModelProviders.of(this).get(DailyViewModel.class);
        dailyViewModel.topDaily.observe(this, dailyBean -> {
            refreshLayout.setRefreshing(false);
            if (dailyBean == null) {
                return;
            }
            bannerView.setData(R.layout.item_daily_banner, dailyBean.getTop_stories(), null);

            stories.clear();
            stories.add(new DailyItemBean(true, "今日热闻"));
            for (StoryBean storyBean : dailyBean.getStories()) {
                stories.add(new DailyItemBean(storyBean));
            }
            dailyAdapter.notifyDataSetChanged();
        });
        dailyViewModel.beforeDaily.observe(this, dailyBean -> {
            if (dailyBean == null) {
                dailyAdapter.loadMoreEnd();
                return;
            }
            stories.add(new DailyItemBean(true, DateKit.parseDate(dailyBean.getDate())));
            for (StoryBean storyBean : dailyBean.getStories()) {
                stories.add(new DailyItemBean(storyBean));
            }
            dailyAdapter.notifyDataSetChanged();
            dailyAdapter.loadMoreComplete();
        });
        dailyViewModel.error.observe(this, throwable -> {
            if (date.equals(DateKit.getNowDate())) {
                refreshLayout.setRefreshing(false);
            } else {
                dailyAdapter.loadMoreFail();
            }
            if (NetworkUtils.isConnected()) {
                ToastKit.error(throwable);
            } else {
                ToastKit.error(getString(R.string.net_error_msg));
                NetworkUtils.openWirelessSettings();
            }
        });

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initView(view);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        bannerView.startAutoPlay();
    }

    @Override
    public void onPause() {
        super.onPause();
        bannerView.stopAutoPlay();
    }

    private void initView(View view) {
        rvList = view.findViewById(R.id.rv_list);
        refreshLayout = view.findViewById(R.id.refresh_layout);

        refreshLayout.setOnRefreshListener(this);
        dailyAdapter = new DailyAdapter(stories);
        dailyAdapter.setOnItemClickListener(this);
        dailyAdapter.setOnLoadMoreListener(this, rvList);
        rvList.setLayoutManager(new LinearLayoutManager(getContext()));
        rvList.setAdapter(dailyAdapter);

        bannerView = (BGABanner) LayoutInflater.from(getContext()).inflate(R.layout.layout_banner, rvList, false);
        bannerView.setAdapter(this);
        bannerView.setDelegate(this);
        dailyAdapter.addHeaderView(bannerView);

        dailyViewModel.getLatestDaily();
    }

    @Override
    public void onRefresh() {
        date = DateKit.getNowDate();
        dailyViewModel.getLatestDaily();
    }

    @Override
    public void onLoadMoreRequested() {
        date = DateKit.timeSub(date);
        dailyViewModel.getBeforeDaily(date);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DailyItemBean dailyItemBean = stories.get(position);
        if (dailyItemBean.isHeader) {
            return;
        }
        if (dailyItemBean.t == null) {
            return;
        }
        ARouter.getInstance()
                .build("/zhihu/det")
                .withInt("id", dailyItemBean.t.getId())
                .navigation();
    }

    @Override
    public void fillBannerItem(BGABanner banner, RelativeLayout itemView, TopStoryBean model, int position) {
        ImageView ivBanner = itemView.findViewById(R.id.iv_banner);
        TextView tvBanner = itemView.findViewById(R.id.tv_banner);

        ImageLoader.INSTANCE
                .strategy(App.imageLoaderStrategy())
                .with(getContext())
                .load(model.getImage())
                .options(() -> 10)
                .into(ivBanner);
        tvBanner.setText(model.getTitle());
    }

    @Override
    public void onBannerItemClick(BGABanner banner, RelativeLayout itemView, TopStoryBean model, int position) {
        ARouter.getInstance()
                .build("/zhihu/det")
                .withInt("id", model.getId())
                .navigation();
    }
}
