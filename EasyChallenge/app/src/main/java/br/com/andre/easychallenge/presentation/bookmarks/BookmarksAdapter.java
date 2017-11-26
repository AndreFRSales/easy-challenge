package br.com.andre.easychallenge.presentation.bookmarks;

import android.content.Context;
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

    public BookmarksAdapter(List<Bookmark> bookmarks) {
        this.bookmarks = bookmarks;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookmarks_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(bookmarks.get(position));
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

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        public void bind(Bookmark bookmark) {
            description.setText(bookmark.getDescription());
//            deleteIcon.setOnClickListener();
        }
    }
}
