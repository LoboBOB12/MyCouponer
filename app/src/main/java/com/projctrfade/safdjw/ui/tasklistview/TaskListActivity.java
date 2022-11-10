package com.projctrfade.safdjw.ui.tasklistview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.projctrfade.safdjw.App;
import com.projctrfade.safdjw.R;
import com.projctrfade.safdjw.ui.tasklistview.buildlogic.TaskListViewInjector;

import java.util.Objects;

public class TaskListActivity extends AppCompatActivity {

    private static final String TASK_LIST_VIEW = "TASK_LIST_VIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        TaskListView fragment = (TaskListView) this.getSupportFragmentManager().findFragmentByTag(
                TASK_LIST_VIEW
        );

        if (fragment == null) {
            fragment = TaskListView.newInstance();
        }

        this.getSupportFragmentManager()
                .beginTransaction()
                .replace(
                R.id.root_task_list_view,
                fragment,
                TASK_LIST_VIEW
        ).commit();


        TaskListViewInjector.build(
                fragment,
                Objects.requireNonNull(((App) getApplication()).getServiceLocator())
        );
    }
}