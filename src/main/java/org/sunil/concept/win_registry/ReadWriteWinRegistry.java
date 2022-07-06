package org.sunil.concept.win_registry;

import java.util.prefs.Preferences;

public class ReadWriteWinRegistry {

  private static Preferences prefs;
  public static final String REALKEY= "foo";

  public static void main(String[] args) {
    
    System.out.println("Program started");
    // write into HKCU\Software\Javasoft\Prefs\com.rgagnon.foo
    Preferences p = Preferences.userRoot();
    p.put(REALKEY, "bar");

    // read back from HKEY_CURRENT_USER
    System.out.println(p);
    System.out.println(p.get(REALKEY, "HKCU houston we have a problem"));

    // write into HKLM\Software\Javasoft\Prefs\com.rgagnon.foo
    p = Preferences.systemRoot();
  //  p.put(REALKEY, "barbar");

    // read back from HKEY_LOCAL_MACHINE
    System.out.println(p); 
    p.get("HKEY_LOCAL_MACHINE\\SOFTWARE\\JavaSoft\\Prefs\\", "as");
    System.out.println(p.get("HKEY_LOCAL_MACHINE\\SOFTWARE\\Ephesoft\\sre", "test"));    
    System.out.println("Program ended");
    
  }

}
