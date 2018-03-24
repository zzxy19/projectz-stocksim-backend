package com.projectz.stocksimbackend.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Map;

public class HttpsRequestBuilder {
  private URL requestUrl;

  private static final Logger logger = LoggerFactory.getLogger(HttpsRequestBuilder.class);

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
    String url = requestBuilder.toString();
    logger.info("Http request built: " + url);
    try {
      requestUrl = new URL(url);
    } catch (MalformedURLException ex) {
      // This should not happen.
      throw new IllegalStateException(ex);
    }
  }

  public InputStreamReader getResponseBufferedReader() throws IOException {
    URLConnection connection = requestUrl.openConnection();
    return new InputStreamReader(connection.getInputStream());
  }
}
