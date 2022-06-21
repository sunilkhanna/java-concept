package org.sunil.concept;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import junit.framework.Test;  

public class SuppressedException {
  public static void demoSuppressedException(String filePath) throws IOException {
    FileInputStream fileIn = null;
    try {
      fileIn = new FileInputStream(filePath);
    } catch (FileNotFoundException e) {
      throw new IOException(e);
    } finally {
      fileIn.close();
    }
  }
  
  
  
  @Test(expected = NullPointerException.class)
  public void givenNonExistentFileName_whenAttemptFileOpen_thenNullPointerException() throws IOException {
      demoSuppressedException("/non-existent-path/non-existent-file.txt");
  }
  
}
