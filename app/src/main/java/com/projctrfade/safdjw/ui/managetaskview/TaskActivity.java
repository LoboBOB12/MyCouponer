package com.projctrfade.safdjw.ui.managetaskview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.projctrfade.safdjw.App;
import com.projctrfade.safdjw.R;
import com.projctrfade.safdjw.domain.constants.Extras;
import com.projctrfade.safdjw.ui.dayview.DayActivity;
import com.projctrfade.safdjw.ui.managetaskview.buildlogic.TaskViewInjector;

import java.util.Objects;

import static com.projctrfade.safdjw.common.Messages.GENERIC_ERROR_MESSAGE;

public class TaskActivity extends AppCompatActivity {

    private static final String TASK_VIEW = "TASK_VIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_task);


        final Intent i = this.getIntent();

        if (i == null) {
            Toast.makeText(this,
                    GENERIC_ERROR_MESSAGE,
                    Toast.LENGTH_SHORT
            ).show();

            startActivity(
                    new Intent(this, DayActivity.class)
            );
        }

        int taskId = i.getIntExtra(
                Extras.EXTRA_TASK_ID,
                0
        );

        TaskView fragment = (TaskView) this.getSupportFragmentManager().findFragmentByTag(
                TASK_VIEW
        );

        if (fragment == null) {
            fragment = TaskView.newInstance();
        }

        this.getSupportFragmentManager().beginTransaction().replace(
                R.id.root_manage_task_view,
                fragment,
                TASK_VIEW
        ).commit();

        TaskViewInjector.build(
                fragment,
                Objects.requireNonNull(((App) getApplication()).getServiceLocator()),
                taskId
        );
    }
}