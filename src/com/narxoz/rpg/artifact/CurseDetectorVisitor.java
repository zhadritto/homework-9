package com.narxoz.rpg.artifact;

public class CurseDetectorVisitor implements ArtifactVisitor {
    @Override public void visit(Weapon weapon) {
        if (weapon.getAttackBonus() > 40) System.out.println("CURSE DETECTED: " + weapon.getName() + " may cause bloodlust.");
    }
    @Override public void visit(Potion potion) {
        System.out.println("SAFE: " + potion.getName() + " passes chemical analysis.");
    }
    @Override public void visit(Scroll scroll) {
        if (scroll.getSpellName().contains("Dark")) System.out.println(" CURSE DETECTED: " + scroll.getName() + " contains forbidden magic!");
        else System.out.println("SAFE: " + scroll.getName() + " is safe.");
    }
    @Override public void visit(Ring ring) {
        if (ring.getMagicBonus() > 15) System.out.println(" CURSE DETECTED: " + ring.getName() + " exerts a heavy toll.");
    }
    @Override public void visit(Armor armor) {
        System.out.println("SAFE: " + armor.getName() + " has stable enchantments.");
    }
}
