package com.example.omer.hujicourseandroidex2.CounterTaskClasses;


public abstract class BaseCounterTask {
    OnProgressUpdateListener listener = null;
    public static final int MAX_PROGRESS_VALUE = 10;

    public void setOnProgressUpdateListener(OnProgressUpdateListener listener){
        this.listener = listener;
    }

    public abstract void createClicked();
    public abstract void startClicked();
    public abstract void cancelClicked();

    public interface OnProgressUpdateListener {
        void onProgressUpdate(int progress);
    }

}
