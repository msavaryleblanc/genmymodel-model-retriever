package main.java.data.api.responseEntities;

public class ClassRecommendationElement extends RecommendationElement {
    String relationType;

    public ClassRecommendationElement(String name, double reliableRate) {
        super(name, reliableRate);
    }

    public ClassRecommendationElement() {
    }

    public String getRelationType() {
        return relationType;
    }

    public void setRelationType(String relationType) {
        this.relationType = relationType;
    }

    @Override
    public String toString() {
        return "ResponseClassRecommendationElement{" +
                "relationType='" + relationType + '\'' +
                '}' + super.toString();
    }
}
