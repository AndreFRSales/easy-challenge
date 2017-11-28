package br.com.andre.easychallenge.domain.bookmarks.usecase;

import br.com.andre.easychallenge.data.bookmarks.repository.BookmarksRepository;
import br.com.andre.easychallenge.domain.base.BaseUsecase;
import br.com.andre.easychallenge.domain.bookmarks.mapper.BookmarksMapper;
import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
import io.reactivex.Observable;

/**
 * Created by andre on 27/11/17.
 */

public class DeleteBookmarkUsecase extends BaseUsecase<Void, DeleteBookmarkUsecase.Params> {

    BookmarksRepository repository;

    public DeleteBookmarkUsecase(BookmarksRepository repository) {
        this.repository = repository;
    }

    @Override
    protected Observable<Void> createUseCase(Params params) {
        return repository.deleteBookmark(BookmarksMapper.mapToRepositoryModel(params));
    }

    public static class Params {
        Bookmark bookmark;

        public Params(Bookmark bookmark) {
            this.bookmark = bookmark;
        }

        public Bookmark getBookmark() {
            return bookmark;
        }
    }

}