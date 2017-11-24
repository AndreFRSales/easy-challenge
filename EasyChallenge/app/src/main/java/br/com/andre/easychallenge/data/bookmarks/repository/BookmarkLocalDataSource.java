package br.com.andre.easychallenge.data.bookmarks.repository;

import br.com.andre.easychallenge.data.bookmarks.models.BookmarkEntity;
import io.reactivex.Observable;

/**
 * Created by andre on 23/11/17.
 */

public interface BookmarkLocalDataSource {

    Observable<Void> addBookmark(BookmarkEntity bookmarkEntity);
}
