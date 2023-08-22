package theFishing.cards;

import basemod.helpers.BaseModCardTags;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.MiracleEffect;
import theFishing.patch.PreDrawPatch;
import theFishing.powers.AnglerFormPower;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToSelf;
import static theFishing.util.Wiz.vfx;

public class AnglerForm extends AbstractFishingCard {
    public final static String ID = makeID("AnglerForm");
    // intellij stuff power, self, rare, , , , , 3, 1

    public AnglerForm() {
        super(ID, 3, CardType.POWER, CardRarity.RARE, CardTarget.SELF);
        baseMagicNumber = magicNumber = 9;
        tags.add(BaseModCardTags.FORM);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new MiracleEffect(new Color(0.98431372549F, 0.94901960784F, 0.21176470588F, 0.0F), Color.YELLOW.cpy(), "MAW_DEATH"), 0.1F);
        applyToSelf(new AnglerFormPower(magicNumber));
    }

    @Override
    public void triggerWhenDrawn() {
        if (PreDrawPatch.DRAWN_DURING_TURN) {
            setCostForTurn(0);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = isVoyaged() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeMagicNumber(3);
    }
}