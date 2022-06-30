package main.java.data.api.responseEntities;

import java.util.List;

public class ProjectGeneralResponse {

    List<ProjectResponse> elements;

    public List<ProjectResponse> getProjects() {
        return elements;
    }

    @Override
    public String toString() {
        return "ProjectGeneralResponse{" +
                "projects=" + elements +
                '}';
    }
}
