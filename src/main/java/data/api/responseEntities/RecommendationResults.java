package main.java.data.api.responseEntities;

import java.util.List;

public class RecommendationResults {

    List<RecommendationElement> recommendationElementList;
    List<ClassRecommendationElement> classRecommendationElementList;

    public List<RecommendationElement> getRecommendationElementList() {
        return recommendationElementList;
    }

    public void setRecommendationElementList(List<RecommendationElement> recommendationElementList) {
        this.recommendationElementList = recommendationElementList;
    }

    public List<ClassRecommendationElement> getClassRecommendationElementList() {
        return classRecommendationElementList;
    }

    public void setClassRecommendationElementList(List<ClassRecommendationElement> classRecommendationElementList) {
        this.classRecommendationElementList = classRecommendationElementList;
    }

    @Override
    public String toString() {
        return "ResponseRecommendationResults{" +
                "recommendationElementList=" + recommendationElementList +
                ", classRecommendationElementList=" + classRecommendationElementList +
                '}';
    }
}
