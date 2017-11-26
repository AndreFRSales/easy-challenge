package br.com.andre.easychallenge.presentation.bookmarks;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

import br.com.andre.easychallenge.R;
import br.com.andre.easychallenge.data.bookmarks.repository.BookmarksRepository;
import br.com.andre.easychallenge.data.bookmarks.repository.BookmarksRepositoryImp;
import br.com.andre.easychallenge.domain.bookmarks.usecase.GetBookmarksUsecase;
import io.reactivex.disposables.Disposable;

/**
 * Created by andre on 15/11/17.
 */

public class BookmarksPresenter implements BookmarkPresenterContract {

    BookmarksView view;
    List<Disposable> disposables;
    GetBookmarksUsecase getBookmarksUsecase;

    public BookmarksPresenter(BookmarksView view, BookmarksRepository repository) {
        this.view = view;
        getBookmarksUsecase = new GetBookmarksUsecase(repository);
        disposables = new ArrayList<>();
    }

    @Override
    public void start() {
        view.setToolbar();
        fetchBookmarks();
    }

    @Override
    public void destroy() {
        for (Disposable disposable :
                disposables) {
            if (!disposable.isDisposed()) {
                disposable.dispose();
            }
        }

    }

    @Override
    public void saveState(Bundle outState) {

    }

    @Override
    public void restoreState(Bundle savedInstanceState) {

    }

    public void fetchBookmarks() {
        view.hideMessageError();
        view.showLoadingOverlay();
        disposables.add(getBookmarksUsecase.execute(null)
        .subscribe(
                bookmarks -> {
                    view.showBookmarkList(bookmarks);
                    view.hideLoadingOverlay();
                }, throwable -> {
                    view.hideLoadingOverlay();
                    view.showMessageError(R.string.bookmarks_error_fetching_bookmarks);
                }
        ));
    }
}
