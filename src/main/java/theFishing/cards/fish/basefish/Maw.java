package theFishing.cards.fish.basefish;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class Maw extends AbstractFishCard {
    public final static String ID = makeID("Maw");
    // intellij stuff attack, enemy, 4, , , , , 

    public Maw() {
        super(ID,  CardType.ATTACK, CardTarget.ENEMY);
        baseDamage = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        atb(new DrawCardAction(1));
    }

    public void upp() {
        upgradeDamage(2);
    }
}