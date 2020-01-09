package io.turntabl.Models_old;

import java.util.Date;

public class Project{
    int project_id;
    Date startDate;
    Date endDate;
    Boolean nextProject;

    public Project() {
    }


    public void setNextProject(Boolean nextProject) {
        this.nextProject = nextProject;
    }

    public int getProjectId() {
        return projectId;
    }

    public void setProjectId(int projectId) {
        this.projectId = projectId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
