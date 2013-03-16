package org.yin.Dict;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Page {
  private URL url;
  private String content;

  public URL getUrl() {
    return url;
  }

  public void setUrl(URL url) {
    this.url = url;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  public Page(URL url) {
    this.url = url;
  }

  public Page(String urlString) {
    try {
      url = new URL(urlString);
    } catch (MalformedURLException e) {
      url = null;
//      e.printStackTrace();
    }
  }

  public void downlowd() {
    if (url != null) {
      URLConnection connection;
      try {
        connection = url.openConnection();
        connection.connect();
        BufferedReader reader =
            new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String nextline = null;
        StringBuilder sb = new StringBuilder();
        while ((nextline = reader.readLine()) != null ) {  
          sb.append(nextline);
        }
        content = decode(sb.toString());
      } catch (IOException e) {
        content = "";
//        e.printStackTrace();
      }
    } else {
      content = "";
    }
  }
  
  static final Pattern reUnicode = Pattern.compile("\\\\u([0-9a-zA-Z]{4})");
  public static String decode(String s) {
      Matcher m = reUnicode.matcher(s);
      StringBuffer sb = new StringBuffer(s.length());
      while (m.find()) {
//          m.appendReplacement(sb,
//                  Character.toString((char) Integer.parseInt(m.group(1), 16)));
        m.appendReplacement(sb,
          "...");
      }
      m.appendTail(sb);
      return sb.toString();
  }
}
