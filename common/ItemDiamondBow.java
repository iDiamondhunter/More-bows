package iDiamondhunter.common;

import java.util.Random;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityArrow;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemBow;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;

public class ItemDiamondBow extends ItemBow
{
	private Random rand = new Random();
	
    public ItemDiamondBow(int par1, int par2)
    {
        super(par1);
        maxStackSize = 1;
        setMaxDamage(1016);
        bFull3D = true;
        setCreativeTab(CreativeTabs.tabCombat);
    }
    public String getTextureFile()
    {
    	return "/MoreBows/Bows.png";
    }
    public int getIconIndex(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
            if(player.isUsingItem() && usingItem.itemID == iDiamondhunter.DiamondBow.shiftedIndex)
            {
                if (usingItem != null && usingItem.getItem().shiftedIndex == iDiamondhunter.DiamondBow.shiftedIndex)
                {
                    int k = usingItem.getMaxItemUseDuration() - useRemaining;
                    if (k >= 8) return 3;
                    if (k > 4) return 2;
                    if (k > 0) return 1;
                }
            }
            else 
            {
            iconIndex = iDiamondhunter.DiamondBow_1;
            }
            return getIconIndex(stack);
    }

    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (flag || par3EntityPlayer.inventory.hasItem(Item.arrow.shiftedIndex))
        {
            int i = getMaxItemUseDuration(par1ItemStack) - par4;
            float f = (float)i / 6;
            f = (f * f + f * 2.0F) / 3F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.2F);

            if (f == 1.0F)
            {
                entityarrow.setIsCritical(true);
            }

            int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

            if (j > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (k > 0)
            {
                entityarrow.setKnockbackStrength(k);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
            {
                entityarrow.setFire(140);
            }

            par1ItemStack.damageItem(1, par3EntityPlayer);
            par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);

            if (!flag)
            {
                par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.shiftedIndex);
            }
            else
            {
                entityarrow.canBePickedUp = 2;
            }

            if (!par2World.isRemote)
            {
            	par2World.spawnEntityInWorld(entityarrow);
                entityarrow.setDamage(entityarrow.getDamage() * 2.25D);
                iconIndex = iDiamondhunter.DiamondBow_1;
            }
        }
        
        ItemStack cInv = par3EntityPlayer.inventory.getCurrentItem();
        
        if(cInv.itemID == iDiamondhunter.DiamondBow.shiftedIndex)
        {
        	return;
        }
        else
        {
        	iconIndex = iDiamondhunter.DiamondBow.shiftedIndex;
        	return;
        }
    }

    public ItemStack onFoodEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {

        return par1ItemStack;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getMaxItemUseDuration(ItemStack par1ItemStack)
    {
        return 36000;
    }


    public EnumRarity getRarity(ItemStack par1ItemStack)
    {
        return EnumRarity.rare;
    }

}