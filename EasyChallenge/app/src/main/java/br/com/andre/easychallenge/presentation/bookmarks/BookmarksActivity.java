package br.com.andre.easychallenge.presentation.bookmarks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import java.util.List;

import br.com.andre.easychallenge.R;
import br.com.andre.easychallenge.data.bookmarks.repository.BookmarkLocalDataSourceImp;
import br.com.andre.easychallenge.data.bookmarks.repository.BookmarkRemoteDataSourceImp;
import br.com.andre.easychallenge.data.bookmarks.repository.BookmarksRepositoryImp;
import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarksActivity extends AppCompatActivity implements BookmarksView{

    @BindView(R.id.activity_bookmarks_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_bookmarks_progress_indicator)
    FrameLayout progressbarIndicator;

    @BindView(R.id.activity_bookmarks_recycler_view)
    RecyclerView recyclerView;


    @BindView(R.id.activity_bookmarks_error_message)
    TextView errorMessage;

    BookmarkPresenterContract presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        ButterKnife.bind(this);
        presenter = new BookmarksPresenter(this, new BookmarksRepositoryImp(new BookmarkLocalDataSourceImp(this),
                new BookmarkRemoteDataSourceImp()));
        presenter.start();
    }

    @Override
    public void showLoadingOverlay() {
        progressbarIndicator.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoadingOverlay() {
        progressbarIndicator.setVisibility(View.GONE);
    }

    @Override
    public void setToolbar() {
        setSupportActionBar(toolbar);
        toolbar.setTitle(R.string.title_activity_bookmarks);
    }

    @Override
    public void showBookmarkList(List<Bookmark> bookmarks) {
        BookmarksAdapter bookmarksAdapter = new BookmarksAdapter(bookmarks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bookmarksAdapter);
    }

    @Override
    public void showMessageError(int message) {
        errorMessage.setText(message);
        errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMessageError() {
        errorMessage.setVisibility(View.GONE);
    }
}
