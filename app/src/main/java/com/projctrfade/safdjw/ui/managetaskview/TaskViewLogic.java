package com.projctrfade.safdjw.ui.managetaskview;

import com.projctrfade.safdjw.common.BaseViewLogic;
import com.projctrfade.safdjw.common.Continuation;
import com.projctrfade.safdjw.domain.ITaskStorage;
import com.projctrfade.safdjw.domain.Task;
import com.projctrfade.safdjw.domain.constants.COLOR;
import com.projctrfade.safdjw.domain.constants.ICON;

public class TaskViewLogic extends BaseViewLogic<TaskViewEvent> {

    private ITaskViewContract.View view;
    private ITaskViewContract.ViewModel vm;
    private ITaskStorage storage;


    public TaskViewLogic(ITaskViewContract.View view, ITaskViewContract.ViewModel vm, ITaskStorage storage) {
        this.view = view;
        this.vm = vm;
        this.storage = storage;
    }

    @Override
    public void onViewEvent(TaskViewEvent event) {
        //code like this make me miss Kotlin syntax...
        switch (event.getEvent()) {
            case ON_START:
                onStart();
                break;
            case ON_COLOR_BUTTON_CLICK:
                view.showColorPickerSheet();
                break;
            case ON_COLOR_SELECTED:
                onColorSelected((COLOR) event.getValue());
                break;
            case ON_DONE_CLICK:
                updateStorage();
                break;
            case ON_ICON_SELECTED:
                onIconSelected(
                        (ICON) event.getValue()
                );
                break;
        }
    }


    private void onStart() {
        storage.getTask(vm.getTask().getTaskId(),
                new Continuation<Task>() {
                    @Override
                    public void onSuccess(Task result) {
                        vm.setTask(result);
                        view.setButtonColor(result.getTaskColor());
                        view.setName(result.getTaskName());
                        view.setIconSelection(result.getTaskIcon());
                        view.setSelectedSpinnerItem(result.getTaskId());
                    }

                    @Override
                    public void onException(Exception e) {
                        view.showMessage(e.getMessage());
                        view.goToTaskListActivity();
                    }
                }
        );
    }

    private void onIconSelected(ICON icon) {
        Task oldTask = vm.getTask();
        Task update = new Task(
                oldTask.getTaskId(),
                oldTask.getTaskName(),
                icon,
                oldTask.getTaskColor()
        );

        vm.setTask(update);
        view.setIconSelection(update.getTaskIcon());
    }

    private void updateStorage() {
        Task oldTask = vm.getTask();
        Task update = new Task(
                oldTask.getTaskId(),
                view.getName(),
                oldTask.getTaskIcon(),
                oldTask.getTaskColor()
        );

        storage.updateTask(update,
                new Continuation<Void>() {
                    @Override
                    public void onSuccess(Void result) {
                        view.goToTaskListActivity();
                    }

                    @Override
                    public void onException(Exception e) {
                        view.showMessage(e.getMessage());
                        view.goToTaskListActivity();
                    }
                }
        );
    }

    private void onColorSelected(COLOR color) {
        Task oldTask = vm.getTask();
        Task newTask = new Task(
                oldTask.getTaskId(),
                oldTask.getTaskName(),
                oldTask.getTaskIcon(),
                color
        );
        vm.setTask(
                newTask
        );

        view.setButtonColor(newTask.getTaskColor());
        view.hideColorPickerSheet();
    }


}
