package com.projectz.stocksimbackend.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class HttpsRequestBuilder {
  private URL requestUrl;
  public String url;

  public HttpsRequestBuilder(String domain, String request, Map<String, String> params) {
    StringBuilder requestBuilder = new StringBuilder();
    requestBuilder.append("https://").append(domain);
    if (request != null && !request.isEmpty()) {
      requestBuilder.append("/").append(request);
      if (params != null && !params.isEmpty()) {
        boolean firstAttribute = true;
        for (String attr : params.keySet()) {
          if (firstAttribute) {
            firstAttribute = false;
            requestBuilder.append("?");
          } else {
            requestBuilder.append("&");
          }
          String value = params.get(attr);
          requestBuilder.append(attr).append("=").append(value);
        }
      }
    }
    url = requestBuilder.toString();
    try {
      requestUrl = new URL(requestBuilder.toString());
    } catch (MalformedURLException ex) {
      // This should not happen.
      throw new IllegalStateException(ex);
    }
  }

  public BufferedReader getResponseBufferedReader() throws IOException {
    URLConnection connection = requestUrl.openConnection();
    return new BufferedReader(new InputStreamReader(connection.getInputStream()));
  }
}
