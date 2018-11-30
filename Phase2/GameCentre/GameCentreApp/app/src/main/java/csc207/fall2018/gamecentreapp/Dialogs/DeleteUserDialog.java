package csc207.fall2018.gamecentreapp.Dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;

import csc207.fall2018.gamecentreapp.GameCentreActivity.Session;
import csc207.fall2018.gamecentreapp.R;

public class DeleteUserDialog extends AppCompatDialogFragment {

    /**
     * the session.
     */
    private Session session;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.delete_user_dialog, null);
        session = Session.getInstance(getContext());

        builder.setView(view).setTitle("Delete User Info")
                .setNegativeButton("Cancel", (dialog, which) -> {
                })
                .setPositiveButton("Yes", (dialog, which) -> session.deleteAllInfoBelongCurrentUser(view));
        return builder.create();
    }
}
