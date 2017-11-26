package br.com.andre.easychallenge.presentation.bookmarks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;

import br.com.andre.easychallenge.R;
import butterknife.BindView;
import butterknife.ButterKnife;

public class BookmarksActivity extends AppCompatActivity implements BookmarksView{

    @BindView(R.id.activity_bookmarks_toolbar)
    Toolbar toolbar;

    @BindView(R.id.activity_bookmarks_progress_indicator)
    FrameLayout progressbarIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookmarks);
        ButterKnife.bind(this);
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
}
