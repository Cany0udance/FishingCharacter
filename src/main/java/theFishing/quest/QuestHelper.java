package theFishing.quest;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.helpers.Hitbox;
import theFishing.quest.quests.AbstractQuest;
import theFishing.util.ImageHelper;

import java.util.ArrayList;

public class QuestHelper {
    public static ArrayList<AbstractQuest> quests = new ArrayList<>();
    public static ArrayList<Hitbox> boxes = new ArrayList<>();

    public static final float POSITION_X = 10F * Settings.scale;
    public static final float POSITION_Y = 250F * Settings.scale;

    static {
        for (int i = 0; i < 15; i++) {
            boxes.add(new Hitbox(POSITION_X, Settings.HEIGHT - (POSITION_Y + (i * (25F * Settings.scale))), 200F * Settings.scale, 25F * Settings.scale));
        }
    }

    public static void render(SpriteBatch sb) {
        FontHelper.renderFontLeftTopAligned(
                sb,
                FontHelper.tipHeaderFont,
                "Quest Log",
                POSITION_X,
                Settings.HEIGHT - POSITION_Y + (50 * Settings.scale),
                Settings.GOLD_COLOR
        );
        int xr = 0;
        for (AbstractQuest q : quests) {
            for (int i = 0; i < q.goal; i++) {
                if (i < q.progress || q.goal == 1) {
                    sb.setColor(Color.WHITE.cpy());
                } else {
                    sb.setColor(Color.GRAY.cpy());
                }
                ImageHelper.drawTextureScaled(sb, q.progressTex(), boxes.get(xr).x + ((i * 32) * Settings.scale), boxes.get(xr).y);
            }
            FontHelper.renderFontLeft(
                    sb,
                    FontHelper.tipHeaderFont,
                    q.getName(),
                    boxes.get(xr).x + (100 * Settings.scale),
                    boxes.get(xr).y + (12.5F * Settings.scale),
                    Color.WHITE.cpy()
            );
            xr++;
        }
        for (Hitbox h : boxes) {
            h.render(sb);
        }
    }

    public static void acceptQuest(AbstractQuest quest) {
        quests.add(quest);
    }

    public static void update() {
        for (int i = 0; i < boxes.size(); i++) {
            boxes.get(i).update();
            if (boxes.get(i).hovered) {
                if (i < quests.size()) {
                    ImageHelper.tipBoxAtMousePos(quests.get(i).getName(), quests.get(i).getDescription());
                }
            }
        }
    }

    public static void reset() {
        quests = new ArrayList<>();
    }


    public static void onExhaust(AbstractCard c) {
        for (AbstractQuest q : quests) {
            q.onExhaust(c);
        }
    }
}
