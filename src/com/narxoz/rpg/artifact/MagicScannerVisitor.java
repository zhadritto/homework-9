package com.narxoz.rpg.artifact;

public class MagicScannerVisitor implements ArtifactVisitor {
    @Override public void visit(Weapon weapon) {
        System.out.println("[Magic Scan] " + weapon.getName() + " radiates raw power. (ATK +" + weapon.getAttackBonus() + ")");
    }
    @Override public void visit(Potion potion) {
        System.out.println("[Magic Scan] " + potion.getName() + " bubbles with life force. (Heal +" + potion.getHealing() + ")");
    }
    @Override public void visit(Scroll scroll) {
        System.out.println("[Magic Scan] " + scroll.getName() + " hums with arcane energy. (Spell: " + scroll.getSpellName() + ")");
    }
    @Override public void visit(Ring ring) {
        System.out.println("[Magic Scan] " + ring.getName() + " glows brightly! (Magic +" + ring.getMagicBonus() + ")");
    }
    @Override public void visit(Armor armor) {
        System.out.println("[Magic Scan] " + armor.getName() + " resonates with warding. (DEF +" + armor.getDefenseBonus() + ")");
    }
}