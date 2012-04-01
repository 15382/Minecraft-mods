package net.minecraft.src.nbxlite.chunkproviders;

import java.util.List;
import net.minecraft.src.*;

public class ChunkProviderGenerate2
    implements IChunkProvider
{
    public ChunkProviderGenerateInfdev infdevGen;
    public ChunkProviderGenerateOldInfdev oldInfdevGen;
    public ChunkProviderFinite indevGen;
    public ChunkProviderGenerateAlpha alphaGen;
    public ChunkProviderGenerateBeta betaGen;
    public ChunkProviderSky betaGenSky;
    public ChunkProviderGenerateRelease releaseGen;

    public ChunkProviderGenerate2(World world, long l, boolean flag)
    {
        infdevGen = new ChunkProviderGenerateInfdev(world, l, flag);
        oldInfdevGen = new ChunkProviderGenerateOldInfdev(world, l, flag);
        indevGen = new ChunkProviderFinite(world, l, flag);
        alphaGen = new ChunkProviderGenerateAlpha(world, l, flag);
        betaGen = new ChunkProviderGenerateBeta(world, l, flag);
        betaGenSky = new ChunkProviderSky(world, l, flag);
        releaseGen = new ChunkProviderGenerateRelease(world, l, flag);
    }

    public Chunk loadChunk(int i, int j)
    {
        return provideChunk(i, j);
    }

    public Chunk provideChunk(int i, int j)
    {
        if(mod_noBiomesX.Generator==0)
        {
            if (mod_noBiomesX.MapFeatures==0){
                return alphaGen.provideChunk(i, j);
            }else if (mod_noBiomesX.MapFeatures==1){
                return infdevGen.provideChunk(i, j);
            }else if (mod_noBiomesX.MapFeatures==2){
                return oldInfdevGen.provideChunk(i, j);
            }
            return indevGen.provideChunk(i, j);
        } else if(mod_noBiomesX.Generator==1)
        {
            if (mod_noBiomesX.MapFeatures>=5){
                return betaGenSky.provideChunk(i, j);
            }
            return betaGen.provideChunk(i, j);
        }
        return releaseGen.provideChunk(i, j);
    }

    public boolean chunkExists(int i, int j)
    {
        return true;
    }

    public void populate(IChunkProvider ichunkprovider, int i, int j)
    {
        if(mod_noBiomesX.Generator==0)
        {
            if (mod_noBiomesX.MapFeatures==0){
                alphaGen.populate(ichunkprovider, i, j);
            }else if (mod_noBiomesX.MapFeatures==1){
                infdevGen.populate(ichunkprovider, i, j);
            }else if (mod_noBiomesX.MapFeatures==2){
                oldInfdevGen.populate(ichunkprovider, i, j);
            }else{
                indevGen.populate(ichunkprovider, i, j);
            }
        } else if(mod_noBiomesX.Generator==1)
        {
            if (mod_noBiomesX.MapFeatures>=5){
                betaGenSky.populate(ichunkprovider, i, j);
            }else{
                betaGen.populate(ichunkprovider, i, j);
            }
        } else
        {
            releaseGen.populate(ichunkprovider, i, j);
        }
    }

    public boolean saveChunks(boolean flag, IProgressUpdate iprogressupdate)
    {
        return true;
    }

    public boolean unload100OldestChunks()
    {
        return false;
    }

    public boolean canSave()
    {
        return true;
    }

    public List getPossibleCreatures(EnumCreatureType enumcreaturetype, int i, int j, int k)
    {
        return releaseGen.func_40377_a_release(enumcreaturetype,i,j,k);
    }

    public ChunkPosition findClosestStructure(World world, String s, int i, int j, int k)
    {
        if(mod_noBiomesX.Generator==0)
        {
            if (mod_noBiomesX.MapFeatures==0){
                return alphaGen.findClosestStructure(world, s, i, j, k);
            }else if (mod_noBiomesX.MapFeatures==1){
                return infdevGen.findClosestStructure(world, s, i, j, k);
            }else if (mod_noBiomesX.MapFeatures==2){
                return oldInfdevGen.findClosestStructure(world, s, i, j, k);
            }
            return indevGen.findClosestStructure(world, s, i, j, k);
        } else if(mod_noBiomesX.Generator==1)
        {
            if (mod_noBiomesX.MapFeatures>=5){
                return betaGenSky.findClosestStructure(world, s, i, j, k);
            }
            return betaGen.findClosestStructure(world, s, i, j, k);
        }
        return releaseGen.findClosestStructure(world, s, i, j, k);
    }
}
