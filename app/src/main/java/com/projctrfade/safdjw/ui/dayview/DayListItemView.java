package com.projctrfade.safdjw.ui.dayview;

import androidx.annotation.IntegerRes;
import androidx.annotation.VisibleForTesting;

import java.util.List;

public
class DayListItemView {
    private final String hourBlockText;

    @IntegerRes
    private final int[] iconResId;

    @IntegerRes
    private final int[] backgroundResId;

    private final String[] taskNames;

    private final LIST_ITEM_TYPE type;

    public String getHourBlockText() {
        return hourBlockText;
    }

    public int[] getIconResId() {
        return iconResId;
    }

    public int[] getBackgroundResId() {
        return backgroundResId;
    }

    public String[] getTaskNames() {
        return taskNames;
    }

    public LIST_ITEM_TYPE getType() {
        return type;
    }

    public DayListItemView(String hourBlockText, int[] iconResId, int[] backgroundResId,  String[] taskNames, LIST_ITEM_TYPE type) {
        this.hourBlockText = hourBlockText;
        this.backgroundResId = backgroundResId;
        this.iconResId = iconResId;
        this.taskNames = taskNames;
        this.type = type;
    }
}
