package spm;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Configurator {

    private final URL mProxyUrl;

    public Configurator(String url) throws java.net.MalformedURLException {
        mProxyUrl = new URL(url);
        if (!pingServer()) {
            throw new RuntimeException("Could not contact mock server");
        }
    }

    boolean pingServer() throws java.net.MalformedURLException {
        final URL pingUrl = new URL(mProxyUrl, "/mockSettings/ping");
        boolean status = true;
        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) pingUrl.openConnection();
        } catch (final java.io.IOException e) {
            // TODO: handle properly
            throw new RuntimeException("io exception");
        }

        try {
            connection.setRequestMethod("GET");
        } catch (final java.net.ProtocolException e) {
            // TODO: handle properly
            throw new RuntimeException("ProtocolException");
        }

        StringBuffer responseBody = new StringBuffer();
        int responseCode;
        try {
            responseCode = connection.getResponseCode();

            BufferedReader in = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String inputLine;


            while ((inputLine = in.readLine()) != null) {
                responseBody.append(inputLine);
            }
            in.close();
        } catch (final java.io.IOException e) {
            // TODO: handle properly
            throw new RuntimeException("io exception");
        }

        if (responseCode != HttpURLConnection.HTTP_OK || responseBody.toString().equals("ping")) {
            status = false;
        }


        return status;
    }
}
