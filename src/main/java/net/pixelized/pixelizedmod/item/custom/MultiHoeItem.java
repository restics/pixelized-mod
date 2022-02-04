/*
Hoe that can till multiple tiles in front of it
 */

package net.pixelized.pixelizedmod.item.custom;

import com.mojang.datafixers.util.Pair;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.item.HoeItem;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolMaterial;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Objects;


public class MultiHoeItem extends HoeItem {

    private final int AREA_WIDTH;
    private final int AREA_LENGTH;


    public MultiHoeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings, int width, int length) {
        super(material, attackDamage, attackSpeed, settings);
        AREA_WIDTH = width;
        AREA_LENGTH = length;
    }
    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        Direction dir = Objects.requireNonNull(context.getPlayer()).getMovementDirection();
        ActionResult r = super.useOnBlock(context);
        if (r != ActionResult.PASS) tillLooped(context, dir);
        return r;
    }

    public void tillLooped(ItemUsageContext context, Direction dir){
        World world = context.getWorld();
        int xPos = context.getBlockPos().getX();
        int yPos = context.getBlockPos().getY();
        int zPos = context.getBlockPos().getZ();
        int xOffset = 0;
        int zOffset = 0;

        int tempWidth = AREA_WIDTH;
        int tempLength = AREA_LENGTH;

        switch (dir) {
            case WEST :
                xOffset = -(tempLength - 1);
            case EAST :
                zOffset = -(tempWidth / 2);
                break;

            case NORTH :
                int temp = tempWidth;
                tempWidth = tempLength;
                tempLength = temp;

                xOffset = -(tempLength / 2);
                zOffset = -(tempWidth - 1);
                break;
            case SOUTH:
                temp = tempWidth;
                tempWidth = tempLength;
                tempLength = temp;

                xOffset = -(tempLength / 2);
                break;

        }
        for(int x = 0; x < tempLength; x++){
            for(int z = 0; z < tempWidth; z++){
                BlockPos position = new BlockPos(xPos + x + xOffset,yPos,zPos + z + zOffset);
                Block blockType = world.getBlockState(position).getBlock();
                Pair pair = TILLING_ACTIONS.get(blockType);
                //checks if its a valid block
                if(pair != null){
                    if (!world.isClient){
                        context.getWorld().setBlockState(position, Blocks.FARMLAND.getDefaultState().with(FarmlandBlock.MOISTURE, 7), 11);
                    }
                }
            }

        }
    }

}
