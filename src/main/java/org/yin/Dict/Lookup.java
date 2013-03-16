package org.yin.Dict;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.yin.Json.JSONArray;
import org.yin.Json.JSONException;
import org.yin.Json.JSONObject;

public class Lookup {
  public static String DICT_LOCATION = "http://fanyi.youdao.com/openapi.do?keyfrom=tinxing&"
      + "key=1312427901&type=data&doctype=json&version=1.1&q=";

  public static String BASIC = "basic";
  public static String BASIC_PHONETIC = "phonetic";
  public static String BASIC_EXPLAINS = "explains";


  private String basicPhonetic;
  private ArrayList<String> basicExplains;

  public String keywords;

  public Lookup(String keywords) {
    this.keywords = keywords;
  }

  public void parse(String pageContents) {
    try {
      JSONObject jsonObj = new JSONObject(pageContents);
      JSONObject basicObj = jsonObj.getJSONObject(BASIC);
      JSONArray basicExplainsArray = basicObj.getJSONArray(BASIC_EXPLAINS);
      basicExplains = new ArrayList<String>();
      for (int i = 0; i < basicExplainsArray.length(); i++)
        basicExplains.add(basicExplainsArray.getString(i));
      basicPhonetic = basicObj.getString(BASIC_PHONETIC);
    } catch (JSONException e) {
//       e.printStackTrace();
    }
  }

  public void print() {
    System.out.println(keywords);
    if (basicPhonetic != null)
      System.out.println("/" + basicPhonetic + "/");
    
    if (basicExplains != null) {
      for (int i = 0; i < basicExplains.size(); i++) {
        System.out.println(basicExplains.get(i));
      } 
    } else {
        System.out.println("Sorry, undefined.");
    }
  }

  public void search() {
    if (!keywords.isEmpty()) {
      Page page = new Page(DICT_LOCATION + keywords);
      page.downlowd();
      parse(page.getContent());
      print();
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
