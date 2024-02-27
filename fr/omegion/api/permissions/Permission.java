// Déclaration de la classe Permission
public class Permission {
   // Variables d'instance pour stocker les informations de la permission
   private Integer id;
   private String slug;
   private String name;
   private String description;

   // Constructeur pour initialiser les propriétés de la permission
   public Permission(Integer id, String slug, String name, String description) {
      this.id = id;
      this.slug = slug;
      this.name = name;
      this.description = description;
   }

   // Méthodes getter et setter pour accéder et modifier les propriétés de la permission
   public Integer getId() {
      return this.id;
   }

   // La méthode setId est privée car l'ID d'une permission ne devrait pas être modifié une fois qu'elle est créée
   private void setId(Integer id) {
      this.id = id;
   }

   public String getSlug() {
      return this.slug;
   }

   public void setSlug(String slug) {
      this.slug = slug;
   }

   public String getName() {
      return this.name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getDescription() {
      return this.description;
   }

   public void setDescription(String description) {
      this.description = description;
   }
}
