package theFishing.cards;

import com.badlogic.gdx.math.MathUtils;
import com.evacipated.cardcrawl.mod.stslib.cards.interfaces.StartupCard;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.shuffleIn;

public class WakaWaka extends AbstractFishingCard implements StartupCard {
    public final static String ID = makeID("WakaWaka");
    // intellij stuff attack, enemy, uncommon, 8, 2, , , , 

    public WakaWaka() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 8;
        cardsToPreview = new PowerPellet();
    }

    @Override
    public boolean atBattleStartPreDraw() {
        shuffleIn(new PowerPellet(), 2);
        return true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction(makeID("WAKA_WAKA")));
        dmg(m, AbstractGameAction.AttackEffect.NONE);
    }

    public void upp() {
        upgradeDamage(2);
        selfRetain = true;
        uDesc();
    }
}