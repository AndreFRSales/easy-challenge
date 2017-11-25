package br.com.andre.easychallenge.data.bookmarks.repository;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import br.com.andre.easychallenge.data.bookmarks.models.BookmarkEntity;
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
    public Observable<Void> addBookmark(BookmarkEntity bookmarkEntity) {
        return Observable.create(emitter -> {
            boolean isSaved = saveBookmarkList(bookmarkEntity);
            if(isSaved) {
                emitter.onComplete();
            } else {
                emitter.onError(new Exception());
            }
        });
    }

    private List<BookmarkEntity> getBookmarkList() {
        List<BookmarkEntity> bookmarkEntityList = parseSharedPreferencesIntoBookmarkList();
        if(bookmarkEntityList == null || bookmarkEntityList.size() == 0) {
            return new ArrayList<>();
        } else {
            return bookmarkEntityList;
        }
    }

    private List<BookmarkEntity> parseSharedPreferencesIntoBookmarkList() {
        String bookmarkListJson = sharedPreferences.getString(BOOKMARK_KEY, "");
        Gson gson = new Gson();

        return gson.fromJson(bookmarkListJson, new TypeToken<List<BookmarkEntity>>(){}.getType());
    }

    private boolean saveBookmarkList(BookmarkEntity bookmarkEntity) {
        Gson gson = new Gson();
        List<BookmarkEntity> list = getBookmarkList();
        list.add(bookmarkEntity);
        String json = gson.toJson(list);
        SharedPreferences.Editor editor = sharedPreferences.edit().putString(BOOKMARK_KEY, json);
        editor.apply();

        return checkSuccessfulUpdate(list);
    }

    private boolean checkSuccessfulUpdate(List<BookmarkEntity> list) {
        if(list.size() == getBookmarkList().size()) {
            return true;
        } else {
            return false;
        }
    }
}
