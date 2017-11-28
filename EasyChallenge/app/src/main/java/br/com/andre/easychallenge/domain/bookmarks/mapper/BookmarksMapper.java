package br.com.andre.easychallenge.domain.bookmarks.mapper;

import java.util.ArrayList;
import java.util.List;

import br.com.andre.easychallenge.data.bookmarks.models.BookmarkLocalEntity;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkRepositoryEntity;
import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
import br.com.andre.easychallenge.domain.bookmarks.usecase.DeleteBookmarkUsecase;
import br.com.andre.easychallenge.domain.bookmarks.usecase.SaveBookmarkUsecase;

/**
 * Created by andre on 15/11/17.
 */

public class BookmarksMapper {

    public static BookmarkLocalEntity mapToRepositoryModel(SaveBookmarkUsecase.Params params) {
        return new BookmarkLocalEntity(params.getDescription(), params.getCurrentPosition().getLatitude(),
                params.getCurrentPosition().getLongitude());
    }

    public static BookmarkLocalEntity mapToRepositoryModel(DeleteBookmarkUsecase.Params params) {
        Bookmark bookmark = params.getBookmark();
        return new BookmarkLocalEntity(bookmark.getDescription(), bookmark.getLatitude(), bookmark.getLongitude());
    }

    public static List<Bookmark> mapToDomainModel(List<BookmarkRepositoryEntity> bookmarkRepositoryEntityList) {
        ArrayList<Bookmark> bookmarks = new ArrayList<>();
        for (BookmarkRepositoryEntity bookmarkLocalEntity : bookmarkRepositoryEntityList) {
            bookmarks.add(new Bookmark(bookmarkLocalEntity.getDescription(), bookmarkLocalEntity.getLatitude(),
                    bookmarkLocalEntity.getLongitude()));
        }

        return bookmarks;
    }
}
