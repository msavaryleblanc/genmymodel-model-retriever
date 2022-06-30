package main.java.data.api;

import main.java.data.api.responseEntities.DiagramsResponse;
import main.java.data.api.responseEntities.ProjectGeneralResponse;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Path;
import retrofit2.http.Query;

import java.util.List;

public interface CallService {
    @GET("https://app.genmymodel.com/api/projects/{id}/xmi?withDiagrams=true")
        //@GET("https://apipreprodks.genmymodel.com/projects/{id}/xmi")
    Call<ResponseBody> getXmiString(@Header("Cookie") String sessionIdAndToken, @Path("id") String projectId);

    // https://app.genmymodel.com/api/projects/public?minDataSize=10000
    @GET("https://app.genmymodel.com/api/projects/public?minDataSize=10000")
    //@GET("https://apipreprodks.genmymodel.com/projects/public?minDataSize=10000")
    Call<ProjectGeneralResponse> getProjectList(@Query("page") int pageNumber);

    @GET("https://app.genmymodel.com/api/projects/{id}/diagrams")
        //@GET("https://apipreprodks.genmymodel.com/projects/{id}/xmi")
    Call<List<DiagramsResponse>> getDiagramsList(@Header("Cookie") String sessionIdAndToken, @Path("id") String projectId);


}
