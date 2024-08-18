package de.johnadept.renderer;

import de.pnku.mjnv.block.MoreJukeboxVariantBlock;
import de.pnku.mjnv.block.MoreJukeboxVariantBlockEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.entity.BlockEntityRenderer;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.render.model.json.ModelTransformationMode;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.world.LightType;
import net.minecraft.world.World;

@Environment(EnvType.CLIENT)
public class MoreJukeboxVariantBlockEntityRenderer implements BlockEntityRenderer<MoreJukeboxVariantBlockEntity> {
    private static final MinecraftClient client = MinecraftClient.getInstance();

    @SuppressWarnings("unused")
    public MoreJukeboxVariantBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {

    }

    @Override
    public void render(MoreJukeboxVariantBlockEntity blockEntity, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int overlay) {
        if (blockEntity instanceof MoreJukeboxVariantBlockEntity && blockEntity.getWorld().getBlockState(blockEntity.getPos()).getBlock() instanceof MoreJukeboxVariantBlock block) {
            if (blockEntity.getWorld().getBlockState(blockEntity.getPos()).get(MoreJukeboxVariantBlock.HAS_RECORD)) {
                ItemStack stack = blockEntity.getStack();
//                if (blockEntity.getManager().getSong() != null) {
//                    MinecraftClient.getInstance().player.sendMessage(Text.literal(blockEntity.getManager().getSong().description().getContent().toString()));
//                }
                if (!stack.isEmpty()) {
                    ItemRenderer itemRenderer = MinecraftClient.getInstance().getItemRenderer();
                    matrices.push();
                    matrices.translate(0.5f, 1f, 0.51f);
                    matrices.scale(0.5f, 0.6f, 0.6f);
                    matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(0f));
                    matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90f));
                    float angle = (client.world.getTime() + tickDelta) * 5 % 360;
//                    matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(angle)); permanently rotate item
                    itemRenderer.renderItem(stack, ModelTransformationMode.GUI, getLightLevel(blockEntity.getWorld(), blockEntity.getPos()), OverlayTexture.DEFAULT_UV, matrices, vertexConsumers, blockEntity.getWorld(), 1);
                    matrices.pop();
                }
            }
        }
    }

    private int getLightLevel(World world, BlockPos pos) {
        int blockLight = 15;  // Simulating maximum block light
        int skyLight = world.getLightLevel(LightType.SKY, pos);
        return LightmapTextureManager.pack(blockLight, skyLight);
    }
}
