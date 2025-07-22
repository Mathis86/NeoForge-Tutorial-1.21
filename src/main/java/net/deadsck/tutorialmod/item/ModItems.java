package net.deadsck.tutorialmod.item;

import net.deadsck.tutorialmod.TutorialMod;
import net.deadsck.tutorialmod.item.custom.ChiselItem;
import net.deadsck.tutorialmod.item.custom.FrostFireIceItem;
import net.deadsck.tutorialmod.item.custom.StarlightAshesItem;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(TutorialMod.MOD_ID);

    public static final DeferredItem<Item> BISMUTH = ITEMS.register("bismuth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> RAW_BISMUTH = ITEMS.register("raw_bismuth",
            () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> CHISEL = ITEMS.register("chisel",
            () -> new ChiselItem(new Item.Properties().durability(32)));

    // == FUELS =================================================================================================
    // Voir les burn_time Vanilla : MAJ x2 > AbstractFurnaceBlockEntity
    // 1ère manière (FuelItem class)
    public static final DeferredItem<Item> FROSTFIRE_ICE = ITEMS.register("frostfire_ice",
            () -> new FrostFireIceItem(new Item.Properties().food(ModFoodProperties.FROSTFIRE_ICE)));

    // 2ème manière (data/neoforge/data_maps/item)
    public static final DeferredItem<Item> STARLIGHT_ASHES = ITEMS.register("starlight_ashes",
            () -> new StarlightAshesItem(new Item.Properties().food(ModFoodProperties.STARLIGHT_ASHES)));

    // == FOODS =================================================================================================
    public static final DeferredItem<Item> RADISH = ITEMS.register("radish",
            () -> new Item(new Item.Properties().food(ModFoodProperties.RADISH)) {
        // Classe anonyme pour changer l'animation d'utilisation
//                @Override
//                public UseAnim getUseAnimation(ItemStack stack) {
//                    return UseAnim.DRINK;
//                }
            });

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
