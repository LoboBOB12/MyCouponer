package com.projctrfade.safdjw.persistence;

import android.util.Log;

import com.projctrfade.safdjw.ApplicationExecutors;
import com.projctrfade.safdjw.common.Continuation;
import com.projctrfade.safdjw.domain.Day;
import com.projctrfade.safdjw.domain.ITaskStorage;
import com.projctrfade.safdjw.domain.Task;
import com.projctrfade.safdjw.domain.Tasks;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class LocalTaskStorageImpl implements ITaskStorage {
    private ApplicationExecutors exec;
    private final File pathToStorageFile;


    private static final String FILE_NAME = "task.txt";


    public LocalTaskStorageImpl(String fileStorageDirectory,
                                ApplicationExecutors exec
    ) {
        this.exec = exec;
        this.pathToStorageFile = new File(fileStorageDirectory, FILE_NAME);
    }

    @Override
    public void getTasks(Continuation<Tasks> continuation) {
        exec.getBackground().execute(
                () -> {
                    Object data;

                    try {
                        data = loadTasks();
                    } catch (Exception e) {
                        data = e;
                        Log.d("STORAGE", Log.getStackTraceString(e));
                    }

                    final Object finalData = data;

                    exec.getMainThread().execute(
                            () -> {
                                if (finalData instanceof Tasks) continuation.onSuccess(
                                        (Tasks) finalData
                                );
                                else continuation.onException(
                                        (Exception) finalData
                                );

                            }
                    );
                });
    }


    private Tasks loadTasks() throws Exception {
        Tasks tasks;

        try {
        FileInputStream fileInputStream =
                new FileInputStream(this.pathToStorageFile);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

            tasks = (Tasks) objectInputStream.readObject();
            objectInputStream.close();
        } catch (FileNotFoundException e) {
            tasks = preloadData();
        }

        return tasks;
    }

    private Tasks preloadData() throws Exception {
        Tasks tasks = PreloadData.getPreloadedTasks();

        FileOutputStream fileOutputStream =
                new FileOutputStream(this.pathToStorageFile);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(tasks);
        objectOutputStream.close();

        return tasks;
    }


    @Override
    public void getTask(int taskId, Continuation<Task> continuation) {
        exec.getBackground().execute(
                () -> {
                    Object data;

                    try {
                        Tasks tasks = loadTasks();

                        data = tasks.getTaskById(taskId);

                    } catch (Exception e) {
                        data = e;
                    }

                    final Object finalData = data;

                    exec.getMainThread().execute(
                            () -> {
                                if (finalData instanceof Task) continuation.onSuccess(
                                        (Task) finalData
                                );
                                else continuation.onException(
                                        (Exception) finalData
                                );

                            }
                    );
                });
    }


    @Override
    public void updateTask(Task task, Continuation<Void> continuation) {
        exec.getBackground().execute(
                () -> {
                    boolean exceptionOccurred = false;

                    try {
                        updateTaskInStorage(task);
                    } catch (Exception e) {
                        exceptionOccurred = true;
                    }

                    final boolean finalExceptionOccurred = exceptionOccurred;

                    exec.getMainThread().execute(
                            () -> {
                                if (finalExceptionOccurred) continuation.onException(
                                        new Exception()
                                );
                                else continuation.onSuccess(null);
                            }
                    );
                });
    }


    private void updateTaskInStorage(Task task) throws Exception {
        try {
            //retrieve current persisted state
            Task[] tasks = loadTasks().get();

            //update the appropriate task
            for (int index = 0; index < tasks.length; index++) {
                if (tasks[index].getTaskId() == task.getTaskId()) tasks[index] = task;
            }

            FileOutputStream fileOutputStream =
                    new FileOutputStream(this.pathToStorageFile);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(new Tasks(tasks));
            objectOutputStream.close();
        } catch (IOException e) {
            throw new IOException("Unable to access Game Data");
        }
    }
}
