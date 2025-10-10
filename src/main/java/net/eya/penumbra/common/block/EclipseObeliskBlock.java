package net.eya.penumbra.common.block;

import net.eya.penumbra.common.lodestone.particle.AllParticles;
import net.eya.penumbra.common.lodestone.worldvfx.AllVFX;

import net.eya.penumbra.foundation.EffectInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

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
    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if(world.isClient()) {
           // AllVFX.obeliskBeam(pos, 600);
            return ActionResult.SUCCESS;
        }
        return ActionResult.FAIL;
    }
}
