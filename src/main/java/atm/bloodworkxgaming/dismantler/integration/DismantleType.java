package atm.bloodworkxgaming.dismantler.integration;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public abstract class DismantleType {
    protected Set<Block> triggerBlocks;
    protected String modLimitation;

    public DismantleType(String modLimitation, Block... triggerBlocks) {
        this.triggerBlocks = new HashSet<>();
        Collections.addAll(this.triggerBlocks, triggerBlocks);
        this.modLimitation = modLimitation;
    }

    public boolean shouldTrigger(IBlockState state){
        return triggerBlocks.contains(state.getBlock()) && checkBlockState(state);
    }

    /**
     * Breaks the block and adds the drop to the list
     * @param worldIn
     * @param pos
     * @param drops
     */
    public void breakBlock(World worldIn, BlockPos pos, NonNullList<ItemStack> drops){
        IBlockState currentState = worldIn.getBlockState(pos);

        if (!currentState.getBlock().getRegistryName().getResourceDomain().equals(modLimitation))
            return;

        currentState.getBlock().getDrops(drops, worldIn, pos, currentState, 0);
        worldIn.destroyBlock(pos, false);
    }

    /**
     * Function being called when thingy got matched
     * @param player
     * @param worldIn
     * @param pos
     * @param state
     * @param drops List of drops which are getting added to the player/dropped into the world
     */
    public abstract void startMining(EntityPlayer player, World worldIn, BlockPos pos, IBlockState state, NonNullList<ItemStack> drops);
    protected abstract boolean checkBlockState(IBlockState state);
}
