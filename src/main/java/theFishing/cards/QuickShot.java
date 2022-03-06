package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractEasyCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class QuickShot extends AbstractEasyCard {
    public final static String ID = makeID("QuickShot");
    // intellij stuff attack, enemy, common, 6, 3, , , , 

    public QuickShot() {
        super(ID, 0, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
    }

    public void upp() {
        upgradeDamage(3);
    }
}