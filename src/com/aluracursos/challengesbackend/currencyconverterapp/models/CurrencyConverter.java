package com.aluracursos.challengesbackend.currencyconverterapp.models;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CurrencyConverter {

    public Conversion evaluateExchange(String fromCountry, String toCountry, double amount){

        try{

            URI direccion = URI.create("https://v6.exchangerate-api.com/v6/1dc20381986410d20e8cefe5/pair/"+
                    fromCountry+"/"+toCountry+"/"+amount+"/");


            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(direccion)
                    .build();
            HttpResponse<String> response = client
                    .send(request, HttpResponse.BodyHandlers.ofString());

            String json = response.body();


            return new Gson().fromJson(json, Conversion.class);


        } catch (NumberFormatException | IOException | InterruptedException e){
            System.out.println("Ocurrio un Error!");
            System.out.println(e.getMessage());
        } catch (IllegalArgumentException e){
            System.out.println("Error en la URI, Verifique la dirección.");
        }

        return null;
    }
}
