package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Bobber extends AbstractFishingCard {
    public final static String ID = makeID("Bobber");
    // intellij stuff skill, none, uncommon, 9, 1, 12, 3, , 

    public Bobber() {
        super(ID, -2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.NONE);
        baseBlock = 12;
        baseMagicNumber = magicNumber = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    private boolean isValid = false;

    @Override
    public void atTurnStart() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                isValid = true;
            }
        });
    }

    @Override
    public void atTurnStartPreDraw() {
        addToBot(new AbstractGameAction() {
            @Override
            public void update() {
                isDone = true;
                isValid = false;
            }
        });
    }

    @Override
    public void triggerWhenDrawn() {
        if (isValid) {
            blck();
            applyToSelf(new StrengthPower(AbstractDungeon.player, magicNumber));
        }
    }

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        cantUseMessage = "I have to draw this Bobber on my turn to use it.";
        return false;
    }

    public void upp() {
        upgradeBlock(3);
        upgradeMagicNumber(1);
    }
}