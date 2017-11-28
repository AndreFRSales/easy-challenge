package br.com.andre.easychallenge.data.bookmarks.repository;

import java.util.List;

import br.com.andre.easychallenge.data.bookmarks.models.BookmarkLocalEntity;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkRepositoryEntity;
import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
import io.reactivex.Observable;

/**
 * Created by andre on 15/11/17.
 */

public interface BookmarksRepository {

    Observable<Void> addBookmark(BookmarkLocalEntity bookmarkLocalEntity);
    Observable<List<BookmarkRepositoryEntity>> getBookmarks();
    Observable<Void> deleteBookmark(BookmarkLocalEntity bookmark);
}
