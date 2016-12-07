package smovie.movieapp.enums;

/**
 * Created by sniper on 12/7/16.
 * Use this flags to send toolbar setup from the fragments,
 * because they are the views and the toolbar need to be corresponding with the current view
 */
public enum ToolbarAction {
    ACTION_SET_TITLE,
    ACTION_BACK_TO_PREVIOUS_SCREEN,
    ACTION_REMOVE_BACK_TO_PREVIOUS_SCREEN,
    ACTION_MAKE_TRANSPARENT_BACKGROUND,
    ACTION_MAKE_NORMAL_BACKGROUND;
}
