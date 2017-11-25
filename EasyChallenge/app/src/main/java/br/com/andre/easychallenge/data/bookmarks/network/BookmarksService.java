package br.com.andre.easychallenge.data.bookmarks.network;


import br.com.andre.easychallenge.data.bookmarks.models.BookmarkResponseList;
import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by andre on 25/11/17.
 */

public interface BookmarksService {

    @GET("v2/59dbaacc110000590007481d")
    Observable<BookmarkResponseList> getBookmarksList();

}
