package edu.unca.nrodrigu.Demo;

import java.util.Random;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.TreeType;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

public class DemoTreePopulator extends BlockPopulator {
	
	public void populate(World world, Random random, Chunk chunk) {
		int x, y, z;
		
		if (random.nextInt(100) < 10) {
			x = 6 + random.nextInt(4);
			z = 6 + random.nextInt(4);
			
			for (y = 40; chunk.getBlock(x, y, z).getType() == Material.AIR; --y);
			
			TreeType type = (random.nextInt(100) < 30) ? TreeType.BIG_TREE : TreeType.TREE;
			
			world.generateTree(chunk.getBlock(x, y, z).getLocation(), type);
		}
		
	}
}