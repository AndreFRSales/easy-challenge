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
import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
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
            boolean isSaved = saveBookmark(bookmarkLocalEntity);
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

    @Override
    public Observable<Void> delete(BookmarkLocalEntity bookmarkLocalEntity) {
        return Observable.create(emitter -> {
            List<BookmarkLocalEntity> bookmarks = getBookmarkList();
            if(removeBookmarkFromList(bookmarks, bookmarkLocalEntity)) {
                deleteAllList();
                saveBookmarkList(bookmarks);
                emitter.onComplete();
            } else {
                emitter.onError(new Exception());
            }
        });
    }

    public boolean removeBookmarkFromList(List<BookmarkLocalEntity> list, BookmarkLocalEntity bookmarkRemoved) {
        boolean removed = false;
        for(int counter = 0; counter < list.size(); counter++) {
            if(list.get(counter).getDescription().equals(bookmarkRemoved.getDescription())) {
                list.remove(counter);
                removed = true;
            }
        }

        return removed;
    }

    private void saveBookmarkList(List<BookmarkLocalEntity> bookmarks) {
        saveJson(bookmarks);
    }

    private void saveJson(List<BookmarkLocalEntity> bookmarks) {
        Gson gson = new Gson();
        String json = gson.toJson(bookmarks);
        SharedPreferences.Editor editor = sharedPreferences.edit().putString(BOOKMARK_KEY, json);
        editor.apply();
    }

    private void deleteAllList() {
        SharedPreferences.Editor editor = sharedPreferences.edit().remove(BOOKMARK_KEY);
        editor.apply();
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

    private boolean saveBookmark(BookmarkLocalEntity bookmarkLocalEntity) {
        List<BookmarkLocalEntity> list = getBookmarkList();
        list.add(bookmarkLocalEntity);
        saveJson(list);
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
