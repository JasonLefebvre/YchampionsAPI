package fr.omegion.api.accounts;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;

// This class represents a Omegion skull
public class OmegionSkull {
   // The skull's item stack
   private final ItemStack skullFromStack;

   // Constructor: Initializes the skull with the player's info
   public OmegionSkull(Player player) {
      ItemStack skull = new ItemStack(Material.SKULL_ITEM, 1);
      skull.setDurability((short)3);
      SkullMeta skullMeta = (SkullMeta)skull.getItemMeta();
      skullMeta.setDisplayName(player.getDisplayName());
      skullMeta.setOwner("" + player.getDisplayName());
      skull.setItemMeta(skullMeta);
      this.skullFromStack = skull;
   }

   // This method returns a rendered item stack of the skull
   public ItemStack render() {
      return this.skullFromStack;
   }
}
