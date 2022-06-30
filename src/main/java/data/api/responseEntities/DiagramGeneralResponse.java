package main.java.data.api.responseEntities;

import java.util.List;

public class DiagramGeneralResponse {

    List<DiagramsResponse> elements;

    public List<DiagramsResponse> getProjects() {
        return elements;
    }

    @Override
    public String toString() {
        return "DiagramsGeneralResponse{" +
                "projects=" + elements +
                '}';
    }
}
