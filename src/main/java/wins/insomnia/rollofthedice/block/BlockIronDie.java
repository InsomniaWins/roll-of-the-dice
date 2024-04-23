package wins.insomnia.rollofthedice.block;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyBool;
import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockIronDie extends Block {

    public static final PropertyInteger TOP_NUMBER = PropertyInteger.create("top_number", 0, 5);
    public static final PropertyBool READY_TO_ROLL = PropertyBool.create("ready_to_roll");

    public BlockIronDie(Material material) {
        super(material);
        setSoundType(SoundType.METAL);
        setHardness(2);
        setHarvestLevel("pickaxe", 1);
        setDefaultState(getBlockState().getBaseState().withProperty(TOP_NUMBER, 0).withProperty(READY_TO_ROLL, true));
    }

    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block neighborBlock, BlockPos neighborPos) {
        if (!world.isRemote) {
            boolean isPowered = world.isBlockPowered(pos);

            if (isPowered && state.getValue(READY_TO_ROLL)) {
                world.setBlockState(pos, state.withProperty(READY_TO_ROLL, false), 2);
                world.updateComparatorOutputLevel(pos, this);

                updateTick(world, pos, state, RANDOM);

            } else if (!isPowered && !state.getValue(READY_TO_ROLL)) {

                world.setBlockState(pos, state.withProperty(READY_TO_ROLL, true).withProperty(TOP_NUMBER, RANDOM.nextInt(6)), 2);

                world.updateComparatorOutputLevel(pos, this);
            }
        }
    }

    public void onBlockPlacedBy(World world, BlockPos pos, IBlockState state, EntityLivingBase placer, ItemStack stack) {

        world.setBlockState(pos, state.withProperty(READY_TO_ROLL, world.getRedstonePowerFromNeighbors(pos) == 0), 2);
        world.updateComparatorOutputLevel(pos, this);

    }

    @Override
    public int getMetaFromState(IBlockState state) {
        return state.getValue(TOP_NUMBER);
    }

    @Override
    public IBlockState getStateFromMeta(int meta) {
        return getDefaultState().withProperty(TOP_NUMBER, meta);
    }

    @Override
    protected BlockStateContainer createBlockState() {

        return new BlockStateContainer(this, TOP_NUMBER, READY_TO_ROLL);

    }

    @Override
    public IBlockState getStateForPlacement(World world, BlockPos pos, EnumFacing facing, float hitX, float hitY, float hitZ, int meta, EntityLivingBase placer) {

        return getDefaultState().withProperty(TOP_NUMBER, 0).withProperty(READY_TO_ROLL, true);

    }

    public boolean hasComparatorInputOverride(IBlockState state) {
        return true;
    }

    public int getComparatorInputOverride(IBlockState blockState, World worldIn, BlockPos pos) {
        return blockState.getValue(TOP_NUMBER) + 1;
    }
}
