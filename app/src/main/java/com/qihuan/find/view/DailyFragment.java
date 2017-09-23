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
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qihuan.find.App;
import com.qihuan.find.R;
import com.qihuan.find.kit.ToastKit;
import com.qihuan.find.model.bean.zhihu.DailyItemBean;
import com.qihuan.find.model.bean.zhihu.TopStoryBean;
import com.qihuan.find.view.adapter.DailyAdapter;
import com.qihuan.find.view.base.BaseFragment;
import com.qihuan.find.viewmodel.DailyViewModel;
import com.qihuan.imageloader.ImageLoader;

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
    private DailyAdapter dailyAdapter;
    private BGABanner bannerView;
    private DailyViewModel dailyViewModel;

    public static DailyFragment newInstance() {
        return new DailyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        observe();
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

    private void observe() {
        dailyViewModel = ViewModelProviders.of(this).get(DailyViewModel.class);
        dailyViewModel.topStories().observe(this, topStory -> bannerView.setData(R.layout.item_daily_banner, topStory, null));
        dailyViewModel.stories().observe(this, dailyItems -> {
            if (dailyItems == null) {
                return;
            }
            dailyAdapter.addData(dailyItems);
        });
        dailyViewModel.error().observe(this, result -> {
            if (result == null) {
                return;
            }
            ToastKit.error(result.getMsg());
            if (result.isRefresh()) {
                refreshLayout.setRefreshing(false);
            } else {
                dailyAdapter.loadMoreFail();
            }
        });
        dailyViewModel.complete().observe(this, result -> {
            if (result == null) {
                return;
            }
            if (result.isRefresh()) {
                refreshLayout.setRefreshing(false);
            } else {
                dailyAdapter.loadMoreComplete();
            }
        });
    }

    private void initView(View view) {
        rvList = view.findViewById(R.id.rv_list);
        refreshLayout = view.findViewById(R.id.refresh_layout);

        refreshLayout.setOnRefreshListener(this);
        dailyAdapter = new DailyAdapter();
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
        dailyViewModel.getLatestDaily();
    }

    @Override
    public void onLoadMoreRequested() {
        dailyViewModel.getBeforeDaily();
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DailyItemBean dailyItemBean = (DailyItemBean) adapter.getData().get(position);
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
