package net.deadsck.tutorialmod.item.custom;

import net.deadsck.tutorialmod.block.ModBlocks;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.Map;

public class ChiselItem extends ModToolTipItem {
    public ChiselItem(Properties properties) {
        super(properties, "chisel");
    }

    private static final Map<Block, Block> CHISEL_MAP = Map.of(
            Blocks.DIAMOND_BLOCK, ModBlocks.BISMUTH_BLOCK.get(),
            ModBlocks.BISMUTH_BLOCK.get(), Blocks.DIAMOND_BLOCK
    );

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if (CHISEL_MAP.containsKey(clickedBlock) && !level.isClientSide()) {
            level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());

            EquipmentSlot slot = context.getHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;

            context.getItemInHand().hurtAndBreak(1, ((ServerLevel) level), ((ServerPlayer) context.getPlayer()),
                    item -> {
                        context.getPlayer().onEquippedItemBroken(item, slot);
                        level.playSound(null, context.getClickedPos(), SoundEvents.SMALL_AMETHYST_BUD_BREAK, SoundSource.PLAYERS, 20f, 1f);
                    });

            level.playSound(null, context.getClickedPos(), SoundEvents.GRINDSTONE_USE, SoundSource.BLOCKS);
        }

        return InteractionResult.SUCCESS;
    }
}
