package br.com.andre.easychallenge.presentation.bookmarks.presenter;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
import br.com.andre.easychallenge.domain.map.models.CurrentPosition;
import br.com.andre.easychallenge.presentation.maps.presenter.MapsBundler;

import static br.com.andre.easychallenge.presentation.bookmarks.presenter.BookmarksBundler.BOOKMARK_LIST_KEY;

/**
 * Created by andre on 21/11/17.
 */

public class BookmarksBundleReader {

    Bundle bundle;

    public BookmarksBundleReader(Bundle bundle) {
        this.bundle = bundle;
    }

    public List<Bookmark> getBookmarkList() {
        if(bundle.containsKey(BOOKMARK_LIST_KEY)) {
            return (List<Bookmark>) bundle.getSerializable(BOOKMARK_LIST_KEY);
        } else {
            return new ArrayList<>();
        }
    }
}
