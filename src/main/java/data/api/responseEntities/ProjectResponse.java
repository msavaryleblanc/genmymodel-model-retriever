package main.java.data.api.responseEntities;

import java.util.List;

public class ProjectResponse {
    String projectId;
    String owner;
    String name;
    String type;
    String lastModificationDate;
    String creationDate;
    List<LinkResponse> links;

    public String getProjectId() {
        return projectId;
    }

    public String getOwner() {
        return owner;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLastModificationDate() {
        return lastModificationDate;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public List<LinkResponse> getLinks() {
        return links;
    }

    public String getXmiLink() {
        for (LinkResponse linkResponse : links) {
            if (linkResponse.getRel().equals("xmi")) {
                return linkResponse.getHref();
            }
        }
        return "";
    }

    @Override
    public String toString() {
        return "ProjectResponse{" +
                "projectId='" + projectId + '\'' +
                ", owner='" + owner + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", lastModificationDate='" + lastModificationDate + '\'' +
                ", creationDate='" + creationDate + '\'' +
                ", links=" + links +
                '}';
    }
}
