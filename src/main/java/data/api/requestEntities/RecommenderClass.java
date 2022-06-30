package main.java.data.api.requestEntities;

import java.util.List;

public class RecommenderClass {

    String name;
    List<String> properties;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProperties() {
        return properties;
    }

    public void setProperties(List<String> properties) {
        this.properties = properties;
    }

    @Override
    public String toString() {
        return "RecommenderClass{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                '}';
    }
}
