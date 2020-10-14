package com.github.theonlytails.ruby.client.render;

import com.github.theonlytails.ruby.RubyMod;
import com.github.theonlytails.ruby.client.model.RubySheepModel;
import com.github.theonlytails.ruby.entities.RubySheepEntity;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class RubySheepRenderer extends MobRenderer<RubySheepEntity, RubySheepModel<RubySheepEntity>> {
    private static final ResourceLocation SHEARED_SHEEP_TEXTURES = new ResourceLocation(
            RubyMod.MOD_ID,
            "textures/entity/ruby_sheep/ruby_sheep.png"
    );

    public RubySheepRenderer(EntityRendererManager renderManagerIn) {
        super(renderManagerIn, new RubySheepModel<>(), 0.7f);
        this.addLayer(new RubySheepWoolLayer(this));
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(@NotNull RubySheepEntity entity) {
        return SHEARED_SHEEP_TEXTURES;
    }

}