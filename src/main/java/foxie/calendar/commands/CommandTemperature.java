package foxie.calendar.commands;

import foxie.calendar.Config;
import foxie.calendar.api.CalendarAPI;
import foxie.calendar.api.MCVersionHelper;
import foxie.calendar.versionhelpers.AbstractCommand;
import foxie.calendar.versionhelpers.TextComponentString;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.server.MinecraftServer;

import javax.vecmath.Point3d;


public class CommandTemperature extends AbstractCommand {
   @Override
   public String getName() {
      return "gettemp";
   }

   @Override
   public String getUsage(ICommandSender sender) {
      return "commands.season.usage";
   }

   @Override
   public void doCommand(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
      if (!Config.enableGetTempCommand)
         return;

      Point3d location = getPosition(sender);

      sender.sendMessage(new TextComponentString("Average temperature: " +
              CalendarAPI.getSeasonProvider(MCVersionHelper.getDimensionId(sender.getEntityWorld()))
                      .getAverageTemperature(sender.getEntityWorld(), false)));

      sender.sendMessage(new TextComponentString("Actual temperature: " +
              CalendarAPI.getSeasonProvider(MCVersionHelper.getDimensionId(sender.getEntityWorld()))
                      .getTemperature(sender.getEntityWorld(),
                              (int)location.x, (int)location.y, (int)location.z)));
   }
}
