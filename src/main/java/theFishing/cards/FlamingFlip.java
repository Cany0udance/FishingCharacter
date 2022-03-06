package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class FlamingFlip extends AbstractFishingCard {
    public final static String ID = makeID("FlamingFlip");
    // intellij stuff skill, self, uncommon, , , 5, 3, , 

    public FlamingFlip() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 5;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new ExhaustAction(1, false));
        atb(new DrawCardAction(2));
    }

    public void upp() {
        upgradeBlock(3);
    }
}