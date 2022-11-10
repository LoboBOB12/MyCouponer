package com.projctrfade.safdjw.ui.dayview.buildlogic;

import com.projctrfade.safdjw.StorageServiceLocator;
import com.projctrfade.safdjw.ui.dayview.DayView;
import com.projctrfade.safdjw.ui.dayview.DayViewLogic;
import com.projctrfade.safdjw.ui.dayview.DayViewModel;
import com.projctrfade.safdjw.ui.dayview.IDayViewContract;

public class DayViewInjector {
    public static void build(
            DayView view,
            StorageServiceLocator locator
    ) {
        IDayViewContract.ViewModel vm = new DayViewModel();

        view.setLogic(
                new DayViewLogic(
                        view,
                        vm,
                        locator.getDayStorageImpl(),
                        locator.getTaskStorageImpl()
                )
        );
    }
}
