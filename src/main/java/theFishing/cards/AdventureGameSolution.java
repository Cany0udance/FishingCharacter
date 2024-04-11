package theFishing.cards;

import basemod.patches.com.megacrit.cardcrawl.cards.AbstractCard.MultiCardPreview;
import com.megacrit.cardcrawl.cards.tempCards.Insight;
import com.megacrit.cardcrawl.cards.tempCards.Miracle;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.cards.fish.AbstractFishCard;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.makeInHand;

public class AdventureGameSolution extends AbstractFishingCard {
    public final static String ID = makeID("AdventureGameSolution");
    // intellij stuff skill, self, rare, , , , , , 

    public AdventureGameSolution() {
        super(ID, 0, CardType.SKILL, CardRarity.RARE, CardTarget.NONE);
        baseMagicNumber = magicNumber = 1;
        MultiCardPreview.add(this, new Insight(), new Miracle());
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        makeInHand(AbstractFishCard.returnRandomFish());
        makeInHand(new Insight());
        makeInHand(new Miracle(), magicNumber);
    }

    public void upp() {
        upgradeMagicNumber(1);
        uDesc();
    }
}