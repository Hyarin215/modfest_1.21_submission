package com.doublepi.temporang.blocks.cannon;

import com.doublepi.temporang.blocks.ModBlocks;
import com.doublepi.temporang.utils.ModModelLayers;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import it.unimi.dsi.fastutil.floats.Float2FloatFunction;
import it.unimi.dsi.fastutil.ints.Int2IntFunction;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.BrightnessCombiner;
import net.minecraft.client.resources.model.Material;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.LidBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class CannonRenderer<T extends BlockEntity> implements BlockEntityRenderer<T> {
    private final ModelPart base;
    private final ModelPart head;


    public CannonRenderer(BlockEntityRendererProvider.Context context){
        ModelPart cannonPart = context.bakeLayer(ModModelLayers.CANNON);
        this.base = cannonPart.getChild("Base");
        this.head = cannonPart.getChild("Head");
    }



    @Override
    public void render(T blockEntity, float partialTick, PoseStack poseStack, MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
        Level level = blockEntity.getLevel();

        boolean flag = level != null;
        BlockState blockstate = flag ?
                blockEntity.getBlockState() :
                (BlockState) ModBlocks.CANNON.get().defaultBlockState()
                        .setValue(CannonBlock.FACING,Direction.NORTH);
                        //.setValue(CannonBlock.AIM,0);

        Block var12 = blockstate.getBlock();

        if (var12 instanceof AbstractChestBlock<?> abstractchestblock) {
            //boolean flag1 = chesttype != ChestType.SINGLE; always true
            poseStack.pushPose();
            float f = ((Direction)blockstate.getValue(ChestBlock.FACING)).toYRot();
            poseStack.translate(0.5F, 0.5F, 0.5F);
            poseStack.mulPose(Axis.YP.rotationDegrees(-f));
            poseStack.translate(-0.5F, -0.5F, -0.5F);
            DoubleBlockCombiner.NeighborCombineResult neighborcombineresult;

            neighborcombineresult = abstractchestblock.combine(blockstate, level, blockEntity.getBlockPos(), true);


            float f1 = ((Float2FloatFunction)neighborcombineresult.apply(ChestBlock.opennessCombiner((LidBlockEntity)blockEntity))).get(partialTick);
            f1 = 1.0F - f1;
            f1 = 1.0F - f1 * f1 * f1;
            int i = ((Int2IntFunction)neighborcombineresult.apply(new BrightnessCombiner())).applyAsInt(packedLight);
//            Material material = this.getMaterial(blockEntity, chesttype);
//            VertexConsumer vertexconsumer = material.buffer(bufferSource, RenderType::entityCutout);
//            if (flag1) {
//                if (chesttype == ChestType.LEFT) {
//                    this.render(poseStack, vertexconsumer, this.doubleLeftLid, this.doubleLeftLock, this.doubleLeftBottom, f1, i, packedOverlay);
//                } else {
//                    this.render(poseStack, vertexconsumer, this.doubleRightLid, this.doubleRightLock, this.doubleRightBottom, f1, i, packedOverlay);
//                }
//            } else {
//                this.render(poseStack, vertexconsumer, this.lid, this.lock, this.bottom, f1, i, packedOverlay);
//            }

            poseStack.popPose();
        }
    }
}
