package atm.bloodworkxgaming.dismantler.integration.envoTech;

import atm.bloodworkxgaming.dismantler.integration.DismantleType;
import com.valkyrieofnight.valkyrielib.api.multiblock.structure.IMultiBlockStructure;
import com.valkyrieofnight.valkyrielib.api.util.BlockOffset;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class EnvoTechStructures extends DismantleType {
    private final IMultiBlockStructure multiBlockStructure;

    public EnvoTechStructures(IMultiBlockStructure multiBlockStructure, Block... triggerBlocks) {
        super("environmentaltech", triggerBlocks);
        this.multiBlockStructure = multiBlockStructure;
    }

    @Override
    public void startMining(EntityPlayer player, World worldIn, BlockPos pos, IBlockState state, NonNullList<ItemStack> drops) {
        for (BlockOffset blockOffset : multiBlockStructure.getRequiredBlockLayout().keySet()) {
            breakBlock(worldIn, blockOffset.getPosition(pos), drops);
        }

        breakBlock(worldIn, pos, drops);
    }

    @Override
    protected boolean checkBlockState(IBlockState state) {
        return true;
    }
}
