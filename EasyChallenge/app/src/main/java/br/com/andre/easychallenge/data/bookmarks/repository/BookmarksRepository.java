package br.com.andre.easychallenge.data.bookmarks.repository;

import br.com.andre.easychallenge.data.bookmarks.models.BookmarkEntity;
import io.reactivex.Observable;

/**
 * Created by andre on 15/11/17.
 */

public interface BookmarksRepository {

    Observable<Void> addBookmark(BookmarkEntity bookmarkEntity);
}
