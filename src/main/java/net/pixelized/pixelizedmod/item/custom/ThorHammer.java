package net.pixelized.pixelizedmod.item.custom;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.*;
import net.minecraft.world.World;

import java.util.Random;

public class ThorHammer extends PickaxeItem {

    Random rand = new Random();

    public ThorHammer(ToolMaterial toolMaterial, int attackDamage, float attackSpeed, Settings settings) {
        super(toolMaterial, attackDamage, attackSpeed, settings);
    }

    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        World world = attacker.getWorld();
        if (rand.nextInt(100) > 75) {
            LightningEntity spawnedLightning = new LightningEntity(EntityType.LIGHTNING_BOLT, world);
            spawnedLightning.setPosition(target.getPos());
            world.spawnEntity(spawnedLightning);
        }
        return true;
    }
}
