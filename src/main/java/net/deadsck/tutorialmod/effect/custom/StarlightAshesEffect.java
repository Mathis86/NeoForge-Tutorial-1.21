package net.deadsck.tutorialmod.effect.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.client.extensions.common.IClientMobEffectExtensions;
import net.neoforged.neoforge.common.EffectCure;

import java.util.Set;
import java.util.function.Consumer;

public class StarlightAshesEffect extends MobEffect implements IClientMobEffectExtensions {
    public StarlightAshesEffect(MobEffectCategory category, int color) {
        super(category, color);
    }

    @Override
    public boolean isVisibleInInventory(MobEffectInstance instance) {
        return false;
    }


    @Override
    public boolean applyEffectTick(LivingEntity livingEntity, int amplifier) {
        Level level = livingEntity.level();

        if (!level.isClientSide() && livingEntity instanceof ServerPlayer player) {
            int radius = (int) (Math.random() * 6) + 6;
            BlockPos playerPos = player.blockPosition();

            level.playSound(null, player.blockPosition(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER);

            for (int i = 0; i < Math.random() * 4 + 1; i++) {
                double t = Math.random() * 2 * Math.PI;

                double dx = radius * Math.cos(t);
                double dz = radius * Math.sin(t);

                Vec3 thunderPos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, playerPos.offset((int) dx, 0, (int) dz)).getCenter();

                LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                lightningBolt.setPos(thunderPos);
                level.addFreshEntity(lightningBolt);
            }
        }

        return true;
    }

    @Override
    public boolean shouldApplyEffectTickThisTick(int duration, int amplifier) {
        return duration % 20 == 0;
    }

    @Override
    public void fillEffectCures(Set<EffectCure> cures, MobEffectInstance effectInstance) {
    }

    @Override
    public void onEffectAdded(LivingEntity livingEntity, int amplifier) {
        Level level = livingEntity.level();

        if (!level.isClientSide() && livingEntity instanceof ServerPlayer player) {
            int radius = (int) (Math.random() * 6) + 6;
            int width = (int) (Math.random() * 2) + 2;
            BlockPos playerPos = player.blockPosition();

            level.playSound(null, player.blockPosition(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER);

            for (int dr = radius - width / 2; dr < radius + width / 2; dr++) {
                for (double t = 0; t < 2 * Math.PI; t += 1f / radius) {

                    double dx = dr * Math.cos(t);
                    double dz = dr * Math.sin(t);

                    BlockPos firePos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, playerPos.offset((int) dx, 0, (int) dz));

                        level.setBlockAndUpdate(firePos, Blocks.FIRE.defaultBlockState());

                        if (dr == radius) {
                            LightningBolt lightningBolt = new LightningBolt(EntityType.LIGHTNING_BOLT, level);
                            lightningBolt.setPos(firePos.getCenter());
                            level.addFreshEntity(lightningBolt);
                        }
                }
            }
        }

        super.onEffectAdded(livingEntity, amplifier);
    }
}
