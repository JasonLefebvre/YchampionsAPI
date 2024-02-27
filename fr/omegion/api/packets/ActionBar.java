// Package dans lequel se trouve la classe
package fr.omegion.api.packets;

// Importations de classes nécessaires pour gérer les paquets et les joueurs
import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

// Déclaration de la classe ActionBar
public class ActionBar {
   // Déprécié : Variable de debug (non recommandée pour un usage en production)
   @Deprecated
   public static boolean DEBUG;

   // Objet JSON pour stocker le texte à afficher dans l'action bar
   private JSONObject json;

   // Constructeur prenant une chaîne de texte en paramètre pour initialiser l'ActionBar
   public ActionBar(String text) {
      Preconditions.checkNotNull(text);
      this.json = Title.convert(text);
   }

   // Constructeur prenant un objet JSON en paramètre pour initialiser l'ActionBar
   public ActionBar(JSONObject json) {
      Preconditions.checkNotNull(json);
      Preconditions.checkArgument(!json.isEmpty());
      this.json = json;
   }

   // Méthode statique dépréciée pour envoyer un message à un joueur (utilise le constructeur avec une chaîne de texte)
   @Deprecated
   public static void send(Player player, String message) {
      (new ActionBar(message)).send(player);
   }

   // Méthode statique dépréciée pour envoyer un message à tous les joueurs en ligne (utilise le constructeur avec une chaîne de texte)
   @Deprecated
   public static void sendToAll(String message) {
      (new ActionBar(message)).sendToAll();
   }

   // Méthode pour envoyer l'ActionBar à un joueur spécifique
   public void send(Player player) {
      Preconditions.checkNotNull(player);

      try {
         // Utilisation de la réflexion pour accéder aux classes et méthodes de Minecraft Server
         Class<?> clsIChatBaseComponent = ServerPackage.MINECRAFT.getClass("IChatBaseComponent");
         Class<?> clsChatMessageType = ServerPackage.MINECRAFT.getClass("ChatMessageType");
         Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
         Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
         Object chatBaseComponent = ServerPackage.MINECRAFT.getClass("IChatBaseComponent$ChatSerializer").getMethod("a", String.class).invoke((Object)null, this.json.toString());
         Object chatMessageType = clsChatMessageType.getMethod("valueOf", String.class).invoke((Object)null, "GAME_INFO");
         Object packetPlayOutChat = ServerPackage.MINECRAFT.getClass("PacketPlayOutChat").getConstructor(clsIChatBaseComponent, clsChatMessageType).newInstance(chatBaseComponent, chatMessageType);
         playerConnection.getClass().getMethod("sendPacket", ServerPackage.MINECRAFT.getClass("Packet")).invoke(playerConnection, packetPlayOutChat);
      } catch (Throwable var9) {
         throw new RuntimeException(var9);
      }
   }

   // Méthode pour envoyer l'ActionBar à tous les joueurs en ligne
   public void sendToAll() {
      Bukkit.getOnlinePlayers().forEach(this::send);
   }

   // Méthode pour définir le texte de l'ActionBar en utilisant une chaîne de texte
   public void setText(String text) {
      Preconditions.checkNotNull(text);
      this.json = Title.convert(text);
   }

   // Méthode pour définir le texte de l'ActionBar en utilisant un objet JSON
   public void setJsonText(JSONObject json) {
      Preconditions.checkNotNull(json);
      Preconditions.checkArgument(!json.isEmpty());
      this.json = json;
   }
}
