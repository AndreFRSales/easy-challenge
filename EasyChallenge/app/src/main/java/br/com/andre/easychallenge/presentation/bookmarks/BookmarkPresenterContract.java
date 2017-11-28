package br.com.andre.easychallenge.presentation.bookmarks;

import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
import br.com.andre.easychallenge.presentation.base.BasePresenterContract;

/**
 * Created by andre on 26/11/17.
 */

interface BookmarkPresenterContract extends BasePresenterContract {

    void deleteBookmark(Bookmark bookmark);
}
