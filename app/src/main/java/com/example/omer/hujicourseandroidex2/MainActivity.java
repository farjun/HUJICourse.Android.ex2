package com.example.omer.hujicourseandroidex2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.omer.hujicourseandroidex2.CounterTaskClasses.AsyncTaskCounter;
import com.example.omer.hujicourseandroidex2.CounterTaskClasses.BaseCounterTask;
import com.example.omer.hujicourseandroidex2.CounterTaskClasses.ThreadTask;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void start_activity(Class<? extends BaseCounterTask> taskToInit){
        Intent intent = new Intent(this,AsyncClassActivity.class);
        intent.putExtra("task",taskToInit);
        startActivity(intent);
    }

    public void threads_activity_button_clicked(View view) {
        start_activity(ThreadTask.class);
    }

    public void async_task_button_clicked(View view) {
        start_activity(AsyncTaskCounter.class);
    }
}
