package com.narxoz.rpg.vault;

import com.narxoz.rpg.combatant.Hero;
import com.narxoz.rpg.memento.Caretaker;
import com.narxoz.rpg.combatant.HeroMemento;
import com.narxoz.rpg.artifact.AppraisalVisitor;
import com.narxoz.rpg.artifact.WeightVisitor;
import com.narxoz.rpg.artifact.MagicScannerVisitor;
import com.narxoz.rpg.artifact.CurseDetectorVisitor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ChronomancerEngine {

    public VaultRunResult runVault(List<Hero> party) {
        int artifactsAppraised = 0;
        int mementosCreated = 0;
        int restoredCount = 0;

        System.out.println("\n--- Entering the Chronomancer's Vault ---");
        Caretaker caretaker = new Caretaker();

        AppraisalVisitor appraiser = new AppraisalVisitor();
        WeightVisitor weigher = new WeightVisitor();
        MagicScannerVisitor scanner = new MagicScannerVisitor();
        CurseDetectorVisitor curseDetector = new CurseDetectorVisitor();

        System.out.println("\n[ Phase 1: Artifact Appraisal (Visitor) ]");
        for (Hero hero : party) {
            System.out.println("\nEvaluating inventory for " + hero.getName() + "...");
            hero.getInventory().accept(appraiser);
            hero.getInventory().accept(weigher);
            hero.getInventory().accept(scanner);
            hero.getInventory().accept(curseDetector);
            artifactsAppraised += (hero.getInventory().size() * 4);
        }

        System.out.println("\nTotal Party Artifact Value: " + appraiser.getTotalValue() + "G");
        System.out.println("Total Party Artifact Weight: " + weigher.getTotalWeight() + " lbs");

        System.out.println("\n[ Phase 2: Storing Time Crystals (Memento) ]");
        for (Hero hero : party) {
            caretaker.save(hero.createMemento());
            mementosCreated++;
            System.out.println("Saved snapshot for " + hero.getName() + " (HP: " + hero.getHp() + ", Gold: " + hero.getGold() + ")");
        }

        System.out.println("\n[ Phase 3: A Chronomantic Trap is Triggered! ]");
        for (Hero hero : party) {
            hero.takeDamage(75);
            hero.spendGold(hero.getGold() / 2);
            System.out.println(hero.getName() + " is blasted! HP drops to " + hero.getHp() + ", Gold drops to " + hero.getGold() + ".");
        }


        System.out.println("\n[ Phase 4: Rewinding the Clock! (Undo) ]");
        List<Hero> reverseParty = new ArrayList<>(party);
        Collections.reverse(reverseParty);

        for (Hero hero : reverseParty) {
            HeroMemento pastState = caretaker.undo();
            if (pastState != null) {
                hero.restoreFromMemento(pastState);
                restoredCount++;
                System.out.println(hero.getName() + "'s timeline restored! HP back to " + hero.getHp() + ", Gold back to " + hero.getGold() + ".");
            }
        }

        return new VaultRunResult(artifactsAppraised, mementosCreated, restoredCount);
    }
}