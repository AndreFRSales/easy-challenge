package br.com.andre.easychallenge.data.bookmarks.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by andre on 25/11/17.
 */

public class BookmarkResponseList {

    @SerializedName("favorites")
    private List<BookmarkResponse> bookmarkEntityList;

    public List<BookmarkResponse> getBookmarkEntityList() {
        return bookmarkEntityList;
    }
}
