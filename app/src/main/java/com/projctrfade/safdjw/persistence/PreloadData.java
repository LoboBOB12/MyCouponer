package com.projctrfade.safdjw.persistence;

import com.projctrfade.safdjw.domain.Day;
import com.projctrfade.safdjw.domain.Hour;
import com.projctrfade.safdjw.domain.QuarterHour;
import com.projctrfade.safdjw.domain.Task;
import com.projctrfade.safdjw.domain.Tasks;
import com.projctrfade.safdjw.domain.constants.COLOR;
import com.projctrfade.safdjw.domain.constants.HOUR_MODE;
import com.projctrfade.safdjw.domain.constants.ICON;
import com.projctrfade.safdjw.domain.constants.QUARTER;

import static com.projctrfade.safdjw.domain.constants.COLOR.*;
import static com.projctrfade.safdjw.domain.constants.ICON.*;


class PreloadData {

    static Tasks getPreloadedTasks() {
        return new Tasks(
                new Task[]{
                        new Task(0, "Free Time", FREE_TIME, GREEN),
                        new Task(1, "Break", BREAK, DARK_BLUE),
                        new Task(2, "Study", STUDY, TEAL),
                        new Task(3, "Work", WORK, DARK_RED),
                        new Task(4, "Exercise", EXERCISE, BURNT_ORANGE),
                        new Task(5, "Meditate", MENTAL_CULTIVATION, LIGHT_BLUE),
                        new Task(6, "Eat", EAT, BROWN),
                        new Task(7, "Sleep", SLEEP, MAUVE),
                        new Task(8, "Shop", SHOP, DARK_LIME)
                }
        );
    }

    static Day getPreloadedDay() {
        return new Day(
                HOUR_MODE.TWELVE_HOUR,
                new Hour[]{
                        //sleep 22-6 hours (10pm-6am)
                        getHour(7,0),
                        getHour(7,1),
                        getHour(7,2),
                        getHour(7,3),
                        getHour(7,4),
                        getHour(7,5),
                        //Exercise half hour
                        //Mental Cultivation half hour
                        new Hour(
                                new QuarterHour[]{
                                        new QuarterHour(4, QUARTER.ZERO, true),
                                        new QuarterHour(4, QUARTER.FIFTEEN, false),
                                        new QuarterHour(5, QUARTER.THIRTY, true),
                                        new QuarterHour(5, QUARTER.FOURTY_FIVE, false)
                                },
                                6
                        ),
                        //work from 7-11
                        getHour(3,7),
                        getHour(3,8),
                        getHour(3,9),
                        getHour(3,10),
                        //eat half hour
                        //break half hour
                        new Hour(
                                new QuarterHour[]{
                                        new QuarterHour(6, QUARTER.ZERO, true),
                                        new QuarterHour(6, QUARTER.FIFTEEN, false),
                                        new QuarterHour(1, QUARTER.THIRTY, true),
                                        new QuarterHour(1, QUARTER.FOURTY_FIVE, false)
                                },
                                11
                        ),
                        //work four hours
                        getHour(3,12),
                        getHour(3,13),
                        getHour(3,14),
                        getHour(3,15),
                        // 4pm Eat half hour
                        // Shop half hour
                        new Hour(
                                new QuarterHour[]{
                                        new QuarterHour(6, QUARTER.ZERO, true),
                                        new QuarterHour(6, QUARTER.FIFTEEN, false),
                                        new QuarterHour(8, QUARTER.THIRTY, true),
                                        new QuarterHour(8, QUARTER.FOURTY_FIVE, false)
                                },
                                16
                        ),
                        // 5pm Free time four hours
                        getHour(0, 17),
                        getHour(0, 18),
                        getHour(0, 19),
                        getHour(0, 20),
                        //9pm half hour study
                        //half hour meditate
                        new Hour(
                                new QuarterHour[]{
                                        new QuarterHour(2, QUARTER.ZERO, true),
                                        new QuarterHour(2, QUARTER.FIFTEEN, false),
                                        new QuarterHour(5, QUARTER.THIRTY, true),
                                        new QuarterHour(5, QUARTER.FOURTY_FIVE, false)
                                },
                                21
                        ),
                        //10pm sleep
                        getHour(7,22),
                        getHour(7,23)
                }
        );
    }


    static Hour getHour(int taskId, int hourInt) {
        return new Hour(
                new QuarterHour[]{
                        new QuarterHour(taskId, QUARTER.ZERO, true),
                        new QuarterHour(taskId, QUARTER.FIFTEEN, false),
                        new QuarterHour(taskId, QUARTER.THIRTY, false),
                        new QuarterHour(taskId, QUARTER.FOURTY_FIVE, false)
                },
                hourInt
        );
    }
}
