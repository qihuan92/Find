package com.qihuan.find.presenter;

import com.qihuan.find.app.App;
import com.qihuan.find.contact.DailyDetContract;
import com.qihuan.find.model.bean.find.CollectionBean;
import com.qihuan.find.model.bean.find.CollectionBean_;
import com.qihuan.find.model.bean.zhihu.StoryContentBean;
import com.qihuan.find.model.remote.RetrofitClient;
import com.qihuan.find.presenter.base.BasePresenterImpl;

import io.objectbox.Box;
import io.objectbox.android.AndroidScheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class DailyDetPresenter extends BasePresenterImpl<DailyDetContract.View> implements DailyDetContract.Presenter {

    private final Box<CollectionBean> collectionBox;
    private StoryContentBean storyContentBean;

    public DailyDetPresenter() {
        collectionBox = App.getInstance().getBoxStore().boxFor(CollectionBean.class);
    }

    @Override
    public void detachView() {
        super.detachView();
    }

    @Override
    public void getStoryContent(int id) {
        checkViewAttached();
        disposables.add(
                RetrofitClient.zhihuApi().getStoryContent(id)
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
                RetrofitClient.zhihuApi().getStoryExtra(id)
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
        collectionBox.query()
                .equal(CollectionBean_.collectionId, String.valueOf(id))
                .build()
                .subscribe()
                .single()
                .on(AndroidScheduler.mainThread())
                .observer(data -> {
                    getView().onFavoriteChange(data.size() != 0);
                    getView().hideLoading();
                });
    }

    @Override
    public void updateFavoriteStory(int id) {
        checkViewAttached();
        if (storyContentBean == null) {
            return;
        }
        collectionBox.query()
                .equal(CollectionBean_.collectionId, String.valueOf(id))
                .build()
                .subscribe()
                .on(AndroidScheduler.mainThread())
                .single()
                .observer(data -> {
                    boolean isFavorite = data.size() != 0;
                    if (isFavorite) {
                        collectionBox.remove(data);
                    } else {
                        CollectionBean collectionBean = new CollectionBean()
                                .setCollectionId(String.valueOf(id))
                                .setType(0)
                                .setTitle(storyContentBean.getTitle())
                                .setImg(storyContentBean.getImage());
                        collectionBox.put(collectionBean);
                    }
                    getView().hideLoading();
                    getView().onFavoriteChange(!isFavorite);
                });
    }

}
