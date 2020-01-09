package io.turntabl.Models_old;

import java.util.List;

public class Dev {
    private int devId;
    private String status;
    private List<Project> devProjects;
    private Leave leave;

    public Dev() {
    }

    public int getDevId() {
        return devId;
    }

    public void setDevId(int devId) {
        this.devId = devId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Project> getDevProjects() {
        return devProjects;
    }

    public void setDevProjects(List<Project> devProjects) {
        this.devProjects = devProjects;
    }

    public Leave getLeave() {
        return leave;
    }

    public void setLeave(Leave leave) {
        this.leave = leave;
    }
}
