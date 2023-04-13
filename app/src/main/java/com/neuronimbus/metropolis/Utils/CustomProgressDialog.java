package com.neuronimbus.metropolis.Utils;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.neuronimbus.metropolis.R;

public class CustomProgressDialog extends Dialog {

    Context mContext;
    TextView tvLoadingText;
    com.neuronimbus.metropolis.Utils.CustomProgressDialog dialog;
    MaterialProgressBar progress1;


    public CustomProgressDialog(Context context) {
        super(context);
        this.mContext = context;
    }

    public CustomProgressDialog(Context context, int theme) {
        super(context, theme);
    }

    public com.neuronimbus.metropolis.Utils.CustomProgressDialog show(CharSequence message) {

        dialog = new CustomProgressDialog(mContext, R.style.ProgressDialog);
        dialog.setContentView(R.layout.view_material_progress);

        //progress1 = (ProgressBar) dialog.findViewById (R.id.progress1);
        progress1 = (MaterialProgressBar) dialog.findViewById(R.id.progress1);
        tvLoadingText = (TextView) dialog.findViewById(R.id.tv_loading_Text);
        tvLoadingText.setText(message);

        progress1.setColorSchemeResources(R.color.purple_200, R.color.purple_500, R.color.purple_700, R.color.purple_200);


        progress1.setVisibility(View.VISIBLE);
        //    animationDrawable.start();
        //   mProgressBar.setVisibility(View.GONE);
        // animationDrawable.stop();

        ///progress1.setColorSchemeResources(R.color.red, R.color.green, R.color.blue, R.color.orange);
        dialog.setCancelable(false);
        dialog.setTitle("Loading...");
        if (dialog != null) {
            dialog.show();
        }
        return dialog;
    }

    public com.neuronimbus.metropolis.Utils.CustomProgressDialog dismiss(CharSequence message) {
        if (dialog != null) {
            dialog.dismiss();
        }

        return dialog;

    }

}


