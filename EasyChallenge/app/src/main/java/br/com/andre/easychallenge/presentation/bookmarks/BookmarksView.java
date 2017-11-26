package br.com.andre.easychallenge.presentation.bookmarks;

import java.util.List;

import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
import br.com.andre.easychallenge.presentation.maps.BaseView;

/**
 * Created by andre on 15/11/17.
 */

public interface BookmarksView extends BaseView {

    void setToolbar();
    void showBookmarkList(List<Bookmark> bookmarks);
    void showMessageError(int message);
    void hideMessageError();
}
