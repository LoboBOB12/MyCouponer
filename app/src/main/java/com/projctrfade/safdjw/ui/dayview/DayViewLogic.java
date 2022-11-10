package com.projctrfade.safdjw.ui.dayview;

import com.projctrfade.safdjw.common.BaseViewLogic;
import com.projctrfade.safdjw.common.Continuation;
import com.projctrfade.safdjw.common.Messages;
import com.projctrfade.safdjw.domain.Day;
import com.projctrfade.safdjw.domain.IDayStorage;
import com.projctrfade.safdjw.domain.ITaskStorage;
import com.projctrfade.safdjw.domain.Tasks;

public class DayViewLogic extends BaseViewLogic<DayViewEvent> {

    private final IDayViewContract.View view;
    private final IDayViewContract.ViewModel vm;

    private final IDayStorage dayStorage;
    private final ITaskStorage taskStorage;

    public DayViewLogic(IDayViewContract.View view, IDayViewContract.ViewModel vm, IDayStorage dayStorage, ITaskStorage taskStorage) {
        this.view = view;
        this.vm = vm;
        this.dayStorage = dayStorage;
        this.taskStorage = taskStorage;
    }

    @Override
    public void onViewEvent(DayViewEvent event) {
        switch (event.getEvent()) {
            case ON_START:
                onStart();
                break;
            case ON_HOUR_SELECTED:
                onHourSelected((int)event.getValue());
                break;
            case ON_MANAGE_TASKS_SELECTED:
                onManageTaskSelected();
                break;
        }
    }

    private void onManageTaskSelected() {
        view.navigateToTasksView();
    }

    private void onHourSelected(int hourInteger) {
        view.navigateToHourView(hourInteger);
    }

    private void onStart() {
        dayStorage.getDay(new Continuation<Day>() {
            @Override
            public void onSuccess(Day result) {
                getTasks(result);
            }

            @Override
            public void onException(Exception e) {
                view.showMessage(Messages.GENERIC_ERROR_MESSAGE);
                view.restartFeature();
            }
        });
    }

    private void getTasks(Day dayResult) {
        taskStorage.getTasks(new Continuation<Tasks>() {
            @Override
            public void onSuccess(Tasks taskResult) {
                bindData(dayResult, taskResult);
            }

            @Override
            public void onException(Exception e) {
                view.showMessage(Messages.GENERIC_ERROR_MESSAGE);
                view.restartFeature();
            }
        });
    }

    private void bindData(Day dayResult, Tasks taskResult) {
        vm.setDay(dayResult);
        vm.setTasks(taskResult);
        view.setData(dayResult, taskResult);
    }
}
