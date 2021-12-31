package net.pixelized.pixelizedmod.item.custom;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class Pepper extends Item {

    public Pepper(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack finishUsing(ItemStack stack, World world, LivingEntity user) {
        user.setFireTicks(100);
        return super.finishUsing(stack,world,user);
    }

}
