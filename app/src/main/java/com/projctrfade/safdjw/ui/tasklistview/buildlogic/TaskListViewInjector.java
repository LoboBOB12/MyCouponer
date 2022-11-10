package com.projctrfade.safdjw.ui.tasklistview.buildlogic;

import com.projctrfade.safdjw.StorageServiceLocator;
import com.projctrfade.safdjw.ui.tasklistview.ITaskListViewContract;
import com.projctrfade.safdjw.ui.tasklistview.TaskListView;
import com.projctrfade.safdjw.ui.tasklistview.TaskListViewLogic;
import com.projctrfade.safdjw.ui.tasklistview.TaskListViewModel;

public class TaskListViewInjector {
    public static void build(
            TaskListView view,
            StorageServiceLocator locator
    ) {

        ITaskListViewContract.ViewModel vm = new TaskListViewModel();

        view.setLogic(
                new TaskListViewLogic(
                        view,
                        vm,
                        locator.getTaskStorageImpl()
                )
        );
    }
}
