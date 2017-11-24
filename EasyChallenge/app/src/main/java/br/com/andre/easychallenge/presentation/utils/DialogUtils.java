package br.com.andre.easychallenge.presentation.utils;

import android.app.Activity;
import android.support.annotation.StringRes;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import br.com.andre.easychallenge.R;

/**
 * Created by andre on 23/11/17.
 */

public class DialogUtils {

    Activity activity;
    EditText editTextDialog;
    OnDialogClicked listener;

    public DialogUtils(Activity activity) {
        this.activity = activity;
        if(activity instanceof OnDialogClicked) {
            listener = (OnDialogClicked) activity;
        }
    }

    public void createBookmarkDialog(@StringRes int title, @StringRes int positiveButton, @StringRes int negativeButton) {
        LayoutInflater layoutInflater= activity.getLayoutInflater();
        final View view = layoutInflater.inflate(R.layout.component_edit_text_dialog, null);
        AlertDialog alertDialog = new AlertDialog.Builder(activity).create();
        alertDialog.setTitle(activity.getString(title));

        alertDialog.setCancelable(false);

        editTextDialog = (EditText) view.findViewById(R.id.component_edit_text_bookmark_description);

        if(positiveButton > 0 && listener != null)
        alertDialog.setButton(AlertDialog.BUTTON_POSITIVE,activity.getString(positiveButton), (dialog, which) -> {
            listener.onDialogButtonPositiveClicked(editTextDialog.getText().toString());
            dialog.dismiss();
        });

        if(negativeButton > 0 && listener != null) {
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, activity.getString(negativeButton), (dialog, which) -> {
                listener.onDialogButtonNegativeClicked();
                dialog.dismiss();
            });
        }

        alertDialog.setView(view);
        alertDialog.show();
    }

    public interface OnDialogClicked {
        void onDialogButtonPositiveClicked(String dialogResult);
        void onDialogButtonNegativeClicked();
    }

}
