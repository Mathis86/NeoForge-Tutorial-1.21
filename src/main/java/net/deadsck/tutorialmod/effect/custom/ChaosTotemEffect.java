package net.deadsck.tutorialmod.effect.custom;

import net.deadsck.tutorialmod.effect.ModMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.EffectCure;

import java.util.Set;

public class ChaosTotemEffect extends MobEffect {

    public static class Sounds extends MobEffect {
        public Sounds() {
            super(MobEffectCategory.BENEFICIAL, 0);
        }

        @Override
        public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
            Level level = livingEntity.level();
            if (!level.isClientSide()) {
                level.playSound(null, livingEntity.getOnPos(), SoundEvents.BLAZE_AMBIENT, SoundSource.AMBIENT);
            }
            return super.applyEffectTick(livingEntity, amplifier);
        }

        @Override
        public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
            return duration % 30 == 0;
        }

        @Override
        public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
        }
    }

    // Variables constantes
    public static final int DURATION = 10 * 20;
    public static final int LIGHTNINGS_RADIUS = 10;

    public ChaosTotemEffect() {
        super(MobEffectCategory.BENEFICIAL, 0);
    }

    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        Level level = livingEntity.level();

        if (!level.isClientSide()) {
            LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
            double t = Math.random() * 2 * Math.PI;
            double dx = LIGHTNINGS_RADIUS * Math.cos(t);
            double dz = LIGHTNINGS_RADIUS * Math.sin(t);
            Vec3 lightningBoltPos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, livingEntity.blockPosition().offset((int) dx, 0, (int) dz)).getCenter();
            lightningBolt.setPos(lightningBoltPos);
            level.addFreshEntity(lightningBolt);
        }

        return true;
    }

    @Override
    public void onEffectStarted(LivingEntity livingEntity, int amplifier) {
        Level level = livingEntity.level();

        if(!level.isClientSide()) {
            ((ServerLevel) level).setWeatherParameters(0, DURATION - 2 * 20, true, true);
            livingEntity.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, DURATION, amplifier+1, false, true, false));
            livingEntity.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, DURATION, amplifier+1, false, true, false));

            // Jouer les sons de Blaze
            livingEntity.addEffect(new MobEffectInstance(ModMobEffects.CHAOS_TOTEM_SOUNDS, DURATION, 0, false, false));
        }
        super.onEffectStarted(livingEntity, amplifier);
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % (20 / (amplifier+1)) == 0;
    }

    @Override
    public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) { }
}
