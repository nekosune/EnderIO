package crazypants.enderio.machine.solar;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;
import crazypants.enderio.EnderIO;
import crazypants.enderio.EnderIOTab;
import crazypants.enderio.config.Config;
import crazypants.enderio.gui.IAdvancedTooltipProvider;
import crazypants.enderio.gui.IResourceTooltipProvider;
import crazypants.enderio.gui.TooltipAddera;
import crazypants.enderio.machine.power.PowerDisplayUtil;
import crazypants.util.Lang;

public class BlockItemSolarPanel extends ItemBlockWithMetadata implements IAdvancedTooltipProvider, IResourceTooltipProvider {

  public BlockItemSolarPanel() {
    super(EnderIO.blockSolarPanel, EnderIO.blockSolarPanel);
    setHasSubtypes(true);
    setCreativeTab(EnderIOTab.tabEnderIO);
  }
  
  public BlockItemSolarPanel(Block block) {
    super(block, block);
    setHasSubtypes(true);
    setCreativeTab(EnderIOTab.tabEnderIO);
  }
  
  @Override
  public String getUnlocalizedName(ItemStack par1ItemStack) {
    int meta = par1ItemStack.getItemDamage();
    String result = super.getUnlocalizedName(par1ItemStack);   
    if(meta == 1) {
      result += ".advanced";
    }
    return result;
  }
  
  @Override
  @SuppressWarnings({ "rawtypes", "unchecked" })
  public void getSubItems(Item par1, CreativeTabs par2CreativeTabs, List par3List) {
    ItemStack stack = new ItemStack(this, 1,0);
    par3List.add(stack);
    stack = new ItemStack(this, 1,1);
    par3List.add(stack);
  }
  
  @SuppressWarnings("rawtypes")
  @Override
  public void addCommonEntries(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {       
    TooltipAddera.addCommonTooltipFromResources(list, itemstack);
  }

  @SuppressWarnings("rawtypes")
  @Override
  public void addBasicEntries(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {       
  }

  @SuppressWarnings({ "unchecked", "rawtypes" })
  @Override
  public void addDetailedEntries(ItemStack itemstack, EntityPlayer entityplayer, List list, boolean flag) {
    TooltipAddera.addDetailedTooltipFromResources(list, itemstack); 
    int prod = Config.maxPhotovoltaicOutputRF;
    if(itemstack.getItemDamage() == 1) {
      prod = Config.maxPhotovoltaicAdvancedOutputRF;
    }
    list.add(Lang.localize("maxSolorProduction") + " " + PowerDisplayUtil.formatPowerPerTick(prod));
  }

  @Override
  public String getUnlocalizedNameForTooltip(ItemStack itemStack) {
	  return super.getUnlocalizedName(itemStack);
  }

}
