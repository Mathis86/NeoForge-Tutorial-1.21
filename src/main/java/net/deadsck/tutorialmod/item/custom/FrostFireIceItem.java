package net.deadsck.tutorialmod.item.custom;

import net.deadsck.tutorialmod.item.ModFoodProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

public class FrostFireIceItem extends ModToolTipItem {
    public FrostFireIceItem(Properties properties) {
        super(properties, "frostfire_ice"); // 40 secondes * 20 ticks
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide() && livingEntity instanceof ServerPlayer player) {
            ((ServerLevel) level).setWeatherParameters(0, 18*20, true, true);

            float randomPitch = (float) Math.random() * 0.4f + 1f;
            int radius = (int) (Math.random() * 3) + 4;
            BlockPos playerPos = player.blockPosition();

            // Cercle de glace plein
            for (int dr = 0; dr < radius + 5; dr++) {
                for (double theta = 0; theta < Math.PI; theta += 1f / radius) {
                    for (double phi = 0; phi < 2 * Math.PI; phi += 1f / radius) {
                        double dx = dr * Math.sin(theta) * Math.cos(phi);
                        double dy = dr * Math.sin(theta) * Math.sin(phi);
                        double dz = dr * Math.cos(theta);

                        BlockPos icePos = playerPos.offset((int) dx, (int) dy, (int) dz).below();

                        if (level.getBlockState(icePos).isAir()) continue;

                        if (dr < radius || Math.random() < 0.75f / (1 + dr - radius)) {
                            level.setBlockAndUpdate(icePos, Blocks.PACKED_ICE.defaultBlockState());
                            level.playSound(null, player.blockPosition(), SoundEvents.GLASS_BREAK, SoundSource.BLOCKS, 0.03f, randomPitch);
                        }
                    }
                }
            }
        }
        return super.finishUsingItem(stack, level, livingEntity);
    }
}
