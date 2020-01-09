package io.turntabl.serviceImpl_old;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.turntabl.Models_old.Dev;
import io.turntabl.Models_old.Project;
import io.turntabl.service.IAvailableDeveloper;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;


public class DeveloperServiceImpl implements IAvailableDeveloper {

    public static void main(String[] args) {

    }

    public List<Dev> getAllAvailableDevelopers2() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(""))
                .build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        String resp = response.body();
        JsonElement jsonElement = new JsonParser().parse(resp);

        JsonArray employeeElement = jsonElement
                .getAsJsonObject()
                .getAsJsonArray();

        Type listType = new TypeToken<List<JsonElement>>() {}.getType();
        List<JsonElement> employeeList = new Gson().fromJson(employeeElement, listType);

        List<JsonElement> elevation = employeeList.stream()
                .filter(e -> e.getAsJsonObject().get("employee_onleave").toString() == "false")
                .collect(Collectors.toList());

        Project project = new Project();
        Date newProjStartDate = project.getStartDate();
        Date newProjEndDate = project.getEndDate();

        Project nextProject = new Project();

        List<Dev> devs = getAllDevs();

        List<Dev> devsPresent = new ArrayList<>();
        List<Dev> devsOnLeave = new ArrayList<>();

        for(Dev developer : devs){
            if(developer.getStatus() == "unAvailable"){
//                Leave devsOnLeave = getAllDevsOnLeave(developer.devId);
//                if(devsOnLeave.endLeave < projectStartDate){
//                    availableDevs.add(developer);
//                }
//                availableDevs.add(developer);
            }
            else {
                devsPresent.add(developer);
            }
        }

        // update dev project history
        List<Dev> availableDev = new ArrayList<>();
        for (Dev devPresent : devsPresent) {
            List<Project> devPresentProjectHistory = devPresent.getDevProjects();
            for (Project prj: devPresentProjectHistory) {
                if (prj.getEndDate().compareTo(LocalDate.now()) > 0) {
                    devPresentProjectHistory.add(prj);
                }
            }
            availableDev.add(devPresent);
        }

        List<Dev> availDevToConsider = new ArrayList<>();
        for (Dev dev : availableDev) {
            for (Project pr : dev.getDevProjects()) {
                if (newProjStartDate.compareTo(pr.getEndDate())) {
                    if((nextProject.getNextProject())){
                        (nextProject.getStartDate().compareTo(newProjStartDate);
                        availDevToConsider.add(dev);
                        break;
                    }
                    availDevToConsider.add(dev);
                }
            }
        }
        return null;
    }


    @Override
    public List<Dev> getAllAvailableDevelopers() {
        Project project = new Project();
        Date newProjStartDate = project.getStartDate();
        Date newProjEndDate = project.getEndDate();

        Project nextProject = new Project();

        List<Dev> devs = getAllDevs();

        List<Dev> devsPresent = new ArrayList<>();
        List<Dev> devsOnLeave = new ArrayList<>();

        for(Dev developer : devs){
            if(developer.getStatus() == "unAvailable"){
//                Leave devsOnLeave = getAllDevsOnLeave(developer.devId);
//                if(devsOnLeave.endLeave < projectStartDate){
//                    availableDevs.add(developer);
//                }
//                availableDevs.add(developer);
            }
            else {
                devsPresent.add(developer);
            }
        }

        // update dev project history
        List<Dev> availableDev = new ArrayList<>();
        for (Dev devPresent : devsPresent) {
            List<Project> devPresentProjectHistory = devPresent.getDevProjects();
            for (Project prj: devPresentProjectHistory) {
                if (prj.getEndDate().compareTo(LocalDate.now()) > 0) {
                    devPresentProjectHistory.add(prj);
                }
            }
            availableDev.add(devPresent);
        }

        List<Dev> availDevToConsider = new ArrayList<>();
        for (Dev dev : availableDev) {
            for (Project pr : dev.getDevProjects()) {
                if (newProjStartDate.compareTo(pr.getEndDate())) {
                    if((nextProject.getNextProject())){
                        (nextProject.getStartDate().compareTo(newProjStartDate);
                        availDevToConsider.add(dev);
                        break;
                    }
                    availDevToConsider.add(dev);
                }
            }
        }

        return availDevToConsider;
    }

    private List<Dev> getAllDevs() {
        return null;
    }

}
