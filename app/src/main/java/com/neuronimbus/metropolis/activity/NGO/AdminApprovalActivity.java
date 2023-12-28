package com.neuronimbus.metropolis.activity.NGO;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.AuthModels.CommonAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.NgoPopupResponse;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.activity.Home.HomeActivity;
import com.neuronimbus.metropolis.databinding.ActivityAdminApprovalBinding;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class AdminApprovalActivity extends AppCompatActivity {
    Context context;
    SPManager spManager;
    ActivityAdminApprovalBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        binding = ActivityAdminApprovalBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getController();
    }

    private void getController() {
        context = AdminApprovalActivity.this;
        spManager = new SPManager(context);
        ngoPopupMessage();

    }

    private void ngoPopupMessage(){
        CommonAuthModel model = new CommonAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().ngoPopupMessage(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<NgoPopupResponse>() {
                    @Override
                    public void onNext(NgoPopupResponse ngoPopupResponse) {
                        String msg = ngoPopupResponse.getMsg();

                        if (msg.equals("success")){
                            String popupMsg = ngoPopupResponse.getPopup_msg();
                            pendingPopup(popupMsg);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
    public void pendingPopup(String popupMsg) {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.admin_approval_popup);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.logout_popup));
        RelativeLayout exploreTstaLay = dialog.findViewById(R.id.exploreTstaLay);
        TextView approvalMsg = dialog.findViewById(R.id.approvalMsg);
        approvalMsg.setText(Html.fromHtml(popupMsg));

        exploreTstaLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, HomeActivity.class);
                spManager.setTstaguestLoginStatus("true");
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
                finish();

            }
        });

        dialog.show();

    }
}