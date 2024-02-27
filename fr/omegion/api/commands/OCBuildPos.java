package fr.omegion.api.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

// This class represents the OCBuildPos command
public class OCBuildPos extends OmegionCommand {
   // Overridden onCommand from the OmegionCommand class
   public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
      String prefix = "§c§lOmegionBuild >> §r";
      if (commandSender instanceof Player) {
         Player player = (Player)commandSender;
         if (player.hasPermission("omegion.build")) {
            if (args.length > 0) {
               String var7 = args[0];
               byte var8 = -1;
               switch(var7.hashCode()) {
               case 113762:
                  if (var7.equals("set")) {
                     var8 = 0;
                  }
               default:
                  switch(var8) {
                  case 0:
                     this.call(new OCBuildPosExecutor(args, player));
                     break;
                  default:
                     player.sendMessage(prefix + "§caucunes actions corréspondante.");
                  }

                  return true;
               }
            }

            player.sendMessage(prefix + "§cmerci de spécifier une action.");
         }
      }

      return false;
   }
}
