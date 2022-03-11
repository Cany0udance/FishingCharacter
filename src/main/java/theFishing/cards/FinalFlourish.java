package theFishing.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.LoseStrengthPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.vfx.combat.IntenseZoomEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class FinalFlourish extends AbstractFishingCard {
    public final static String ID = makeID("FinalFlourish");
    // intellij stuff skill, self, rare, , , , , 5, 1

    public FinalFlourish() {
        super(ID, 1, CardType.SKILL, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 5;
        baseSecondMagic = secondMagic = 10;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (p.hand.size() == 1) {
            CardCrawlGame.sound.play("GUARDIAN_ROLL_UP");
            atb(new VFXAction(p, new IntenseZoomEffect(p.hb.cX, p.hb.cY, false), 0.05F, true));
            applyToSelfNextTurn(new StrengthPower(p, secondMagic));
            applyToSelfNextTurn(new LoseStrengthPower(p, secondMagic));
        } else {
            applyToSelf(new StrengthPower(p, magicNumber));
            applyToSelf(new LoseStrengthPower(p, magicNumber));
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = adp().hand.size() == 1 ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(2);
    }
}