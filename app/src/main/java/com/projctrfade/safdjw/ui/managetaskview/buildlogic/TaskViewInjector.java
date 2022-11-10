package com.projctrfade.safdjw.ui.managetaskview.buildlogic;

import com.projctrfade.safdjw.StorageServiceLocator;
import com.projctrfade.safdjw.domain.Task;
import com.projctrfade.safdjw.domain.constants.COLOR;
import com.projctrfade.safdjw.domain.constants.ICON;
import com.projctrfade.safdjw.ui.managetaskview.ITaskViewContract;
import com.projctrfade.safdjw.ui.managetaskview.TaskView;
import com.projctrfade.safdjw.ui.managetaskview.TaskViewLogic;
import com.projctrfade.safdjw.ui.managetaskview.TaskViewModel;

public class TaskViewInjector {
    public static void build(
            TaskView view,
            StorageServiceLocator locator,
            int taskId
    ) {

        ITaskViewContract.ViewModel vm = new TaskViewModel();

        vm.setTask(
                new Task(
                     taskId,
                     "",
                     ICON.BREAK,
                     COLOR.DARK_BLUE
                )
        );

        view.setLogic(
                new TaskViewLogic(
                        (ITaskViewContract.View) view,
                        vm,
                        locator.getTaskStorageImpl()
                )
        );
    }
}
