package theFishing.cards;

import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.curses.CurseOfTheBell;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AllEnemyLoseHPAction;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.atb;
import static theFishing.util.Wiz.topDeck;

public class RingTheBell extends AbstractFishingCard {
    public final static String ID = makeID("RingTheBell");
    // intellij stuff skill, all_enemy, uncommon, , , , , 13, 
    //ART: Adventurer in the background aiming their pistol at the mocking Cursed Bell in the foreground

    public RingTheBell() {
        super(ID, 1, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 13;
        cardsToPreview = new CurseOfTheBell();
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        atb(new SFXAction("BELL"));
        atb(new AllEnemyLoseHPAction(magicNumber));
        topDeck(new CurseOfTheBell());
    }

    public void upp() {
        isEthereal = true;
        uDesc();
    }
}