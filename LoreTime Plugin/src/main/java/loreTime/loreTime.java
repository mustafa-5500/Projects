package loreTime;

import org.bukkit.plugin.java.JavaPlugin;

import java.text.ParseException;
import java.util.logging.Level;
import java.util.Date;
import java.text.SimpleDateFormat;

public class loreTime extends JavaPlugin {

    private final String Start_Date = "2023-01-01";
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().log(Level.INFO, "LoreTime Plugin has been enabled");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().log(Level.INFO, "LoreTime Plugin has been disabled");
    }
    private Date getStartDate(){
        try {
            return dateFormat.parse(Start_Date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
    /*
    Returns the number of hours since the start day January 1, 2023
     */
    private long currentHour(){
        Date currentDate = new Date();
        long elapsedRealWorldtime = (currentDate.getTime() - getStartDate().getTime()) / 1000;
        //1,000 milliseconds per second
        return elapsedRealWorldtime / 3600;
        //there are 3,600 seconds in an hour, therefore we are returning the number of hours since start date

    }
    /*
    Takes the input of the current lore month returns the lore season
    1-3 Winter, 4-6 Spring, 7-9 Summer, 10-12 Autumn. This is inclusive.
    param currentMonth, an int form 1 - 12
     */
    public String loreSeason(int currentMonth){
        if(1<= currentMonth && currentMonth <= 3){
            return "Winter";
        } else if (4<= currentMonth && currentMonth <= 6){
            return "Spring";
        } else if (7<= currentMonth && currentMonth <= 9){
            return "Summer";
        } else {
            return "Autumn";
        }
    }
    /*
    Returns the lore date and season in the form of a string.
    In the format [Season] yyyy-MM-dd
    Assuming all months are 30 days long
     */
    public String loretime(){
        long currentHour = currentHour();
        int currentMonth = (int) (currentHour / 24) / 7;
        int currentYear = currentMonth / 12;
        currentMonth = (currentMonth % 12) + 1;
        currentHour = currentHour % (7*24);
        int currentDay = (int) (currentHour/5.6) + 1;

        return loreSeason(currentMonth)
                + " "
                + String.format("%04d", currentYear)
                + "-"
                + String.format("%02d", currentMonth)
                + "-"
                + String.format("%02d", currentDay);

    }
}
