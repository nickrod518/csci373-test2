package edu.unca.nrodrigu.Demo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.util.noise.SimplexOctaveGenerator;

public class DemoGenerator extends ChunkGenerator {
	
	public List<BlockPopulator> getDefaultPopulators(World world) {
		ArrayList<BlockPopulator> populators = new ArrayList<BlockPopulator>();
		
		populators.add(new DemoGrassPopulator());
		populators.add(new DemoWaterPopulator());
		populators.add(new DemoLavaPopulator());
		populators.add(new DemoTreePopulator());
		
		return populators;
	}
	
	public Location getFixedSpawnLocation(World world, Random random) {
		return new Location(world, 0, 39, 0);
	}
	
	private int coordsToByte(int x, int y, int z) {
		return (x * 16 + z) * 128 + y;
	}
	
	public byte[] generate(World world, Random random, int chunkX, int chunkZ) {
		byte[] blocks = new byte[32768];
		int x, y, z;
		
		Random rand = new Random(world.getSeed());
		
		SimplexOctaveGenerator octave = new SimplexOctaveGenerator(rand, 8);
		
		octave.setScale(1 / 64.0);
		
		for (x = 0; x < 16; ++x) {
			for (z = 0; z < 16; ++z) {
				blocks[coordsToByte(x,  0,  z)] = (byte) Material.BEDROCK.getId();
				blocks[coordsToByte(x, 50, z)] = (byte) Material.LAVA.getId();
				blocks[coordsToByte(x, 49, z)] = (byte) Material.GLASS.getId();
				
				double noise = octave.noise(x + chunkX * 16,  z + chunkZ * 16,  0.5, 0.5) * 8;
				
				for (y = 1; y < 32 + noise; ++y) {
					blocks[coordsToByte(x, y, z)] = (byte) Material.DIRT.getId();					
				}
				
				blocks[coordsToByte(x, y, z)] = (byte) Material.GRASS.getId();
			}
		}
		
		return blocks;
	}
}