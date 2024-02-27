// Package dans lequel se trouve l'interface
package fr.omegion.api.items;

// Déclaration de l'interface ItemEvent
public interface ItemEvent {
   // Méthode abstraite qui sera implémentée par les classes utilisant cette interface
   void call(EventItemHandler var1);
}
