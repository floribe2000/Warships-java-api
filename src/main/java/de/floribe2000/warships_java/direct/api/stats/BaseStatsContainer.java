package de.floribe2000.warships_java.direct.api.stats;

/**
 * Base stats container for all requests that provide stats in some way. More detailed responses
 * will inherit this container.
 *
 * @author SirLefti
 */
public class BaseStatsContainer {

	private int wins = 0;

    private int losses = 0;

    private int battles = 0;

    private int survived_wins = 0;

    private int survived_battles = 0;

    private int xp = 0;

    public int getWins() {
        return this.wins;
    }

    public int getLosses() {
        return this.losses;
    }

    public int getBattles() {
        return this.battles;
    }

    public int getSurvived_wins() {
        return this.survived_wins;
    }

    public int getSurvived_battles() {
        return this.survived_battles;
    }

    public int getXp() {
        return this.xp;
    }
}
