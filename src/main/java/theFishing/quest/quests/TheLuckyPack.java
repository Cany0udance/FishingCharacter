package theFishing.quest.quests;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.actions.RepeatCardAction;
import theFishing.patch.foil.FoilPatches;

import static theFishing.util.Wiz.att;

public class TheLuckyPack extends AbstractQuest {

    public static final String ID = "TheLuckyPack";

    public TheLuckyPack() {
        super(ID, 3);
    }


    @Override
    public String getName() {
        return "The Lucky Pack";
    }

    @Override
    public String getDescription() {
        return "#yQuest: Play #b" + goal + " #yFoil cards in a row. NL #yReward: Play the last card again.";
    }

    @Override
    public void grantReward() {
        AbstractCard q = AbstractDungeon.actionManager.cardsPlayedThisCombat.get(AbstractDungeon.actionManager.cardsPlayedThisCombat.size()-1);
        att(new RepeatCardAction(q));
    }

    @Override
    public void onPlayCard(AbstractCard card) {
        if (FoilPatches.isFoil(card)) {
            increment();
        }
        else {
            progress = 0;
        }
    }
}
