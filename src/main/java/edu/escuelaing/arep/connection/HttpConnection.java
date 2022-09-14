package edu.escuelaing.arep.connection;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpConnection {
    private static final String USER_AGENT = "Mozilla/5.0";


    /**
     * Funcion encargada de entregar los datos de la API del clima de una ciudad especificada por el usuario.
     * En este cvaso lo que se hace es tener una URL definida, a la cual se le pega el nombre de la ciudad para que
     * empiece a crear una conexión (HttpURLConnection con = connecting(GET_URL);)
     * En caso de que la conexion haya sido exitosa, se retorna los datos de la API
     * @param city
     * @return
     * @throws IOException
     */
    public static StringBuffer getData(String city) throws IOException {
        String GET_URL = "https://api.openweathermap.org/data/2.5/weather?q="+city+"&appid=f70fd1eb9fb8c63c6a242766f8f90670";
        HttpURLConnection con = connecting(GET_URL);
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code :: " + responseCode);
        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            return getResponse(con);
        }
        else {
            System.out.println("GET request not worked");
        }        return null;
    }

    /**
     * Funcion encargada de conectarnos  a la API mediante internet. Esto gracias a que le damos una URL a la cual se pude dirigir
     * Lo que se hace es que se genera una HtppURLConnection gracias a la URL.
     * Después se le dice que la operación que se va a hace con la conexión es de tipo GET (traer datos)
     * Se retorna la conexion
     * @param URL
     * @return
     * @throws IOException
     */
    public static HttpURLConnection connecting(String URL) throws IOException {
        URL obj = new URL(URL);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        return con;
    }

    /**
     * Funcion encargada de retornar la respuesta que genera la API
     * Lo que se hace es crear un BufferedRedaer, el cual lee las líneas que haya mandado la API
     * luego se crea un inputline y se crea un StringBuffer. Este útlimo guarda toda la info de la API para luego revelarla
     * Mientras que el inputline se le defina la línea que está leyendo el BufferedReader y que no sea nulo, entonces esa
     * respuesta la vas a meter al StringBuffer, que es quien tendra toda la respuesta para luego enviarla
     * @param con
     * @return
     * @throws IOException
     */
    public static StringBuffer getResponse(HttpURLConnection con) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        // print result
        System.out.println(response.toString());
        return response;
    }


}
