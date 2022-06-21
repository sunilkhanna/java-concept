package org.sunil.concept;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class NonAscii {

  public static void main(String[] args) {

    BufferedReader reader;
    try {
      reader =
          new BufferedReader(new FileReader("C:\\Users\\RSI\\Downloads\\ephesoft-sre-installs.log"));
      String line = reader.readLine();
      while (line != null) {
        // String s=line.replaceAll("[^a-zA-Z0-9]","");
//[\\x00]
        String s = line.replaceAll("[\\x00-\\x00]", "");

        System.out.println("replaceed @@@@ : " + s);
        // read next line
        line = reader.readLine();
      }
      reader.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


}
