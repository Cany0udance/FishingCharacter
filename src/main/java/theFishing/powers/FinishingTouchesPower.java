package theFishing.powers;

import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;

import static theFishing.FishingMod.makeID;

public class FinishingTouchesPower extends AbstractAdventurerPower {
    public static String ID = makeID(FinishingTouchesPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(ID);

    public FinishingTouchesPower() {
        super(makeID("FinishingTouchesPower"), powerStrings.NAME, AbstractPower.PowerType.BUFF, false, AbstractDungeon.player, 1);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (!card.purgeOnUse && this.amount > 0 && AbstractDungeon.player.hand
                .size() == 1 && AbstractDungeon.player.hand.contains(card)) {
            this.flash();
            for (int i = 0; i < amount; i++) {
                AbstractMonster m = null;
                if (action.target != null) {
                    m = (AbstractMonster) action.target;
                }

                AbstractCard tmp = card.makeSameInstanceOf();
                AbstractDungeon.player.limbo.addToBottom(tmp);
                tmp.current_x = card.current_x;
                tmp.current_y = card.current_y;
                tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
                tmp.target_y = (float) Settings.HEIGHT / 2.0F;
                if (m != null) {
                    tmp.calculateCardDamage(m);
                }

                tmp.purgeOnUse = true;
                AbstractDungeon.actionManager
                        .addCardQueueItem(new CardQueueItem(tmp, m, card.energyOnUse, true, true), true);
            }
        }

    }

    @Override
    public void updateDescription() {
        description = amount == 1 ? powerStrings.DESCRIPTIONS[0] : (powerStrings.DESCRIPTIONS[1] + amount + powerStrings.DESCRIPTIONS[2]);
    }
}
