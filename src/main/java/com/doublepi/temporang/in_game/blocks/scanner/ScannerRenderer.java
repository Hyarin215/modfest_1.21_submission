//package com.doublepi.temporang.level.blocks.scanner;
//
//import com.doublepi.temporang.level.items.ModItems;
//import com.doublepi.temporang.utils.ModRenderers;
//import com.mojang.blaze3d.vertex.PoseStack;
//import com.mojang.math.Axis;
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.model.geom.ModelLayers;
//import net.minecraft.client.model.geom.ModelPart;
//import net.minecraft.client.model.geom.PartPose;
//import net.minecraft.client.model.geom.builders.CubeListBuilder;
//import net.minecraft.client.model.geom.builders.LayerDefinition;
//import net.minecraft.client.model.geom.builders.MeshDefinition;
//import net.minecraft.client.model.geom.builders.PartDefinition;
//import net.minecraft.client.renderer.LightTexture;
//import net.minecraft.client.renderer.MultiBufferSource;
//import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
//import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
//import net.minecraft.client.renderer.entity.ItemRenderer;
//import net.minecraft.client.renderer.texture.OverlayTexture;
//import net.minecraft.core.BlockPos;
//import net.minecraft.world.item.ItemDisplayContext;
//import net.minecraft.world.item.ItemStack;
//import net.minecraft.world.level.Level;
//import net.minecraft.world.level.LightLayer;
//
//public class ScannerRenderer implements BlockEntityRenderer<ScannerBlockEntity> {
//
//    //private final ModelPart satellite;
//
//    public ScannerRenderer(BlockEntityRendererProvider.Context context) {
//        context.bakeLayer(ModRenderersSCANNER_MODEL);
//
////        ModelPart modelpart = context.bakeLayer(ModRenderers.SCANNER_MODEL);
////        this.satellite = modelpart.getChild("satellite_group");
//    }
//
//
//    @Override
//    public void render(ScannerBlockEntity blockEntity, float partialTick, PoseStack poseStack,
//                       MultiBufferSource multiBufferSource, int packedLight, int packedOverlay) {
//        ItemRenderer itemRenderer = Minecraft.getInstance().getItemRenderer();
//        ItemStack stack = new ItemStack(ModItems.STONE_BOOMERANG.get());
//        poseStack.pushPose();
//        poseStack.translate(0.5f, 1.15f, 0.5f);
//        poseStack.scale(0.5f, 0.5f, 0.5f);
//        poseStack.mulPose(Axis.YP.rotationDegrees(blockEntity.getRenderingRotation()));
//        itemRenderer.renderStatic(stack, ItemDisplayContext.FIXED, getLightLevel(blockEntity.getLevel(),
//                blockEntity.getBlockPos()), OverlayTexture.NO_OVERLAY, poseStack, multiBufferSource, blockEntity.getLevel(), 1);
//        poseStack.popPose();
//    }
//    private int getLightLevel(Level level, BlockPos pos) {
//        int bLight = level.getBrightness(LightLayer.BLOCK, pos);
//        int sLight = level.getBrightness(LightLayer.SKY, pos);
//        return LightTexture.pack(bLight, sLight);
//    }
//}
