package com.example.omer.hujicourseandroidex2;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.omer.hujicourseandroidex2.CounterTaskClasses.AsyncTaskCounter;
import com.example.omer.hujicourseandroidex2.CounterTaskClasses.BaseCounterTask;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * to handle interaction events.
 * Use the {@link AsyncClassFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AsyncClassFragment extends Fragment  implements View.OnClickListener,BaseCounterTask.OnProgressUpdateListener{

    public static final String TASK_PROGRESS_STATE = "TaskProgressState";
    public static final String BUTTONS_SAVE_KEY = "buttons";
    public static final String DONE_MESSAGE = "Done";
    private BaseCounterTask task;

    //UI stuff
    TextView taskPrograssStatus;
    TextView counterView;

    Button create;
    Button start;
    Button cancel;

    public AsyncClassFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment AsyncClassFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AsyncClassFragment newInstance(BaseCounterTask task) {
        AsyncClassFragment fragment = new AsyncClassFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        fragment.task = task;

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true); // Retain this fragment on oreintation
        task.setOnProgressUpdateListener(this);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        final boolean[] buttenStates = new boolean[]{
                create.isEnabled(),
                start.isEnabled(),
                cancel.isEnabled()
        };
        outState.putBooleanArray(BUTTONS_SAVE_KEY,buttenStates);
        outState.putCharSequence(TASK_PROGRESS_STATE,taskPrograssStatus.getText());

    }

    private void restoreSavedInstanceState(@NonNull Bundle savedInstance){
        taskPrograssStatus.setText(savedInstance.getCharSequence(TASK_PROGRESS_STATE));
        final boolean[] buttonStates = savedInstance.getBooleanArray(BUTTONS_SAVE_KEY);
        if (buttonStates == null)
                return;

        create.setEnabled(buttonStates[0]);
        start.setEnabled(buttonStates[0]);
        cancel.setEnabled(buttonStates[0]);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_async_class, container, false);

        //set up buttons
        this.create = view.findViewById(R.id.create_button);
        this.create.setOnClickListener(this);
        this.cancel= view.findViewById(R.id.cancel_button);
        this.cancel.setOnClickListener(this);
        this.start  = view.findViewById(R.id.start_button);
        this.start.setOnClickListener(this);
        this.start.setEnabled(false);
        this.cancel.setEnabled(false);

        this.taskPrograssStatus = view.findViewById(R.id.fragment_task_status);
        this.counterView = view.findViewById(R.id.counter_text_view);
        this.task.setOnProgressUpdateListener(this);

        //todo  savedInstanceState
        if (savedInstanceState !=null)
            restoreSavedInstanceState(savedInstanceState);

        return view;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        task.cancelClicked();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.create_button:
                this.task.createClicked();
                create.setEnabled(false);
                start.setEnabled(true);
                counterView.setText("00");
                taskPrograssStatus.setText("Ready");
                break;
            case R.id.start_button:
                this.task.startClicked();
                start.setEnabled(false);
                cancel.setEnabled(true);
                taskPrograssStatus.setText("in progress");
                break;

            case R.id.cancel_button:
                this.task.cancelClicked();
                create.setEnabled(true);
                cancel.setEnabled(false);
                taskPrograssStatus.setText("Canceled");
                break;


        }
    }

    @Override
    public void onProgressUpdate(int progress) {
        if(progress == BaseCounterTask.MAX_PROGRESS_VALUE){
            taskPrograssStatus.setText(DONE_MESSAGE);
            cancel.setEnabled(false);
            create.setEnabled(true);
            return;
        }
        counterView.setText(Integer.toString(progress));
    }


}
