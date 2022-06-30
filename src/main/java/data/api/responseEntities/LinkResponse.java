package main.java.data.api.responseEntities;

public class LinkResponse {
    String rel;
    String href;

    public String getRel() {
        return rel;
    }

    public String getHref() {
        return href;
    }

    @Override
    public String toString() {
        return "LinkResponse{" +
                "rel='" + rel + '\'' +
                ", href='" + href + '\'' +
                '}';
    }
}
