package theFishing.cards;

import basemod.ReflectionHacks;
import com.badlogic.gdx.graphics.Color;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import com.megacrit.cardcrawl.powers.WeakPower;
import com.megacrit.cardcrawl.vfx.AbstractGameEffect;
import com.megacrit.cardcrawl.vfx.combat.ClashEffect;
import theFishing.actions.EasyXCostAction;

import static theFishing.FishingMod.STAR_IN_ART;
import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class RatedX extends AbstractFishingCard {
    public final static String ID = makeID("RatedX");
    // intellij stuff attack, enemy, rare, 9, 3, 14, 4, 1, 1

    public RatedX() {
        super(ID, -1, CardType.ATTACK, CardRarity.RARE, CardTarget.ENEMY);
        baseDamage = 8;
        baseBlock = 14;
        baseMagicNumber = magicNumber = 1;
        tags.add(STAR_IN_ART);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new EasyXCostAction(this, (effect, params) -> {
            if (effect >= 4) {
                att(new GainBlockAction(p, block));
            }
            if (effect >= 2) {
                applyToSelfTop(new StrengthPower(p, magicNumber));
            }
            if (effect >= 1) {
                applyToEnemyTop(m, new VulnerablePower(m, magicNumber, false));
                applyToEnemyTop(m, new WeakPower(m, magicNumber, false));
            }
            dmgTop(m, AbstractGameAction.AttackEffect.NONE);
            AbstractGameEffect e = new ClashEffect(m.hb.cX, m.hb.cY);
            ReflectionHacks.setPrivate(e, AbstractGameEffect.class, "color", Color.DARK_GRAY.cpy());
            this.addToTop(new VFXAction(p, e, 0.1F));
            return true;
        }));
    }

    public void upp() {
        upgradeDamage(2);
        upgradeBlock(4);
        upgradeMagicNumber(1);
    }
}