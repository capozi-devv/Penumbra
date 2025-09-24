package net.eya.penumbra.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class EclipseObeliskBlock extends Block {
    public static final VoxelShape SHAPE = createCuboidShape((double)2.0F, (double)0.0F, (double)2.0F, (double)14.0F, (double)32.0F, (double)14.0F);
    public EclipseObeliskBlock(Settings settings) {
        super(settings);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape voxelShape = SHAPE;
        return voxelShape;
    }
}
