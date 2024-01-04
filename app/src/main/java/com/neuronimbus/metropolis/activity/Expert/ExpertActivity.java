package com.neuronimbus.metropolis.activity.Expert;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.SystemClock;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.neuronimbus.metropolis.API.WebServiceModel;
import com.neuronimbus.metropolis.Fragment.SettingsFragment;
import com.neuronimbus.metropolis.Utils.AudioRecorder;
import com.neuronimbus.metropolis.activity.Home.HomeActivity;
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
import java.io.FileInputStream;
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
    String refresh_status = "open", chat_ref_status = "no", time = "", selectRecordingLanguage = "";
    int chatSize, select_chat_size = 0;
    ActivityExpertBinding binding;
    private static final int REQUEST_PERMISSION_CODE = 100;
    private MediaRecorder mediaRecorder;
    private MediaPlayer mediaPlayer;
    Boolean isRecording = false;
    Boolean isRecordingStop = false;
    Boolean recordingPopup = false;
    private int recordingTimeInSeconds = 0;
    private int dummyInSeconds = 0;
    private int playableSeconds = 0;
    private Handler handler = new Handler();
    //private String audioFilePath1, audioFilePath2, mergedAudioFilePath;



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
                if (selectRecordingLanguage != null && !selectRecordingLanguage.isEmpty()){
                    binding.voiceRecordingRelLayout.setVisibility(View.VISIBLE);
                    binding.relBottomTab.setVisibility(View.GONE);
                    startRecording();
                }
                else {
                    if (recordingPopup){
                        binding.voiceRecordingRelLayout.setVisibility(View.VISIBLE);
                        binding.relBottomTab.setVisibility(View.GONE);
                        startRecording();
                    }
                    else {
                        recordingLanguagePopup();
                    }
                }


            }
        });
        binding.deleteVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                binding.voiceRecordingRelLayout.setVisibility(View.GONE);
                binding.relBottomTab.setVisibility(View.VISIBLE);
                binding.recordingPlay.setVisibility(View.GONE);
                binding.recordingPause.setVisibility(View.GONE);
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
                stopRecording();
//                if (isRecording){
//                    binding.recordingPause.setVisibility(View.VISIBLE);
//                    stopRecording();
//                }
//                else {
//                    binding.recordingPause.setVisibility(View.GONE);
//                    mergeAudio();
//                }
            }
        });
        binding.sendVoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isRecordingStop){
                    convertAudioToBase64(getRecordingFilePath());
                }
                else {
                    stopRecordingSend();
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

        selectRecordingLanguage = spManager.getRecordingLang();

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
        handler.removeCallbacks(audioRunnable);
        //Toast.makeText(context, "Recording Pause", Toast.LENGTH_SHORT).show();

    }

    private void playRecording() {
        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(getRecordingFilePath());
            mediaPlayer.prepare();
            mediaPlayer.start();
            //playableSeconds = dummyInSeconds;
            handler.postDelayed(audioRunnable, 1000);
            //Toast.makeText(context, "Recording Play", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public Runnable audioRunnable = new Runnable() {
        @Override
        public void run() {
            if (dummyInSeconds != playableSeconds){
                dummyInSeconds++;
                int minutes = dummyInSeconds / 60;
                int seconds = dummyInSeconds % 60;
                time = String.format("%02d:%02d", minutes, seconds);
                handler.postDelayed(this, 1000); // Update every 1 second
                binding.recordingTime.setText(time);
            }
            else {
                pauseRecording();
                binding.recordingPlay.setVisibility(View.GONE);
                binding.recordingPause.setVisibility(View.VISIBLE);
            }

        }
    };

    private void startRecording() {
        try {
            isRecording = true;
            isRecordingStop = false;
//            audioFilePath1 = Environment.getExternalStorageDirectory().getAbsolutePath() + getRecordingFilePath();
//            audioFilePath2 = Environment.getExternalStorageDirectory().getAbsolutePath() + getRecordingFilePath();
            binding.pauseResumeVoice.setVisibility(View.VISIBLE);
            mediaRecorder = new MediaRecorder();
            mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
            mediaRecorder.setOutputFile(getRecordingFilePath());
            mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
            mediaRecorder.prepare();
            mediaRecorder.start();
            handler.postDelayed(timerRunnable, 1000);
            binding.recordingTime.setText(recordingTimeInSeconds);
            playableSeconds = 0;
            dummyInSeconds = 0;

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    stopRecording();
                }
            }, 180000);
            //Toast.makeText(context, "Recording Started", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void stopRecordingSend() {
        if (mediaRecorder != null){
            isRecording = false;
            isRecordingStop = true;
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            binding.pauseResumeVoice.setVisibility(View.GONE);
            binding.recordingPause.setVisibility(View.VISIBLE);
            playableSeconds = recordingTimeInSeconds;

            handler.removeCallbacks(timerRunnable);
            convertAudioToBase64(getRecordingFilePath());

            //Toast.makeText(context, "Recording Stop and Send", Toast.LENGTH_SHORT).show();
        }

    }
    private void stopRecording() {
        if (mediaRecorder != null){
            isRecording = false;
            isRecordingStop = true;
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            binding.pauseResumeVoice.setVisibility(View.GONE);
            binding.recordingPause.setVisibility(View.VISIBLE);
            playableSeconds = recordingTimeInSeconds;
            //dummyInSeconds = recordingTimeInSeconds;

            handler.removeCallbacks(timerRunnable);

            //Toast.makeText(context, "Recording Stop", Toast.LENGTH_SHORT).show();
        }

    }
    public String convertAudioToBase64(String filePath) {
        File audioFile = new File(filePath);

        try {
            // Step 1: Read the Audio File
            byte[] audioData = new byte[(int) audioFile.length()];
            FileInputStream fileInputStream = new FileInputStream(audioFile);
            fileInputStream.read(audioData);
            fileInputStream.close();

            // Step 2: Convert to Base64
            String base64Audio = Base64.encodeToString(audioData, Base64.DEFAULT);
            Log.d("dipakss", base64Audio);
            binding.voiceRecordingRelLayout.setVisibility(View.GONE);
            binding.relBottomTab.setVisibility(View.VISIBLE);
            binding.recordingPlay.setVisibility(View.GONE);
            binding.recordingPause.setVisibility(View.GONE);
            binding.recordingTime.setText(getString(R.string._00_00));
            getAskQuestions(base64Audio, "audio", String.valueOf(playableSeconds));
            ask_questions.setText("");

            return base64Audio;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

//    private void mergeAudio() {
//        // Use a unique file name for the merged audio
//        mergedAudioFilePath = Environment.getExternalStorageDirectory().getAbsolutePath() +getRecordingFilePath();
//
//        try {
//            // Create a MediaPlayer for each recorded audio file
//            MediaPlayer mediaPlayer1 = new MediaPlayer();
//            MediaPlayer mediaPlayer2 = new MediaPlayer();
//
//            mediaPlayer1.setDataSource(audioFilePath1);
//            mediaPlayer2.setDataSource(audioFilePath2);
//
//            mediaPlayer1.prepare();
//            mediaPlayer2.prepare();
//
//            // Create a new MediaRecorder for the merged audio
//            MediaRecorder merger = new MediaRecorder();
//            merger.setAudioSource(MediaRecorder.AudioSource.MIC);
//            merger.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
//            merger.setOutputFile(mergedAudioFilePath);
//            merger.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
//
//            // Start the MediaPlayers
//            mediaPlayer1.start();
//            mediaPlayer2.start();
//
//            // Start the merger (recording) after a delay (adjust as needed)
//            Thread.sleep(1000);
//
//            merger.prepare();
//            merger.start();
//
//            // Wait for both recordings to finish
//            while (mediaPlayer1.isPlaying() || mediaPlayer2.isPlaying()) {
//                Thread.sleep(500);
//            }
//
//            // Stop and release everything
//            merger.stop();
//            merger.release();
//            mediaPlayer1.release();
//            mediaPlayer2.release();
//
//            //Toast.makeText(this, "Audio files merged", Toast.LENGTH_SHORT).show();
//        } catch (IOException | InterruptedException e) {
//            e.printStackTrace();
//        }
//    }
    private void deleteRecording() {
        File file = new File(getRecordingFilePath());
        if (file.exists()) {
            if (file.delete()) {
                recordingTimeInSeconds = 0;
                handler.removeCallbacks(timerRunnable);
                //Toast.makeText(this, "Recording deleted", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(this, "Failed to delete recording", Toast.LENGTH_SHORT).show();
            }
        } else {
            //Toast.makeText(this, "No recording to delete", Toast.LENGTH_SHORT).show();
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
            handler.postDelayed(this, 1000); // Update every 1 second
        }
    };
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

    public void recordingLanguagePopup() {

        Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.recording_language_selection_popup);
        dialog.setCancelable(false);
        dialog.getWindow().setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.logout_popup));
        RelativeLayout exploreTstaLay = dialog.findViewById(R.id.exploreTstaLay);
        RelativeLayout englishRelLay = dialog.findViewById(R.id.englishRelLay);
        TextView englishText = dialog.findViewById(R.id.englishText);
        RelativeLayout englishCheckLay = dialog.findViewById(R.id.englishCheckLay);
        TextView englishTick = dialog.findViewById(R.id.englishTick);
        RelativeLayout hindiRelLay = dialog.findViewById(R.id.hindiRelLay);
        TextView hindiText = dialog.findViewById(R.id.hindiText);
        RelativeLayout hindiActiveCheckLay = dialog.findViewById(R.id.hindiActiveCheckLay);
        TextView hindiTick = dialog.findViewById(R.id.hindiTick);
        RelativeLayout marathiRelLay = dialog.findViewById(R.id.marathiRelLay);
        TextView marathiText = dialog.findViewById(R.id.marathiText);
        RelativeLayout marathiCheckLay = dialog.findViewById(R.id.marathiCheckLay);
        TextView marathiTick = dialog.findViewById(R.id.marathiTick);

        if (selectRecordingLanguage != null){
            if (selectRecordingLanguage.equals("en")){
                englishText.setTextColor(ContextCompat.getColor(context, R.color.white));
                englishCheckLay.setBackgroundResource(R.drawable.lang_check_active);
                englishRelLay.setBackgroundResource(R.drawable.lang_active);
                englishTick.setVisibility(View.VISIBLE);

                hindiText.setTextColor(ContextCompat.getColor(context, R.color.black));
                hindiActiveCheckLay.setBackgroundResource(R.drawable.health_inactive);
                hindiRelLay.setBackgroundResource(R.drawable.health_inactive);
                hindiTick.setVisibility(View.GONE);

                marathiText.setTextColor(ContextCompat.getColor(context, R.color.black));
                marathiCheckLay.setBackgroundResource(R.drawable.health_inactive);
                marathiRelLay.setBackgroundResource(R.drawable.health_inactive);
                marathiTick.setVisibility(View.GONE);
            }
            else if (selectRecordingLanguage.equals("hi")){
                hindiText.setTextColor(ContextCompat.getColor(context, R.color.white));
                hindiActiveCheckLay.setBackgroundResource(R.drawable.lang_check_active);
                hindiRelLay.setBackgroundResource(R.drawable.lang_active);
                hindiTick.setVisibility(View.VISIBLE);

                englishText.setTextColor(ContextCompat.getColor(context, R.color.black));
                englishCheckLay.setBackgroundResource(R.drawable.health_inactive);
                englishRelLay.setBackgroundResource(R.drawable.health_inactive);
                englishTick.setVisibility(View.GONE);

                marathiText.setTextColor(ContextCompat.getColor(context, R.color.black));
                marathiCheckLay.setBackgroundResource(R.drawable.health_inactive);
                marathiRelLay.setBackgroundResource(R.drawable.health_inactive);
                marathiTick.setVisibility(View.GONE);
            }
            else if (selectRecordingLanguage.equals("mr")){
                marathiText.setTextColor(ContextCompat.getColor(context, R.color.white));
                marathiCheckLay.setBackgroundResource(R.drawable.lang_check_active);
                marathiRelLay.setBackgroundResource(R.drawable.lang_active);
                marathiTick.setVisibility(View.VISIBLE);

                hindiText.setTextColor(ContextCompat.getColor(context, R.color.black));
                hindiActiveCheckLay.setBackgroundResource(R.drawable.health_inactive);
                hindiRelLay.setBackgroundResource(R.drawable.health_inactive);
                hindiTick.setVisibility(View.GONE);

                englishText.setTextColor(ContextCompat.getColor(context, R.color.black));
                englishCheckLay.setBackgroundResource(R.drawable.health_inactive);
                englishRelLay.setBackgroundResource(R.drawable.health_inactive);
                englishTick.setVisibility(View.GONE);
            }
        }

        englishRelLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spManager.setRecordingLang("en");
                englishText.setTextColor(ContextCompat.getColor(context, R.color.white));
                englishCheckLay.setBackgroundResource(R.drawable.lang_check_active);
                englishRelLay.setBackgroundResource(R.drawable.lang_active);
                englishTick.setVisibility(View.VISIBLE);

                hindiText.setTextColor(ContextCompat.getColor(context, R.color.black));
                hindiActiveCheckLay.setBackgroundResource(R.drawable.health_inactive);
                hindiRelLay.setBackgroundResource(R.drawable.health_inactive);
                hindiTick.setVisibility(View.GONE);

                marathiText.setTextColor(ContextCompat.getColor(context, R.color.black));
                marathiCheckLay.setBackgroundResource(R.drawable.health_inactive);
                marathiRelLay.setBackgroundResource(R.drawable.health_inactive);
                marathiTick.setVisibility(View.GONE);

            }
        });

        hindiRelLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spManager.setRecordingLang("hi");
                hindiText.setTextColor(ContextCompat.getColor(context, R.color.white));
                hindiActiveCheckLay.setBackgroundResource(R.drawable.lang_check_active);
                hindiRelLay.setBackgroundResource(R.drawable.lang_active);
                hindiTick.setVisibility(View.VISIBLE);

                englishText.setTextColor(ContextCompat.getColor(context, R.color.black));
                englishCheckLay.setBackgroundResource(R.drawable.health_inactive);
                englishRelLay.setBackgroundResource(R.drawable.health_inactive);
                englishTick.setVisibility(View.GONE);

                marathiText.setTextColor(ContextCompat.getColor(context, R.color.black));
                marathiCheckLay.setBackgroundResource(R.drawable.health_inactive);
                marathiRelLay.setBackgroundResource(R.drawable.health_inactive);
                marathiTick.setVisibility(View.GONE);

            }
        });

        marathiRelLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                spManager.setRecordingLang("mr");
                marathiText.setTextColor(ContextCompat.getColor(context, R.color.white));
                marathiCheckLay.setBackgroundResource(R.drawable.lang_check_active);
                marathiRelLay.setBackgroundResource(R.drawable.lang_active);
                marathiTick.setVisibility(View.VISIBLE);

                hindiText.setTextColor(ContextCompat.getColor(context, R.color.black));
                hindiActiveCheckLay.setBackgroundResource(R.drawable.health_inactive);
                hindiRelLay.setBackgroundResource(R.drawable.health_inactive);
                hindiTick.setVisibility(View.GONE);

                englishText.setTextColor(ContextCompat.getColor(context, R.color.black));
                englishCheckLay.setBackgroundResource(R.drawable.health_inactive);
                englishRelLay.setBackgroundResource(R.drawable.health_inactive);
                englishTick.setVisibility(View.GONE);

            }
        });

        exploreTstaLay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recordingPopup = true;
                binding.voiceRecordingRelLayout.setVisibility(View.VISIBLE);
                binding.relBottomTab.setVisibility(View.GONE);
                startRecording();
                dialog.dismiss();
            }
        });

        dialog.show();

    }
    public void getExpertReply(String refresh) {

        ExpertReplyAuthModel model = new ExpertReplyAuthModel();
        model.setUser_id(spManager.getUserId());
        Log.d("dkdkdk",spManager.getUserId().toString());
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
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                        //Log.d("dkdkdk",e.toString());
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
                        Toast.makeText(context, e.toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    public void getAskQuestions(String message, String quetype, String recordingDuration) {

        AskQuestionsAuthModel model = new AskQuestionsAuthModel();
        model.setUser_id(spManager.getUserId());
        model.setReply(message);
        model.setQuestionType(quetype);
        model.setLanguage(selectRecordingLanguage);
        model.setRecordingDuration(recordingDuration);

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
                getAskQuestions(message, "text","");
                chat_ref_status = "yes";
                ask_questions.setText("");
            } else if (adapter == null) {

                getAskQuestions(message, "text","");
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