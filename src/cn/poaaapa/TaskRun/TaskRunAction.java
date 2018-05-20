package cn.poaaapa.TaskRun;

import cn.poaaapa.TaskEdit.TaskAction;
import cn.poaaapa.TaskEdit.TaskEntity;

import cn.poaaapa.crawler.*;

public class TaskRunAction {

    public boolean taskRun(TaskEntity task) throws Exception {
        TaskAction tsa = new TaskAction();
        int taskState = task.getTaskState();
        if(taskState==0 || taskState==2){
            taskState=1;
            task.setTaskState(taskState);
            tsa.updateTask(task);
            if (task.getUrlType() == 0){
                System.out.println("自定义");
            } else if (task.getUrlType() == 1){
                //美团
                GetMtUrl getmt = new GetMtUrl(task.getUrl());
            } else if (task.getUrlType() == 2){
                //天猫
                GetTmallUrl gettm = new GetTmallUrl(task.getUrl());
            } else if (task.getUrlType() == 3){
                //知乎
                GetZhihuUrl getzhihu = new GetZhihuUrl(task.getUrl());
            } else if (task.getUrlType() == 4){
                //百度图片
                GetBaiImgUrl getimg = new GetBaiImgUrl(task.getUrl());
            } else {
                //百度贴吧
                GetBaiduUrl getbd = new GetBaiduUrl(task.getUrl());
            }
        } else{
            return false;
        }
        return true;
    }

    public boolean taskStop(TaskEntity task) throws Exception{
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
