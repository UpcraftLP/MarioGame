package upcraftlp.mariogame.util;

/**
 * (c)2017 UpcraftLP
 */
public class Constants {


   /**DO NOT EDIT BELOW THIS LINE, EVERYTHING ELSE IS CHANGED BY THE GRADLE SCRIPT!**/

   public static final String VERSION;
   public static final boolean IS_DEV;
   static {
      String ver = Constants.class.getPackage().getImplementationVersion();
      if(ver != null) {
         VERSION = "v" + ver;
         IS_DEV = false; //TODO better way?
      }
      else {
         VERSION = "Development Edition";
         IS_DEV = true;
      }
   }

}
