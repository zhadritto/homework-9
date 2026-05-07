package com.narxoz.rpg;

import com.narxoz.rpg.artifact.*;
import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.vault.ChronomancerEngine;
import com.narxoz.rpg.vault.VaultRunResult;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Homework 9 Demo: Visitor + Memento ===");

        Hero knight = new Hero("Sir Galahad", 120, 20, 45, 60, 500, new Inventory());
        knight.getInventory().addArtifact(new Weapon("Sunblade", 1500, 12, 50));
        knight.getInventory().addArtifact(new Armor("Aegis Plate", 2000, 65, 40));
        knight.getInventory().addArtifact(new Potion("Elixir of Vitality", 100, 2, 100));

        Hero mage = new Hero("Eldoria", 70, 300, 10, 20, 800, new Inventory());
        mage.getInventory().addArtifact(new Scroll("Necronomicon Extract", 500, 5, "Dark Resurrection"));
        mage.getInventory().addArtifact(new Ring("Band of the Archmage", 3000, 1, 20));

        List<Hero> party = List.of(knight, mage);

        ChronomancerEngine engine = new ChronomancerEngine();
        VaultRunResult summary = engine.runVault(party);
        System.out.println("\n=== Final Run Summary ===");
        System.out.println(summary);
    }
}