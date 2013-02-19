package org.yin.Dict;

/**
 * Hello world!
 * 
 */
public class App {
  
  public static void main(String[] args) {
    if (args.length < 1) return; 
    Lookup lookup = new Lookup(args[0]);
    lookup.search();
  }
}
