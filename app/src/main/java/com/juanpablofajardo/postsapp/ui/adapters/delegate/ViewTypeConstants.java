package com.juanpablofajardo.postsapp.ui.adapters.delegate;

/**
 * Created by Juan Pablo Fajardo Cano on 4/28/18.
 * <p>
 * Class where every {@link ViewType} must be declared as follows:
 * public static final int NAME_OF_VIEWTYPE = 0x00000X
 * Where X should be replaced by the next number
 */

public class ViewTypeConstants {

    public static final int POST_VIEW_TYPE = 0x00001;
    public static final int POST_DETAIL_POST_INFO_VIEW_TYPE = 0x00002;
    public static final int POST_DETAIL_USER_INFO_VIEW_TYPE = 0x00003;
    public static final int POST_DETAIL_COMMENT_VIEW_TYPE = 0x00004;
    public static final int LAYOUT_ONLY_VIEWTYPE = 0x00005;

}