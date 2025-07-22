package net.deadsck.tutorialmod.item.custom;

import net.deadsck.tutorialmod.TutorialMod;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;

import java.util.List;

public class ModToolTipItem extends Item {
    private String itemID = "";
    public ModToolTipItem(Properties properties, String itemID) {
        super(properties);
        this.itemID = itemID;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        if (Screen.hasShiftDown()) tooltipComponents.add(Component.translatable("tooltip." + TutorialMod.MOD_ID + "." + itemID));
        else tooltipComponents.add(Component.translatable("tooltip.tutorialmod.press_shift"));

        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
