package br.com.andre.easychallenge.presentation.bookmarks.presenter;

import android.os.Bundle;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
import br.com.andre.easychallenge.domain.map.models.CurrentPosition;

/**
 * Created by andre on 21/11/17.
 */

public class BookmarksBundler {

    private Bundle bundle;
    public static final String BOOKMARK_LIST_KEY = "bookmarkBundleKey";

    public BookmarksBundler(Bundle bundle) {
        this.bundle = bundle;
    }

    public void setBookmarkList(List<Bookmark> bookmarkList) {
        this.bundle.putSerializable(BOOKMARK_LIST_KEY, (Serializable)bookmarkList);
    }


}
