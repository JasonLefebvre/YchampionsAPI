// Package dans lequel se trouve la classe
package fr.omegion.api.items;

// Importations de classes nécessaires pour gérer les items
import java.util.Arrays;
import java.util.List;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

// Déclaration de la classe Item
public class Item {
   // Nom de l'item
   private final String name;

   // ItemStack représentant l'item
   private final ItemStack item;

   // Enchantement appliqué à l'item (peut être null s'il n'y a pas d'enchantement)
   private final Enchantment enchantment;

   // Puissance de l'enchantement
   private final int enchantmentPower;

   // List des descriptions de l'item (lores)
   private final List<String> lores;

   // Événement associé à l'item (peut être null s'il n'y a pas d'événement)
   private ItemEvent event;

   // Constructeur de la classe pour créer un nouvel Item
   public Item(String name, Material material, Enchantment enchantment, int enchantmentPower, String... lore) {
      this.name = name;
      this.item = new ItemStack(material);
      this.enchantment = enchantment;
      this.enchantmentPower = enchantmentPower;
      this.lores = Arrays.asList(lore);
      this.generate();
   }

   // Méthode pour convertir l'objet Item en ItemStack
   public ItemStack toItemStack() {
      return this.item;
   }

   // Méthode pour définir l'événement associé à cet Item
   public void setEvent(ItemEvent event) {
      this.event = event;
   }

   // Méthode pour appeler l'événement associé à cet Item
   public void callEvent(EventItemHandler event) {
      this.event.call(event);
   }

   // Méthode privée pour générer les métadonnées de l'Item, telles que le nom, les lores et l'enchantement
   private void generate() {
      // Récupération des métadonnées de l'Item
      ItemMeta meta = this.item.getItemMeta();

      // Définition du nom de l'Item
      meta.setDisplayName(this.name);

      // Définition des lores de l'Item
      meta.setLore(this.lores);

      // Si un enchantement est spécifié, ajout de l'enchantement aux métadonnées
      if (this.enchantment != null) {
         meta.addEnchant(this.enchantment, this.enchantmentPower, true);
      }

      // Application des métadonnées à l'Item
      this.item.setItemMeta(meta);
   }
}
