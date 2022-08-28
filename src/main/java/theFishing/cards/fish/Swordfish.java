package theFishing.cards.fish;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;

public class Swordfish extends AbstractFishCard {
    public final static String ID = makeID("Swordfish");
    // intellij stuff attack, enemy, 7, 2, , , , 

    public Swordfish() {
        super(ID, CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 7;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL);
    }
}