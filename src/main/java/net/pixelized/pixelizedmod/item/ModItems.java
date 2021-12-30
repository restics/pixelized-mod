package net.pixelized.pixelizedmod.item;


import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.pixelized.pixelizedmod.PixelizedMod;
import net.pixelized.pixelizedmod.item.custom.Pepper;

public class ModItems {

    public static final Item TOPAZ = registerItem("topaz", new Item(new FabricItemSettings().group(ModItemGroup.PIXELIZED)));

    public static final Pepper PEPPER = (Pepper) registerItem("pepper", new Pepper(new FabricItemSettings().food(new FoodComponent.Builder().hunger(2).saturationModifier(0.2f).statusEffect(
            new StatusEffectInstance(StatusEffects.STRENGTH, 100, 0), 1.0f).build()).group(ModItemGroup.PIXELIZED)));


    private static Item registerItem(String name, Item item){

        return Registry.register(Registry.ITEM, new Identifier(PixelizedMod.MOD_ID, name), item);
    }

    public static void registerModItems(){
        System.out.println("Registering mod items for " + PixelizedMod.MOD_ID);
    }
}
