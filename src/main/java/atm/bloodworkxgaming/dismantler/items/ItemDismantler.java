package atm.bloodworkxgaming.dismantler.items;

import atm.bloodworkxgaming.dismantler.integration.DismantleHelper;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemDismantler extends Item {

    public ItemDismantler() {
        super();

        this.setUnlocalizedName("item_dismantler");
        this.setRegistryName("item_dismantler");

        this.setCreativeTab(CreativeTabs.TOOLS);

    }

    @Override
    public int getItemStackLimit(ItemStack stack) {
        return 1;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World worldIn, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        IBlockState state = worldIn.getBlockState(pos);
        if (state.getBlock() == Blocks.AIR)
            return EnumActionResult.PASS;

        if (DismantleHelper.dismantle(player, worldIn, pos, state))
            return EnumActionResult.SUCCESS;

        return EnumActionResult.PASS;
    }
}
