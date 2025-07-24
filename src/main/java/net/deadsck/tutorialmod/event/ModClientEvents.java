package net.deadsck.tutorialmod.event;

import net.deadsck.tutorialmod.TutorialMod;
import net.deadsck.tutorialmod.effect.ModMobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.extensions.common.IClientMobEffectExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

@EventBusSubscriber(modid = TutorialMod.MOD_ID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public class ModClientEvents {

    @SubscribeEvent
    public static void registerClientExtension(RegisterClientExtensionsEvent event) {
        event.registerMobEffect(new IClientMobEffectExtensions() {
            @Override
            public boolean isVisibleInInventory(MobEffectInstance instance) {
                return false;
            }
        }, ModMobEffects.STARLIGHT_ASHES.value());
    }
}
