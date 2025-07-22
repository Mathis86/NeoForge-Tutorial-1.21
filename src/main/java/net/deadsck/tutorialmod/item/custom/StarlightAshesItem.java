package net.deadsck.tutorialmod.item.custom;

import net.deadsck.tutorialmod.item.ModFoodProperties;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.TickTask;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;

import java.util.List;
import java.util.Random;

public class StarlightAshesItem extends ModToolTipItem {
    public StarlightAshesItem(Properties properties) {
        super(properties, "starlight_ashes"); // 40 secondes * 20 ticks
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
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

        return super.finishUsingItem(stack, level, livingEntity);
    }
}
