package de.johnadept;

import de.johnadept.renderer.JukeboxBlockEntityRenderer;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.render.block.entity.BlockEntityRendererFactories;

public class MusicDiscRendererClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockEntityRendererFactories.register(BlockEntityType.JUKEBOX, JukeboxBlockEntityRenderer::new);
    }
}
