package cn.poaaapa.TaskRun;

import cn.poaaapa.TaskEdit.TaskAction;
import cn.poaaapa.TaskEdit.TaskEntity;

public class TaskRunAction {

    public boolean taskRun(TaskEntity task){
        TaskAction tsa = new TaskAction();
        int taskState = task.getTaskState();
        if(taskState==0 || taskState==2){
            taskState=1;
            task.setTaskState(taskState);
            tsa.updateTask(task);
        } else{
            return false;
        }
        return true;
    }

    public boolean taskStop(TaskEntity task){
        TaskAction tsa = new TaskAction();
        int taskState = task.getTaskState();
        if (taskState ==1){
            taskState = 2;
            task.setTaskState(taskState);
            tsa.updateTask(task);
        }else {
            return  false;
        }

        return true;

    }

    public void xlsDownload(int id){

    }

}
