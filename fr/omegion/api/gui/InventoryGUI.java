// Package dans lequel se trouve la classe
package fr.omegion.api.gui;

// Importations de classes nécessaires pour la gestion des inventaires et des items
import fr.omegion.api.items.Item;
import fr.omegion.api.items.ItemEvent;
import java.util.HashMap;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

// Déclaration de la classe abstraite InventoryGUI
public abstract class InventoryGUI {
   // Taille de l'inventaire
   private int size;

   // Titre de l'inventaire
   private String title;

   // Inventaire Bukkit
   private Inventory inventory;

   // HashMap stockant les positions et les objets Item associés
   private HashMap<Integer, Item> items = new HashMap();

   // Constructeur de la classe
   public InventoryGUI(String title, int row) {
      this.title = title;
      this.size = row * 9;
      this.inventory = Bukkit.createInventory((InventoryHolder)null, this.size, this.title);
   }

   // Méthode pour initialiser l'inventaire en plaçant les items aux positions spécifiées
   protected void initialize() {
      this.items.forEach((pos, item) -> {
         this.inventory.setItem(pos, item.toItemStack());
      });
   }

   // Méthode pour rendre l'inventaire non fermable depuis l'interface graphique
   public void setClosableFromInterface() {
      int last = this.size - 1;

      // Création d'un item de fermeture du menu
      Item close = new Item("§cFermer le menu", Material.IRON_DOOR, (Enchantment)null, 0, new String[]{"", "", "Cliquez ici pour fermer le menu"});

      // Ajout de l'item à l'inventaire avec un événement pour fermer le menu
      this.addItem(last, close, (event) -> {
         event.getPlayer().closeInventory();
      });
   }

   // Méthode pour ajouter un item à l'inventaire à une position donnée avec un événement associé
   protected void addItem(Integer pos, Item item, ItemEvent event) {
      if (event != null) {
         item.setEvent(event);
      }

      this.items.put(pos, item);
   }

   // Méthode pour définir un ItemStack à une position donnée dans l'inventaire
   protected void setItemFromItemStack(Integer pos, ItemStack item) {
      this.inventory.setItem(pos, item);
   }

   // Méthode pour obtenir l'objet Item à une position donnée dans l'inventaire
   public Item getItem(Integer pos) {
      return (Item)this.items.get(pos);
   }

   // Méthode pour obtenir l'inventaire Bukkit associé à cette classe
   public Inventory toInventory() {
      return this.inventory;
   }

   // Méthode pour obtenir la taille de l'inventaire
   public int getSize() {
      return this.size;
   }

   // Méthode pour définir la taille de l'inventaire
   public void setSize(int size) {
      this.size = size;
   }

   // Méthode abstraite à implémenter dans les sous-classes pour le rendu spécifique de l'inventaire
   protected abstract void render();
}
