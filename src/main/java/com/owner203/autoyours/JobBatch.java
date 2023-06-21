package com.owner203.autoyours;

import java.io.File;
import java.net.URI;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.moandjiezana.toml.Toml;

@Component
public class JobBatch {

    private static final String CONFIG_FILE_PATH = ".";
    private static final String CONFIG_FILE_NAME = "config.toml";

    private Config config;
    private List<Long> todo = new ArrayList<Long>();
    private String cookie;

    public int execute() {
        if (configLoad() != 0) return 1;
        if (todoGenerate() != 0) return 1;
        if (accountLogin() != 0) return 1;
        for (long start_unixtime : todo) {
            if (bookingRequest(start_unixtime) != 0) return 1;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }
        if (bookingList() != 0) return 1;
        return 0;
    }

    private int configLoad() {
        System.out.println("[configLoad]Begin");
        File configFile = new File(String.format("%s/%s", CONFIG_FILE_PATH, CONFIG_FILE_NAME));
        try {
            config = new Toml().read(configFile).to(Config.class);
        } catch (Exception ex) {
            // bad config file
            System.out.println("Bad config file.");
            System.out.println("[configLoad]End");
            return 1;
        }

        System.out.println("[account]");
        System.out.println("login_id: " + config.account.login_id);
        System.out.println("password: " + config.account.password);
        System.out.println("customer_id: " + config.account.customer_id);
        System.out.println("customer_company_name: " + config.account.customer_company_name);
        System.out.println("customer_name: " + config.account.customer_name);
        System.out.println("customer_email: " + config.account.customer_email);
        System.out.println("[setups]");
        System.out.println("service_id: " + config.setups.service_id);
        System.out.println("service_menu_id: " + config.setups.service_menu_id);
        System.out.println("next_monday1: " + config.setups.next_monday1);
        System.out.println("next_tuesday1: " + config.setups.next_tuesday1);
        System.out.println("next_wednesday1: " + config.setups.next_wednesday1);
        System.out.println("next_thursday1: " + config.setups.next_thursday1);
        System.out.println("next_friday1: " + config.setups.next_friday1);
        System.out.println("next_monday2: " + config.setups.next_monday2);
        System.out.println("next_tuesday2: " + config.setups.next_tuesday2);
        System.out.println("next_wednesday2: " + config.setups.next_wednesday2);
        System.out.println("next_thursday2: " + config.setups.next_thursday2);
        System.out.println("next_friday2: " + config.setups.next_friday2);
        System.out.println("[configLoad]End");
        return 0;
    }

    private int todoGenerate() {
        System.out.println("[todoGenerate]Begin");
        final LocalDate NOW = LocalDate.now();
        final LocalDate NEXT_MONDAY1 = NOW.plusWeeks(1).with(DayOfWeek.MONDAY);
        final LocalDate NEXT_TUESDAY1 = NOW.plusWeeks(1).with(DayOfWeek.TUESDAY);
        final LocalDate NEXT_WEDNESDAY1 = NOW.plusWeeks(1).with(DayOfWeek.WEDNESDAY);
        final LocalDate NEXT_THURSDAY1 = NOW.plusWeeks(1).with(DayOfWeek.THURSDAY);
        final LocalDate NEXT_FRIDAY1 = NOW.plusWeeks(1).with(DayOfWeek.FRIDAY);
        final LocalDate NEXT_MONDAY2 = NOW.plusWeeks(2).with(DayOfWeek.MONDAY);
        final LocalDate NEXT_TUESDAY2 = NOW.plusWeeks(2).with(DayOfWeek.TUESDAY);
        final LocalDate NEXT_WEDNESDAY2 = NOW.plusWeeks(2).with(DayOfWeek.WEDNESDAY);
        final LocalDate NEXT_THURSDAY2 = NOW.plusWeeks(2).with(DayOfWeek.THURSDAY);
        final LocalDate NEXT_FRIDAY2 = NOW.plusWeeks(2).with(DayOfWeek.FRIDAY);

        Timestamp timestamp;
        switch (config.setups.next_monday1) {
            case 1200: timestamp = Timestamp.valueOf(NEXT_MONDAY1.atTime(12, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1215: timestamp = Timestamp.valueOf(NEXT_MONDAY1.atTime(12, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1230: timestamp = Timestamp.valueOf(NEXT_MONDAY1.atTime(12, 30)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1245: timestamp = Timestamp.valueOf(NEXT_MONDAY1.atTime(12, 45)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1300: timestamp = Timestamp.valueOf(NEXT_MONDAY1.atTime(13, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1315: timestamp = Timestamp.valueOf(NEXT_MONDAY1.atTime(13, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            default: break;
        }
        switch (config.setups.next_tuesday1) {
            case 1200: timestamp = Timestamp.valueOf(NEXT_TUESDAY1.atTime(12, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1215: timestamp = Timestamp.valueOf(NEXT_TUESDAY1.atTime(12, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1230: timestamp = Timestamp.valueOf(NEXT_TUESDAY1.atTime(12, 30)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1245: timestamp = Timestamp.valueOf(NEXT_TUESDAY1.atTime(12, 45)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1300: timestamp = Timestamp.valueOf(NEXT_TUESDAY1.atTime(13, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1315: timestamp = Timestamp.valueOf(NEXT_TUESDAY1.atTime(13, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            default: break;
        }
        switch (config.setups.next_wednesday1) {
            case 1200: timestamp = Timestamp.valueOf(NEXT_WEDNESDAY1.atTime(12, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1215: timestamp = Timestamp.valueOf(NEXT_WEDNESDAY1.atTime(12, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1230: timestamp = Timestamp.valueOf(NEXT_WEDNESDAY1.atTime(12, 30)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1245: timestamp = Timestamp.valueOf(NEXT_WEDNESDAY1.atTime(12, 45)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1300: timestamp = Timestamp.valueOf(NEXT_WEDNESDAY1.atTime(13, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1315: timestamp = Timestamp.valueOf(NEXT_WEDNESDAY1.atTime(13, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            default: break;
        }
        switch (config.setups.next_thursday1) {
            case 1200: timestamp = Timestamp.valueOf(NEXT_THURSDAY1.atTime(12, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1215: timestamp = Timestamp.valueOf(NEXT_THURSDAY1.atTime(12, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1230: timestamp = Timestamp.valueOf(NEXT_THURSDAY1.atTime(12, 30)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1245: timestamp = Timestamp.valueOf(NEXT_THURSDAY1.atTime(12, 45)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1300: timestamp = Timestamp.valueOf(NEXT_THURSDAY1.atTime(13, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1315: timestamp = Timestamp.valueOf(NEXT_THURSDAY1.atTime(13, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            default: break;
        }
        switch (config.setups.next_friday1) {
            case 1200: timestamp = Timestamp.valueOf(NEXT_FRIDAY1.atTime(12, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1215: timestamp = Timestamp.valueOf(NEXT_FRIDAY1.atTime(12, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1230: timestamp = Timestamp.valueOf(NEXT_FRIDAY1.atTime(12, 30)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1245: timestamp = Timestamp.valueOf(NEXT_FRIDAY1.atTime(12, 45)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1300: timestamp = Timestamp.valueOf(NEXT_FRIDAY1.atTime(13, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1315: timestamp = Timestamp.valueOf(NEXT_FRIDAY1.atTime(13, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            default: break;
        }
        switch (config.setups.next_monday2) {
            case 1200: timestamp = Timestamp.valueOf(NEXT_MONDAY2.atTime(12, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1215: timestamp = Timestamp.valueOf(NEXT_MONDAY2.atTime(12, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1230: timestamp = Timestamp.valueOf(NEXT_MONDAY2.atTime(12, 30)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1245: timestamp = Timestamp.valueOf(NEXT_MONDAY2.atTime(12, 45)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1300: timestamp = Timestamp.valueOf(NEXT_MONDAY2.atTime(13, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1315: timestamp = Timestamp.valueOf(NEXT_MONDAY2.atTime(13, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            default: break;
        }
        switch (config.setups.next_tuesday2) {
            case 1200: timestamp = Timestamp.valueOf(NEXT_TUESDAY2.atTime(12, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1215: timestamp = Timestamp.valueOf(NEXT_TUESDAY2.atTime(12, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1230: timestamp = Timestamp.valueOf(NEXT_TUESDAY2.atTime(12, 30)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1245: timestamp = Timestamp.valueOf(NEXT_TUESDAY2.atTime(12, 45)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1300: timestamp = Timestamp.valueOf(NEXT_TUESDAY2.atTime(13, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1315: timestamp = Timestamp.valueOf(NEXT_TUESDAY2.atTime(13, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            default: break;
        }
        switch (config.setups.next_wednesday2) {
            case 1200: timestamp = Timestamp.valueOf(NEXT_WEDNESDAY2.atTime(12, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1215: timestamp = Timestamp.valueOf(NEXT_WEDNESDAY2.atTime(12, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1230: timestamp = Timestamp.valueOf(NEXT_WEDNESDAY2.atTime(12, 30)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1245: timestamp = Timestamp.valueOf(NEXT_WEDNESDAY2.atTime(12, 45)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1300: timestamp = Timestamp.valueOf(NEXT_WEDNESDAY2.atTime(13, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1315: timestamp = Timestamp.valueOf(NEXT_WEDNESDAY2.atTime(13, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            default: break;
        }
        switch (config.setups.next_thursday2) {
            case 1200: timestamp = Timestamp.valueOf(NEXT_THURSDAY2.atTime(12, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1215: timestamp = Timestamp.valueOf(NEXT_THURSDAY2.atTime(12, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1230: timestamp = Timestamp.valueOf(NEXT_THURSDAY2.atTime(12, 30)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1245: timestamp = Timestamp.valueOf(NEXT_THURSDAY2.atTime(12, 45)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1300: timestamp = Timestamp.valueOf(NEXT_THURSDAY2.atTime(13, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1315: timestamp = Timestamp.valueOf(NEXT_THURSDAY2.atTime(13, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            default: break;
        }
        switch (config.setups.next_friday2) {
            case 1200: timestamp = Timestamp.valueOf(NEXT_FRIDAY2.atTime(12, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1215: timestamp = Timestamp.valueOf(NEXT_FRIDAY2.atTime(12, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1230: timestamp = Timestamp.valueOf(NEXT_FRIDAY2.atTime(12, 30)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1245: timestamp = Timestamp.valueOf(NEXT_FRIDAY2.atTime(12, 45)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1300: timestamp = Timestamp.valueOf(NEXT_FRIDAY2.atTime(13, 0)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            case 1315: timestamp = Timestamp.valueOf(NEXT_FRIDAY2.atTime(13, 15)); System.out.println(timestamp); todo.add(timestamp.getTime()/1000); break;
            default: break;
        }

        if (Objects.isNull(todo) || todo.isEmpty()) {
            System.out.println("Null todo list.");
            System.out.println("[todoGenerate]End");
            return 1;
        } else {
            System.out.println(todo);
            System.out.println("[todoGenerate]End");
            return 0;
        }
    }

    private int accountLogin() {
        System.out.println("[accountLogin]Begin");
        URI uri = UriComponentsBuilder.fromUriString("https://gmoyours.dt-r.com/customer/ajaxLogin.php")
                                            .queryParam("action", "login")
                                            .queryParam("login_id", config.account.login_id)
                                            .queryParam("password", config.account.password)
                                            .build().encode().toUri();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept", "*/*");
        requestHeaders.set("User-Agent", "Thunder Client (https://www.thunderclient.com)");

        HttpEntity<Void> requestEntity = new HttpEntity<Void>(requestHeaders);
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(uri, HttpMethod.POST, requestEntity, String.class);

        HttpHeaders responseHeaders;
        String responseBody = "";
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            responseHeaders = responseEntity.getHeaders();
            cookie = responseHeaders.getFirst(HttpHeaders.SET_COOKIE);
            responseBody = responseEntity.getBody();
        } else {
            System.out.println(String.format("Error HTTP status. (%s)", responseEntity.getStatusCode().toString()));
            System.out.println("[accountLogin]End");
            return 1;
        }
        
        if (responseBody.equals("1")) {
            System.out.println("Login succeeded.");
            System.out.println(cookie);
            System.out.println("[accountLogin]End");
            return 0;
        } else {
            System.out.println("Login failed.");
            System.out.println("[accountLogin]End");
            return 1;
        }
    }

    private int bookingRequest(long start_unixtime) {
        System.out.println("[bookingRequest]Begin");
        URI uri = UriComponentsBuilder.fromUriString("https://gmoyours.dt-r.com/reservation/ajaxBooking.php")
                                            .queryParam("action", "regist")
                                            .queryParam("booking_data[calendar_id]", config.setups.service_id + "." + config.setups.service_menu_id + ".." + String.valueOf(start_unixtime) + "." + String.valueOf(start_unixtime + 1800))
                                            .queryParam("booking_data[service_id]", config.setups.service_id)
                                            .queryParam("booking_data[service_menu_id]", config.setups.service_menu_id)
                                            .queryParam("booking_data[start_unixtime]", String.valueOf(start_unixtime))
                                            .queryParam("booking_data[end_unixtime]", String.valueOf(start_unixtime + 1800))
                                            .queryParam("booking_data[num]", String.valueOf(1))
                                            .queryParam("booking_data[customer_id]", config.account.customer_id)
                                            .queryParam("booking_data[customer_company_name]", config.account.customer_company_name)
                                            .queryParam("booking_data[customer_name]", config.account.customer_name)
                                            .queryParam("booking_data[customer_email]", config.account.customer_email)
                                            .queryParam("confirm", String.valueOf(1))
                                            .build().encode().toUri();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept", "*/*");
        requestHeaders.set("User-Agent", "Thunder Client (https://www.thunderclient.com)");
        requestHeaders.set("Cookie", cookie);

        HttpEntity<Void> requestEntity = new HttpEntity<Void>(requestHeaders);
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(uri, HttpMethod.POST, requestEntity, String.class);

        String responseBody = "";
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            responseBody = responseEntity.getBody();
        } else {
            System.out.println(String.format("Error HTTP status. (%s)", responseEntity.getStatusCode().toString()));
            System.out.println("[accountLogin]End");
            return 1;
        }

        System.out.println(responseBody);
        System.out.println("[bookingRequest]End");
        return 0;
    }

    private int bookingList() {
        System.out.println("[bookingList]Begin");
        URI uri = UriComponentsBuilder.fromUriString("https://gmoyours.dt-r.com/customer/reservation/ajaxViewList.php")
                                            .build().encode().toUri();

        HttpHeaders requestHeaders = new HttpHeaders();
        requestHeaders.set("Accept", "*/*");
        requestHeaders.set("User-Agent", "Thunder Client (https://www.thunderclient.com)");
        requestHeaders.set("Cookie", cookie);

        HttpEntity<Void> requestEntity = new HttpEntity<Void>(requestHeaders);
        ResponseEntity<String> responseEntity = new RestTemplate().exchange(uri, HttpMethod.GET, requestEntity, String.class);

        String responseBody = "";
        if (responseEntity.getStatusCode().is2xxSuccessful()) {
            responseBody = responseEntity.getBody();
        } else {
            System.out.println(String.format("Error HTTP status. (%s)", responseEntity.getStatusCode().toString()));
            System.out.println("[bookingList]End");
            return 1;
        }

        System.out.println(responseBody);
        System.out.println("[bookingList]End");
        return 0;
    }

}

class Config {

    Account account;
    Setups setups;

    Config(String login_id, String password, String customer_id, String customer_company_name, String customer_name, String customer_email,
           String service_id, String service_menu_id,
           int next_monday1, int next_tuesday1, int next_wednesday1, int next_thursday1, int next_friday1,
           int next_monday2, int next_tuesday2, int next_wednesday2, int next_thursday2, int next_friday2) {
        account = new Account(login_id, password, customer_id, customer_company_name, customer_name, customer_email);
        setups = new Setups(service_id, service_menu_id,
                            next_monday1, next_tuesday1, next_wednesday1, next_thursday1, next_friday1,
                            next_monday2, next_tuesday2, next_wednesday2, next_thursday2, next_friday2);
    }

}

    class Account {

        String login_id;
        String password;
        String customer_id;
        String customer_company_name;
        String customer_name;
        String customer_email;

        Account(String login_id, String password, String customer_id, String customer_company_name, String customer_name, String customer_email) {
            this.login_id = login_id;
            this.password = password;
            this.customer_id = customer_id;
            this.customer_company_name = customer_company_name;
            this.customer_name = customer_name;
            this.customer_email = customer_email;
        }

    }

    class Setups {

        String service_id;
        String service_menu_id;
        int next_monday1;
        int next_tuesday1;
        int next_wednesday1;
        int next_thursday1;
        int next_friday1;
        int next_monday2;
        int next_tuesday2;
        int next_wednesday2;
        int next_thursday2;
        int next_friday2;

        Setups(String service_id, String service_menu_id,
               int next_monday1, int next_tuesday1, int next_wednesday1, int next_thursday1, int next_friday1,
               int next_monday2, int next_tuesday2, int next_wednesday2, int next_thursday2, int next_friday2) {
            this.service_id = service_id;
            this.service_menu_id = service_menu_id;
            this.next_monday1 = next_monday1;
            this.next_tuesday1 = next_tuesday1;
            this.next_wednesday1 = next_wednesday1;
            this.next_thursday1 = next_thursday1;
            this.next_friday1 = next_friday1;
            this.next_monday2 = next_monday2;
            this.next_tuesday2 = next_tuesday2;
            this.next_wednesday2 = next_wednesday2;
            this.next_thursday2 = next_thursday2;
            this.next_friday2 = next_friday2;
        }

    }
