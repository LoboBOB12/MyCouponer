package com.projctrfade.safdjw.domain;

import com.projctrfade.safdjw.common.Continuation;

public interface ITaskStorage {
    public void getTasks(Continuation<Tasks> continuation);

    public void getTask(int taskId, Continuation<Task> continuation);

    public void updateTask(Task task, Continuation<Void> continuation);
}
