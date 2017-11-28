package br.com.andre.easychallenge.presentation.bookmarks;

import android.content.Intent;
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
import br.com.andre.easychallenge.presentation.maps.MapsActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarksActivity extends AppCompatActivity implements BookmarksView, BookmarksAdapter.OnBookmarkListener {

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
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.start();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        presenter.saveState(outState);

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        presenter.restoreState(savedInstanceState);
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
        BookmarksAdapter bookmarksAdapter = new BookmarksAdapter(bookmarks, this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(bookmarksAdapter);
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void showMessageError(int message) {
        recyclerView.setVisibility(View.GONE);
        errorMessage.setText(message);
        errorMessage.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMessageError() {
        recyclerView.setVisibility(View.VISIBLE);
        errorMessage.setVisibility(View.GONE);
    }

    @Override
    public void onBookmarkClicked(Bookmark bookmark) {
        Intent intent = new Intent(this, MapsActivity.class);
        Bundle bundle = MapsActivity.newInstance(bookmark);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void onDeleteBookmarkClicked(Bookmark bookmark) {
        presenter.deleteBookmark(bookmark);
    }
}