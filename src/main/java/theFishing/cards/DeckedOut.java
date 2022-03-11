package theFishing.cards;

import com.megacrit.cardcrawl.actions.animations.AnimateJumpAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import com.megacrit.cardcrawl.powers.PlatedArmorPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class DeckedOut extends AbstractFishingCard {
    public final static String ID = makeID("DeckedOut");
    // intellij stuff power, self, uncommon, , , , , 3, 1

    public DeckedOut() {
        super(ID, 1, CardType.POWER, CardRarity.UNCOMMON, CardTarget.SELF);
        baseMagicNumber = magicNumber = 3;
        baseSecondMagic = secondMagic = 1;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new ApplyPowerAction(p, p, new PlatedArmorPower(p, magicNumber), magicNumber));
        if (CardCrawlGame.clientUtils.isSteamRunningOnSteamDeck()) {
            atb(new AnimateJumpAction(p));
            atb(new ApplyPowerAction(p, p, new ArtifactPower(p, secondMagic), secondMagic));
        }
    }

    public void upp() {
        upgradeMagicNumber(1);
        upgradeSecondMagic(1);
    }
}