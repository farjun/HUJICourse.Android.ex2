package com.example.omer.hujicourseandroidex2.CounterTaskClasses;


import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class ThreadTask extends BaseCounterTask implements Runnable {
    private static final String ITERATION_NUM_MESSAGE_KEY = "iterationNum";
    private Handler handler;
    private Thread thread;

    @Override
    public void createClicked() {
        //we override the handleMessage method to notify our listener
        handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                listener.onProgressUpdate(msg.getData().getInt(ITERATION_NUM_MESSAGE_KEY));
            }
        };
        thread = new Thread(this);

    }

    @Override
    public void startClicked() {
        thread.start();
    }

    @Override
    public void cancelClicked() {
        if (thread!=null)
            thread.interrupt();

    }

    @Override
    public void run() {

        for (int i = 1; i <= MAX_PROGRESS_VALUE; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
                return;
            }

            //like writing: Message mgs = new Message()
            Message msgToSend = handler.obtainMessage();
            //create the bundle to send
            final Bundle bundle = new Bundle();
            bundle.putInt(ITERATION_NUM_MESSAGE_KEY, i);
            //wrap the bundle with a message
            msgToSend.setData(bundle);
            //send
            handler.sendMessage(msgToSend);
        }


    }

}
