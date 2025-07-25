package net.deadsck.tutorialmod.item.custom;

import net.deadsck.tutorialmod.block.ModBlocks;
import net.deadsck.tutorialmod.effect.ModMobEffects;
import net.deadsck.tutorialmod.effect.custom.ChaosTotemEffect;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LightningBolt;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import org.jetbrains.annotations.NotNull;

import java.util.Map;

public class ChaosTotemItem extends Item {
    public static final int USE_DURATION = 60;
    public static final int MAX_AMPLIFIER = 2;

    public ChaosTotemItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand usedHand) {
        player.startUsingItem(usedHand);
        return super.use(level, player, usedHand);
    }

    @Override
    public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
        if (!level.isClientSide() && remainingUseDuration % 15 == 5) {
            level.playSound(null, livingEntity.getOnPos(), SoundEvents.LIGHTNING_BOLT_THUNDER, SoundSource.WEATHER);
        }
        super.onUseTick(level, livingEntity, stack, remainingUseDuration);
    }

    private void useItem(Level level, LivingEntity livingEntity, ItemStack stack, int amplifier) {
        if (!level.isClientSide()) {
            // Animation de destruction de l'item
            EquipmentSlot slot = livingEntity.getUsedItemHand() == InteractionHand.MAIN_HAND ? EquipmentSlot.MAINHAND : EquipmentSlot.OFFHAND;
            livingEntity.onEquippedItemBroken(stack.getItem(), slot);

            // Application de l'effet correspondant
            livingEntity.addEffect(new MobEffectInstance(ModMobEffects.CHAOS_TOTEM, ChaosTotemEffect.DURATION, amplifier, false, false, true));
        }

        if (livingEntity instanceof Player player && !player.getAbilities().instabuild) {
            stack.shrink(1);
        }
    }

    @Override
    public void releaseUsing(ItemStack stack, Level level, LivingEntity livingEntity, int timeCharged) {
        int amplifier = (MAX_AMPLIFIER + 1) * (USE_DURATION - timeCharged) / USE_DURATION;
        if (amplifier > 0) {
            useItem(level, livingEntity, stack, amplifier - 1);
        }

        super.releaseUsing(stack, level, livingEntity, timeCharged);
    }

    @Override
    public @NotNull ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity livingEntity) {
        useItem(level, livingEntity, stack, MAX_AMPLIFIER);

        return super.finishUsingItem(stack, level, livingEntity);
    }

    @Override
    public int getUseDuration(ItemStack stack, LivingEntity entity) {
        return USE_DURATION;
    }

    @Override
    public UseAnim getUseAnimation(ItemStack stack) {
        return UseAnim.BOW;
    }
}
