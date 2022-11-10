package com.projctrfade.safdjw.ui.managehourview.buildlogic;

import com.projctrfade.safdjw.StorageServiceLocator;
import com.projctrfade.safdjw.domain.Hour;
import com.projctrfade.safdjw.ui.managehourview.HourView;
import com.projctrfade.safdjw.ui.managehourview.HourViewLogic;
import com.projctrfade.safdjw.ui.managehourview.HourViewModel;
import com.projctrfade.safdjw.ui.managehourview.IHourContract;

public class HourViewInjector {
    public static void build(
            int hourInt,
            HourView view,
            StorageServiceLocator locator
    ) {
        IHourContract.ViewModel vm = new HourViewModel();
        vm.setHour(
                new Hour(
                        null,
                        hourInt
                )
        );

        view.setLogic(
                new HourViewLogic(
                        view,
                        vm,
                        locator.getDayStorageImpl(),
                        locator.getTaskStorageImpl()
                )
        );
    }
}
