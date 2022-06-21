package net.pixelized.pixelizedmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.Random;

public class CounterBlock extends Block {

    public static final int TELEPORT_RADIUS = 255;

    Random rand = new Random();
    public CounterBlock(Settings settings) {
        super(settings);
    }

    @Override
    public void onBreak(World world, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBreak(world, pos, state, player);
        if (world.isClient) {
            return;
        }
        int randomNum = rand.nextInt(1000);
        if (randomNum > 250){
            stealItems(player, world);
        }
        else if (randomNum < 250){
            zombieSwarm( world, pos, player);
        }
    }

    //steals as many items from a players inventory as possible and moves them into a random chest, then gives them the coords.
    public void stealItems(PlayerEntity player, World world){
        // check if teleportedY is within bounds of world
        int teleportedY = player.getBlockY() - (TELEPORT_RADIUS/2) + rand.nextInt(TELEPORT_RADIUS);
        if (teleportedY > 255){
            teleportedY = 255;
        }
        else if (teleportedY < -60){
            teleportedY = -60;
        }
        BlockPos teleportLoc = new BlockPos(player.getBlockX() - (TELEPORT_RADIUS/2) + rand.nextInt(TELEPORT_RADIUS),
                teleportedY,
                player.getBlockZ() - (TELEPORT_RADIUS/2) + rand.nextInt(TELEPORT_RADIUS));

        world.setBlockState(teleportLoc, Blocks.CHEST.getDefaultState());
        ChestBlockEntity chest = (ChestBlockEntity) world.getBlockEntity(teleportLoc);
        assert chest != null;
        for (int slot = 0 ,chestSlot = 0; slot < 36 && chestSlot < 27; slot++){
            ItemStack items = player.getInventory().getStack(slot);
            if (!items.isEmpty()) {
                chest.setStack(chestSlot, items);
                chestSlot++;
            }
            player.getInventory().setStack(slot, ItemStack.EMPTY);
        }
        sendMessage(player, "Your stuff is at X:" + teleportLoc.getX() + " Y:" + teleportedY + " Z:" + teleportLoc.getZ() + ", have fun =)");
    }

    public void zombieSwarm(World world, BlockPos pos, PlayerEntity player){
        player.setHealth(0.5f);
        player.getHungerManager().setSaturationLevel(0);

        for(int count = 0; count < 10; count++){
            ZombieEntity zombies = new ZombieEntity(world);
            zombies.setBaby(true);
            zombies.setStackInHand(Hand.MAIN_HAND, new ItemStack(Items.IRON_AXE));
            zombies.setStackInHand(Hand.OFF_HAND, new ItemStack(Items.SHIELD));
            zombies.equipStack(EquipmentSlot.HEAD, new ItemStack(Items.IRON_HELMET));
            zombies.equipStack(EquipmentSlot.CHEST, new ItemStack(Items.IRON_CHESTPLATE));
            zombies.equipStack(EquipmentSlot.LEGS, new ItemStack(Items.IRON_LEGGINGS));
            zombies.equipStack(EquipmentSlot.FEET, new ItemStack(Items.IRON_BOOTS));
            zombies.setEquipmentDropChance(EquipmentSlot.HEAD, 0F);
            zombies.setEquipmentDropChance(EquipmentSlot.CHEST, 0F);
            zombies.setEquipmentDropChance(EquipmentSlot.LEGS, 0F);
            zombies.setEquipmentDropChance(EquipmentSlot.FEET, 0F);
            zombies.setEquipmentDropChance(EquipmentSlot.MAINHAND, 0F);
            zombies.setEquipmentDropChance(EquipmentSlot.OFFHAND, 0F);
            zombies.setPos(pos.getX()+ 0.5, pos.getY() + 0.5, pos.getZ() + 0.5);
            world.spawnEntity(zombies);

        }
        sendMessage(player, "Run!!!");
    }

    public void sendMessage(PlayerEntity player, String message){
        player.sendMessage(new LiteralText(message), false);
    }
}
