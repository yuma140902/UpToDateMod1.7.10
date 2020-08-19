package yuma140902.uptodatemod.entity.monster;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.EnumDifficulty;
import net.minecraft.world.World;

public class EntityHusk extends EntityZombie {
	public EntityHusk(World world) {
		super(world);
	}
	
	@Override
	public boolean attackEntityAsMob(Entity target) {
		boolean flag = super.attackEntityAsMob(target);
		if(flag){
			if(!(target instanceof EntityLivingBase)) return flag;
			
			// 擬似的な地域難易度。範囲は[0.0f, 1.0f]
			float pseudoLocalDifficulty;
			if(worldObj.difficultySetting.getDifficultyId() <= EnumDifficulty.EASY.getDifficultyId()){ /* ピースフルまたはEasy */
				pseudoLocalDifficulty = 0.0f;
			}
			else if(worldObj.difficultySetting == EnumDifficulty.NORMAL){ /* Normal */
				float f = 1.5f + (4.0f - 1.5f) * this.rand.nextFloat();
				if(f < 2.0f) pseudoLocalDifficulty = 0.0f;
				else pseudoLocalDifficulty = (f - 2.0f) / 2.0f;
			}
			else{ /* Hard */
				float f = 2.25f + (6.75f - 2.25f) * this.rand.nextFloat();
				if(f > 4.0f) pseudoLocalDifficulty = 1.0f;
				else pseudoLocalDifficulty = (f - 2.0f) / 2.0f;
			}
			
			EntityLivingBase livingTarget = (EntityLivingBase)target;
			livingTarget.addPotionEffect(new PotionEffect(Potion.hunger.getId(), Math.round(pseudoLocalDifficulty)*7*20, 0));
		}
		return flag;
	}
	
	@Override
	public void setFire(int seconds) {
		if(worldObj.isDaytime()) return;
		super.setFire(seconds);
	}
}
