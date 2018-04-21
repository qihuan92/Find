package com.qihuan.dailymodule.presenter;

import com.qihuan.commonmodule.base.BasePresenterImpl;
import com.qihuan.commonmodule.collection.bean.CollectionBean;
import com.qihuan.commonmodule.collection.model.CollectionModel;
import com.qihuan.dailymodule.contract.DailyDetContract;
import com.qihuan.dailymodule.model.ZhihuModel;
import com.qihuan.dailymodule.model.bean.StoryContentBean;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DailyDetPresenter extends BasePresenterImpl<DailyDetContract.View> implements DailyDetContract.Presenter {

    private final CollectionModel collectionModel;
    private final ZhihuModel zhihuModel;
    private StoryContentBean storyContentBean;

    public DailyDetPresenter() {
        collectionModel = new CollectionModel();
        zhihuModel = new ZhihuModel();
    }

    @Override
    public void detachView() {
        super.detachView();
        collectionModel.onDestroy();
    }

    @Override
    public void getStoryContent(int id) {
        disposables.add(
            zhihuModel.getApi().getStoryContent(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(storyContentBean -> {
                    if (storyContentBean == null) {
                        getView().showError("该日报已不存在~");
                        return;
                    }
                    this.storyContentBean = storyContentBean;
                    getView().storyContent(storyContentBean);
                }, throwable -> getView().showError(throwable.getMessage()))
        );
    }

    @Override
    public void getStoryExtra(int id) {
        disposables.add(
            zhihuModel.getApi().getStoryExtra(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(storyExtraBean -> getView().storyExtra(storyExtraBean),
                    throwable -> getView().showError(throwable.getMessage()))
        );
    }

    @Override
    public void getFavoriteStory(int id) {
        getView().showLoading();
        collectionModel.getFavoriteList(String.valueOf(id), collectionList -> {
            getView().onFavoriteChange(collectionList.size() != 0);
            getView().hideLoading();
        });
    }

    @Override
    public void updateFavoriteStory(int id) {
        if (storyContentBean == null) {
            return;
        }
        CollectionBean collectionBean = new CollectionBean()
            .setCollectionId(String.valueOf(id))
            .setType(0)
            .setTitle(storyContentBean.getTitle())
            .setImg(storyContentBean.getImage());
        collectionModel.updateFavorite(collectionBean, isFavorite -> {
            getView().hideLoading();
            getView().onFavoriteChange(isFavorite);
            getView().showUpdateFavoriteInfo(isFavorite);
        });
    }

}