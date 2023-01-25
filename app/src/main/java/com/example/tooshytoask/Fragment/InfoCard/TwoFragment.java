package com.example.tooshytoask.Fragment.InfoCard;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Bundle;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tooshytoask.API.WebServiceModel;
import com.example.tooshytoask.Adapters.CategoryAdapter;
import com.example.tooshytoask.Adapters.ProfileAdapter;
import com.example.tooshytoask.AuthModels.HealthCateModel;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.AvatarResponse;
import com.example.tooshytoask.Models.HealthCateResponse;
import com.example.tooshytoask.Models.avatarList;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Utils.ImagePickUtilsCamera;
import com.example.tooshytoask.Utils.ImagePickUtilsFile;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class TwoFragment extends Fragment implements View.OnClickListener, OnClickListner {
    Context context;
    SPManager spManager;
    ClickListener clickListener;
    TextView skip_btn2;
    ImageButton next_btn2;
    RecyclerView profile_recy;
    ImageView camera, file;
    ProfileAdapter adapter;
    String profile_pic;
    OnClickListner onclicklistener;
    CircleImageView avatar1,avatar2,avatar3,avatar4,avatar5,avatar6,avatar7,avatar8;
    ArrayList<AvatarResponse>profileItems;
    ArrayList<avatarList>avatarLists;
    private static final int TAKE_PICTURE = 1;
    public static final int SELECT_FILE = 2754;
    String[] permissions = new String[]{

            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.CAMERA,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_two, container, false);

        getActivity().getWindow().setStatusBarColor(getResources().getColor(R.color.white));
        context = getActivity();
        spManager = new SPManager(context);
        camera = view.findViewById(R.id.camera);
        camera.setOnClickListener(this);
        file = view.findViewById(R.id.file);
        file.setOnClickListener(this);
        skip_btn2 = view.findViewById(R.id.skip_btn2);
        skip_btn2.setOnClickListener(this);
        next_btn2 = view.findViewById(R.id.next_btn2);
        next_btn2.setOnClickListener(this);
        avatar1 = view.findViewById(R.id.avatar1);
        avatar1.setOnClickListener(this);
        avatar2 = view.findViewById(R.id.avatar2);
        avatar2.setOnClickListener(this);
        avatar3 = view.findViewById(R.id.avatar3);
        avatar3.setOnClickListener(this);
        avatar4 = view.findViewById(R.id.avatar4);
        avatar4.setOnClickListener(this);
        avatar5 = view.findViewById(R.id.avatar5);
        avatar5.setOnClickListener(this);
        avatar6 = view.findViewById(R.id.avatar6);
        avatar6.setOnClickListener(this);
        avatar7 = view.findViewById(R.id.avatar7);
        avatar7.setOnClickListener(this);
        avatar8 = view.findViewById(R.id.avatar8);
        avatar8.setOnClickListener(this);
        clickListener=(ClickListener)context;
        clickListener.onClick(false);

        profile_recy = view.findViewById(R.id.profile_recy);

        profile_recy.setLayoutManager(new GridLayoutManager(context,4, GridLayoutManager.VERTICAL, false));

       /* profileItems  = new ArrayList<>();

        profileItems.add(new AvatarResponse(R.drawable.avatar1, false));
        profileItems.add(new AvatarResponse(R.drawable.avatar2, false));
        profileItems.add(new AvatarResponse(R.drawable.avatar3, false));
        profileItems.add(new AvatarResponse(R.drawable.avatar4, false));
        profileItems.add(new AvatarResponse(R.drawable.avatar5, false));
        profileItems.add(new AvatarResponse(R.drawable.avatar6, false));
        profileItems.add(new AvatarResponse(R.drawable.avatar7, false));
        profileItems.add(new AvatarResponse(R.drawable.avatar8, false));

        profile_recy.setAdapter(new ProfileAdapter(profileItems, this, context));*/

        checkPermissions();
        healthcategory();
        return view;
    }

    public void healthcategory() {

        HealthCateModel model = new HealthCateModel();
        model.setUser_id(spManager.getUserId());
        WebServiceModel.getRestApi().healthcategory(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AvatarResponse>() {
                    @Override
                    public void onNext(AvatarResponse avatarResponse) {

                        String msg = avatarResponse.getMsg();

                        if (msg.equals("success")) {

                            avatarLists = avatarResponse.getAvatarLists();

                            adapter = new ProfileAdapter(avatarLists,onclicklistener,context);
                            profile_recy.setAdapter(adapter);

                        } else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }

                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == skip_btn2.getId()){
            clickListener.onClick(true);
        }

        ArrayList<Boolean> myvalue=new ArrayList<Boolean>();

        for(int i=0;i<avatarLists.size();i++)
        {
            myvalue.add(avatarLists.get(i).getSelected());
        }
        boolean ans = myvalue.contains(true);

            if(ans)
            {
                clickListener.onClick(true);


            }else
            {
                clickListener.onClick(false);
            }


        if (id == camera.getId()) {
            boolean status=checkPermissions();
            if(status)
            {
                ImagePickUtilsCamera.selectImage(context);
                camera.isClickable();
                next_btn2.setBackgroundResource(R.drawable.circle_button_active);
            }
            else
            {
                checkPermissions();
            }
        } else if (id == file.getId()) {
            boolean status=checkPermissions();
            if(status)
            {
                ImagePickUtilsFile.selectImage(context);

                next_btn2.setBackgroundResource(R.drawable.circle_button_active);
            }
            else
            {
                checkPermissions();
            }
        }


    }

    private boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(getActivity(), p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(getActivity(), listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }

    @Override
    public void onClickData(int position, int id) {
        ArrayList<Boolean> myvalue=new ArrayList<Boolean>();

        for(int i=0;i<avatarLists.size();i++)
        {
            myvalue.add(avatarLists.get(i).getSelected());
        }

        boolean ans = myvalue.contains(true);

        if(ans)
        {
            next_btn2.setBackgroundResource(R.drawable.circle_button_active);


        }else
        {
            next_btn2.setBackgroundResource(R.drawable.circle_button_inactive);
        }

    }

}