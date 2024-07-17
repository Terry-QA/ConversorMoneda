package org.example;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Paths;
import java.time.Duration;

public class prueba {
    Gson gson = new Gson();

    public static String rqhttp(double cantidad_base, String request_moneda) throws IOException, InterruptedException {

        // DEFINIENDO Y DECLARANDO VARIABLES

        String API_KEY = "e0e36971ef2c5c4b789950cb"; //API KEY CONVERSOR DOLARES
        //equest_moneda = "USD";

        String moneda_base = "PEN";

        //https://api.exchangeratesapi.io/latest?base=PEN&symbols=USD&amount=100
        // Construir la URL para obtener las tasas de cambio
        String url = "https://v6.exchangerate-api.com/v6/" + API_KEY + "/pair/" + moneda_base + "/" + request_moneda +
                "/" + cantidad_base;

        // Crear instancia del cliente HTTP
        HttpClient client = HttpClient.newHttpClient();

        // Construir la solicitud HTTP GET
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .timeout(Duration.ofMinutes(2))
                .header("Content-Type", "application/json")
                .GET()
                .build();

        try {
            // Enviar la solicitud y capturar la respuesta de forma síncrona
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            // Verificar el código de estado de la respuesta
            int statusCode = response.statusCode();
            if (statusCode >= 200 && statusCode < 300) {
                System.out.println("Solicitud enviada exitosamente. Código de estado: " + statusCode);
                // Imprimir la respuesta recibida
                System.out.println("Respuesta recibida:");
                System.out.println(response.body());
            } else {
                System.out.println("La solicitud no se pudo completar. Código de estado: " + statusCode);
            }

            // Retornar el cuerpo de la respuesta como String
            return response.body();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
            return null; // Manejar errores como sea adecuado en tu aplicación
        }
    }


    public static void pr (String txt){
        System.out.println(txt);
    }


}
