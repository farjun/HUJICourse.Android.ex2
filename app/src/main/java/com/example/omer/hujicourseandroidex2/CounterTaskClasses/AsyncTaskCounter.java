package com.example.omer.hujicourseandroidex2.CounterTaskClasses;


import android.net.sip.SipSession;
import android.os.AsyncTask;

public class AsyncTaskCounter extends BaseCounterTask {
    private innerCounterAsyncedTask curTask;



    @Override
    public void createClicked() {
        curTask = new innerCounterAsyncedTask(listener);
    }

    @Override
    public void startClicked() {
        curTask.execute();
    }

    @Override
    public void cancelClicked() {
        if(curTask != null){
            curTask.cancel(true);
        }
    }
    static class innerCounterAsyncedTask extends AsyncTask<Void,Integer,Void>{
        OnProgressUpdateListener listener;

        innerCounterAsyncedTask(OnProgressUpdateListener listener){
            this.listener = listener;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            listener.onProgressUpdate(values[0]);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            for(int i=1;i<=10;i++){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                publishProgress(i);
            }


            return null;
        }



    }
}


