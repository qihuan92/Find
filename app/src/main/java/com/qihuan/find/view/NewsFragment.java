package com.qihuan.find.view;


import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.qihuan.find.R;
import com.qihuan.find.bean.zhihu.DailyEntity;
import com.qihuan.find.bean.zhihu.DailyItem;
import com.qihuan.find.bean.zhihu.StoriesEntity;
import com.qihuan.find.bean.zhihu.TopStoriesEntity;
import com.qihuan.find.kit.DateKit;
import com.qihuan.find.kit.ToastKit;
import com.qihuan.find.presenter.NewsPresenter;
import com.qihuan.find.view.adapter.DailyAdapter;
import com.qihuan.find.view.base.BaseFragment;
import com.qihuan.find.view.custom.weight.GlideRoundTransform;
import com.qihuan.find.view.i.INewsView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.bingoogolapple.bgabanner.BGABanner;
import easymvp.annotation.FragmentView;
import easymvp.annotation.Presenter;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * NewsFragment
 */
@FragmentView(presenter = NewsPresenter.class)
public class NewsFragment extends BaseFragment implements INewsView,
        SwipeRefreshLayout.OnRefreshListener,
        BaseQuickAdapter.OnItemClickListener,
        BaseQuickAdapter.RequestLoadMoreListener,
        BGABanner.Adapter<RelativeLayout, TopStoriesEntity>,
        BGABanner.Delegate<RelativeLayout, TopStoriesEntity> {

    @Presenter
    NewsPresenter newsPresenter;

    private SwipeRefreshLayout refreshLayout;
    private RecyclerView rvList;
    private List<TopStoriesEntity> topStories = new ArrayList<>();
    private List<DailyItem> stories = new ArrayList<>();
    private DailyAdapter dailyAdapter;
    private String date = DateKit.getNowDate();
    private BGABanner bannerView;

    public static NewsFragment newInstance() {
        return new NewsFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news, container, false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        rvList = (RecyclerView) view.findViewById(R.id.rv_list);
        refreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.refresh_layout);

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

        Observable.timer(500, TimeUnit.MILLISECONDS)
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        newsPresenter.getLatestDaily();
                    }
                });

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

    @Override
    public void start() {

    }

    @Override
    public void end() {
        refreshLayout.setRefreshing(false);
    }

    @Override
    public void error(String message) {
        refreshLayout.setRefreshing(false);
        ToastKit.error(message);
    }

    @Override
    public void topDaily(DailyEntity dailyEntity) {
        topStories.clear();
        topStories.addAll(dailyEntity.getTop_stories());
        bannerView.setData(R.layout.item_daily_banner, dailyEntity.getTop_stories(), null);

        stories.clear();
        stories.add(new DailyItem(true, "今日热闻"));
        for (StoriesEntity storiesEntity : dailyEntity.getStories()) {
            stories.add(new DailyItem(storiesEntity));
        }
        dailyAdapter.notifyDataSetChanged();
    }

    @Override
    public void beforeDaily(DailyEntity dailyEntity) {
        stories.add(new DailyItem(true, DateKit.parseDate(dailyEntity.getDate())));
        for (StoriesEntity storiesEntity : dailyEntity.getStories()) {
            stories.add(new DailyItem(storiesEntity));
        }
        dailyAdapter.notifyDataSetChanged();
        dailyAdapter.loadMoreComplete();
    }

    @Override
    public void onRefresh() {
        date = DateKit.getNowDate();
        newsPresenter.getLatestDaily();
    }

    @Override
    public void onLoadMoreRequested() {
        date = DateKit.timeSub(date);
        newsPresenter.getBeforeDaily(date);
    }

    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
        DailyItem dailyItem = stories.get(position);
        if (dailyItem.isHeader) {
            return;
        }
        if (dailyItem.t == null) {
            return;
        }
        ToastKit.success(dailyItem.t.getTitle());
    }

    @Override
    public void fillBannerItem(BGABanner banner, RelativeLayout itemView, TopStoriesEntity model, int position) {
        ImageView ivBanner = (ImageView) itemView.findViewById(R.id.iv_banner);
        TextView tvBanner = (TextView) itemView.findViewById(R.id.tv_banner);
        Glide.with(this)
                .load(model.getImage())
                .transform(new CenterCrop(getContext()), new GlideRoundTransform(getContext(), 4))
                .crossFade()
                .into(ivBanner);
        tvBanner.setText(model.getTitle());
    }

    @Override
    public void onBannerItemClick(BGABanner banner, RelativeLayout itemView, TopStoriesEntity model, int position) {
        ToastKit.success(model.getTitle());
    }
}
