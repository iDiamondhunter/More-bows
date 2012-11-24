package iDiamondhunter.common;

import net.minecraft.src.Block;
import net.minecraft.src.Entity;
import net.minecraft.src.EntityArrow;
import net.minecraft.src.EntityLiving;
import net.minecraft.src.World;

public class EntityArrows extends EntityArrow
{
    public EntityArrows(World var1, EntityLiving var2, EntityLiving var3, float var4, float var5)
    {
        super(var1, var2, var3, var4, var5);
    }

    public EntityArrows(World var1, EntityLiving var2, float var3)
    {
        super(var1, var2, var3);
    }

    public EntityArrows(World var1, double var2, double var4, double var6)
    {
        super(var1, var2, var4, var6);
    }

    public EntityArrows(World var1)
    {
        super(var1);
    }


    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        super.onUpdate();
    }

    public void hitGround(int var1, int var2, int var3, int var4, int var5)
    {
        if (var4 == 0)
        {
            --var2;
        }

        if (var4 == 1)
        {
            ++var2;
        }

        if (var4 == 2)
        {
            --var3;
        }

        if (var4 == 3)
        {
            ++var3;
        }

        if (var4 == 4)
        {
            --var1;
        }

        if (var4 == 5)
        {
            ++var1;
        }

        if (this.worldObj.canPlaceEntityOnSide(Block.fire.blockID, var1, var2, var3, true, 1, (Entity)null))
        {

            this.setDead();
        }
    }

    public boolean validTarget(EntityLiving var1)
    {
        return !var1.isBurning() && !var1.handleWaterMovement();
    }

    public boolean hitTarget(Entity cow)
    {
        double var2 = this.rand.nextGaussian() * 0.02D;
        double var4 = this.rand.nextGaussian() * 0.02D;
        double var6 = this.rand.nextGaussian() * 0.02D;
        cow.setFire(15);
    	cow.worldObj.spawnParticle("portal", cow.posX + (double)(this.rand.nextFloat() * cow.width * 2.0F) - (double)cow.width, cow.posY + 0.5D + (double)(this.rand.nextFloat() * cow.height), cow.posZ + (double)(this.rand.nextFloat() * cow.width * 2.0F) - (double)cow.width, var2, var4, var6);
        return true;
    }
}