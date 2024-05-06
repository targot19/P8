package com.example.javacalenderproject.functionlayer;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import com.example.javacalenderproject.R;

public class HelpPopup {

    public static void showHelpPopup(Context context) {
        // Create a new AlertDialog Builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);

        // Inflate the layout for the pop-up window
        View view = LayoutInflater.from(context).inflate(R.layout.help_popup, null);

        // Set the layout view to the AlertDialog
        builder.setView(view);

        // Create and show the AlertDialog
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        alertDialog.getWindow().setLayout(1200,700);
    }
}
