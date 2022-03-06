package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.AbstractFishingCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class Splash extends AbstractFishingCard {
    public final static String ID = makeID("Splash");
    // intellij stuff attack, self_and_enemy, , 15, 2, 15, 2, , 

    public Splash() {
        super(ID, 2, CardType.ATTACK, CardRarity.SPECIAL, CardTarget.ENEMY);
        baseDamage = 15;
        baseBlock = 15;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        blck();
        dmg(m, AbstractGameAction.AttackEffect.SLASH_HEAVY);
    }

    @Override
    public void applyPowers() {
        int realBaseDamage = upgraded ? 18 : 15;
        int realBaseBlock = upgraded ? 18 : 15;
        int modifier = (int) AbstractDungeon.player.hand.group.stream().filter(c -> c != this).count();
        baseDamage -= modifier;
        baseBlock -= modifier;
        super.applyPowers();
        this.baseDamage = realBaseDamage;
        this.baseBlock = realBaseBlock;
        this.isDamageModified = this.damage != this.baseDamage;
        this.isBlockModified = this.block != this.baseBlock;
    }

    public void upp() {
        upgradeDamage(3);
        upgradeBlock(3);
    }
}