package smovie.movieapp.ui.activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.databinding.DataBindingUtil;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import smovie.movieapp.MovieApp;
import smovie.movieapp.R;
import smovie.movieapp.databinding.ActivityMainBinding;
import smovie.movieapp.enums.FragmentAction;
import smovie.movieapp.enums.ToolbarAction;
import smovie.movieapp.ui.fragments.BaseFragment;
import smovie.movieapp.utils.ConnectivityChangeBroadcastReceiver;
import smovie.movieapp.utils.IConnectivityChangeListener;

/**
 * Created by sniper on 12/6/16.
 */
public abstract class BaseActivity extends AppCompatActivity implements FragmentManager.OnBackStackChangedListener, IConnectivityChangeListener {

    private ConnectivityChangeBroadcastReceiver mReceiver;// internet connection checker
    protected ActivityMainBinding mViewBinding;

    public abstract void setToolbarAction(@NonNull ToolbarAction action , @Nullable Object... additionalParams);

    @CallSuper
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        getFragmentManager().addOnBackStackChangedListener(this);
    }

    @CallSuper
    @Override
    public void onResume() {
        super.onResume();
        if (mReceiver == null) {
            mReceiver = new ConnectivityChangeBroadcastReceiver(this);
        }
        IntentFilter filter = new IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION);
        registerReceiver(mReceiver, filter);
    }

    @CallSuper
    @Override
    public void onStop() {
        super.onStop();
        try {
            unregisterReceiver(mReceiver);
        }catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }

    @Override
    public void onBackStackChanged() {}

    @CallSuper
    @Override
    public void connectivityChange(boolean connected) {
        //if the coming notification about the internet connection is same like the one that we have just ignore it
        if(connected == MovieApp.isOnline) return;

        MovieApp.isOnline = connected;
        notifyTopScreenAboutConnectionChanges();
    }
    /**
     * Use this to replace existing fragment with other fragment
     * @param fragment
     * @param withAnimationTransaction
     */
    @CallSuper
    public void replaceFragment(Fragment fragment, boolean withAnimationTransaction) {
        if(fragment == null) return;
        FragmentTransaction t = getFragmentManager().beginTransaction();
        if(withAnimationTransaction) t.setCustomAnimations(R.animator.fragment_slide_left_enter, R.animator.fragment_slide_left_exit, R.animator.fragment_slide_right_enter, R.animator.fragment_slide_right_exit);
        try {
            String fragmentClassName = fragment.getClass().getCanonicalName();
            t.replace(R.id.activity_fragment_container, fragment, fragmentClassName);
            t.addToBackStack(fragmentClassName);
            t.commit();
        }catch (IllegalArgumentException ex){
            ex.printStackTrace();
        }
    }

    @CallSuper
    @Override
    public void onBackPressed() {
        getFragmentManager().popBackStackImmediate();
        if (getFragmentManager().getBackStackEntryCount() < 1) {
            super.onBackPressed();
        }
    }
    @CallSuper
    public void showSoftKeyBoard(View view) {
        view.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT);
    }
    @CallSuper
    public void hideSoftKeyBoard(View view) {
//        Pass the view which is on focus with the keyboard
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * Use this method when you want ot start new Activity with Standard System Transition
     * @param clazz - class name of the new Activity (ex. HomeActivity.class)
     */
    @CallSuper
    public void openActivity(@NonNull Class<?> clazz , @Nullable Bundle extras) {
        Intent newIntent = new Intent(this, clazz);
        if(extras != null){
            newIntent.putExtras(extras);
        }
        startActivity(newIntent);
    }

    @CallSuper
    protected void notifyTopScreenAboutConnectionChanges(){
        final BaseFragment fragment = getTopFragment();
        if(fragment != null) {
            fragment.notifyFragmentAboutAction(FragmentAction.ACTION_CONNECTIVITY_CHANGE);
        }
    }
    @CallSuper
    protected BaseFragment getTopFragment(){
        try {
            FragmentManager.BackStackEntry backEntry = getFragmentManager().getBackStackEntryAt(getFragmentManager().getBackStackEntryCount() - 1);
            String topFragmentName = backEntry.getName();
            return  (BaseFragment) getFragmentManager().findFragmentByTag(topFragmentName);
        }catch (ArrayIndexOutOfBoundsException ex){
            throw new RuntimeException("Top Screen Fragment does not exist: ArrayIndexOutOfBoundsException");
        }catch (ClassCastException ex){
            throw new IllegalStateException("Your are trying to notify the top fragment, but it is not BaseFragment instance. You can't notify this kind of Fragment");
        }catch (Exception ex){
            throw new IllegalStateException("Your are trying to notify the top fragment, but something wrong happen. Your notify action will be skipped");
        }
    }
}