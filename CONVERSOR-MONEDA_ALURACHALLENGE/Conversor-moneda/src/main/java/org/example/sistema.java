package org.example;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.Scanner;

public class sistema {

    static Scanner getTeclado = new Scanner(System.in);
    static int opcionMoneda;
    static double opcionCantidad;
    static boolean estadoSistema = true, estadoMoneda = true;
    static String request_moneda;

    public sistema (){

    }


    public static void solicitud () throws IOException, InterruptedException {

        while (estadoSistema == true){
            pr("1: DOLARES - USD");
            pr("2: EUROS - EUR");
            pr("3: PESOS ARGENTINOS - ARS");
            pr("4: COLONES COSTA RICA - CRC");
            pr("5: SALIDA");
            pr(" Selecciona");
            opcionMoneda = getTeclado.nextInt();

            switch (opcionMoneda) {
                case 1:
                    // secuencia de sentencias.
                    request_moneda = "USD";
                    break;
                case 2:
                    // secuencia de sentencias.
                    request_moneda = "EUR";
                    break;
                case 3:
                    // secuencia de sentencias.
                    request_moneda = "ARS";
                    break;
                case 4:
                    request_moneda = "CRC";
                    break;
            }

            if(opcionMoneda!=5){
                pr("INSERTE CANTIDAD");
                while(estadoMoneda==true){
                    opcionCantidad = getTeclado.nextDouble();

                    prueba prueba = new prueba();

                    String jsonResponse = prueba.rqhttp(opcionCantidad, request_moneda);

                    if(jsonResponse != null){
                        Gson gson = new Gson();
                        JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);

                        // Extraer los atributos que te interesan
                        String result = jsonObject.get("result").getAsString();
                        String baseCode = jsonObject.get("base_code").getAsString();
                        String targetCode = jsonObject.get("target_code").getAsString();
                        double conversionRate = jsonObject.get("conversion_rate").getAsDouble();
                        double conversionResult = jsonObject.get("conversion_result").getAsDouble();

                        // Mostrar la información
                        System.out.println("Result: " + result);
                        System.out.println("Base Code: " + baseCode);
                        System.out.println("Target Code: " + targetCode);
                        System.out.println("Conversion Rate: " + conversionRate);
                        System.out.println("Conversion Result: " + conversionResult);
                        System.out.println("####################################");
                    }


                    if(opcionCantidad>0){
                        opcionCantidad = 0.0;
                        estadoMoneda=false;
                    }
                }
            }else{
                pr("PROCESO FINALIZADO");
                estadoSistema = false;
            }

        }

    }

    public static void pr (String txt){
        System.out.println(txt);

    }

}
