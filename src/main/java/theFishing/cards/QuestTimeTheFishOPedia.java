package theFishing.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theFishing.actions.AcceptQuestAction;
import theFishing.cards.fish.AbstractFishCard;
import theFishing.quest.QuestHelper;
import theFishing.quest.quests.TheFishOPedia;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.*;

public class QuestTimeTheFishOPedia extends AbstractFishingCard {
    public final static String ID = makeID("QuestTimeTheFishOPedia");
    // intellij stuff skill, self, uncommon, , , , , , 

    public QuestTimeTheFishOPedia() {
        super(ID, 1, CardType.ATTACK, CardRarity.UNCOMMON, CardTarget.ENEMY);
        baseDamage = 4;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        dmg(m, AbstractGameAction.AttackEffect.FIRE);
        makeInHand(AbstractFishCard.returnRandomFish());
        atb(new AcceptQuestAction(new TheFishOPedia()));
    }

    @Override
    public void triggerOnGlowCheck() {
        glowColor = QuestHelper.hasQuest(TheFishOPedia.ID) ? QuestHelper.QUEST_DUPE_BORDER_GLOW_COLOR : BLUE_BORDER_GLOW_COLOR;
    }

    public void upp() {
        upgradeDamage(4);
    }
}