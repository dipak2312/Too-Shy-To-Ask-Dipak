package com.neuronimbus.metropolis.activity.Expert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.Utils.AudioRecorder;
import com.neuronimbus.metropolis.adapters.ChattingAdapter;
import com.neuronimbus.metropolis.AuthModels.AskQuestionsAuthModel;
import com.neuronimbus.metropolis.AuthModels.ExpertReplyAuthModel;
import com.neuronimbus.metropolis.Helper.SPManager;
import com.neuronimbus.metropolis.Models.AskQuestionsResponse;
import com.neuronimbus.metropolis.Models.ExpertReplyResponse;
import com.neuronimbus.metropolis.Models.chats;
import com.neuronimbus.metropolis.R;
import com.neuronimbus.metropolis.Utils.CustomProgressDialog;
import com.neuronimbus.metropolis.databinding.ActivityExpertBinding;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class ExpertActivity extends AppCompatActivity implements View.OnClickListener {
    SPManager spManager;
    Context context;
    CustomProgressDialog dialog;
    RelativeLayout rel_back;
    LinearLayout send_msg;
    RecyclerView recy_user_msg;
    EditText ask_questions;
    ChattingAdapter adapter;
    ArrayList<chats> chats;
    ArrayList<chats> demochat;
    SwipeRefreshLayout swipe_refresh;
    String refresh_status = "open", chat_ref_status = "no", time = "";
    int chatSize, select_chat_size = 0;
    ActivityExpertBinding binding;
    private static final int REQUEST_PERMISSION_CODE = 100;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    Boolean isRecording = false;
    private int recordingTimeInSeconds = 0;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityExpertBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        swipe_refresh = findViewById(R.id.swipe_refresh);
        ask_questions = findViewById(R.id.ask_questions);
        rel_back = findViewById(R.id.rel_back);
        rel_back.setOnClickListener(this);
        send_msg = findViewById(R.id.send_msg);
        send_msg.setOnClickListener(this);

        getControl();
        onClicks();

    }

    private void onClicks() {
        binding.relVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.voiceRecordingRelLayout.setVisibility(View.VISIBLE);
                binding.relBottomTab.setVisibility(View.GONE);
                startRecording();

            }
        });
        binding.deleteVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.voiceRecordingRelLayout.setVisibility(View.GONE);
                binding.relBottomTab.setVisibility(View.VISIBLE);
                deleteRecording();
            }
        });
        binding.recordingPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseRecording();
                binding.recordingPlay.setVisibility(View.GONE);
                binding.recordingPause.setVisibility(View.VISIBLE);

            }
        });
        binding.recordingPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playRecording();
                binding.recordingPause.setVisibility(View.GONE);
                binding.recordingPlay.setVisibility(View.VISIBLE);

            }
        });
        binding.pauseResumeVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRecording){
                    binding.recordingPause.setVisibility(View.VISIBLE);
                    stopRecording();
                }
                else {
                    binding.recordingPause.setVisibility(View.GONE);
                    startRecording();
                }


            }
        });
    }

    private void getControl() {
        context = ExpertActivity.this;
        spManager = new SPManager(context);
        dialog = new CustomProgressDialog(context);


        chats = new ArrayList<>();
        demochat = new ArrayList<>();

        recy_user_msg = findViewById(R.id.recy_user_msg);
        LinearLayoutManager lm = new LinearLayoutManager(context);
        lm.setOrientation(RecyclerView.VERTICAL);
        recy_user_msg.setLayoutManager(lm);
        recy_user_msg.getLayoutManager().scrollToPosition(chats.size() - 1);

        if (isMicroPhonePresent()) {
            checkPermission();
        }

        swipe_refresh.setColorSchemeResources(R.color.purple);
        swipe_refresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getExpertReply("swipe");
            }
        });
    }

    private boolean isMicroPhonePresent() {
        if (this.getPackageManager().hasSystemFeature(PackageManager.FEATURE_MICROPHONE)) {
            return true;
        } else {
            return false;
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_DENIED) {

            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO, Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_PERMISSION_CODE);

        }
    }

    private void pauseRecording() {

        mediaPlayer.pause();

        Toast.makeText(context, "Recording Pause", Toast.LENGTH_SHORT).show();

    }

    private void playRecording() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getRecordingFilePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            handler.postDelayed(timerRunnable, 1000);
            Toast.makeText(context, "Recording Play", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void startRecording() {
        try {
            isRecording = true;
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(getRecordingFilePath());
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
            mediaRecorder.start();
            handler.postDelayed(timerRunnable, 1000);

            Toast.makeText(context, "Recording Started", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public Runnable timerRunnable = new Runnable() {
        @Override
        public void run() {
            recordingTimeInSeconds++;
            int minutes = recordingTimeInSeconds / 60;
            int seconds = recordingTimeInSeconds % 60;
            time = String.format("%02d:%02d", minutes, seconds);
            binding.recordingTime.setText(time);
            binding.recordingTime.setText(time);
            handler.postDelayed(this, 1000); // Update every 1 second
        }
    };

    private void stopRecording() {
        if (mediaRecorder != null){
            isRecording = false;
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            recordingTimeInSeconds = 0;
            handler.removeCallbacks(timerRunnable);
            Toast.makeText(context, "Recording Stop", Toast.LENGTH_SHORT).show();
        }

    }
    private void deleteRecording() {
        File file = new File(getRecordingFilePath());
        if (file.exists()) {
            if (file.delete()) {
                recordingTimeInSeconds = 0;
                Toast.makeText(this, "Recording deleted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Failed to delete recording", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "No recording to delete", Toast.LENGTH_SHORT).show();
        }
    }

    private String getRecordingFilePath() {
        ContextWrapper contextWrapper = new ContextWrapper(context);
        File musicDirectory = contextWrapper.getExternalFilesDir(Environment.DIRECTORY_MUSIC);
        File file = new File(musicDirectory, "Test Recording File" + ".mp3");
        return file.getPath();

    }

    @Override
    protected void onResume() {
        super.onResume();
        refresh_status = "open";
        getExpertReply("w_swipe");
        chat_ref_status = "no";
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        refresh_status = "close";
    }

    @Override
    protected void onPause() {
        super.onPause();
        refresh_status = "close";
        //Toast.makeText(context,"onPause",Toast.LENGTH_SHORT).show();
    }


    public void getExpertReply(String refresh) {

        ExpertReplyAuthModel model = new ExpertReplyAuthModel();
        model.setUser_id(spManager.getUserId());

        if (refresh.equals("w_swipe")) {
            dialog.show("");
        }

        WebServiceModel.getRestApi().getExpertReply(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ExpertReplyResponse>() {
                    @Override
                    public void onNext(ExpertReplyResponse expertReplyResponse) {
                        if (refresh.equals("w_swipe")) {
                            dialog.dismiss("");
                        } else {
                            swipe_refresh.setRefreshing(false);
                        }

                        String msg = expertReplyResponse.getMsg();


                        if (msg.equals("success")) {
                            chats = expertReplyResponse.getChats();

                            if (chats.size() != 0) {
                                swipe_refresh.setVisibility(View.VISIBLE);
                                adapter = new ChattingAdapter(context, chats);
                                recy_user_msg.setAdapter(adapter);
                                recy_user_msg.getLayoutManager().smoothScrollToPosition(recy_user_msg, null, adapter.getItemCount() - 1);
                            }
                            if (chats.size() == 0) {
                                recy_user_msg.setVisibility(View.GONE);
                            }

                            if (chatSize != select_chat_size) {
                                select_chat_size = chatSize;
                                chats = demochat;


                                if (chat_ref_status.equals("no")) {
                                    adapter = new ChattingAdapter(context, chats);
                                    recy_user_msg.setAdapter(adapter);
                                    recy_user_msg.getLayoutManager().smoothScrollToPosition(recy_user_msg, null, adapter.getItemCount() - 1);
                                } else {
                                    chat_ref_status = "no";
                                }
                            }
                        } else {

                            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();

                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, "Please Check Your Network..Unable to Connect Server!!", Toast.LENGTH_SHORT).show();
                        if (refresh.equals("w_swipe")) {
                            dialog.dismiss("");
                        } else {
                            swipe_refresh.setRefreshing(false);
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public void getuserReply() {

        ExpertReplyAuthModel model = new ExpertReplyAuthModel();
        model.setUser_id(spManager.getUserId());

        WebServiceModel.getRestApi().getExpertReply(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<ExpertReplyResponse>() {
                    @Override
                    public void onNext(ExpertReplyResponse expertReplyResponse) {
                        String msg = expertReplyResponse.getMsg();

                        if (msg.equals("success")) {
                            chats = expertReplyResponse.getChats();

                            adapter = new ChattingAdapter(context, chats);
                            recy_user_msg.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                            recy_user_msg.getLayoutManager().smoothScrollToPosition(recy_user_msg, null, adapter.getItemCount() - 1);

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

    public void getAskQuestions(String message) {

        AskQuestionsAuthModel model = new AskQuestionsAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setReply(message);
        model.setQuestionType("text");
        model.setLanguage("en");

        WebServiceModel.getRestApi().getAskQuestions(model)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<AskQuestionsResponse>() {
                    @Override
                    public void onNext(AskQuestionsResponse askQuestionsResponse) {
                        String msg = askQuestionsResponse.getMsg();
                        if (msg.equals("success")) {
                            getuserReply();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void sendMessage() {

        /*if (ask_questions.getText().toString().trim().equals("")) {
            Toast.makeText(context, "", Toast.LENGTH_SHORT).show();
        }*/
        if (!ask_questions.getText().toString().trim().equals("")) {
            String message = ask_questions.getText().toString().trim();

            if (adapter != null) {
                adapter.notifyDataSetChanged();
                recy_user_msg.getLayoutManager().smoothScrollToPosition(recy_user_msg, null, adapter.getItemCount() - 1);
                getAskQuestions(message);
                chat_ref_status = "yes";
                ask_questions.setText("");
            } else if (adapter == null) {

                getAskQuestions(message);
                chat_ref_status = "yes";
                ask_questions.setText("");
            } else {
                chat_ref_status = "no";
                getExpertReply("w_swipe");
            }
        }
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if (id == rel_back.getId()) {
            finish();
        } else if (id == send_msg.getId()) {
            sendMessage();
        }


    }
}