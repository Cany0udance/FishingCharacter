package theFishing.patch;

import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theFishing.FishingMod;
import theFishing.cards.Bobber;
import theFishing.util.Wiz;

public class VoyagedPatch {

    @SpirePatch(
            clz = AbstractPlayer.class,
            method = "applyStartOfTurnPostDrawRelics"
    )
    public static class AbstractPlayerApplyStartOfTurnPostDrawRelicsPatch {
        public static void Prefix(AbstractPlayer __instance) {
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    FishingMod.determineNonVoyagedCards();
                }
            });
            AbstractDungeon.actionManager.addToBottom(new AbstractGameAction() {
                @Override
                public void update() {
                    isDone = true;
                    for (AbstractCard q : Wiz.getAllCardsInCardGroups(true, false)) {
                        if (q instanceof Bobber) {
                            ((Bobber) q).isValid = true;
                        }
                    }
                }
            });
        }
    }
}
