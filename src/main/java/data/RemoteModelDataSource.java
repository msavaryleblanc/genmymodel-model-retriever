package main.java.data;

import com.google.gson.Gson;
import main.java.data.api.CallService;
import main.java.data.api.RetrofitProvider;
import main.java.data.api.responseEntities.DiagramsResponse;
import main.java.data.api.responseEntities.ProjectGeneralResponse;
import main.java.data.api.responseEntities.ProjectResponse;
import okhttp3.ResponseBody;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.List;

@Service
public class RemoteModelDataSource {

    CallService callService;

    Gson gson;
    CSVPrinter csvPrinter;

    public RemoteModelDataSource() {
        this.callService = RetrofitProvider.getRetrofit().create(CallService.class);

        try {
            String outputFileName = "file-download.csv";
            String[] headers = new String[]{
                    "project_name",
                    "project_owner",
                    "project_creation_date",
                    "project_id",
                    "project_last_modif_date",
                    "project_xmi",
                    "project_type",
                    "project_downloaded",
                    "diagram_project_id",
                    "diagram_id",
                    "diagram_name",
                    "diagram_kind",
                    "diagram_jpeg_link",
                    "diagram_number_of_elements"
            };
            Writer writer = new FileWriter(outputFileName);
            this.csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT.withHeader(headers));
        } catch (Exception e) {
            System.out.println("Couldnt start CSV printer");
        }


    }

    public void getAllModelsUntilPage(int pageMin, int pageMax) {

        if (pageMax == -1) {
            pageMax = 4552;
        }
        for (int i = pageMin; i < pageMax + 1; i++) {
            System.out.println((i * 100.0d) / (pageMax * 1.0d) + "% ----- PAGE : " + i + " -----");
            try {
                getModelsFromPage(i);
                /*System.out.println("Writing graph ...");
                String json = gson.toJson(graph);
                try (PrintWriter out = new PrintWriter("graph.json")) {
                    out.println(json);
                } catch (IOException io) {
                    System.out.println("Error while writting graph to file" + io.getMessage());
                }*/
            }
            //TODO delete, just not to loose everything now :)
            catch (Exception e) {
                System.out.println("ERROR : " + e.getMessage());
                e.printStackTrace();
            }
        }
        //return this.graph;
    }

    public void getModelsFromPage(int pageNumber) {
        try {

            ProjectGeneralResponse pgr = callService.getProjectList(pageNumber).execute().body();
            System.out.println(pgr.toString());
            int counter = 1;
            for (ProjectResponse projectResponse : pgr.getProjects()) {


                if (projectResponse.getType().equals("UML") || projectResponse.getType().equals("FLOWCHART")) {
                    System.out.println(counter + "/" + pgr.getProjects().size());
                    getProjectModel(projectResponse.getProjectId(), projectResponse.getType(), pageNumber, projectResponse);
                    counter++;
                } else {
                    csvPrinter.printRecord(projectResponse.getName(),
                            projectResponse.getOwner(),
                            projectResponse.getCreationDate(),
                            projectResponse.getProjectId(),
                            projectResponse.getLastModificationDate(),
                            projectResponse.getXmiLink(),
                            projectResponse.getType(),
                            "No",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                    );
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void getProjectModel(String projectId, String type, int pageNumber, ProjectResponse projectResponse) {
        //Cookie de la request car il faut être connecté ...
        String cookie = "JSESSIONID=770C45038AE061D4B1B9DA999B75218C; _ga=GA1.2.1465889423.1577396624; _gid=GA1.2.1593196309.1656510683; JSESSIONID=3394796017F001103BF6D64F27DD1B27; _gat=1; AWSALB=SaMDF0JOC5F1/nQ1oQ1dhal4PyOTD9jRXwpU9XHBBTgWggJW7EGIdMKe4k7mvOhhnBKRK1E6mdD+uIjS2N8p7nEQBUGYHsz0oyA0A3sZ8HZXGnxKPagDrQ04+sif; AWSALBCORS=SaMDF0JOC5F1/nQ1oQ1dhal4PyOTD9jRXwpU9XHBBTgWggJW7EGIdMKe4k7mvOhhnBKRK1E6mdD+uIjS2N8p7nEQBUGYHsz0oyA0A3sZ8HZXGnxKPagDrQ04+sif";
        try {
            ResponseBody responseBody = callService.getXmiString(cookie, projectId).execute().body();
            String body = responseBody.string();
            createFile(projectId, body, type, pageNumber);

            List<DiagramsResponse> diagramGeneralResponse = callService.getDiagramsList(cookie, projectId).execute().body();
            for (DiagramsResponse diagramsResponse : diagramGeneralResponse) {
                csvPrinter.printRecord(projectResponse.getName(),
                        projectResponse.getOwner(),
                        projectResponse.getCreationDate(),
                        projectResponse.getProjectId(),
                        projectResponse.getLastModificationDate(),
                        projectResponse.getXmiLink(),
                        projectResponse.getType(),
                        "Yes",
                        diagramsResponse.getProjectId(),
                        diagramsResponse.getDiagramId(),
                        diagramsResponse.getName(),
                        diagramsResponse.getKind(),
                        diagramsResponse.getJpegLink(),
                        diagramsResponse.getNumberOfElements()
                );
            }


            //Model model = xmiModelMapper.readXmiModel(body);
            //MappingModel mm = umlModelToMappingModelMapper.mapUmlModel(model);
            //mappingModelToGraphElementsMapper.mapMappingModel(mm);
        } catch (IOException e) {
            e.printStackTrace();
            try {
                csvPrinter.printRecord(projectResponse.getName(),
                        projectResponse.getOwner(),
                        projectResponse.getCreationDate(),
                        projectResponse.getProjectId(),
                        projectResponse.getLastModificationDate(),
                        projectResponse.getXmiLink(),
                        projectResponse.getType(),
                        "ERROR",
                        "",
                        "",
                        "",
                        "",
                        "",
                        ""
                );
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    private void createFile(String id, String body, String type, int pageNumber) {
        File file = new File("./xmi/" + pageNumber + "-" + type + "-" + id + ".xmi");
        try {
            file.createNewFile();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        try {
            PrintWriter pw = new PrintWriter(file);
            pw.print(body);
            pw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
