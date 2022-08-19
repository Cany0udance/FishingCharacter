package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class DoubleUp extends AbstractFishingCard implements StartupCard {
    public final static String ID = makeID("DoubleUp");
    // intellij stuff attack, enemy, common, 4, 2, , , , 

    public DoubleUp() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 4;
        baseMagicNumber = magicNumber = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
        dmg(m, AbstractGameAction.AttackEffect.BLUNT_LIGHT);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        atb(new DamageAllEnemiesAction(AbstractDungeon.player, DamageInfo.createDamageMatrix(magicNumber), damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        return true;
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(2);
    }
}