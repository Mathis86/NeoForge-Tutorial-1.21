package net.deadsck.tutorialmod.effect;

import net.deadsck.tutorialmod.TutorialMod;
import net.deadsck.tutorialmod.effect.custom.ChaosTotemEffect;
import net.deadsck.tutorialmod.effect.custom.StarlightAshesEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, TutorialMod.MOD_ID);

    public static final Holder<MobEffect> STARLIGHT_ASHES = MOB_EFFECTS.register("starlight_ashes",
            () -> new StarlightAshesEffect(MobEffectCategory.NEUTRAL, 0)
                    .addAttributeModifier(Attributes.MOVEMENT_SPEED, ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "starlight_ashes"), -1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.ARMOR, ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "starlight_ashes"), 2000f, AttributeModifier.Operation.ADD_VALUE)
                    .addAttributeModifier(Attributes.JUMP_STRENGTH, ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "starlight_ashes"), -1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.BLOCK_BREAK_SPEED, ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "starlight_ashes"), -1f, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL)
                    .addAttributeModifier(Attributes.KNOCKBACK_RESISTANCE, ResourceLocation.fromNamespaceAndPath(TutorialMod.MOD_ID, "starlight_ashes"), 2000f, AttributeModifier.Operation.ADD_VALUE)
    );

    public static final Holder<MobEffect> CHAOS_TOTEM = MOB_EFFECTS.register("chaos_totem", ChaosTotemEffect::new);
    public static final Holder<MobEffect> CHAOS_TOTEM_SOUNDS = MOB_EFFECTS.register("chaos_totem_sounds", ChaosTotemEffect.Sounds::new);

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
