package br.com.andre.easychallenge.data.bookmarks.mappers;

import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
import br.com.andre.easychallenge.domain.bookmarks.usecase.DeleteBookmarkUsecase;

/**
 * Created by andre on 15/11/17.
 */

public class BookmarksDomainMapper {

    public static DeleteBookmarkUsecase.Params mapToDomainUsecase(Bookmark bookmark) {
        return new DeleteBookmarkUsecase.Params(bookmark);
    }

}
