package theFishing.patch.achievements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.*;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.screens.stats.StatsScreen;
import javassist.CtBehavior;

import java.util.Iterator;

@SpirePatch(clz = StatsScreen.class, method = "renderStatScreen")
public class RenderStatScreenPatch {
    @SpireInsertPatch(locator = Locator.class, localvars = {"renderY"})
    public static void Insert(StatsScreen __instance, SpriteBatch sb, @ByRef float[] renderY) {
        renderY[0] += 50.0F * Settings.scale;

        // Set the color before rendering the header
        sb.setColor(Settings.CREAM_COLOR);

        // Render the More Achievements section
        StatsScreen.renderHeader(sb, "#yAdventurer #yAchievements", 300.0F * Settings.scale, renderY[0]);
        StatsScreenPatch.getFishingAchievements().render(sb, renderY[0]);
        renderY[0] -= StatsScreenPatch.getFishingAchievements().calculateHeight(); // Add the height of the More Achievements grid
        renderY[0] -= 100.0F * Settings.scale; // Add some additional spacing after the More Achievements section
    }

    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(Iterator.class, "hasNext");
            return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
        }
    }
}
