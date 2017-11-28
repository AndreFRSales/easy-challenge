package br.com.andre.easychallenge.data.bookmarks.mappers;

import java.util.ArrayList;
import java.util.List;

import br.com.andre.easychallenge.data.bookmarks.models.BookmarkLocalEntity;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkRepositoryEntity;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkResponse;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkResponseList;

/**
 * Created by andre on 25/11/17.
 */

public class BookmarksRepositoryMapper {

    public static List<BookmarkRepositoryEntity> mapToRepositoryEntity(BookmarkResponseList response) {
        ArrayList<BookmarkRepositoryEntity> list = new ArrayList<>();
        for (BookmarkResponse bookmarkResponse : response.getBookmarkEntityList()) {
            list.add(new BookmarkRepositoryEntity(bookmarkResponse.getName(), bookmarkResponse.getLatitude(),
                    bookmarkResponse.getLongitude()));
        }

        return list;
    }

    public static List<BookmarkRepositoryEntity> mapToRepositoryEntity(List<BookmarkLocalEntity> local) {
        ArrayList<BookmarkRepositoryEntity> list = new ArrayList<>();
        for (BookmarkLocalEntity bookmarkLocalEntity: local) {
            list.add(new BookmarkRepositoryEntity(bookmarkLocalEntity.getDescription(), bookmarkLocalEntity.getLatitude(),
                    bookmarkLocalEntity.getLongitude()));
        }

        return list;
    }


}
