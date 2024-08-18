package de.johnadept;

import de.johnadept.renderer.JukeboxBlockEntityRenderer;
import de.johnadept.renderer.MoreJukeboxVariantBlockEntityRenderer;
import de.pnku.mjnv.init.MjnvBlockInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class MusicDiscRendererClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(BlockEntityType.JUKEBOX, JukeboxBlockEntityRenderer::new);

        if (FabricLoader.getInstance().isModLoaded("quad-lolmjnv")) {
            BlockEntityRendererFactories.register(MjnvBlockInit.MORE_JUKEBOX_VARIANT_BLOCK_ENTITY, MoreJukeboxVariantBlockEntityRenderer::new);
        }
    }
}
