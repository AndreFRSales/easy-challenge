package br.com.andre.easychallenge.domain.bookmarks.usecase;

import br.com.andre.easychallenge.data.bookmarks.repository.BookmarksRepository;
import br.com.andre.easychallenge.domain.base.BaseUsecase;
import br.com.andre.easychallenge.domain.bookmarks.mapper.BookmarksMapper;
import br.com.andre.easychallenge.domain.map.models.CurrentPosition;
import io.reactivex.Observable;

/**
 * Created by andre on 15/11/17.
 */

public class SaveBookmarkUsecase extends BaseUsecase<Void, SaveBookmarkUsecase.Params> {

    BookmarksRepository bookmarksRepository;

    public SaveBookmarkUsecase(BookmarksRepository bookmarksRepository) {
        this.bookmarksRepository = bookmarksRepository;
    }

    @Override
    protected Observable<Void> createUseCase(Params params) {
        return bookmarksRepository.addBookmark(BookmarksMapper.mapToRepositoryModel(params));
    }

    public static class Params {

        String description;
        CurrentPosition currentPosition;

        public Params(String description, CurrentPosition currentPosition) {
            this.description = description;
            this.currentPosition = currentPosition;
        }

        public String getDescription() {
            return description;
        }

        public CurrentPosition getCurrentPosition() {
            return currentPosition;
        }
    }

}
