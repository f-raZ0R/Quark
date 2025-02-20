package vazkii.quark.content.building.module;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import vazkii.quark.base.Quark;
import vazkii.quark.base.module.LoadModule;
import vazkii.quark.base.module.ModuleCategory;
import vazkii.quark.base.module.QuarkModule;
import vazkii.quark.base.util.VanillaWoods;
import vazkii.quark.base.util.VanillaWoods.Wood;
import vazkii.quark.content.building.block.HedgeBlock;
import vazkii.quark.content.world.block.BlossomSaplingBlock.BlossomTree;
import vazkii.quark.content.world.module.BlossomTreesModule;

@LoadModule(category = ModuleCategory.BUILDING)
public class HedgesModule extends QuarkModule {

	public static TagKey<Block> hedgesTag;
	
	@Override
	public void register() {
		for(Wood wood : VanillaWoods.OVERWORLD)
			new HedgeBlock(this, wood.fence(), wood.leaf());
		
		new HedgeBlock(this, Blocks.OAK_FENCE, Blocks.AZALEA_LEAVES);
		new HedgeBlock(this, Blocks.OAK_FENCE, Blocks.FLOWERING_AZALEA_LEAVES);
	}

	@Override
	public void postRegister() {
		for (BlossomTree tree : BlossomTreesModule.trees.keySet())
			new HedgeBlock(this, BlossomTreesModule.woodSet.fence, tree.leaf.getBlock());
	}
	
	@Override
	public void setup() {
		hedgesTag = BlockTags.create(new ResourceLocation(Quark.MOD_ID, "hedges"));
	}
	
}
