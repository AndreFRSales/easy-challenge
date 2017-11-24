package br.com.andre.easychallenge.domain.bookmarks.mapper;

import br.com.andre.easychallenge.data.bookmarks.models.BookmarkEntity;
import br.com.andre.easychallenge.domain.bookmarks.usecase.SaveBookmarkUsecase;

/**
 * Created by andre on 15/11/17.
 */

public class BookmarksMapper {

    public BookmarkEntity mapToRepositoryModel(SaveBookmarkUsecase.Params params) {
        return new BookmarkEntity(params.getDescription(), params.getCurrentPosition().getLatitude(),
                params.getCurrentPosition().getLongitude());
    }
}
