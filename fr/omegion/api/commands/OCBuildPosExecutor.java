package fr.omegion.api.commands;

import org.bukkit.entity.Player;

// This class represents the executor of OCBuildPos command
public class OCBuildPosExecutor implements OmegionCommandInterface {
   // The player executing the command and the command's arguments
   private String[] args;
   private Player player;

   // Constructor: Initializes the executor with all necessary information
   public OCBuildPosExecutor(String[] args, Player player) {
      this.args = args;
      this.player = player;
   }

   // Implementing the execute method from the OmegionCommandInterface
   public void execute() {
   }
}
