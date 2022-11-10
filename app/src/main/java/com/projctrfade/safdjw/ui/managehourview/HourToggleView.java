package com.projctrfade.safdjw.ui.managehourview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import com.projctrfade.safdjw.R;
import com.projctrfade.safdjw.domain.constants.QUARTER;

public class HourToggleView extends ConstraintLayout {
    private Switch quarterHourIsActive;
    private TextView timeLabel;
    private Spinner selectTask;
    private View bottomBorder;
    private QUARTER quarter;
    private ToggleViewClickHandler callback;

    public void setToggleState(QUARTER quarter, boolean isActive) {
        if (quarter != QUARTER.ZERO) {
            quarterHourIsActive.setChecked(isActive);
            setTimeLabelColor(isActive);
        } else {
            quarterHourIsActive.setVisibility(INVISIBLE);
            setTimeLabelColor(true);
        }

       if (quarter == QUARTER.FOURTY_FIVE) hideBottomDivider();
    }

    private void setTimeLabelColor(boolean isAccentColor) {
        if (isAccentColor) timeLabel.setTextColor(
                ContextCompat.getColor(
                        getContext(),
                        R.color.colorAccent
                )
        );
        else {
            timeLabel.setTextColor(
                    ContextCompat.getColor(
                            getContext(),
                            android.R.color.white
                    )
            );
        }
    }

    interface ToggleViewClickHandler {
        void onQuarterToggle(QUARTER quarter, boolean activate);

        void onTaskSelected(QUARTER quarter, int position);
    }


    public HourToggleView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        build();
    }

    private void build() {
        inflate(getContext(), R.layout.view_quarter_hour_toggle, this);

        quarterHourIsActive = findViewById(R.id.swi_activate_quarter_hour);
        quarterHourIsActive.setOnCheckedChangeListener(
                (view, isChecked) -> {
                    if (callback != null) callback.onQuarterToggle(quarter, isChecked);
                    setTimeLabelColor(isChecked);
                }
        );

        timeLabel = findViewById(R.id.lbl_quarter_hour);

        selectTask = findViewById(R.id.spn_quarter_hour);
        bottomBorder = findViewById(R.id.view_bottom_border);
    }

    public void setCallback(ToggleViewClickHandler callback) {
        this.callback = callback;

        selectTask.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> adapterView,
                                               View view,
                                               int position,
                                               long l) {
                        if (callback != null) callback.onTaskSelected(quarter, position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> adapterView) {}
                }
        );
    }


    public void setTimeLabel(String text) { timeLabel.setText(text);}

    public void hideBottomDivider() { bottomBorder.setVisibility(INVISIBLE); }

    public QUARTER getQuarter() { return quarter;}

    public void setQuarter(QUARTER quarter) { this.quarter = quarter;}

    public void createTaskAdapter(String[] tasks, int startingPosition) {
        ArrayAdapter<String> adapter = new ArrayAdapter(getContext(), android.R.layout.simple_spinner_dropdown_item, tasks);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        selectTask.setAdapter(adapter);

        selectTask.setSelection(startingPosition, false);
    }
}
