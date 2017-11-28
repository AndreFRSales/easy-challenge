package br.com.andre.easychallenge.data.bookmarks.repository;

import java.util.List;

import br.com.andre.easychallenge.data.bookmarks.mappers.BookmarksRepositoryMapper;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkLocalEntity;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkRepositoryEntity;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkResponseList;
import br.com.andre.easychallenge.data.bookmarks.network.BookmarksService;
import br.com.andre.easychallenge.data.retrofit.RetrofitFactory;
import io.reactivex.Observable;

/**
 * Created by andre on 25/11/17.
 */

public class BookmarkRemoteDataSourceImp implements  BookmarkRemoteDataSource{

    BookmarksService bookmarksService;

    public BookmarkRemoteDataSourceImp() {
        this.bookmarksService = RetrofitFactory.createMockApiProvider().create(BookmarksService.class);
    }

    @Override
    public Observable<List<BookmarkRepositoryEntity>> getBookmarksList() {
        return bookmarksService.getBookmarksList()
                .map(BookmarksRepositoryMapper::mapToRepositoryEntity)
                .doOnError(Observable::error);
    }
}
