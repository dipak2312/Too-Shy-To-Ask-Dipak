package com.neuronimbus.metropolis.Utils;

import android.media.MediaRecorder;
import java.io.IOException;

public class AudioRecorder {
    private MediaRecorder mediaRecorder;
    private String outputFile;

    public AudioRecorder(String outputFile) {
        this.outputFile = outputFile;
    }
    public String getOutputFilePath() {
        return outputFile;
    }

    public void startRecording() {
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        mediaRecorder.setOutputFile(outputFile);

        try {
            mediaRecorder.prepare();
            mediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stopRecording() {
        if (mediaRecorder != null) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
        }
    }
}

