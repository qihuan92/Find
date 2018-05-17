package com.qihuan.moviemodule.view;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.qihuan.commonmodule.base.BaseFragment;
import com.qihuan.commonmodule.router.Router;
import com.qihuan.moviemodule.R;
import com.qihuan.moviemodule.model.DoubanModel;
import com.qihuan.moviemodule.model.bean.USboxBean;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * MovieFragment
 *
 * @author Qi
 */
@Route(path = Router.MOVIE_FRAGMENT)
public class MovieFragment extends BaseFragment {

    public static MovieFragment newInstance() {
        return new MovieFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_movie, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
