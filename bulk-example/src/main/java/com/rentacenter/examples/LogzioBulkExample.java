package com.rentacenter.examples;

import java.io.IOException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.LoggingFilter;
import org.json.JSONObject;

public class LogzioBulkExample {
    public static void main(String[] args) {
        ClientConfig config = new DefaultClientConfig();
        Client client = Client.create(config);

        // add filter to see the request & response
        client.addFilter(new LoggingFilter(System.out));

        WebResource resource = client.resource(UriBuilder.fromUri("https://listener.logz.io:8071/?token=" + System.getenv("LOGZIO_TOKEN") + "&type=bulk").build());

        JSONObject input = new JSONObject();

        // all logs must include "environment" & "application"
        input.put("environment", System.getenv("ENVIRONMENT"));
        input.put("application", "logzioexamples");
        input.put("source_system", "LogzioBulkExample");

        // any additional values
        input.put("team", "DevOps");
        input.put("project", "Logz.io examples");

        // send JSON message & print response
        input.put("message", "Testing logz.io!");
        ClientResponse response = resource.accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, input.toString());
        System.out.println("Response: " + response.getEntity(String.class));

        // overwrite just "message" and send again
        input.put("message", "Winter is coming");
        response = resource.accept(MediaType.APPLICATION_JSON_TYPE).type(MediaType.APPLICATION_JSON_TYPE).post(ClientResponse.class, input.toString());
        System.out.println("Response: " + response.getEntity(String.class));
    }
}
