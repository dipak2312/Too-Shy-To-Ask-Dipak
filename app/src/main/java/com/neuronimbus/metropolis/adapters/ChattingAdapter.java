package com.neuronimbus.metropolis.adapters;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.neuronimbus.metropolis.Models.chats;
import com.neuronimbus.metropolis.R;

import java.io.IOException;
import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import jp.shts.android.storiesprogressview.StoriesProgressView;

public class ChattingAdapter extends RecyclerView.Adapter<ChattingAdapter.ViewHolder> {
    Context context;
    ArrayList<chats> chats;
    private static final int VIEW_TYPE_MESSAGE_ADMIN = 1;
    private static final int VIEW_TYPE_MESSAGE_USER = 2;
    String time, part1, part2, audioRecordingTime;
    int recordingTime, mp3Time;
    double progressBarTime;
    private Handler handler = new Handler();
    MediaPlayer mediaPlayer = new MediaPlayer();
    boolean isPlaying = false;
    int playablePosition = -1;

    public ChattingAdapter(Context context, ArrayList<com.neuronimbus.metropolis.Models.chats> chats) {
        this.context = context;
        this.chats = chats;
    }

    @Override
    public int getItemViewType(int position) {
        if(chats.get(position).getType().equals("reply"))
        {

            return VIEW_TYPE_MESSAGE_ADMIN;
        }
        else if(chats.get(position).getType().equals("question"))
        {

            return  VIEW_TYPE_MESSAGE_USER;
        }

        return position;
    }

    @NonNull
    @Override
    public ChattingAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType==VIEW_TYPE_MESSAGE_ADMIN)
        {
            View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ask_expert_chatting_reply_item,parent,false);
            return new ViewHolder(view);

        }
        else if(viewType==VIEW_TYPE_MESSAGE_USER)
        {
            View view1= LayoutInflater.from(parent.getContext()).inflate(R.layout.ask_expert_chatting_item,parent,false);
            return new ViewHolder(view1);
        }

        return null;

    }

    @Override
    public void onBindViewHolder(@NonNull ChattingAdapter.ViewHolder holder, int position) {
        if(chats.get(position).getType().equals("reply"))
        {
            if (chats.get(position).getQuestion_type() != null && chats.get(position).getQuestion_type().equals("audio")) {
                holder.microPhoneIcon.setVisibility(View.VISIBLE);
                int audioTime = Integer.parseInt(chats.get(position).getRecordingDuration());
                int minutes = audioTime / 60;
                int seconds = audioTime % 60;
                audioRecordingTime = String.format("%02d:%02d", minutes, seconds);
                holder.reply_que.setText(context.getString(R.string.voice_message) + " ("+ audioRecordingTime + ")");
                holder.reply_msg.setText(Html.fromHtml(chats.get(position).getReply()));
                holder.reply_msg.setMovementMethod(LinkMovementMethod.getInstance());
                time = chats.get(position).getCreated_at();
                String[] parts = time.split(":");
                part1 = parts[0];
                part2 = parts[1];
                holder.txt_expert_chat_date.setText(part1 + ":" + part2);
            }
            else {
                holder.microPhoneIcon.setVisibility(View.GONE);
                holder.reply_que.setText(Html.fromHtml(chats.get(position).getQuestion()));
                holder.reply_que.setMovementMethod(LinkMovementMethod.getInstance());
                holder.reply_msg.setText(Html.fromHtml(chats.get(position).getReply()));
                holder.reply_msg.setMovementMethod(LinkMovementMethod.getInstance());
                time = chats.get(position).getCreated_at();
                String[] parts = time.split(":");
                part1 = parts[0];
                part2 = parts[1];
                holder.txt_expert_chat_date.setText(part1 + ":" + part2);
            }

        }
        if(chats.get(position).getType().equals("question"))
        {

            if (chats.get(position).getQuestion_type() != null && chats.get(position).getQuestion_type().equals("audio")) {
                holder.audioChattingDesign.setVisibility(View.VISIBLE);
                holder.chatt_lin_lay.setVisibility(View.GONE);
                holder.dateTimeLay.setVisibility(View.GONE);
                time = chats.get(position).getCreated_at();
                String[] parts = time.split(":");
                part1 = parts[0];
                part2 = parts[1];
                holder.audioChatDate.setText(part1 + ":" + part2);
                Glide.with(context).load(chats.get(position).getProfile_pic()).into(holder.userProfilePic);

                if (chats.get(position).getRecordingDuration() != null && !chats.get(position).getRecordingDuration().isEmpty()){
                    int audioTime = Integer.parseInt(chats.get(position).getRecordingDuration());
                    int minutes = audioTime / 60;
                    int seconds = audioTime % 60;
                    audioRecordingTime = String.format("%02d:%02d", minutes, seconds);
                }

                holder.audioFileTime.setText(audioRecordingTime);

                if (playablePosition == position){
                    handler.postDelayed(holder.audioTimerRunnable, 1000);
                    holder.progressbar_completed.setProgress(0);
                    holder.playButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.play));
                }
                else {
                    //isPlaying = false;
                    handler.removeCallbacks(holder.audioTimerRunnable);
                    holder.progressbar_completed.setProgress(0);
                    holder.playButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.pause));
                }

                holder.playButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (chats.get(position).getRecordingDuration() != null && !chats.get(position).getRecordingDuration().isEmpty()
                        && !chats.get(position).getRecordingDuration().equals("0")) {
                            if (playablePosition == position){
                                if (isPlaying){
                                    holder.stopRecording();
                                    holder.playButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.pause));
                                }
                                else {
                                    holder.startRecording(position);

                                    holder.playButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.play));
                                }
                            }
                            else {
                                notifyDataSetChanged();
                                holder.startRecording(position);
                                holder.playButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.play));
                                playablePosition = position;
                            }
                        }
                    }
                });

            }
            else {
                holder.audioChattingDesign.setVisibility(View.GONE);
                holder.chatt_lin_lay.setVisibility(View.VISIBLE);
                holder.dateTimeLay.setVisibility(View.VISIBLE);
                holder.user_msg.setText(Html.fromHtml(chats.get(position).getQuestion()));
                holder.user_msg.setMovementMethod(LinkMovementMethod.getInstance());
                time = chats.get(position).getCreated_at();
                String[] parts = time.split(":");
                part1 = parts[0];
                part2 = parts[1];
                holder.txt_my_chat_date.setText(part1 + ":" + part2);
            }
        }
    }

    @Override
    public int getItemCount() {
        return chats.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView user_msg, txt_my_chat_date, audioChatDate, audioFileTime;
        TextView reply_que, reply_msg, txt_expert_chat_date;
        RelativeLayout chatt_lin_lay, dateTimeLay, audioChattingDesign;
        CircleImageView userProfilePic;
        ImageView playButton, pauseButton, microPhoneIcon;
        ProgressBar progressbar_completed;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            user_msg = itemView.findViewById(R.id.user_msg);
            txt_my_chat_date = itemView.findViewById(R.id.txt_my_chat_date);
            reply_que = itemView.findViewById(R.id.reply_que);
            reply_msg = itemView.findViewById(R.id.reply_msg);
            txt_expert_chat_date = itemView.findViewById(R.id.txt_expert_chat_date);
            chatt_lin_lay = itemView.findViewById(R.id.chatt_lin_lay);
            dateTimeLay = itemView.findViewById(R.id.dateTimeLay);
            audioChattingDesign = itemView.findViewById(R.id.audioChattingDesign);
            audioChatDate = itemView.findViewById(R.id.audioChatDate);
            userProfilePic = itemView.findViewById(R.id.userProfilePic);
            playButton = itemView.findViewById(R.id.playButton);
            pauseButton = itemView.findViewById(R.id.pauseButton);
            progressbar_completed = itemView.findViewById(R.id.progressbar_completed);
            audioFileTime = itemView.findViewById(R.id.audioFileTime);
            microPhoneIcon = itemView.findViewById(R.id.microPhoneIcon);
        }
        public void  startRecording(int position){
            stopRecording();

            playButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.play));
            mp3Time = Integer.parseInt(chats.get(position).getRecordingDuration());
            try {
                //handler.postDelayed(audioTimerRunnable, 1000);
                isPlaying = true;
                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(chats.get(position).getQuestion());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void stopRecording(){
            isPlaying = false;
            recordingTime = 0;
            playButton.setImageDrawable(ContextCompat.getDrawable(context,R.drawable.pause));
            mediaPlayer.pause();
            handler.removeCallbacks(audioTimerRunnable);
        }
        Runnable audioTimerRunnable = new Runnable() {
            @Override
            public void run() {
                if (recordingTime != mp3Time) {
                    recordingTime++;
                    int minutes = recordingTime / 60;
                    int seconds = recordingTime % 60;
                    time = String.format("%02d:%02d", minutes, seconds);
                    progressBarTime = (double) 100/mp3Time*recordingTime;
                    handler.postDelayed(this, 1000);
                    progressbar_completed.setProgress((int)progressBarTime);
                    audioFileTime.setText(String.valueOf(time));

                }
                else {
                    stopRecording();
                }
            }
        };
    }

}