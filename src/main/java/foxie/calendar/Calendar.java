package foxie.calendar;

import foxie.calendar.api.CalendarAPI;
import foxie.calendar.commands.CommandDate;
import foxie.calendar.commands.CommandSeason;
import foxie.calendar.commands.CommandTemperature;
import foxie.calendar.commands.FixedCommandTime;
import foxie.calendar.implementation.CalendarImpl;
import foxie.calendar.implementation.SeasonProvider;
import foxie.calendar.proxy.ProxyCommon;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.*;

@Mod(modid = Calendar.MODID, name = Calendar.NAME, version = Calendar.VERSION)
public class Calendar {
   public static final String MODID   = "calendarapi";
   public static final String NAME    = "CalendarAPI";
   public static final String VERSION = "@VERSION@";

   @SidedProxy(clientSide = "foxie.calendar.proxy.ProxyClient", serverSide = "foxie.calendar.proxy.ProxyCommon", modId = MODID)
   public static ProxyCommon proxy;

   @Mod.Instance(MODID)
   public static Calendar INSTANCE;

   private static Config config;

   private Events events;

   @Mod.EventHandler
   public void preinit(FMLPreInitializationEvent event) {
      events = new Events();
      proxy.preinit(event);
      config = new Config(event.getSuggestedConfigurationFile());

      // register default providers
      CalendarAPI.registerCalendarProvider(0, new CalendarImpl(0));
      CalendarAPI.registerSeasonProvider(0, new SeasonProvider());

      events.preinit();
   }

   @Mod.EventHandler
   public void init(FMLInitializationEvent event) {
      proxy.init(event);
      events.init();
   }

   @Mod.EventHandler
   public void postinit(FMLPostInitializationEvent event) {
      proxy.postinit(event);
      events.postinit();
   }

   @Mod.EventHandler
   public void serverStarting(FMLServerStartingEvent event) {
      event.registerServerCommand(new FixedCommandTime());

      event.registerServerCommand(new CommandDate());

      event.registerServerCommand(new CommandSeason());

      event.registerServerCommand(new CommandTemperature());
   }

   @Mod.EventHandler
   public void serverStarted(FMLServerStartedEvent event) {
      events.serverStarted(event);
   }

   public Config getConfig() {
      return config;
   }

}
