package smovie.movieapp.ui.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;

import smovie.movieapp.enums.ToolbarAction;
import smovie.movieapp.ui.fragments.SearchMovieFragment;
import smovie.movieapp.utils.RegularUtils;

public class MainActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mViewBinding.toolbarInclude.toolbarBack.setOnClickListener((view) -> onBackPressed());

        //start the first screen
        replaceFragment(SearchMovieFragment.newInstance(null), false);
    }

    @Override
    public void setToolbarAction(@NonNull ToolbarAction action, @Nullable Object... additionalParams) {
        switch (action){
            case ACTION_SET_TITLE:
                mViewBinding.toolbarInclude.toolbarTitle.setText(RegularUtils.parseAdditionalParams(additionalParams, 0, String.class));
                break;
            case ACTION_BACK_TO_PREVIOUS_SCREEN:
                mViewBinding.toolbarInclude.toolbarBack.setVisibility(View.VISIBLE);
                break;
            case ACTION_REMOVE_BACK_TO_PREVIOUS_SCREEN:
                mViewBinding.toolbarInclude.toolbarBack.setVisibility(View.GONE);
                break;
        }
    }
}
