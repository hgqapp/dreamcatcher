package com.hgq.dreamcatcher.study;

import java.util.List;

public class CourseInfo {

    private String name;
    private String status;
    private Double progress;
    private List<String> detail;
    private List<Courseware> coursewares;

    public CourseInfo(String name, String status, Double progress, List<String> detail, List<Courseware> coursewares) {
        this.name = name;
        this.status = status;
        this.progress = progress;
        this.detail = detail;
        this.coursewares = coursewares;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getProgress() {
        return progress;
    }

    public void setProgress(Double progress) {
        this.progress = progress;
    }

    public List<String> getDetail() {
        return detail;
    }

    public void setDetail(List<String> detail) {
        this.detail = detail;
    }

    public List<Courseware> getCoursewares() {
        return coursewares;
    }

    public void setCoursewares(List<Courseware> coursewares) {
        this.coursewares = coursewares;
    }
}
