package com.projctrfade.safdjw.ui.managetaskview;

import com.projctrfade.safdjw.domain.Task;

public class TaskViewModel implements ITaskViewContract.ViewModel {
    private Task task;

    @Override
    public void setTask(Task task) {
        this.task = task;
    }

    @Override
    public Task getTask() {
        return this.task;
    }
}
