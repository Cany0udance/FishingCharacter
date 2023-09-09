package theFishing.cards;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.FlamingFlipAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class FlamingFlip extends AbstractFishingCard {
    public final static String ID = makeID("FlamingFlip");
    // intellij stuff skill, self, uncommon, , , 5, 3, , 

    public FlamingFlip() {
        super(ID, 0, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseBlock = 6;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        atb(new FlamingFlipAction());
    }

    public void upp() {
        upgradeBlock(3);
    }
}