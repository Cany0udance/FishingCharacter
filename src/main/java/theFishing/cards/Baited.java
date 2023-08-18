package theFishing.cards;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.math.MathUtils;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BiteEffect;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;
import static theFishing.util.Wiz.vfx;

public class Baited extends AbstractFishingCard {
    public final static String ID = makeID("Baited");
    // intellij stuff attack, enemy, uncommon, 16, 2, , , 1, 1

    public Baited() {
        super(ID, 2, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 14;
        baseMagicNumber = magicNumber = 2;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        vfx(new BiteEffect(m.hb.cX + MathUtils.random(-25.0F, 25.0F) * Settings.scale, m.hb.cY + MathUtils.random(-25.0F, 25.0F) * Settings.scale, Color.WHITE.cpy()), 0.0F);
        dmg(m, AbstractGameAction.AttackEffect.NONE);
        if (m.getIntentBaseDmg() >= 0) {
            for (int i = 0; i < magicNumber; i++) {
                makeInHand(AbstractFishCard.returnRandomFish());
            }
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = AbstractCard.BLUE_BORDER_GLOW_COLOR.cpy();
        if (AbstractDungeon.getCurrRoom().monsters.monsters.stream().anyMatch(m -> !m.isDeadOrEscaped() && m.getIntentBaseDmg() >= 0)) {
            this.glowColor = AbstractCard.GOLD_BORDER_GLOW_COLOR.cpy();
        }
    }

    public void upp() {
        upgradeDamage(6);
    }
}