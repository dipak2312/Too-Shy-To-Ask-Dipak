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
    int recordingTimeInSeconds, mp3Time;
    double progressBarTime;
    private Handler handler = new Handler();
    MediaPlayer mediaPlayer = new MediaPlayer();
    boolean isRecording = true;

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
            if (chats.get(position).getQuestion_type().equals("audio")) {
                holder.microPhoneIcon.setVisibility(View.VISIBLE);
                int audioTime = Integer.parseInt(chats.get(position).getRecordingDuration());
                int minutes = audioTime / 60;
                int seconds = audioTime % 60;
                audioRecordingTime = String.format("%02d:%02d", minutes, seconds);
                holder.reply_que.setText("Voice message " + "("+ audioRecordingTime + ")");
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

            if (chats.get(position).getQuestion_type().equals("audio")) {
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

                holder.playButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (chats.get(position).getRecordingDuration() != null && !chats.get(position).getRecordingDuration().isEmpty()) {
                            holder.adapterRefresh(chats,position);
                        }
                    }
                });
                holder.pauseButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.stopRecording();

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
        public void adapterRefresh(ArrayList<chats> chat, int position){
                if (chat.get(position).status){
                    startRecording(position);
                    chat.get(position).setStatus(false);
                    notifyDataSetChanged();
                    pauseButton.setVisibility(View.GONE);
                    playButton.setVisibility(View.VISIBLE);
                }
            else {
                    chat.get(position).setStatus(true);
                    pauseButton.setVisibility(View.VISIBLE);
                    playButton.setVisibility(View.GONE);
                }
        }
        public void  startRecording(int position){
            stopRecording();
            isRecording = true;
            pauseButton.setVisibility(View.VISIBLE);
            playButton.setVisibility(View.GONE);
            mp3Time = Integer.parseInt(chats.get(position).getRecordingDuration());
            try {
                handler.postDelayed(timerRunnable, 1000);

                mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(chats.get(position).getQuestion());
                mediaPlayer.prepare();
                mediaPlayer.start();
                //Toast.makeText(context, "Recording Play", Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        public void stopRecording(){
            isRecording = false;
            recordingTimeInSeconds = 0;
            pauseButton.setVisibility(View.GONE);
            playButton.setVisibility(View.VISIBLE);
            mediaPlayer.pause();
            handler.removeCallbacks(timerRunnable);
           // Toast.makeText(context, "Recording Pause", Toast.LENGTH_SHORT).show();
        }
        Runnable timerRunnable = new Runnable() {
            @Override
            public void run() {
                if (recordingTimeInSeconds != mp3Time) {
                    recordingTimeInSeconds++;
                    int minutes = recordingTimeInSeconds / 60;
                    int seconds = recordingTimeInSeconds % 60;
                    time = String.format("%02d:%02d", minutes, seconds);
                    progressBarTime = (double) 100/mp3Time*recordingTimeInSeconds;
                    handler.postDelayed(this, 1000); // Update every 1 second
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
