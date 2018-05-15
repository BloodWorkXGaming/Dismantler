package atm.bloodworkxgaming.dismantler.integration.woot;

import atm.bloodworkxgaming.dismantler.integration.DismantleType;
import gnu.trove.map.TIntObjectMap;
import gnu.trove.map.hash.TIntObjectHashMap;
import ipsis.Woot;
import ipsis.woot.block.BlockMobFactoryHeart;
import ipsis.woot.block.BlockMobFactoryStructure;
import ipsis.woot.init.ModBlocks;
import ipsis.woot.multiblock.EnumMobFactoryModule;
import ipsis.woot.multiblock.EnumMobFactoryTier;
import ipsis.woot.tileentity.ILayoutBlockInfo;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;

public class WootStructure extends DismantleType {
    public WootStructure() {
        super("woot", ModBlocks.blockFactoryHeart);
    }

    @Override
    public void startMining(EntityPlayer player, World worldIn, BlockPos pos, IBlockState state, NonNullList<ItemStack> drops) {
        int tier = checkTier(worldIn, pos);
        EnumFacing facing = state.getValue(BlockMobFactoryHeart.FACING);

        ArrayList<ILayoutBlockInfo> list = new ArrayList<>();
        Woot.factoryPatternRepository.getFactoryLayout(EnumMobFactoryTier.getTier(tier - 1), pos, facing, list);

        for (ILayoutBlockInfo iLayoutBlockInfo : list) {
            breakBlock(worldIn, iLayoutBlockInfo.getPos(), drops);
        }

        BlockPos controllerPos = pos.offset(facing.getOpposite()).up();
        EnumFacing facingRot = facing.rotateY();

        for (int i = -2; i <= 2; i++) {
            for (int j = 0; j < 3; j++) {
                breakBlock(worldIn, controllerPos.offset(facingRot, i).up(j), drops);
            }
        }

        // breakBlock(worldIn, controllerPos, drops);
        breakBlock(worldIn, pos, drops);
    }


    private int checkTier(World worldIn, BlockPos pos) {
        BlockPos bottom = pos.down();

        for (int i = 5; i > 1; i--) {
            BlockPos t4 = bottom.west(i);
            IBlockState t4State = worldIn.getBlockState(t4);

            if (t4State.getBlock() instanceof BlockMobFactoryStructure && t4State.getValue(BlockMobFactoryStructure.MODULE) == factoryModuleMap.get(i)) {
                return Math.min(i, 4);
            }
        }

        return -1;
    }

    @Override
    protected boolean checkBlockState(IBlockState state) {
        return true;
    }

    private static TIntObjectMap<EnumMobFactoryModule> factoryModuleMap = new TIntObjectHashMap<>();
    static {
        factoryModuleMap.put(1, EnumMobFactoryModule.BLOCK_1);
        factoryModuleMap.put(2, EnumMobFactoryModule.BLOCK_2);
        factoryModuleMap.put(3, EnumMobFactoryModule.BLOCK_3);
        factoryModuleMap.put(4, EnumMobFactoryModule.BLOCK_4);
        factoryModuleMap.put(5, EnumMobFactoryModule.BLOCK_5);
    }
}
