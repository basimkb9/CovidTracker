package io.TBZ.CovidTracker.Services;

import io.TBZ.CovidTracker.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;


@Service
public class CoronaVirusDataService {
    private static String Confirmed_Cases_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static String Confirmed_Deaths_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    private static String Confirmed_Recovered_URL = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    private List<LocationStats> allStats_CC = new ArrayList<>();// created an array list for locations.
    private List<LocationStats> allStats_CD = new ArrayList<>();
    private List<LocationStats> allStats_CR = new ArrayList<>();


    public List<LocationStats> getAllStats_CC() {
        return allStats_CC;
    }

    public List<LocationStats> getAllStats_CD() {
        return allStats_CD;
    }

    public List<LocationStats> getAllStats_CR() {
        return allStats_CR;
    }

    public static String getConfirmed_Cases_URL() {
        return Confirmed_Cases_URL;
    }

    public static String getConfirmed_Deaths_URL() {
        return Confirmed_Deaths_URL;
    }

    public static String getConfirmed_Recovered_URL() {
        return Confirmed_Recovered_URL;
    }

    @PostConstruct  //telling program to execute this method when you construct this service.
    @Scheduled(cron = "* * 5 * * *") // scheduling program to run on fifth hour of everyday.
    public void fetchVirusData() throws IOException, InterruptedException    // Added Exceptions so that if client send fails the program won't crash
    {
        List<LocationStats> newStats_CC= new ArrayList<>(); // i made this list for concurrency because I dont want people to get error while we are updating this.
        List<LocationStats> newStats_CD= new ArrayList<>();
        List<LocationStats> newStats_CR= new ArrayList<>();
        // For Confirmed Cases
        HttpClient client_CC = HttpClient.newHttpClient();   // Http call
        HttpRequest request_CC = HttpRequest.newBuilder()    // Creating a Http Request
                .uri(URI.create(Confirmed_Cases_URL))          // Creating URI
                .build();
        HttpResponse<String> httpResponse_CC = client_CC.send(request_CC, HttpResponse.BodyHandlers.ofString());  // sending Request and returning body as a string
       StringReader csvBodyReader_CC = new StringReader(httpResponse_CC.body());

        // For Confirmed Deaths
        HttpClient client_CD = HttpClient.newHttpClient();   // Http call
        HttpRequest request_CD = HttpRequest.newBuilder()    // Creating a Http Request
                .uri(URI.create(Confirmed_Deaths_URL))          // Creating URI
                .build();
        HttpResponse<String> httpResponse_CD = client_CD.send(request_CD, HttpResponse.BodyHandlers.ofString());  // sending Request and returning body as a string
        StringReader csvBodyReader_CD = new StringReader(httpResponse_CD.body());

        // For Confirmed Recovered
        HttpClient client_CR = HttpClient.newHttpClient();   // Http call
        HttpRequest request_CR = HttpRequest.newBuilder()    // Creating a Http Request
                .uri(URI.create(Confirmed_Recovered_URL))          // Creating URI
                .build();
        HttpResponse<String> httpResponse_CR = client_CR.send(request_CR, HttpResponse.BodyHandlers.ofString());  // sending Request and returning body as a string
        StringReader csvBodyReader_CR = new StringReader(httpResponse_CR.body());

        // Parsing..

        Iterable<CSVRecord> records_CC = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader_CC);
        Iterable<CSVRecord> records_CD = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader_CD);
        Iterable<CSVRecord> records_CR = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyReader_CR);

        for(CSVRecord record : records_CC)
        {
            LocationStats locationStat_CC = new LocationStats();
            locationStat_CC.setState(record.get("Province/State"));
            locationStat_CC.setCountry(record.get("Country/Region"));

            int latestCases = Integer.parseInt(record.get(record.size() - 1));
            int prevDayCases = Integer.parseInt(record.get(record.size() - 2));

            locationStat_CC.setConfirmedCases(latestCases);
            locationStat_CC.setDiffFromPrevDay(latestCases - prevDayCases);
//          System.out.println(locationStat_CC);
            newStats_CC.add(locationStat_CC);
        }

        for(CSVRecord record : records_CD)
        {
            LocationStats locationStat_CD = new LocationStats();
            locationStat_CD.setState(record.get("Province/State"));
            locationStat_CD.setCountry(record.get("Country/Region"));
            locationStat_CD.setConfirmedDeaths(Integer.parseInt(record.get(record.size() - 1)));
//          System.out.println(locationStat_CD);
            newStats_CD.add(locationStat_CD);
        }

        for(CSVRecord record : records_CR)
        {
            LocationStats locationStat_CR = new LocationStats();
            locationStat_CR.setState(record.get("Province/State"));
            locationStat_CR.setCountry(record.get("Country/Region"));
            locationStat_CR.setConfirmedRecovered(Integer.parseInt(record.get(record.size() - 1)));
//          System.out.println(locationStat_CR);
            newStats_CR.add(locationStat_CR);
        }

        this.allStats_CC = newStats_CC;
        this.allStats_CD = newStats_CD;
        this.allStats_CR = newStats_CR;

    }
}
