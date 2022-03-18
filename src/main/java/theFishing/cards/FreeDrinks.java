package theFishing.cards;

import basemod.helpers.TooltipInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.PotionHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.potions.PotionSlot;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.rooms.MonsterRoom;
import com.megacrit.cardcrawl.rooms.MonsterRoomBoss;
import com.megacrit.cardcrawl.rooms.MonsterRoomElite;

import java.util.Arrays;
import java.util.List;

import static theFishing.FishingMod.makeID;
import static theFishing.util.Wiz.applyToEnemy;
import static theFishing.util.Wiz.forAllMonstersLiving;

public class FreeDrinks extends AbstractFishingCard implements OnObtainCard {
    public final static String ID = makeID("FreeDrinks");
    // intellij stuff skill, all_enemy, uncommon, , , , , 3, 1

    public FreeDrinks() {
        super(ID, 2, CardType.SKILL, CardRarity.UNCOMMON, CardTarget.ALL_ENEMY);
        baseMagicNumber = magicNumber = 3;
        exhaust = true;
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        forAllMonstersLiving(q -> applyToEnemy(q, new StrengthPower(q, -magicNumber)));
    }

    @Override
    public void onObtainCard() {
        if (AbstractDungeon.player.potions.stream().anyMatch(c -> c instanceof PotionSlot)) {
            AbstractDungeon.player.obtainPotion(AbstractDungeon.returnRandomPotion());
        }
    }

    @Override
    public List<TooltipInfo> getCustomTooltips() {
        return Arrays.asList(new TooltipInfo("DISCLAIMER", "Offer only valid with an empty potion slot."));
    }

    public void upp() {
        upgradeMagicNumber(1);
    }
}