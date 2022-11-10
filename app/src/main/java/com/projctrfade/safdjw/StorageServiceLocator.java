package com.projctrfade.safdjw;

import android.content.Context;

import com.projctrfade.safdjw.domain.IDayStorage;
import com.projctrfade.safdjw.domain.ITaskStorage;
import com.projctrfade.safdjw.persistence.LocalDayStorageImpl;
import com.projctrfade.safdjw.persistence.LocalTaskStorageImpl;

public class StorageServiceLocator {

    private final IDayStorage dayStorageImpl;
    private final ITaskStorage taskStorageImpl;


    public StorageServiceLocator(Context context) {
        ApplicationExecutors exec = new ApplicationExecutors();

        dayStorageImpl = new LocalDayStorageImpl(
                context.getFilesDir().getPath(),
                exec
        );
        taskStorageImpl = new LocalTaskStorageImpl(
                context.getFilesDir().getPath(),
                exec
        );
    }

    public IDayStorage getDayStorageImpl() {
        return dayStorageImpl;
    }

    public ITaskStorage getTaskStorageImpl() {
        return taskStorageImpl;
    }
}
