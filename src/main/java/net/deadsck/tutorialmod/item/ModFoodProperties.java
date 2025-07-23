package net.deadsck.tutorialmod.item;

import net.deadsck.tutorialmod.effect.ModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {
    public static final FoodProperties RADISH = new FoodProperties.Builder()
            .nutrition(3)
            .saturationModifier(0.25f)
            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 400), 0.5f)
            .build();

    public static final FoodProperties FROSTFIRE_ICE = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.5f)
            .alwaysEdible()
            .effect(() -> new MobEffectInstance(MobEffects.NIGHT_VISION, 400), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 400, 1), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 400, 1), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.WITHER, 400), 1f)
            .build();

    public static final FoodProperties STARLIGHT_ASHES = new FoodProperties.Builder()
            .nutrition(5)
            .saturationModifier(0.5f)
            .alwaysEdible()
//            .effect(() -> new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 30*20), 1f)
//            .effect(() -> new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 30*20, 2), 1f)
//            .effect(() -> new MobEffectInstance(MobEffects.JUMP, 30*20, 1), 1f)
//            .effect(() -> new MobEffectInstance(MobEffects.DAMAGE_BOOST, 30*20, 2), 1f)
//            .effect(() -> new MobEffectInstance(MobEffects.DIG_SPEED, 30*20, 2), 1f)
            .effect(() -> new MobEffectInstance(ModMobEffects.STARLIGHT_ASHES, 10*20, 0, false, false, false), 1f)
            .build();
}
