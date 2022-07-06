package org.sunil.concept.win_registry;

import java.util.prefs.Preferences;

public class WinRegistryTest {
  public static void main(String args[]) throws Exception {
    
    
    WinRegWrapper regWrapper=new WinRegWrapper();
    
    regWrapper.createRegKey("SOFTWARE\\SUNILremoveme\\GYU");
    regWrapper.writeStringValue("SOFTWARE\\SUNILremoveme\\GYU", "who", "ur baap");
    
    String value=regWrapper.readRegKey("SOFTWARE\\SUNILremoveme\\GYUcc", "who");
    System.out.println("sdsdddddddddddd "+value);
    
    regWrapper.deleteRegKey("SOFTWARE\\sunil");
    
    //regWrapper.createKey(0x80000002, "SOFTWARE\\removemeee");
    
   // WinRegistry.createKey(0x80000002, "SOFTWARE\\removemeeee");
    
    /**
    
        Preferences systemRoot = Preferences.systemRoot();

      //  systemRoot.get("HKEY_LOCAL_MACHINE\\SOFTWARE\\Ephesoft\\sre\\", "tets");
    
        systemRoot.node("HKEY_LOCAL_MACHINE\\SOFTWARE\\Example");
    
        systemRoot.systemNodeForPackage(WinRegistry.class).node("\\SOFTWARE\\AAExample").flush();;
    
    
      String value = "";

      // IE Download directory (HKEY_CURRENT_USER)
      value = WinRegistry.readString(
          WinRegistry.HKEY_CURRENT_USER,
          "SOFTWARE\\Ephesoft\\sre\\",
          "test");
      System.out.println("IE Download directory = " + value);

      // Query for Acrobat Reader installation path (HKEY_LOCAL_MACHINE)
      value = WinRegistry.readString(
          WinRegistry.HKEY_LOCAL_MACHINE,
          "SOFTWARE\\Ephesoft\\sre",
          "test");
      System.out.println("Acrobat Reader Path = " + value);

      WinRegistry.createKey(WinRegistry.HKEY_LOCAL_MACHINE, "SOFTWARE\\sunil");
*/      
      /**
      WinRegistry.writeStringValue(
          WinRegistry.HKEY_LOCAL_MACHINE,
          "sunil.khanna",
          "HowTo",
          "registery");
      
      */

      /*
         this code is broken under win7 64 :-(  20130112

      // Loop through installed JRE and print the JAVA_HOME value
      // HKEY_LOCAL_MACHINE\SOFTWARE\JavaSoft\Java Runtime Environment
      java.util.Map res1 = WinRegistry.readStringValues(
          WinRegistry.HKEY_LOCAL_MACHINE,
          "SOFTWARE\\Wow6432Node\\JavaSoft\\Java Runtime Environment");
      System.out.println("1:" + res1.toString());
      */

      // on 64bit Windows, you need Wow6432Node to access 32bit related information
      //   "SOFTWARE\\Wow6432Node\\Microsoft\\Windows NT\\CurrentVersion"
 /**     java.util.List res2 = WinRegistry.readStringSubKeys(
          WinRegistry.HKEY_LOCAL_MACHINE,
          "SOFTWARE\\Ephesoft\\sre\\test");
      System.out.println("********"+res2.toString());

      WinRegistry.createKey(
          WinRegistry.HKEY_CURRENT_USER, "SOFTWARE\\rgagnon.com");
      WinRegistry.writeStringValue(
          WinRegistry.HKEY_CURRENT_USER,
          "SOFTWARE\\rgagnon.com",
          "HowTo",
          "java");
*/
//      WinRegistry.deleteValue(
//          WinRegistry.HKEY_CURRENT_USER,
//          "SOFTWARE\\rgagnon.com", "HowTo");
//      WinRegistry.deleteKey(
//          WinRegistry.HKEY_CURRENT_USER,
//          "SOFTWARE\\rgagnon.com\\");

      System.out.println("Done." );
  }
}

