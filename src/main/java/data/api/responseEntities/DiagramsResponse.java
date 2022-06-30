package main.java.data.api.responseEntities;

import java.util.List;

public class DiagramsResponse {
    String projectId;
    String diagramId;
    String name;
    String kind;
    int numberOfElements;
    List<LinkResponse> links;

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getDiagramId() {
        return diagramId;
    }

    public void setDiagramId(String diagramId) {
        this.diagramId = diagramId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public int getNumberOfElements() {
        return numberOfElements;
    }

    public void setNumberOfElements(int numberOfElements) {
        this.numberOfElements = numberOfElements;
    }

    public List<LinkResponse> getLinks() {
        return links;
    }

    public void setLinks(List<LinkResponse> links) {
        this.links = links;
    }

    public String getJpegLink() {
        for (LinkResponse linkResponse : links) {
            if (linkResponse.getRel().equals("jpeg")) {
                return linkResponse.getHref();
            }
        }
        return "";
    }


}
