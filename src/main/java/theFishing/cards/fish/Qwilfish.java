package theFishing.cards.fish;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.PoisonPower;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;

public class Qwilfish extends AbstractFishCard {
    public final static String ID = makeID("Qwilfish");
    // intellij stuff skill, enemy, special, , , , , 5, 2

    public Qwilfish() {
        super(ID, AbstractCard.CardType.SKILL, AbstractCard.CardTarget.ENEMY);
        baseMagicNumber = magicNumber = 5;
    }

    public void fishEffect(AbstractPlayer p, AbstractMonster m) {
        applyToEnemy(m, new PoisonPower(m, p, magicNumber));
    }
}