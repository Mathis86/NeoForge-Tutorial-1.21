package net.deadsck.tutorialmod.item.custom;

import net.deadsck.tutorialmod.item.ModFoodProperties;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class FrostFireIceItem extends FuelItem {
    public FrostFireIceItem(Properties properties) {
        super(properties.food(ModFoodProperties.FROSTFIRE_ICE), 40*20); // 40 secondes * 20 ticks
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide()) {
            ((ServerLevel) level).setWeatherParameters(0, 20*20, true, true);
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
