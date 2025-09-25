package net.eya.penumbra.common.block;

import net.eya.penumbra.common.lodestone.particle.AllParticles;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class EclipseObeliskBlock extends Block {
    public static final VoxelShape SHAPE = createCuboidShape((double)1.5F, (double)0.0F, (double)1.5F, (double)14.5F, (double)32.0F, (double)14.5F);
    public EclipseObeliskBlock(Settings settings) {
        super(settings);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        VoxelShape voxelShape = SHAPE;
        return voxelShape;
    }
    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        super.randomDisplayTick(state, world, pos, random);
        if(world.isClient()) {
            AllParticles.obeliskParticles(world, Vec3d.ofCenter(pos));
        }
    }
}
