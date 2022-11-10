package com.projctrfade.safdjw.ui.dayview;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;


import com.projctrfade.safdjw.App;
import com.projctrfade.safdjw.R;
import com.projctrfade.safdjw.ui.dayview.buildlogic.DayViewInjector;

import java.util.Objects;


public class DayActivity extends AppCompatActivity {

    private static final String DAY_VIEW = "DAY_VIEW";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_day);

        DayView fragment = (DayView) this.getSupportFragmentManager().findFragmentByTag(
                DAY_VIEW
        );

        if (fragment == null) {
         fragment = DayView.newInstance();
        }

        this.getSupportFragmentManager().beginTransaction().replace(
                R.id.root_day_view,
                fragment,
                DAY_VIEW
        ).commit();

        DayViewInjector.build(fragment, Objects.requireNonNull(((App) getApplication()).getServiceLocator()));
    }
}