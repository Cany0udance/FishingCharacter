package theFishing.cards.treasures;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class Rhaast extends AbstractTreasureCard {
    public final static String ID = makeID("Rhaast");
    // intellij stuff attack, enemy, 10, 3, , , , 

    public Rhaast() {
        super(ID, 1, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 12;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        for (int i = 0; i < 3; i++) {
            dmg(m, AbstractGameAction.AttackEffect.SLASH_VERTICAL);
        }
    }

    public void upp() {
        upgradeDamage(3);
    }
}