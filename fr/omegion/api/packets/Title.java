// Package dans lequel se trouve la classe
package fr.omegion.api.packets;

// Importations de classes nécessaires pour gérer les titres et les joueurs
import com.google.common.base.Preconditions;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.JSONObject;

// Déclaration de la classe Title
public class Title {
   // Déprécié : Variable de debug (non recommandée pour un usage en production)
   @Deprecated
   public static boolean DEBUG;

   // Objets JSON pour stocker le titre, le sous-titre et les propriétés du titre
   private JSONObject title;
   private JSONObject subtitle;
   private int fadeIn;
   private int fadeOut;
   private int stay;

   // Constructeur pour créer un objet Title avec un titre, un sous-titre et les propriétés du titre
   public Title(String title, String subtitle, int fadeIn, int stay, int fadeOut) {
      this.title = convert(title);
      this.subtitle = convert(subtitle);
      this.fadeIn = fadeIn;
      this.fadeOut = fadeOut;
      this.stay = stay;
   }

   // Constructeur pour créer un objet Title avec des objets JSON, un sous-titre et les propriétés du titre
   public Title(JSONObject title, JSONObject subtitle, int fadeIn, int fadeOut, int stay) {
      this.title = title;
      this.subtitle = subtitle;
      this.fadeIn = fadeIn;
      this.fadeOut = fadeOut;
      this.stay = stay;
   }

   // Méthode statique pour convertir une chaîne de texte en objet JSON
   static JSONObject convert(String text) {
      JSONObject json = new JSONObject();
      json.put("text", text);
      return json;
   }

   // Méthode pour envoyer le titre à un joueur spécifique
   public void send(Player player) {
      Preconditions.checkNotNull(player);

      try {
         // Utilisation de la réflexion pour accéder aux classes et méthodes internes de Minecraft Server
         Object entityPlayer = player.getClass().getMethod("getHandle").invoke(player);
         Object playerConnection = entityPlayer.getClass().getField("playerConnection").get(entityPlayer);
         Class<?> clsPacketPlayOutTitle = ServerPackage.MINECRAFT.getClass("PacketPlayOutTitle");
         Class<?> clsPacket = ServerPackage.MINECRAFT.getClass("Packet");
         Class<?> clsIChatBaseComponent = ServerPackage.MINECRAFT.getClass("IChatBaseComponent");
         Class<?> clsChatSerializer = ServerPackage.MINECRAFT.getClass("IChatBaseComponent$ChatSerializer");
         Class<?> clsEnumTitleAction = ServerPackage.MINECRAFT.getClass("PacketPlayOutTitle$EnumTitleAction");
         Object timesPacket = clsPacketPlayOutTitle.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE).newInstance(this.fadeIn, this.stay, this.fadeOut);
         playerConnection.getClass().getMethod("sendPacket", clsPacket).invoke(playerConnection, timesPacket);
         Object subtitleComponent;
         Object subtitlePacket;

         // Envoi du titre
         if (this.title != null && !this.title.isEmpty()) {
            subtitleComponent = clsChatSerializer.getMethod("a", String.class).invoke((Object)null, this.title.toString());
            subtitlePacket = clsPacketPlayOutTitle.getConstructor(clsEnumTitleAction, clsIChatBaseComponent).newInstance(clsEnumTitleAction.getField("TITLE").get((Object)null), subtitleComponent);
            playerConnection.getClass().getMethod("sendPacket", clsPacket).invoke(playerConnection, subtitlePacket);
         }

         // Envoi du sous-titre
         if (this.subtitle != null && !this.subtitle.isEmpty()) {
            subtitleComponent = clsChatSerializer.getMethod("a", String.class).invoke((Object)null, this.subtitle.toString());
            subtitlePacket = clsPacketPlayOutTitle.getConstructor(clsEnumTitleAction, clsIChatBaseComponent).newInstance(clsEnumTitleAction.getField("SUBTITLE").get((Object)null), subtitleComponent);
            playerConnection.getClass().getMethod("sendPacket", clsPacket).invoke(playerConnection, subtitlePacket);
         }

      } catch (Throwable var12) {
         throw new RuntimeException(var12);
      }
   }

   // Méthode pour envoyer le titre à tous les joueurs en ligne
   public void sendToAll() {
      Bukkit.getOnlinePlayers().forEach(this::send);
   }

   // Méthodes getter et setter pour accéder et modifier les propriétés du titre
   public JSONObject getTitle() {
      return this.title;
   }

   public void setTitle(String title) {
      this.title = convert(title);
   }

   public void setTitle(JSONObject title) {
      this.title = title;
   }

   public JSONObject getSubtitle() {
      return this.subtitle;
   }

   public void setSubtitle(String subtitle) {
      this.subtitle = convert(subtitle);
   }

   public void setSubtitle(JSONObject subtitle) {
      this.subtitle = subtitle;
   }

   public int getFadeIn() {
      return this.fadeIn;
   }

   public void setFadeIn(int fadeIn) {
      this.fadeIn = fadeIn;
   }

   public int getFadeOut() {
      return this.fadeOut;
   }

   public void setFadeOut(int fadeOut) {
      this.fadeOut = fadeOut;
   }

   public int getStay() {
      return this.stay;
   }

   public void setStay(int stay) {
      this.stay = stay;
   }
}
