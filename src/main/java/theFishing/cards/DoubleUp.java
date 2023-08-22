package theFishing.cards;

import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.GainGoldAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.RainingGoldEffect;
import theFishing.FishingMod;
import theFishing.actions.AllEnemyLoseHPAction;
import theFishing.actions.EnterTheDungeonAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.att;

public class DoubleUp extends AbstractFishingCard implements StartupCard {
    public final static String ID = makeID("DoubleUp");
    // intellij stuff attack, enemy, common, 4, 2, , , , 

    public DoubleUp() {
        super(ID, 1, CardType.ATTACK, CardRarity.COMMON, CardTarget.ENEMY);
        baseDamage = 8;
        baseMagicNumber = magicNumber = 2;
        tags.add(FishingMod.DELVES);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.SLASH_DIAGONAL);
        atb(new EnterTheDungeonAction());
    }

    public void upp() {
        upgradeDamage(2);
        upgradeMagicNumber(1);
    }

    @Override
    public boolean atBattleStartPreDraw() {
        AbstractDungeon.effectList.add(new RainingGoldEffect(this.magicNumber, true));
        atb(new GainGoldAction(this.magicNumber));
        return true;
    }
}