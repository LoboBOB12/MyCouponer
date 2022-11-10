package com.projctrfade.safdjw.ui.dayview;

import com.projctrfade.safdjw.domain.Day;
import com.projctrfade.safdjw.domain.Tasks;

public interface IDayViewContract {
    interface View {
        void setData(Day day, Tasks tasks);
        void navigateToHourView(int hourInteger);
        void navigateToTasksView();
        void showMessage(String message);
        void restartFeature();
    }

    interface ViewModel {
        void setDay(Day day);
        Day getDay();
        void setTasks(Tasks tasks);
        Tasks getTasks();
    }

}
