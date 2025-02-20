package vazkii.quark.content.client.module;

import net.minecraft.client.renderer.entity.ArmorStandRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.EntityRenderersEvent.AddLayers;
import vazkii.quark.base.module.LoadModule;
import vazkii.quark.base.module.ModuleCategory;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.base.module.config.Config;
import vazkii.quark.content.tweaks.client.layer.ArmorStandFakePlayerLayer;

@LoadModule(category = ModuleCategory.CLIENT)
public class UsesForCursesModule extends QuarkModule {

	private static final ResourceLocation PUMPKIN_OVERLAY = new ResourceLocation("textures/misc/pumpkinblur.png");

	public static boolean staticEnabled;

	@Config
	public static boolean vanishPumpkinOverlay = true;

	@Config
	public static boolean bindArmorStandsWithPlayerHeads = true;

	@Override
	public void configChanged() {
		staticEnabled = enabled;
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void modelLayers(AddLayers event) {
		ArmorStandRenderer render = event.getRenderer(EntityType.ARMOR_STAND);
		render.addLayer(new ArmorStandFakePlayerLayer<>(render, event.getEntityModels()));
	}

	@OnlyIn(Dist.CLIENT)
	public static boolean shouldHideArmorStandModel(ItemStack stack) {
		if(!staticEnabled || !bindArmorStandsWithPlayerHeads || !stack.is(Items.PLAYER_HEAD))
			return false;
		return EnchantmentHelper.hasBindingCurse(stack);
	}

	@OnlyIn(Dist.CLIENT)
	public static boolean shouldHidePumpkinOverlay(ResourceLocation location, Player player) {
		if(!staticEnabled || !vanishPumpkinOverlay || !location.equals(PUMPKIN_OVERLAY))
			return false;
		ItemStack stack = player.getInventory().getArmor(3);
		return stack.is(Blocks.CARVED_PUMPKIN.asItem()) &&
				EnchantmentHelper.hasVanishingCurse(stack);
	}

}
