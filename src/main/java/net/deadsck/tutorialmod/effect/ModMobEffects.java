package net.deadsck.tutorialmod.effect;

import net.deadsck.tutorialmod.TutorialMod;
import net.deadsck.tutorialmod.effect.custom.StarlightAshesEffect;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS = DeferredRegister.create(Registries.MOB_EFFECT, TutorialMod.MOD_ID);

    public static final Holder<MobEffect> STARLIGHT_ASHES = MOB_EFFECTS.register("starlight_ashes",
            () -> new StarlightAshesEffect(MobEffectCategory.NEUTRAL, 0));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
