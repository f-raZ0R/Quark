package vazkii.quark.content.automation.module;

import net.minecraft.core.Registry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.material.Material;
import vazkii.arl.util.RegistryHelper;
import vazkii.quark.base.module.LoadModule;
import vazkii.quark.base.module.ModuleCategory;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.content.automation.block.ChuteBlock;
import vazkii.quark.content.automation.block.be.ChuteBlockEntity;

/**
 * @author WireSegal
 * Created at 10:25 AM on 9/29/19.
 */
@LoadModule(category = ModuleCategory.AUTOMATION)
public class ChuteModule extends QuarkModule {

	public static BlockEntityType<ChuteBlockEntity> blockEntityType;

	@Override
	public void register() {
		Block chute = new ChuteBlock("chute", this, CreativeModeTab.TAB_REDSTONE,
				Block.Properties.of(Material.WOOD)
						.strength(2.5F)
						.sound(SoundType.WOOD));

		blockEntityType = BlockEntityType.Builder.of(ChuteBlockEntity::new, chute).build(null);
		RegistryHelper.register(blockEntityType, "chute", Registry.BLOCK_ENTITY_TYPE_REGISTRY);
	}
}
