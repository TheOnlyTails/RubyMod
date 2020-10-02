package com.theonlytails.ruby.client.render;

import com.theonlytails.ruby.client.model.RubySheepModel;
import com.theonlytails.ruby.entities.RubySheepEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class RubySheepRenderer extends MobRenderer<RubySheepEntity, RubySheepModel<RubySheepEntity>> {
    private static final ResourceLocation SHEARED_SHEEP_TEXTURES = new ResourceLocation("ruby", "textures/entity/ruby_sheep/ruby_sheep.png");

    public RubySheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RubySheepModel<>(), 0.7f);
        addLayer(new RubySheepWoolLayer(this));
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(@NotNull RubySheepEntity entity) {
        return SHEARED_SHEEP_TEXTURES;
    }

}