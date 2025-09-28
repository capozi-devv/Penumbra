package net.eya.penumbra.common.block;

import net.eya.penumbra.common.item.ServitudeTokenItem;
import net.eya.penumbra.common.lodestone.particle.AllParticles;
import net.eya.penumbra.foundation.ItemInit;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.ShapeContext;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.VertexFormats;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.joml.Matrix4f;
import team.lodestar.lodestone.systems.rendering.LodestoneRenderType;
import team.lodestar.lodestone.systems.rendering.VFXBuilders;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class EclipseObeliskBlock extends Block {
    public static final VoxelShape SHAPE = createCuboidShape((double)1.5F, (double)0.0F, (double)1.5F, (double)14.5F, (double)32.0F, (double)14.5F);
    int times = ThreadLocalRandom.current().nextInt(40, 61);
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
            if(player.getStackInHand(hand).getItem() instanceof ServitudeTokenItem) {
//                Color color = new Color(248, 209, 109);
//                MinecraftClient client = MinecraftClient.getInstance();
//                Camera camera = client.gameRenderer.getCamera();
//                Vec3d camPos = camera.getPos();
//                MatrixStack matrices = new MatrixStack();
//                matrices.translate(-camPos.x, -camPos.y, -camPos.z);
//                Matrix4f matrix4f = matrices.peek().getPositionMatrix();
//                if(world.isClient()) {
//                    VFXBuilders.createWorld().setRenderTypeRaw(RenderLayer.getTranslucent()).setFormatRaw(VertexFormats.POSITION_COLOR).setColor(color).renderBeam(matrix4f, Vec3d.of(pos), Vec3d.of(pos).add(0d, 30d, 0f), 3f);
//                }
                return ActionResult.SUCCESS;
            }
        }
        return ActionResult.FAIL;
    }
}
