package edu.unca.nrodrigu.Demo;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public class DemoLavaPopulator extends BlockPopulator {
	
	public void populate(World world, Random random, Chunk chunk) {
		int x, y, z;
		Block block;
		
		for (x = 0; x < 16; ++x) {
			for (z = 0; z < 16; ++z) {
				if (random.nextInt(100) < 50) {
					for (y = 40; chunk.getBlock(x, y, z).getType() == Material.AIR; --y);
					if (y > 25) {
						block = chunk.getBlock(x,  y + 1,  z);
						
						block.setType(Material.LAVA);
						block.setData((byte) 0x1);
					}
				}
			}
		}
	}
}