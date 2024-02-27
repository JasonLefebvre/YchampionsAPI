package fr.omegion.api.accounts;

import fr.omegion.api.OmegionAPI;
import fr.omegion.api.permissions.Group;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.permissions.PermissionAttachment;

// This class represents a Omegion player
public class OmegionPlayer {
   // Player parameters such as id, uuid, pseudo, group, skull and bank account
   private Integer id;
   private UUID uuid;
   private String pseudo;
   private String fakePseudo;
   private Group group;
   private Player player;
   private OmegionSkull skull;
   private BankAccount bankAccount;
   private final PermissionAttachment realPermissions;

   // Constructor: Initializes the player with all necessary information
   public OmegionPlayer(Player player, Integer id, String fakePseudo, Group group, BankAccount bankAccount) {
      this.id = id;
      this.player = player;
      this.uuid = player.getUniqueId();
      this.pseudo = player.getDisplayName();
      this.fakePseudo = fakePseudo;
      this.group = group;
      this.bankAccount = bankAccount;
      this.realPermissions = player.addAttachment(OmegionAPI.getInstance());
      this.group.getPermissions().forEach((permission) -> {
         this.realPermissions.setPermission(permission.getSlug(), true);
      });
   }

   // Getter and setter methods for all player parameters

   public Integer getId() {
      return this.id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public UUID getUuid() {
      return this.uuid;
   }

   public void setUuid(UUID uuid) {
      this.uuid = uuid;
   }

   public String getPseudo() {
      return this.pseudo;
   }

   public void setPseudo(String pseudo) {
      this.pseudo = pseudo;
   }

   public String getFakePseudo() {
      return this.fakePseudo;
   }

   public void setFakePseudo(String fakePseudo) {
      this.fakePseudo = fakePseudo;
   }

   public Group getGroup() {
      return this.group;
   }

   public void setGroup(Group group) {
      this.group = group;
   }

   public Player getPlayer() {
      return this.player;
   }

   public void setPlayer(Player player) {
      this.player = player;
   }

   public OmegionSkull getSkull() {
      return this.skull;
   }

   public void setSkull(OmegionSkull skull) {
      this.skull = skull;
   }

   public BankAccount getBankAccount() {
      return this.bankAccount;
   }

   public void setBankAccount(BankAccount bankAccount) {
      this.bankAccount = bankAccount;
   }
}
