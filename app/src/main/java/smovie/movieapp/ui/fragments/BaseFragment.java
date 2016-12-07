package smovie.movieapp.ui.fragments;

import android.app.Fragment;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.PixelFormat;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntegerRes;
import android.support.annotation.StringRes;
import android.support.annotation.UiThread;
import android.support.v4.content.ContextCompat;

import smovie.movieapp.enums.FragmentAction;
import smovie.movieapp.presenters.BasePresenter;
import smovie.movieapp.ui.activities.BaseActivity;

/**
 * Created by sniper on 12/6/16.
 */

public abstract class BaseFragment extends Fragment {

    protected BaseActivity mActivity;
    protected BasePresenter mPresenter;

    @CallSuper
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (BaseActivity) getActivity();
        initDataInstances(savedInstanceState);
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        if(mPresenter != null) {
            mPresenter.bindView(this);
        }
        mActivity.hideSoftKeyBoard(getView());
    }
    @Override
    public void onPause() {
        super.onPause();
        if(mPresenter != null) {
            mPresenter.unbindView();
        }
    }
    @UiThread
    protected String getStringResourceSafety(@StringRes int stringResource){
        return isAdded() ? getResources().getString(stringResource) : "";
    }
    @UiThread
    protected int getIntResourceSafety(@IntegerRes int intResource){
        return isAdded() ? getResources().getInteger(intResource) : 0;
    }
    @UiThread
    protected int getColourResourceSafety(@ColorRes int colourResource){
        return isAdded() ? ContextCompat.getColor(mActivity, colourResource) : 0;
    }
    @UiThread
    protected Drawable getDrawableResourceSafety(@DrawableRes int drawableResource){
        return isAdded() ? ContextCompat.getDrawable(mActivity,drawableResource) : new Drawable() {
            @Override
            public void draw(Canvas canvas) {}
            @Override
            public void setAlpha(int alpha) {}
            @Override
            public void setColorFilter(ColorFilter colorFilter) {}
            @Override
            public int getOpacity() {
                return PixelFormat.OPAQUE;
            }
        };
    }

    protected abstract void initDataInstances(Bundle savedInstanceState);
    public abstract void notifyFragmentAboutAction(FragmentAction fragmentAction);
}

