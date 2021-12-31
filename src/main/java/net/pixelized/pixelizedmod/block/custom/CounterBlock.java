package net.pixelized.pixelizedmod.block.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.ChestBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import java.util.ArrayList;
import java.util.Random;

public class CounterBlock extends Block {

    public static final int TELEPORT_RADIUS = 100;

    Random rand = new Random();
    public CounterBlock(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (world.isClient) {
            return ActionResult.SUCCESS;
        }
        // check if teleportedY is within bounds of world
        int teleportedY = player.getBlockY() - (TELEPORT_RADIUS/4) + rand.nextInt(TELEPORT_RADIUS/2);
        if (teleportedY > 255){
            teleportedY = 255;
        }
        else if (teleportedY < 0){
            teleportedY = 0;
        }
        BlockPos teleportLoc = new BlockPos(player.getBlockX() - (TELEPORT_RADIUS/2) + rand.nextInt(TELEPORT_RADIUS),
                teleportedY,
                player.getBlockZ() - (TELEPORT_RADIUS/2) + rand.nextInt(TELEPORT_RADIUS));

        BlockState block2Change = world.getBlockState(teleportLoc);
        world.setBlockState(teleportLoc, Blocks.CHEST.getDefaultState());
        ChestBlockEntity chest = (ChestBlockEntity) world.getBlockEntity(teleportLoc);

        for (int slot= 0 ,chestSlot = 0; slot < 36 && chestSlot < 27; slot++){
            ItemStack items = player.getInventory().getStack(slot);
            assert chest != null;
            if (!items.isEmpty()) {
                chest.setStack(chestSlot, items);
                chestSlot++;
            }
            player.getInventory().setStack(slot, ItemStack.EMPTY);

        }
        player.sendMessage(new LiteralText("Your stuff is at X:" + teleportLoc.getX() + " Y:" + teleportedY + " Z:" + teleportLoc.getZ() + ", have fun =)"), false);
        world.setBlockState(pos, Blocks.AIR.getDefaultState());

        return ActionResult.SUCCESS;
    }



}
