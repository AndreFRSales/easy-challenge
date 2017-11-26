package br.com.andre.easychallenge.data.bookmarks.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import br.com.andre.easychallenge.data.bookmarks.mappers.BookmarksRepositoryMapper;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkLocalEntity;
import br.com.andre.easychallenge.data.bookmarks.models.BookmarkRepositoryEntity;
import io.reactivex.Observable;

/**
 * Created by andre on 23/11/17.
 */

public class BookmarkLocalDataSourceImp implements BookmarkLocalDataSource{

    SharedPreferences sharedPreferences;
    private static final String SHARED_PREFERENCES_BOOKMARK = "sharedPreferencesBookmarkLocalDataSource";
    private static final String BOOKMARK_KEY = "bookmarkKey";

    public BookmarkLocalDataSourceImp(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_BOOKMARK, Context.MODE_PRIVATE);
    }

    @Override
    public Observable<Void> addBookmark(BookmarkLocalEntity bookmarkLocalEntity) {
        return Observable.create(emitter -> {
            boolean isSaved = saveBookmarkList(bookmarkLocalEntity);
            if(isSaved) {
                emitter.onComplete();
            } else {
                emitter.onError(new Exception());
            }
        });
    }

    @Override
    public Observable<List<BookmarkRepositoryEntity>> getBookmarks() {
        return Observable.create(emitter -> emitter.onNext(BookmarksRepositoryMapper.mapToRepositoryEntity(getBookmarkList())));
    }

    private List<BookmarkLocalEntity> getBookmarkList() {
        List<BookmarkLocalEntity> bookmarkLocalEntityList = parseSharedPreferencesIntoBookmarkList();
        if(bookmarkLocalEntityList == null || bookmarkLocalEntityList.size() == 0) {
            return new ArrayList<>();
        } else {
            return bookmarkLocalEntityList;
        }
    }

    private List<BookmarkLocalEntity> parseSharedPreferencesIntoBookmarkList() {
        String bookmarkListJson = sharedPreferences.getString(BOOKMARK_KEY, "");
        Gson gson = new Gson();

        return gson.fromJson(bookmarkListJson, new TypeToken<List<BookmarkLocalEntity>>(){}.getType());
    }

    private boolean saveBookmarkList(BookmarkLocalEntity bookmarkLocalEntity) {
        Gson gson = new Gson();
        List<BookmarkLocalEntity> list = getBookmarkList();
        list.add(bookmarkLocalEntity);
        String json = gson.toJson(list);
        SharedPreferences.Editor editor = sharedPreferences.edit().putString(BOOKMARK_KEY, json);
        editor.apply();

        return checkSuccessfulUpdate(list);
    }

    private boolean checkSuccessfulUpdate(List<BookmarkLocalEntity> list) {
        if(list.size() == getBookmarkList().size()) {
            return true;
        } else {
            return false;
        }
    }
}
