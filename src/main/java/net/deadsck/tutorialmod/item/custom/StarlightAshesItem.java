package net.deadsck.tutorialmod.item.custom;

import net.deadsck.tutorialmod.item.ModFoodProperties;
import net.minecraft.core.BlockPos;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.Random;

public class StarlightAshesItem extends Item {
    public StarlightAshesItem(Properties properties) {
        super(properties.food(ModFoodProperties.STARLIGHT_ASHES)); // 40 secondes * 20 ticks
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        if (!level.isClientSide() && livingEntity instanceof ServerPlayer player) {
            int radius = (int) (Math.random() * 6) + 6;
            int width = (int) (Math.random() * 2) + 2;

            level.playSound(null, player.blockPosition(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER);

            for (int dr = radius - width; dr < radius + width; dr++) {
                for (double t = 0; t < 2 * Math.PI; t += 1f / radius) {
                    // Position aléatoire autour du joueur
                    BlockPos playerPos = player.blockPosition();

                    double dx = dr * Math.cos(t);
                    double dz = dr * Math.sin(t);

                    BlockPos firePos = level.getHeightmapPos(Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, playerPos.offset((int) dx, 0, (int) dz));

                    // Vérifie qu'on peut poser du feu (le bloc au-dessus doit être de l'air)
                    if (level.getBlockState(firePos).isAir() &&
                            level.getBlockState(firePos.below()).isSolidRender(level, firePos.below())) {
                        level.setBlockAndUpdate(firePos, Blocks.FIRE.defaultBlockState());
                    }
                }
            }
        }

        return super.finishUsingItem(stack, level, livingEntity);
    }
}
