package theFishing.cards.boxtoppers;

import basemod.AutoAdd;
import com.megacrit.cardcrawl.actions.common.BetterDiscardPileToHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NextTurnBlockPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

@AutoAdd.Ignore
public class Hindsight extends AbstractBoxTopper {
    public final static String ID = makeID("Hindsight");
    // intellij stuff skill, self, , , 4, 2, , 

    public Hindsight() {
        super(ID, 1, CardType.SKILL, CardTarget.SELF);
        baseBlock = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        applyToSelf(new NextTurnBlockPower(p, block));
        atb(new BetterDiscardPileToHandAction(1));
    }

    public void upp() {
        upgradeBlock(2);
    }
}