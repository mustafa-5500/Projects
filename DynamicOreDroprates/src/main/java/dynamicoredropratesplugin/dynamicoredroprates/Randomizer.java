package dynamicoredropratesplugin.dynamicoredroprates;


import org.bukkit.Bukkit;

import java.util.Random;

public class Randomizer {
    public static int generate(int input){
        Random random = new Random();
        double u1 = random.nextDouble()*10;
        double u2 = random.nextDouble()*10;
        double z0 = Math.sqrt(2.0 * Math.log(u1)) * Math.cos(2.0 * Math.PI * u2);

        return (int) (input + (2.0 * z0));
    }
}
