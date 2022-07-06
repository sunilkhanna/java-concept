package org.sunil.concept.win_registry;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.prefs.Preferences;

public class WinRegWrapper {

  // private final Logger LOGGER = LoggerFactory.getLogger(SREWindowsServiceManager.class);

  public int HKEY_LOCAL_MACHINE = 0x80000002;
  /* Windows error codes. */
  public final int ERROR_SUCCESS = 0;
  public final int REG_SUCCESS = 0;

  private final int ERROR_FILE_NOT_FOUND = 2;
  private final int ERROR_ACCESS_DENIED = 5;
  private Class<? extends Preferences> systemClass = null;
  // private static Preferences systemRoot = Preferences.systemRoot();

  /* Windows security masks */
  private final int DELETE = 0x10000;
  private final int KEY_QUERY_VALUE = 1;
  private final int KEY_SET_VALUE = 2;
  private final int KEY_CREATE_SUB_KEY = 4;
  private final int KEY_ENUMERATE_SUB_KEYS = 8;
  private final int KEY_READ = 0x20019;
  private final int KEY_WRITE = 0x20006;
  private final int KEY_ALL_ACCESS = 0xf003f;


  private Preferences systemRoot = null;
  private Method regCreateKeyEx = null;
  private Method regOpenKey = null;
  private Method regQueryValueEx = null;
  private Method regCloseKey = null;
  private Method regDeleteKey = null;
  private Method regDeleteValue = null;
  private Method regSetValueEx = null;

  public WinRegWrapper() {

    try {
      systemRoot = Preferences.systemRoot();
      systemClass = systemRoot.getClass();

      regQueryValueEx = systemClass.getDeclaredMethod("WindowsRegQueryValueEx",
          new Class[] {int.class, byte[].class});
      regQueryValueEx.setAccessible(true);

      regCloseKey = systemClass.getDeclaredMethod("WindowsRegCloseKey", new Class[] {int.class});
      regCloseKey.setAccessible(true);

      regOpenKey = systemClass.getDeclaredMethod("WindowsRegOpenKey",
          new Class[] {int.class, byte[].class, int.class});
      regOpenKey.setAccessible(true);

      regCreateKeyEx = systemClass.getDeclaredMethod("WindowsRegCreateKeyEx",
          new Class[] {int.class, byte[].class});
      regCreateKeyEx.setAccessible(true);

      regDeleteValue = systemClass.getDeclaredMethod("WindowsRegDeleteValue",
          new Class[] {int.class, byte[].class});
      regDeleteValue.setAccessible(true);

      regDeleteKey = systemClass.getDeclaredMethod("WindowsRegDeleteKey",
          new Class[] {int.class, byte[].class});
      regDeleteKey.setAccessible(true);

      regSetValueEx = systemClass.getDeclaredMethod("WindowsRegSetValueEx",
          new Class[] {int.class, byte[].class, byte[].class});
      regSetValueEx.setAccessible(true);


    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("ssd");
    }

  }

  private static byte[] toCstr(String str) {
    byte[] result = new byte[str.length() + 1];

    for (int i = 0; i < str.length(); i++) {
      result[i] = (byte) str.charAt(i);
    }
    result[str.length()] = 0;
    return result;
  }

  public void createRegKey(String key) throws NoSuchMethodException, SecurityException,
      IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    int[] ret = (int[]) regCreateKeyEx.invoke(systemRoot,
        new Object[] {new Integer(HKEY_LOCAL_MACHINE), toCstr(key)});
    regCloseKey.invoke(systemRoot, new Object[] {new Integer(ret[0])});
    if (ret[1] != REG_SUCCESS) {
      throw new IllegalArgumentException("rc=" + ret[1] + "  key=" + key);
    }
  }
  
  private   int [] createKey(Preferences root, int hkey, String key)
      throws IllegalArgumentException, IllegalAccessException,
      InvocationTargetException
    {
      return  (int[]) regCreateKeyEx.invoke(root,
          new Object[] { new Integer(hkey), toCstr(key) });
    }
  public   void createKey(int hkey, String key)
      throws IllegalArgumentException, IllegalAccessException,
      InvocationTargetException
    {
      int [] ret = null;
      if (hkey == HKEY_LOCAL_MACHINE) {
        ret = createKey(systemRoot, hkey, key);
        regCloseKey.invoke(systemRoot, new Object[] { new Integer(ret[0]) });
      }
     
      
        if (ret[1] != REG_SUCCESS) { throw new IllegalArgumentException("rc=" + ret[1] + "  key=" +
        key); }
       
    }
  
  

  public String readRegKey(String key, String value)
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

    int[] handles = (int[]) regOpenKey.invoke(systemRoot,
        new Object[] {new Integer(HKEY_LOCAL_MACHINE), toCstr(key), new Integer(KEY_READ)});
    if (handles[1] != REG_SUCCESS) {
      return null;
    }

    byte[] valb = (byte[]) regQueryValueEx.invoke(systemRoot,
        new Object[] {new Integer(handles[0]), toCstr(value)});
    regCloseKey.invoke(systemRoot, new Object[] {new Integer(handles[0])});
    return (valb != null ? new String(valb).trim() : null);


  }


  /**
   * Delete provided key
   * 
   * @param HKEY_LOCAL_MACHINE
   * @param key
   * @throws InvocationTargetException
   * @throws IllegalArgumentException
   * @throws IllegalAccessException
   */
  public int deleteRegKey(String key)
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
    int rc = ((Integer) regDeleteKey.invoke(systemRoot,
        new Object[] {new Integer(HKEY_LOCAL_MACHINE), toCstr(key)})).intValue();
    return rc;
  }

  /**
   * Delete a value for given key by value name
   * 
   * @param HKEY_LOCAL_MACHINE
   * @param key
   * @param value
   * @return
   * @throws IllegalAccessException
   * @throws IllegalArgumentException
   * @throws InvocationTargetException
   */
  public int deleteRegValue(String key, String value)
      throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {

    int[] handles = (int[]) regOpenKey.invoke(systemRoot,
        new Object[] {new Integer(HKEY_LOCAL_MACHINE), toCstr(key), new Integer(KEY_ALL_ACCESS)});
    if (handles[1] != REG_SUCCESS) {
      return handles[1]; // can be REG_NOTFOUND, REG_ACCESSDENIED
    }
    int rc = ((Integer) regDeleteValue.invoke(systemRoot,
        new Object[] {new Integer(handles[0]), toCstr(value)})).intValue();
    regCloseKey.invoke(systemRoot, new Object[] {new Integer(handles[0])});
    return rc;
  }


  public void writeStringValue(String key, String valueName, String value)
      throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
    int[] handles = (int[]) regOpenKey.invoke(systemRoot,
        new Object[] {new Integer(HKEY_LOCAL_MACHINE), toCstr(key), new Integer(KEY_ALL_ACCESS)});

    regSetValueEx.invoke(systemRoot,
        new Object[] {new Integer(handles[0]), toCstr(valueName), toCstr(value)});
    regCloseKey.invoke(systemRoot, new Object[] {new Integer(handles[0])});
  }

  public static void main(String[] args) {
    // TODO Auto-generated method stub

  }

}
