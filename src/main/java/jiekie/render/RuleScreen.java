package jiekie.render;

import jiekie.SepiaFilmRuleMod;
import jiekie.widget.InvisibleButton;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.gui.Drawable;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.sound.PositionedSoundInstance;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import org.lwjgl.glfw.GLFW;

public class RuleScreen extends Screen {
    private int currentPage = 1;
    private final int startPage = 1;
    private final int endPage = 3;

    private InvisibleButton previousPageButton;
    private InvisibleButton nextPageButton;

    private static final Identifier LEFT_ARROW_IMAGE = Identifier.of(SepiaFilmRuleMod.MOD_ID, "textures/arrow/left_arrow.png");
    private static final Identifier RIGHT_ARROW_IMAGE = Identifier.of(SepiaFilmRuleMod.MOD_ID, "textures/arrow/right_arrow.png");

    public RuleScreen() {
        super(Text.of("Rule"));
    }

    @Override
    protected void init() {
        // rule image
        int imageWidth = this.width;
        int imageHeight = this.height;
        this.addDrawable(new Drawable() {
            @Override
            public void render(DrawContext context, int mouseX, int mouseY, float delta) {
                Identifier ruleImage = Identifier.of(SepiaFilmRuleMod.MOD_ID, "textures/rule/rule_" + currentPage + ".png");
                context.drawTexture(ruleImage, 0, 0, 0, 0, imageWidth, imageHeight, imageWidth, imageHeight);
            }
        });

        // previous page
        int arrowWidth = 32;
        int arrowHeight = 32;
        int leftArrowX = 60;
        int arrowY = this.height - 60;
        this.addDrawable(new Drawable() {
            @Override
            public void render(DrawContext context, int mouseX, int mouseY, float delta) {
                context.drawTexture(LEFT_ARROW_IMAGE, leftArrowX, arrowY, 0, 0, arrowWidth, arrowHeight, arrowWidth, arrowHeight);
            }
        });

        previousPageButton = new InvisibleButton(leftArrowX, arrowY, arrowWidth, arrowHeight, () -> {
            if(currentPage == startPage) return;
            currentPage--;
        });
        this.addDrawableChild(previousPageButton);

        // next page
        int rightArrowX = this.width - 60 - arrowWidth;
        this.addDrawable(new Drawable() {
            @Override
            public void render(DrawContext context, int mouseX, int mouseY, float delta) {
                context.drawTexture(RIGHT_ARROW_IMAGE, rightArrowX, arrowY, 0, 0, arrowWidth, arrowHeight, arrowWidth, arrowHeight);
            }
        });

        previousPageButton = new InvisibleButton(rightArrowX, arrowY, arrowWidth, arrowHeight, () -> {
            if(currentPage == endPage) return;
            currentPage++;
        });
        this.addDrawableChild(previousPageButton);
    }

    @Override
    public boolean shouldPause() {
        return false;
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        if(MinecraftClient.getInstance().options.inventoryKey.matchesKey(keyCode, scanCode) || keyCode == GLFW.GLFW_KEY_ESCAPE) {
            this.close();
            return true;
        }

        return super.keyPressed(keyCode, scanCode, modifiers);
    }
}
