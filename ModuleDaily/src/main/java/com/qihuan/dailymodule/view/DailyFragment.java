package com.qihuan.dailymodule.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qihuan.commonmodule.base.BaseMvpFragment;
import com.qihuan.commonmodule.bus.BindEventBus;
import com.qihuan.commonmodule.bus.event.RefreshEvent;
import com.qihuan.commonmodule.imageloader.ImageLoader;
import com.qihuan.commonmodule.router.Router;
import com.qihuan.dailymodule.R;
import com.qihuan.dailymodule.contract.DailyContract;
import com.qihuan.dailymodule.model.bean.DailyItemBean;
import com.qihuan.dailymodule.model.bean.TopStoryBean;
import com.qihuan.dailymodule.presenter.DailyPresenter;
import com.qihuan.dailymodule.view.adapter.DailyAdapter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * DailyFragment
 *
 * @author Qi
 */
@BindEventBus
@Route(path = Router.DAILY_FRAGMENT)
public class DailyFragment extends BaseMvpFragment<DailyContract.Presenter> implements
        DailyContract.View,
        OnRefreshListener,
        OnLoadMoreListener,
        BaseQuickAdapter.OnItemClickListener,
        BGABanner.Adapter<View, TopStoryBean>,
        BGABanner.Delegate<View, TopStoryBean> {

    private SmartRefreshLayout refreshLayout;
    private BGABanner bannerView;
    private DailyAdapter dailyAdapter;
    private RecyclerView rvList;
    private LinearLayoutManager linearLayoutManager;

    public static DailyFragment newInstance() {
        return new DailyFragment();
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
        rvList = view.findViewById(R.id.rv_list);
        refreshLayout = view.findViewById(R.id.refresh_layout);

        refreshLayout.setOnRefreshListener(this);
        refreshLayout.setOnLoadMoreListener(this);
        dailyAdapter = new DailyAdapter();
        dailyAdapter.openLoadAnimation(BaseQuickAdapter.SLIDEIN_BOTTOM);
        dailyAdapter.setOnItemClickListener(this);
        linearLayoutManager = new LinearLayoutManager(getContext());
        rvList.setLayoutManager(linearLayoutManager);
        rvList.setAdapter(dailyAdapter);

        bannerView = (BGABanner) LayoutInflater.from(getContext()).inflate(R.layout.layout_banner, rvList, false);
        bannerView.setAdapter(this);
        bannerView.setDelegate(this);
        dailyAdapter.addHeaderView(bannerView);

        refreshLayout.autoRefresh();
    }

    @Override
    public void onRefresh(@NonNull RefreshLayout refreshLayout) {
        mPresenter.getLatestDaily();
    }

    @Override
    public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
        mPresenter.getBeforeDaily();
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
                .build(Router.DAILY_DET_ACTIVITY)
                .withInt("id", dailyItemBean.t.getId())
                .navigation();
    }

    @Override
    public void fillBannerItem(BGABanner banner, View itemView, TopStoryBean model, int position) {
        ImageView ivBanner = itemView.findViewById(R.id.iv_banner);
        TextView tvBanner = itemView.findViewById(R.id.tv_banner);
        ImageLoader.getInstance()
                .with(getContext())
                .url(model.getImage())
                .placeHolder(R.color.md_grey_400)
                .into(ivBanner);
        tvBanner.setText(model.getTitle());
    }

    @Override
    public void onBannerItemClick(BGABanner banner, View itemView, TopStoryBean model, int position) {
        ARouter.getInstance()
                .build(Router.DAILY_DET_ACTIVITY)
                .withInt("id", model.getId())
                .navigation();
    }

    @Override
    public void latestDaily(List<TopStoryBean> topList) {
        bannerView.setData(R.layout.item_daily_banner, topList, null);
    }

    @Override
    public void beforeDaily(boolean isRefresh, List<DailyItemBean> dailyList) {
        if (isRefresh) {
            dailyAdapter.setNewData(dailyList);
        } else {
            dailyAdapter.addData(dailyList);
        }
    }

    @Override
    public void onRefreshEnd(boolean success) {
        refreshLayout.finishRefresh(success);
    }

    @Override
    public void onLoadMoreEnd(boolean success) {
        refreshLayout.finishLoadMore(success);
    }

    @Subscribe
    public void onRefreshEvent(RefreshEvent refreshEvent) {
        int visibleItemPosition = linearLayoutManager.findFirstCompletelyVisibleItemPosition();
        if (visibleItemPosition == 0) {
            // 刷新
            refreshLayout.autoRefresh();
            return;
        }
        // 超过 20 条, 先滚动到20条, 再平滑滚动
        if (visibleItemPosition > 20) {
            rvList.scrollToPosition(20);
        }
        rvList.smoothScrollToPosition(0);
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

    @Override
    protected DailyContract.Presenter initPresenter() {
        return new DailyPresenter();
    }
}
