package com.doublepi.temporang.in_game.blocks.black_boxer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.core.Direction;

public class BlackBoxerRenderer implements BlockEntityRenderer<BlackBoxerBlockEntity> {
    public final int DISTANCE = 3;
    public final int OFFSET = 2;

    public BlackBoxerRenderer(BlockEntityRendererProvider.Context context){

    }

    @Override
    public boolean shouldRenderOffScreen(BlackBoxerBlockEntity blockEntity) {
        return true;
    }

    @Override
    public void render(BlackBoxerBlockEntity pBlockEntity, float pPartialTick, PoseStack pPoseStack,
                       MultiBufferSource pBufferSource, int pPackedLight, int pPackedOverlay) {
        Direction direction = pBlockEntity.getBlockState().getValue(BlackBoxerBlock.FACING);
        int x = direction.getNormal().getX();
        int z = direction.getNormal().getZ();
        int isPos = x+z>0? 1 : 0;
        int isNeg = 1-isPos;
        int minX = DISTANCE*x+OFFSET*z+isPos;
        int minY = 0;
        int minZ = OFFSET*x+DISTANCE*z+isPos;
        int maxX =  (OFFSET*2+2+DISTANCE)*x - OFFSET*z + isNeg;
        int maxY = OFFSET*2+1;
        int maxZ = -OFFSET*x + (OFFSET*2+2+DISTANCE)*z + isNeg;
        VertexConsumer vertexconsumer = pBufferSource.getBuffer(RenderType.lines());
        LevelRenderer.renderLineBox(pPoseStack, vertexconsumer, minX , minY, minZ, maxX, maxY, maxZ,
                1F, 0F, 0F, 0.8F, 1F, 0F, 0F);
    }

}
