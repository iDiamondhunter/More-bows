package iDiamondhunter.common;

import java.util.Random;

import net.minecraft.client.Minecraft;
import net.minecraft.src.CreativeTabs;
import net.minecraft.src.Enchantment;
import net.minecraft.src.EnchantmentHelper;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityArrow;
import net.minecraft.src.EntityPlayer;
import net.minecraft.src.EnumAction;
import net.minecraft.src.EnumRarity;
import net.minecraft.src.Item;
import net.minecraft.src.ItemStack;
import net.minecraft.src.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowNockEvent;

public class ItemBowEnder extends Item
{
	private Random rand = new Random();
    private int second;
    private int panoramaTimer = 0;
	private boolean pauseGame;
	private Minecraft minecraft;

	public ItemBowEnder(int par1, int par2)
    {
        super(par1);
        maxStackSize = 1;
        setMaxDamage(384);
        bFull3D = true;
        this.setCreativeTab(CreativeTabs.tabCombat);
    }
    public String getTextureFile()
    {
    	return "/MoreBows/Bows.png";
    }
    /**
     * called when the player releases the use item button. Args: itemstack, world, entityplayer, itemInUseCount
     */
    public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
    {
        boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;

        if (flag || par3EntityPlayer.inventory.hasItem(Item.arrow.shiftedIndex) || second == 3)
        {
            int i = getMaxItemUseDuration(par1ItemStack) - par4;
            float f = (float)i / 22;
            f = (f * f + f * 2.0F) / 3F;

            if ((double)f < 0.1D)
            {
                return;
            }

            if (f > 1.0F)
            {
                f = 1.0F;
            }

            EntityArrow entityarrow = new EntityArrow(par2World, par3EntityPlayer, f * 2.0F);
            EntityArrow entityarrow2 = new EntityArrow(par2World, par3EntityPlayer, 1F);
            EntityArrow entityarrow3= new EntityArrow(par2World, par3EntityPlayer, 1.2F);
            EntityArrow entityarrow4 = new EntityArrow(par2World, par3EntityPlayer, 1.5F);
            EntityArrow entityarrow5 = new EntityArrow(par2World, par3EntityPlayer, 1.75F);
            EntityArrow entityarrow6 = new EntityArrow(par2World, par3EntityPlayer, 1.825F);

            if (f == 1.0F)
            {
                entityarrow.setIsCritical(true);
                entityarrow2.setIsCritical(true);
                entityarrow3.setIsCritical(true);
                entityarrow4.setIsCritical(true);
                entityarrow5.setIsCritical(true);
                entityarrow6.setIsCritical(true);

            }

            int j = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);

            if (j > 0)
            {
                entityarrow.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
                entityarrow2.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
                entityarrow3.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
                entityarrow4.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
                entityarrow5.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
                entityarrow6.setDamage(entityarrow.getDamage() + (double)j * 0.5D + 0.5D);
            }

            int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);

            if (k > 0)
            {
                entityarrow.setKnockbackStrength(k);
                entityarrow2.setKnockbackStrength(k);
                entityarrow3.setKnockbackStrength(k);
                entityarrow4.setKnockbackStrength(k);
                entityarrow5.setKnockbackStrength(k);
                entityarrow6.setKnockbackStrength(k);
            }

            if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
            {
                entityarrow.setFire(100);
                entityarrow2.setFire(100);
                entityarrow3.setFire(100);
                entityarrow4.setFire(100);
                entityarrow5.setFire(100);
                entityarrow6.setFire(100);
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
            	entityarrow2.canBePickedUp = 0;
            	entityarrow3.canBePickedUp = 0;
            	entityarrow4.canBePickedUp = 0;
            	entityarrow5.canBePickedUp = 0;
            	entityarrow6.canBePickedUp = 0;
            }

            if (!par2World.isRemote)
            {
            	entityarrow.canBePickedUp = 2;
            	entityarrow2.canBePickedUp = 0;
            	entityarrow3.canBePickedUp = 0;
            	entityarrow4.canBePickedUp = 0;
            	entityarrow5.canBePickedUp = 0;
            	entityarrow6.canBePickedUp = 0;
            	
            	par2World.spawnEntityInWorld(entityarrow);
                entityarrow.setDamage(entityarrow.getDamage() * 1.25D);
                iconIndex = iDiamondhunter.EnderBow_1;
                while(second > -1)
                {
                	if(second == 3)
                	{
                		second = 2;
                		System.out.println(second);
                	}
                	try
                	{
                		Thread.sleep(1000L);
                	}
                	catch(Exception e){};
                	second--;
                	System.out.println(second);

                }
                if(second == -1)
                {
                	par2World.spawnEntityInWorld(entityarrow2);
                	par2World.spawnEntityInWorld(entityarrow3);
                	entityarrow3.posY++;
                	entityarrow3.posX -= 1.25;
                	entityarrow3.posZ += 1.75;
                	par2World.spawnEntityInWorld(entityarrow4);
                	entityarrow4.posY += 1.45;
                	entityarrow4.posX -= 2.25;
                	entityarrow4.posZ -= 0.75;
                	par2World.spawnEntityInWorld(entityarrow5);
                	entityarrow5.posY += 2;
                	entityarrow5.posX += 0.25;
                	entityarrow5.posZ += 2.5;
                	par2World.spawnEntityInWorld(entityarrow6);
                	entityarrow6.posY += 1.75;
                	entityarrow6.posX += 1.75;
                	entityarrow6.posZ += 1.5;
                	
                	second = 3;
                }
            }
        }
        iconIndex = iDiamondhunter.EnderBow_1;
        
        ItemStack cInv = par3EntityPlayer.getCurrentEquippedItem();
        
        if(cInv.itemID != iDiamondhunter.EnderBow.shiftedIndex)
        {
        	this.iconIndex = iDiamondhunter.EnderBow_1;
        	return;
        }
    
    }
    public boolean onImpact(Entity cow, EntityArrow arrow)
    {
        double var2 = this.rand.nextGaussian() * 0.02D;
        double var4 = this.rand.nextGaussian() * 0.02D;
        double var6 = this.rand.nextGaussian() * 0.02D;
    	cow.worldObj.spawnParticle("portal", cow.posX + (double)(this.rand.nextFloat() * cow.width * 2.0F) - (double)cow.width, cow.posY + 0.5D + (double)(this.rand.nextFloat() * cow.height), cow.posZ + (double)(this.rand.nextFloat() * cow.width * 2.0F) - (double)cow.width, var2, var4, var6);
    	return true;
    }
    public int getIconIndex(ItemStack stack, int renderPass, EntityPlayer player, ItemStack usingItem, int useRemaining)
    {
        

        if (usingItem != null && usingItem.getItem().shiftedIndex == iDiamondhunter.EnderBow.shiftedIndex)
        {
            int k = usingItem.getMaxItemUseDuration() - useRemaining;
            if (k >= 18) return iDiamondhunter.EnderBow_4;
            if (k >  13) return iDiamondhunter.EnderBow_3;
            if (k >   0) return iDiamondhunter.EnderBow_2;
        }
        else return iDiamondhunter.EnderBow_1;
        
        
        return getIconIndex(stack);
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
        return 72000;
    }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public EnumAction getItemUseAction(ItemStack par1ItemStack)
    {
        return EnumAction.bow;
    }

    /**
     * Called whenever this item is equipped and the right mouse button is pressed. Args: itemStack, world, entityPlayer
     */
    public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
    {
        ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
        MinecraftForge.EVENT_BUS.post(event);
        if (event.isCanceled())
        {
            return event.result;
        }
        
        if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Item.arrow.shiftedIndex))
        {
            par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
        }

        return par1ItemStack;
    }
    public EnumRarity getRarity(ItemStack itemstack)
    {
    	return EnumRarity.epic;
    }

    /**
     * Return the enchantability factor of the item, most of the time is based on material.
     */
    public int getItemEnchantability()
    {
        return 1;
    }
    public void updateScreen()
    {
        ++this.panoramaTimer;
    }
}
