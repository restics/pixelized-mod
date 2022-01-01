package net.pixelized.pixelizedmod.item.custom;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Formatting;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.pixelized.pixelizedmod.block.ModBlocks;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class DowsingRodItem extends ToolItem implements Vanishable {

    protected final float dowsingPower;

    //once i learn mixins better ill use material.getDowsingPower but until then idk =|
    public DowsingRodItem(ToolMaterial material, Item.Settings settings, float dowsingPower) {
        super(material, settings);
        this.dowsingPower = dowsingPower;
    }

    public float getDowsingPower(){
        return this.dowsingPower;
    }

    //Checks x number of blocks under the target block given the strength of the dowsing rod
    //stops when it finds strength/2 instances of treasure
    @Override
    public ActionResult useOnBlock(ItemUsageContext context){

        //makes sure this is client only (this only gives info, doesnt actually change the environment)
        if (context.getWorld().isClient()){
            BlockPos positionClicked = context.getBlockPos();
            World world = context.getWorld();
            ItemStack stack = context.getStack();
            boolean foundBlock = false;
            PlayerEntity player = Objects.requireNonNull(context.getPlayer());

            for(int i = 0; i < 10 + dowsingPower * 5 ; i++){
                Block blockBelow = world.getBlockState(positionClicked.down(i)).getBlock();
                if (isValuableBlock(blockBelow)){
                    outputValuableCoordinates(blockBelow, positionClicked.add(0, -i, 0),player);
                    foundBlock = true;
                    break;
                }
            }
            if (!foundBlock)
                player.sendMessage(new LiteralText("Nothing found! Try using this somewhere else..."), false);
            stack.damage(1, Objects.requireNonNull(context.getPlayer()), (e) -> {
                e.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
            });
        }
        return super.useOnBlock(context);
    }

    //  for the love of god make a custom ore tag to include all the ores
    private boolean isValuableBlock(Block block){
        return block == Blocks.COAL_ORE || block == Blocks.DEEPSLATE_COAL_ORE ||
                block == Blocks.COPPER_ORE || block == Blocks.DEEPSLATE_COPPER_ORE ||
                block == Blocks.IRON_ORE || block == Blocks.DEEPSLATE_IRON_ORE ||
                block == Blocks.GOLD_ORE || block == Blocks.DEEPSLATE_GOLD_ORE ||
                block == Blocks.DIAMOND_ORE || block == Blocks.DEEPSLATE_DIAMOND_ORE ||
                block == ModBlocks.TOPAZ_ORE || block == Blocks.ANCIENT_DEBRIS;
    }

    private void outputValuableCoordinates(Block blockFound, BlockPos pos, PlayerEntity player){
        String message = "Found " + blockFound.asItem().getName().getString();
        if (dowsingPower >= 3){
            message = message + " at (" + pos.getX() + ", " + pos.getY() + ", " + pos.getZ() + ")";
        }
        player.sendMessage(new LiteralText(message), false);
    }
    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        if (dowsingPower >= 3){
            // until i get an enchant for this =/
            tooltip.add(new LiteralText("Dowser I").setStyle(Style.EMPTY.withColor(Formatting.LIGHT_PURPLE)));
        }
        tooltip.add(new LiteralText("Hold shift for more info.").setStyle(Style.EMPTY.withColor(Formatting.GRAY)));
        if (Screen.hasShiftDown()){
            tooltip.add(new LiteralText("Info: ").setStyle(Style.EMPTY.withColor(Formatting.WHITE)));
            tooltip.add(new LiteralText("   - Dowsing Power : ").setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                    .append(new LiteralText("" + this.dowsingPower).setStyle(Style.EMPTY.withColor(Formatting.YELLOW))));
        }
    }

}
