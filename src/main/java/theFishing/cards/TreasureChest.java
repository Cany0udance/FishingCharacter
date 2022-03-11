package theFishing.cards;

import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class TreasureChest extends AbstractFishingCard {
    public final static String ID = makeID("TreasureChest");
    // intellij stuff skill, self, uncommon, , , , , 20, 10

    public TreasureChest() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 20;
        isEthereal = true;
        exhaust = true;
        tags.add(CardTags.HEALING);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new GainGoldAction(magicNumber));
    }

    public void upp() {
        upgradeMagicNumber(10);
    }
}