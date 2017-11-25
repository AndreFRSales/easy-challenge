package br.com.andre.easychallenge.data.bookmarks.repository;

import java.util.List;

import br.com.andre.easychallenge.data.bookmarks.models.BookmarkLocalEntity;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkRepositoryEntity;
import io.reactivex.Observable;

/**
 * Created by andre on 23/11/17.
 */

public interface BookmarkLocalDataSource {

    Observable<Void> addBookmark(BookmarkLocalEntity bookmarkLocalEntity);
    Observable<List<BookmarkRepositoryEntity>> getBookmarks();
}
