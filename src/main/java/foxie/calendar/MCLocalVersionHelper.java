package foxie.calendar;

import net.minecraft.util.text.translation.I18n;

public class MCLocalVersionHelper {
   public static String translateToLocal(String key) {
      return I18n.translateToLocal(key);
   }

   public static String translateToLocalFormatted(String key, Object... format) {
      return I18n.translateToLocalFormatted(key, format);
   }

   public static String translateToFallback(String key) {
      return I18n.translateToFallback(key);
   }

   public static boolean canTranslate(String key) {
      return I18n.canTranslate(key);
   }

   public static long getLastTranslationUpdateTimeInMilliseconds() {
      return I18n.getLastTranslationUpdateTimeInMilliseconds();
   }
}
