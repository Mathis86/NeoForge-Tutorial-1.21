package net.deadsck.tutorialmod.block.custom;

import net.deadsck.tutorialmod.item.ModItems;
import net.deadsck.tutorialmod.util.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;
import java.util.Map;

public class MagicBlock extends Block {
    public MagicBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        level.playSound(player, pos, SoundEvents.AMETHYST_CLUSTER_PLACE, SoundSource.BLOCKS);

        return InteractionResult.SUCCESS;
    }

    @Override
    public void stepOn(Level level, BlockPos pos, BlockState state, Entity entity) {
        Map<Item, Item> MAGIC_BLOCK_MAP = Map.of(
                ModItems.RAW_BISMUTH.get(), ModItems.BISMUTH.get(),
                ModItems.BISMUTH.get(), Items.DIAMOND
        );

        if (!level.isClientSide() && (entity instanceof ItemEntity itemEntity)) {
            // Variables utiles
            ItemStack itemStack = itemEntity.getItem();
            Item item = itemStack.getItem();
            int stackCount = itemStack.getCount();
            CompoundTag data = itemEntity.getPersistentData();

            if (!data.getBoolean("magic_transformed")) {
                if (MAGIC_BLOCK_MAP.containsKey(item) || isValidItem(itemStack)) {
                    Item itemToGet = MAGIC_BLOCK_MAP.getOrDefault(item, Items.DIAMOND);

                    itemEntity.setItem(new ItemStack(itemToGet, stackCount));
                    level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.BLOCKS, 20f, (float) (Math.random() + 1f));
                    data.putBoolean("magic_transformed", true);
                }
            }
        }

        super.stepOn(level, pos, state, entity);
    }

    private boolean isValidItem(ItemStack itemStack) {
        return itemStack.is(ModTags.Items.TRANSFORMABLE_ITEMS);
    }


    @Override
    public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        tooltipComponents.add(Component.translatable("tooltip.tutorialmod.magic_block"));
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
    }
}
