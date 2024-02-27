// Package dans lequel se trouve la classe
package fr.omegion.api.commands;

// Importation des classes nécessaires de Bukkit
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

// Déclaration de la classe OmegionCommand qui implémente CommandExecutor
public class OmegionCommand implements CommandExecutor {

   // Méthode appelée lorsqu'une commande est exécutée
   // CommandSender : celui qui a exécuté la commande
   // Command : la commande exécutée
   // String : alias de la commande utilisée
   // String[] : les arguments de la commande
   public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
      // Le code actuel renvoie toujours false lorsqu'une commande est exécutée,
      // il peut être modifié selon la logique spécifique de votre commande
      return false;
   }

   // Méthode protégée qui peut être appelée par d'autres classes
   protected void call(OmegionCommandInterface omegionCommandInterface) {
      // Appelle la méthode execute() de l'interface OmegionCommandInterface
      omegionCommandInterface.execute();
   }
}
