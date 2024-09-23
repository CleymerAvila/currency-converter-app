package com.aluracursos.challengesbackend.currencyconverterapp.models;

public record Conversion(String base_code, String target_code,
                         double conversion_rate, double conversion_result ) {
}
