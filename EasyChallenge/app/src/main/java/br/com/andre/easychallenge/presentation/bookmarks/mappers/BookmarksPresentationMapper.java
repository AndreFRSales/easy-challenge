package br.com.andre.easychallenge.presentation.bookmarks.mappers;

import br.com.andre.easychallenge.domain.bookmarks.usecase.SaveBookmarkUsecase;
import br.com.andre.easychallenge.domain.map.models.CurrentPosition;

/**
 * Created by andre on 23/11/17.
 */

public class BookmarksPresentationMapper {

    public static SaveBookmarkUsecase.Params mapToSaveBookmarkDomainModel(String description, CurrentPosition currentPosition) {
        return new SaveBookmarkUsecase.Params(description, currentPosition);
    }
}
