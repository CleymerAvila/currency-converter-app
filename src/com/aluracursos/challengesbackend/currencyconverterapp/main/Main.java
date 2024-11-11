package com.aluracursos.challengesbackend.currencyconverterapp.main;

import com.aluracursos.challengesbackend.currencyconverterapp.models.Conversion;
import com.aluracursos.challengesbackend.currencyconverterapp.models.CurrencyConverter;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Conversion> conversions = new ArrayList<>();
    static String from, to;
    static double amount;
    static int userOption;
    static Conversion conversion;
    static CurrencyConverter currencyConverter = new CurrencyConverter();
    public static void main(String[] args) throws IOException {

        Gson gson = new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.UPPER_CAMEL_CASE)
                .setPrettyPrinting()
                .create();

        do {
            showMenu();
            userOption = sc.nextInt();

            switch (userOption){
                case 1:
                    convertFromTo("USD", "ARS");
                    break;
                case 2:
                    convertFromTo("ARS", "USD");
                    break;
                case 3:
                    convertFromTo("USD", "BRL");
                    break;
                case 4:
                    convertFromTo("BRL", "USD");
                    break;
                case 5:
                    convertFromTo("USD", "COP");
                    break;
                case 6:
                    convertFromTo("COP", "USD");
                    break;
                case 7:
                    convertFromTo("USD", "EUR");
                    break;
                case 8:
                    convertFromTo("EUR", "USD");
                    break;
                case 9:
                    convertFromTo("EUR", "COP");
                    break;
                case 10:
                    System.out.println("\nHa salido de la Aplicacion!");
                    System.out.println("Lista de conversiones"+ conversions);
                    break;
                default:
                    System.out.println("Opcion no validad intente de nuevo!");
                    break;
            }
        } while (userOption != 10);

        FileWriter writer = new FileWriter("conversions.json");
        writer.write(gson.toJson(conversions));
        writer.close();

    }

    public static void showMenu(){
        System.out.println("\n**************************************************");
        System.out.println("Bienvenido a Nuestra App de conversion de Moneda");
        System.out.println("1) Dolar =>> Peso Argentino");
        System.out.println("2) Peso Argentino =>> Dolar");
        System.out.println("3) Dolar =>> Real Brasileño");
        System.out.println("4) Real Brasileño =>> Dolar");
        System.out.println("5) Dolar =>> Peso Colombiano");
        System.out.println("6) Peso Colombiano =>> Dolar");
        System.out.println("7) Dolar =>> Euro");
        System.out.println("8) Euro =>> Dolar");
        System.out.println("9) Euro =>> Peso Colombiano");
        System.out.println("10) Salir de la aplicación");
        System.out.println("\nElija una opción valida");
        System.out.println("****************************************************");
    }

    public static void convertFromTo(String from, String to){
        System.out.println("Ingresa la cantidad que desea convertir");
        amount = sc.nextDouble();
        conversion = currencyConverter.evaluateExchange(from,to, amount);

        System.out.printf("El valor %.2f [%s] corresponde al valor final de =>>> %.2f [%s]"
                , amount, from, conversion.conversion_result(), conversion.target_code());

        conversions.add(conversion);
    }
}
