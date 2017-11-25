package br.com.andre.easychallenge.data.bookmarks.repository;

import java.util.List;

import br.com.andre.easychallenge.data.bookmarks.models.BookmarkRepositoryEntity;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkResponseList;
import io.reactivex.Observable;

/**
 * Created by andre on 25/11/17.
 */

public interface BookmarkRemoteDataSource {

    Observable<List<BookmarkRepositoryEntity>> getBookmarksList();

}
