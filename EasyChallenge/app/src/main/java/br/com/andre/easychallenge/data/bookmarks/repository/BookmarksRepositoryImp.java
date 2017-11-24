package br.com.andre.easychallenge.data.bookmarks.repository;


import br.com.andre.easychallenge.data.bookmarks.models.BookmarkEntity;
import io.reactivex.Observable;

/**
 * Created by andre on 15/11/17.
 */

public class BookmarksRepositoryImp implements BookmarksRepository {

    BookmarkLocalDataSource bookmarkLocalDataSource;

    public BookmarksRepositoryImp(BookmarkLocalDataSource bookmarkLocalDataSource) {
        this.bookmarkLocalDataSource = bookmarkLocalDataSource;
    }

    @Override
    public Observable<Void> addBookmark(BookmarkEntity bookmarkEntity) {
        return bookmarkLocalDataSource.addBookmark(bookmarkEntity);
    }
}
