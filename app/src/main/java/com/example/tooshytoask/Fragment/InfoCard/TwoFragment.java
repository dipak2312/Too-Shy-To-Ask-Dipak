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

import com.bumptech.glide.Glide;
import com.example.tooshytoask.Adapters.ProfileAdapter;
import com.example.tooshytoask.Helper.SPManager;
import com.example.tooshytoask.Models.ProfileItems;
import com.example.tooshytoask.R;
import com.example.tooshytoask.Utils.ClickListener;
import com.example.tooshytoask.Utils.ImagePickUtilsCamera;
import com.example.tooshytoask.Utils.ImagePickUtilsFile;
import com.example.tooshytoask.Utils.OnClickListner;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class TwoFragment extends Fragment implements View.OnClickListener, OnClickListner {
    Context context;
    SPManager spManager;
    ClickListener clickListener;
    TextView skip_btn2;
    ImageButton next_btn2;
    RecyclerView profile_recy;
    ImageView camera, file;
    String profile_pic;
    CircleImageView avatar1,avatar2,avatar3,avatar4,avatar5,avatar6,avatar7,avatar8;
    ArrayList<ProfileItems>profileItems;
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

        profileItems  = new ArrayList<>();

        profileItems.add(new ProfileItems(R.drawable.avatar1, false));
        profileItems.add(new ProfileItems(R.drawable.avatar2, false));
        profileItems.add(new ProfileItems(R.drawable.avatar3, false));
        profileItems.add(new ProfileItems(R.drawable.avatar4, false));
        profileItems.add(new ProfileItems(R.drawable.avatar5, false));
        profileItems.add(new ProfileItems(R.drawable.avatar6, false));
        profileItems.add(new ProfileItems(R.drawable.avatar7, false));
        profileItems.add(new ProfileItems(R.drawable.avatar8, false));

        profile_recy.setAdapter(new ProfileAdapter(profileItems, this, context));

        checkPermissions();
        return view;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == skip_btn2.getId()){
            clickListener.onClick(true);
        } else if (id == avatar1.getId()){
            next_btn2.setBackgroundResource(R.drawable.circle_button_active);
            avatar1.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar2.getId()){
            next_btn2.setBackgroundResource(R.drawable.circle_button_active);
            avatar2.setBackgroundResource(R.drawable.circle_active_background);
            avatar1.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar3.getId()){
            next_btn2.setBackgroundResource(R.drawable.circle_button_active);
            avatar3.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar1.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar4.getId()){
            next_btn2.setBackgroundResource(R.drawable.circle_button_active);
            avatar4.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar1.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar5.getId()){
            next_btn2.setBackgroundResource(R.drawable.circle_button_active);
            avatar5.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar1.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar6.getId()){
            next_btn2.setBackgroundResource(R.drawable.circle_button_active);
            avatar6.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar1.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar7.getId()){
            next_btn2.setBackgroundResource(R.drawable.circle_button_active);
            avatar7.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar1.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar8.setBackgroundResource(R.drawable.circle_inactive_background);
        }
        else if (id == avatar8.getId()){
            next_btn2.setBackgroundResource(R.drawable.circle_button_active);
            avatar8.setBackgroundResource(R.drawable.circle_active_background);
            avatar2.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar3.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar4.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar5.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar6.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar7.setBackgroundResource(R.drawable.circle_inactive_background);
            avatar1.setBackgroundResource(R.drawable.circle_inactive_background);
        }


        ArrayList<Boolean> myvalue=new ArrayList<Boolean>();

        for(int i=0;i<profileItems.size();i++)
        {
            myvalue.add(profileItems.get(i).getSelected());
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

        for(int i=0;i<profileItems.size();i++)
        {
            myvalue.add(profileItems.get(i).getSelected());
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