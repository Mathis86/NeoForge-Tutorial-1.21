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
        super(properties, "starlight_ashes");
    }

//    @Override
//    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
//
//
//        return super.finishUsingItem(stack, level, livingEntity);
//    }
}
