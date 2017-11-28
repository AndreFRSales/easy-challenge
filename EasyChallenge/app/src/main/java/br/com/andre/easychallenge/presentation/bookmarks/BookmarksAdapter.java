package br.com.andre.easychallenge.presentation.bookmarks;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.andre.easychallenge.R;
import br.com.andre.easychallenge.domain.bookmarks.models.Bookmark;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by andre on 26/11/17.
 */

public class BookmarksAdapter extends RecyclerView.Adapter<BookmarksAdapter.ViewHolder> {

    List<Bookmark> bookmarks;
    OnBookmarkListener clickBookmarkListener;

    public BookmarksAdapter(List<Bookmark> bookmarks, OnBookmarkListener listener) {
        this.bookmarks = bookmarks;
        this.clickBookmarkListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmarks_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(bookmarks.get(position), clickBookmarkListener);
    }

    @Override
    public int getItemCount() {
        return bookmarks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.component_bookmark_item_description)
        TextView description;

        @BindView(R.id.component_bookmark_item_delete)
        ImageView deleteIcon;

        View itemView;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            this.itemView = itemView;
        }

        public void bind(Bookmark bookmark, OnBookmarkListener listener) {
            description.setText(bookmark.getDescription());
            this.itemView.setOnClickListener(view -> {
                if(listener != null) {
                    listener.onBookmarkClicked(bookmark);
                }
            });
            deleteIcon.setOnClickListener(view -> {
                if(listener != null) {
                    listener.onDeleteBookmarkClicked(bookmark);
                }
            });
        }
    }

    public interface OnBookmarkListener {
        void onBookmarkClicked(Bookmark bookmark);
        void onDeleteBookmarkClicked(Bookmark bookmark);
    }
}
