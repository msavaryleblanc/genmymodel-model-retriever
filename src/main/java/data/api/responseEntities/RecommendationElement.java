package main.java.data.api.responseEntities;

public class RecommendationElement {

    private String name;
    private double reliableRate;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getReliableRate() {
        return reliableRate;
    }

    public void setReliableRate(double reliableRate) {
        this.reliableRate = reliableRate;
    }

    public RecommendationElement(String name, double reliableRate) {
        super();
        this.name = name;
        this.reliableRate = reliableRate;
    }

    public RecommendationElement() {
    }

    @Override
    public String toString() {
        return "ResponseRecommendationElement{" +
                "name='" + name + '\'' +
                ", reliableRate=" + reliableRate +
                '}';
    }
}
