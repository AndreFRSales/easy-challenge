package br.com.andre.easychallenge.data.bookmarks.repository;


import java.util.List;

import br.com.andre.easychallenge.data.bookmarks.models.BookmarkLocalEntity;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkRepositoryEntity;
import io.reactivex.Observable;

/**
 * Created by andre on 15/11/17.
 */

public class BookmarksRepositoryImp implements BookmarksRepository {

    BookmarkLocalDataSource bookmarkLocalDataSource;
    BookmarkRemoteDataSource bookmarkRemoteDataSource;

    public BookmarksRepositoryImp(BookmarkLocalDataSource bookmarkLocalDataSource, BookmarkRemoteDataSource bookmarkRemoteDataSource) {
        this.bookmarkLocalDataSource = bookmarkLocalDataSource;
        this.bookmarkRemoteDataSource = bookmarkRemoteDataSource;
    }

    @Override
    public Observable<Void> addBookmark(BookmarkLocalEntity bookmarkLocalEntity) {
        return bookmarkLocalDataSource.addBookmark(bookmarkLocalEntity);
    }

    @Override
    public Observable<List<BookmarkRepositoryEntity>> getBookmarks() {
        return Observable.zip(bookmarkRemoteDataSource.getBookmarksList(), bookmarkLocalDataSource.getBookmarks(),
                (bookmarkEntities, bookmarkEntities2) -> {
                bookmarkEntities.addAll(bookmarkEntities2);
                return bookmarkEntities;
        });
    }
}
