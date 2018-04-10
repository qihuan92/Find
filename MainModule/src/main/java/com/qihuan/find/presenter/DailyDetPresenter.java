package com.qihuan.find.presenter;

import com.qihuan.find.contact.DailyDetContract;
import com.qihuan.find.model.bean.find.CollectionBean;
import com.qihuan.find.model.bean.zhihu.StoryContentBean;
import com.qihuan.find.model.local.CollectionModel;
import com.qihuan.find.model.remote.ZhihuModel;
import com.qihuan.find.presenter.base.BasePresenterImpl;

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
        checkViewAttached();
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
        checkViewAttached();
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
        checkViewAttached();
        getView().showLoading();
        collectionModel.getFavoriteList(String.valueOf(id), collectionList -> {
            getView().onFavoriteChange(collectionList.size() != 0);
            getView().hideLoading();
        });
    }

    @Override
    public void updateFavoriteStory(int id) {
        checkViewAttached();
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
