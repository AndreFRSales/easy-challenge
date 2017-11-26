package br.com.andre.easychallenge.presentation.bookmarks;

import android.os.Bundle;

/**
 * Created by andre on 15/11/17.
 */

public class BookmarksPresenter implements BookmarkPresenterContract {

    BookmarksView view;

    public BookmarksPresenter(BookmarksView view) {
        this.view = view;
    }

    @Override
    public void fetchBookmarks() {

    }

    @Override
    public void start() {
         
    }

    @Override
    public void destroy() {

    }

    @Override
    public void saveState(Bundle outState) {

    }

    @Override
    public void restoreState(Bundle savedInstanceState) {

    }
}
