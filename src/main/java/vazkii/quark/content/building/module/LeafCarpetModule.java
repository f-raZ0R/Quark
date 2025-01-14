package vazkii.quark.content.building.module;

import java.util.LinkedList;
import java.util.List;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.ComposterBlock;
import vazkii.quark.base.block.IQuarkBlock;
import vazkii.quark.base.module.LoadModule;
import vazkii.quark.base.module.ModuleCategory;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.base.util.VanillaWoods;
import vazkii.quark.base.util.VanillaWoods.Wood;
import vazkii.quark.content.building.block.LeafCarpetBlock;
import vazkii.quark.content.world.block.BlossomLeavesBlock;
import vazkii.quark.content.world.module.BlossomTreesModule;

@LoadModule(category = ModuleCategory.BUILDING, antiOverlap = { "woodworks" })
public class LeafCarpetModule extends QuarkModule {

	public static List<LeafCarpetBlock> carpets = new LinkedList<>();

	@Override
	public void register() {
		for(Wood wood : VanillaWoods.OVERWORLD)
			carpet(wood.leaf());
		
		carpet(Blocks.AZALEA_LEAVES);
		carpet(Blocks.FLOWERING_AZALEA_LEAVES);
	}

	@Override
	public void postRegister() {
		BlossomTreesModule.trees.keySet().stream().map(t -> (BlossomLeavesBlock) t.leaf.getBlock()).forEach(this::blossomCarpet);
	}

	@Override
	public void loadComplete() {
		enqueue(() -> {
			for(LeafCarpetBlock c : carpets) {
				if(c.asItem() != null)
					ComposterBlock.COMPOSTABLES.put(c.asItem(), 0.2F);
			}
		});
	}

	private void carpet(Block base) {
		carpetBlock(base);
	}

	private void blossomCarpet(BlossomLeavesBlock base) {
		carpetBlock(base).setCondition(base::isEnabled);
	}

	private LeafCarpetBlock carpetBlock(Block base) {
		LeafCarpetBlock carpet = new LeafCarpetBlock(IQuarkBlock.inherit(base, s -> s.replaceAll("_leaves", "_leaf_carpet")), base, this);
		carpets.add(carpet);
		return carpet;
	}

}
