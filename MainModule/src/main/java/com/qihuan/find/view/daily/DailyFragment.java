package com.qihuan.find.view.daily;

import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.qihuan.commonmodule.imageloader.ImageLoader;
import com.qihuan.commonmodule.imageloader.strategy.GlideStrategy;
import com.qihuan.commonmodule.utils.DateUtils;
import com.qihuan.find.R;
import com.qihuan.find.contact.DailyContract;
import com.qihuan.find.model.bean.zhihu.DailyItemBean;
import com.qihuan.find.model.bean.zhihu.TopStoryBean;
import com.qihuan.find.presenter.DailyPresenter;
import com.qihuan.find.view.adapter.DailyAdapter;
import com.qihuan.find.view.base.BaseFragment;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * DailyFragment
 *
 * @author Qi
 */
public class DailyFragment extends BaseFragment implements
        DailyContract.View,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        BGABanner.Adapter<RelativeLayout, TopStoryBean>,
        BGABanner.Delegate<RelativeLayout, TopStoryBean> {

    private SwipeRefreshLayout refreshLayout;
    private BGABanner bannerView;
    private String date;
    private DailyPresenter presenter;
    private DailyAdapter dailyAdapter;

    public static DailyFragment newInstance() {
        return new DailyFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        date = DateUtils.getNowDate();
        presenter = new DailyPresenter();
        presenter.attachView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
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
        RecyclerView rvList = view.findViewById(R.id.rv_list);
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

        presenter.getLatestDaily();
    }

    @Override
    public void onRefresh() {
        dailyAdapter.clear();
        presenter.getLatestDaily();
    }

    @Override
    public void onLoadMoreRequested() {
        date = DateUtils.timeSub(date);
        presenter.getBeforeDaily(date);
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
                .strategy(new GlideStrategy())
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

    @Override
    public void latestDaily(List<TopStoryBean> topList) {
        bannerView.setData(R.layout.item_daily_banner, topList, null);
    }

    @Override
    public void beforeDaily(List<DailyItemBean> dailyList) {
        dailyAdapter.addData(dailyList);
    }

    @Override
    public void onRefreshEnd() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void onLoadMoreEnd(boolean success) {
        if (success) {
            dailyAdapter.loadMoreComplete();
        } else {
            dailyAdapter.loadMoreFail();
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showError(String errorMsg) {

    }
}
