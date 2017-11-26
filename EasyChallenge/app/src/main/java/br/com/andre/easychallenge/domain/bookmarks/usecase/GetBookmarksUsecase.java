package br.com.andre.easychallenge.domain.bookmarks.usecase;

import java.util.List;

import br.com.andre.easychallenge.data.bookmarks.repository.BookmarksRepository;
import br.com.andre.easychallenge.domain.base.BaseUsecase;
import br.com.andre.easychallenge.domain.bookmarks.mapper.BookmarksMapper;
import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
import io.reactivex.Observable;

/**
 * Created by andre on 25/11/17.
 */

public class GetBookmarksUsecase extends BaseUsecase<List<Bookmark>, Void> {

    BookmarksRepository bookmarksRepository;

    public GetBookmarksUsecase(BookmarksRepository bookmarksRepository) {
        this.bookmarksRepository = bookmarksRepository;
    }

    @Override
    protected Observable<List<Bookmark>> createUseCase(Void aVoid) {
        return bookmarksRepository.getBookmarks().map(BookmarksMapper::mapToDomainModel);
    }
}
