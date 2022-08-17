package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.WeightyImpactEffect;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;

public class ShipRam extends AbstractFishingCard {
    public final static String ID = makeID("ShipRam");
    // intellij stuff attack, enemy, uncommon, 15, 5, , , , 

    public ShipRam() {
        super(ID, 2, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 16;
        baseSecondDamage = secondDamage = 32;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        if (isVoyaged()) {
            atb(new VFXAction(new WeightyImpactEffect(m.hb.cX, m.hb.cY)));
            atb(new WaitAction(0.8F));
            atb(new DamageAction(m, new DamageInfo(p, secondDamage, damageTypeForTurn), AbstractGameAction.AttackEffect.NONE));
        } else {
            dmg(m, AbstractGameAction.AttackEffect.BLUNT_HEAVY);
        }
    }

    public void triggerOnGlowCheck() {
        this.glowColor = isVoyaged() ? AbstractCard.GOLD_BORDER_GLOW_COLOR : AbstractCard.BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(4);
        upgradeSecondDamage(8);
    }
}