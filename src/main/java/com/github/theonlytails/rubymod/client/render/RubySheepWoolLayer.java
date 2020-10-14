package com.github.theonlytails.rubymod.client.render;

import com.github.theonlytails.rubymod.RubyMod;
import com.github.theonlytails.rubymod.client.model.RubySheepModel;
import com.github.theonlytails.rubymod.entities.RubySheepEntity;
import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.IRenderTypeBuffer;
import net.minecraft.client.renderer.entity.IEntityRenderer;
import net.minecraft.client.renderer.entity.layers.LayerRenderer;
import net.minecraft.client.renderer.entity.model.SheepWoolModel;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.DyeColor;
import net.minecraft.util.ResourceLocation;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;

public class RubySheepWoolLayer extends LayerRenderer<RubySheepEntity, RubySheepModel<RubySheepEntity>> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(
            RubyMod.MOD_ID,
            "textures/entity/ruby_sheep/ruby_sheep_fur.png"
    );

    private final SheepWoolModel<RubySheepEntity> sheepModel = new SheepWoolModel<>();

    public RubySheepWoolLayer(IEntityRenderer<RubySheepEntity, RubySheepModel<RubySheepEntity>> rendererIn) {
        super(rendererIn);
    }

    @Override
    public void render(@Nonnull MatrixStack matrixStackIn, @NotNull IRenderTypeBuffer bufferIn, int packedLightIn, @NotNull RubySheepEntity entitylivingbaseIn, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        if (!entitylivingbaseIn.getSheared() && !entitylivingbaseIn.isInvisible()) {
            float f;
            float f1;
            float f2;
            if (entitylivingbaseIn.hasCustomName() && "jeb_".equals(entitylivingbaseIn.getName().getUnformattedComponentText())) {
                int i = entitylivingbaseIn.ticksExisted / 25 + entitylivingbaseIn.getEntityId();
                int j = DyeColor.values().length;
                int k = i % j;
                int l = (i + 1) % j;
                float f3 = ((float) (entitylivingbaseIn.ticksExisted % 25) + partialTicks) / 25.0F;
                float[] dyeRgbOfK = SheepEntity.getDyeRgb(DyeColor.byId(k));
                float[] dyeRgbOfL = SheepEntity.getDyeRgb(DyeColor.byId(l));
                f = dyeRgbOfK[0] * (1.0F - f3) + dyeRgbOfL[0] * f3;
                f1 = dyeRgbOfK[1] * (1.0F - f3) + dyeRgbOfL[1] * f3;
                f2 = dyeRgbOfK[2] * (1.0F - f3) + dyeRgbOfL[2] * f3;
            } else {
                float[] dyeRgb = SheepEntity.getDyeRgb(entitylivingbaseIn.getFleeceColor());
                f = dyeRgb[0];
                f1 = dyeRgb[1];
                f2 = dyeRgb[2];
            }

            renderCopyCutoutModel(this.getEntityModel(), this.sheepModel, TEXTURE, matrixStackIn, bufferIn, packedLightIn, entitylivingbaseIn, limbSwing, limbSwingAmount, ageInTicks, netHeadYaw, headPitch, partialTicks, f, f1, f2);
        }
    }
}