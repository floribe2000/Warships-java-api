package de.floribe2000.warships_java.direct.account;

import de.floribe2000.warships_java.direct.api.IApiResponse;
import de.floribe2000.warships_java.direct.api.IRequestAction;
import de.floribe2000.warships_java.direct.api.Meta;
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status;
import lombok.Getter;

import java.util.Map;

/**
 * <p>A representation of the api results for player achievments.
 * Contains a list of all currently available achievments and the amount of times a player has achieved them.</p>
 *
 * <p><b>Important: Field names can be different that display names ingame!</b> For details see the javadoc for each field.</p>
 *
 * <p>To use the achievment names in your program, use the {@link PlayersAchievmentsRequest.AchievmentElement AchievmentElement enum}.</p>
 */
@Getter
public class PlayersAchievments implements IApiResponse {

    /**
     * The api response status
     */
    private Status status = Status.ERROR;

    /**
     * The api meta information
     */
    private Meta meta = null;

    /**
     * A map holding data separated by player ids as string
     */
    private Map<String, Details> data = null;

    /**
     * Achievment details for a single player
     */
    @Getter
    public static class Details {

        /**
         * A list of all battle achievments
         */
        private Battle battle = null;

        /**
         * A list of all progress achievments
         */
        private Progress progress = null;

        @Getter
        public static class Battle {

            /**
             * Ingame name: Not Overly Assaultive
             */
            private int FOOLSDAY_TROOPER = 0;

            /**
             * Ingame name: 3 Years of World of Warships
             */
            private int COLLECTION_HAPPY_BIRTHDAY2018_COMPLETED = 0;

            /**
             * Ingame name: Ho-Ho-Ho!
             */
            private int BD2_NY2016 = 0;

            /**
             * Ingame name: Solo Warrior
             */
            private int ONE_SOLDIER_IN_THE_FIELD = 0;

            /**
             * Ingame name: "New Year Raid"
             */
            private int CAMPAIGN_NEWYEAR2018ELITE_COMPLETED = 0;

            /**
             * Ingame name: Gale
             */
            private int CLAN_SEASON_1_LEAGUE_3 = 0;

            /**
             * Ingame name: Bane of the Oceans
             */
            private int SEA_LEGEND = 0;

            /**
             * Ingame name: Typhoon
             */
            private int CLAN_SEASON_1_LEAGUE_1 = 0;

            /**
             * Ingame name: Squall
             */
            private int CLAN_SEASON_1_LEAGUE_4 = 0;

            /**
             * Ingame name: "Royal Navy Destroyers"
             */
            private int COLLECTION_BRITISHARC_COMPLETED = 0;

            /**
             * Ingame name: Naval Warfare. Tactics
             */
            private int SCIENCE_OF_WINNING_TACTICIAN = 0;

            /**
             * Ingame name: Double Strike
             */
            private int DOUBLE_KILL = 0;

            /**
             * Ingame name: Dunkirk
             */
            private int COLLECTION_DUNKIRK_COMPLETED = 0;

            /**
             * Ingame name: High Caliber
             */
            private int MAIN_CALIBER = 0;

            /**
             * Ingame name: Like a Pro
             */
            private int BD2_RANKS = 0;

            /**
             * Ingame name: Hoarder 2016
             */
            private int NY17_WIN_ALL = 0;

            /**
             * Ingame name: "Yamamoto Isoroku"
             */
            private int COLLECTION_YAMAMOTO_COMPLETED = 0;

            /**
             * Ingame name: American Cruisers
             */
            private int COLLECTION_AMERICANARC_COMPLETED = 0;

            /**
             * Ingame name: Run! Admiral! Run!
             */
            private int BD2016_RUN_FOREST = 0;

            /**
             * Ingame name: Aiming? Too Much Effort
             */
            private int NY17_AIMING = 0;

            /**
             * Ingame name: "Mighty Prinz"
             */
            private int CAMPAIGN_NEWYEAR2019PEF_COMPLETED = 0;

            /**
             * Ingame name: Sharks
             */
            private int AVASHARKS = 0;

            /**
             * Ingame name: Poekhali!
             */
            private int FOOLSDAY_POEKHALI = 0;

            /**
             * Ingame name: "Belle Époque"
             */
            private int COLLECTION_HAPPYNEWYEAR2019_COMPLETED = 0;

            /**
             * Ingame name: Liquidator
             */
            private int LIQUIDATOR = 0;

            /**
             * Ingame name: Storm
             */
            private int CLAN_SEASON_1_LEAGUE_2 = 0;

            /**
             * Ingame name: Witherer
             */
            private int WITHERING = 0;

            /**
             * Ingame name: Fire Show
             */
            private int BD2016_FIRESHOW = 0;

            /**
             * Ingame name: Fireproof
             */
            private int FIREPROOF = 0;

            /**
             * Ingame name: You've Got a Parcel Waiting for You!
             */
            private int BD2_CONTAINERS = 0;

            /**
             * Ingame name: Guardian of the Galaxy
             */
            private int EV1APR19_ATTDEF1 = 0;

            /**
             * Ingame name: Tin Can
             */
            private int NY17_SAFECRACKER = 0;

            /**
             * Ingame name: Confederate
             */
            private int SUPPORT = 0;

            /**
             * Ingame name: Workhorse
             */
            private int MERCENARY = 0;

            /**
             * Ingame name: In the Thick of It
             */
            private int MESSENGER = 0;

            /**
             * Ingame name: Protector
             */
            private int PVE_HON_PR_SAVE_3 = 0;

            /**
             * Ingame name: Shield
             */
            private int PVE_HON_PR_SAVE_1 = 0;

            /**
             * Ingame name: Naval Warfare. Arson
             */
            private int SCIENCE_OF_WINNING_ARSONIST = 0;

            /**
             * Ingame name: "The Hunt for Bismarck"
             */
            private int CAMPAIGN_BISMARCK_COMPLETED = 0;

            /**
             * Ingame name: Twitch Prime
             */
            private int TWITCH_PRIME = 0;

            /**
             * Ingame name: Feeling Good
             */
            private int NY17_DRESS_THE_TREE = 0;

            /**
             * Ingame name: Senior Supply Officer
             */
            private int WORKAHOLIC_L = 0;

            /**
             * Ingame name: Die-Hard
             */
            private int HEADBUTT = 0;

            /**
             * Ingame name: Will Now Have Stories
             */
            private int BD2_CAMPAIGNS = 0;

            /**
             * Ingame name: Skipper on Deck!
             */
            private int BD2_CREW = 0;

            /**
             * Ingame name: Close Quarters Expert
             */
            private int ATBA_CALIBER = 0;

            /**
             * Ingame name: Supply Officer
             */
            private int MERCENARY_L = 0;

            /**
             * Ingame name: Look Who We Have Here!
             */
            private int BD2_ARP = 0;

            /**
             * Ingame name: Naval Aviation
             */
            private int FILLALBUM_BRIT_CVARC_COMPLETED = 0;

            /**
             * Ingame name: Business Magnate
             */
            private int NEVER_ENOUGH_MONEY = 0;

            /**
             * Ingame name: Festive Soup
             */
            private int BD2016_FESTIV_SOUP = 0;

            /**
             * Ingame name: Arsonist
             */
            private int ARSONIST = 0;

            /**
             * Ingame name: Kraken Unleashed!
             */
            private int WARRIOR = 0;

            /**
             * Ingame name: Important Missions
             */
            private int PVE_HON_PR_DONE_ALL_1 = 0;

            /**
             * Ingame name: Detonation
             */
            private int DETONATED = 0;

            /**
             * Ingame name: Chief Naval Architect
             */
            private int CHIEF_ENGINEER = 0;

            /**
             * Ingame name: Natural Selection
             */
            private int PVE_HON_FRAG_CLASS = 0;

            /**
             * Ingame name: Smooth Supply
             */
            private int NO_DAY_WITHOUT_ADVENTURE_L = 0;

            /**
             * Ingame name: Legion of Honor
             */
            private int FILLALBUM_FRENCHDD_COMPLETED = 0;

            /**
             * Ingame name: Naval Warfare. Lucky Shot
             */
            private int SCIENCE_OF_WINNING_LUCKY = 0;

            /**
             * Ingame name: "Honorable Service"
             */
            private int CAMPAIGN_SB_COMPLETED = 0;

            /**
             * Ingame name: Stream Drop!
             */
            private int STREAM = 0;

            /**
             * Ingame name: "High School Fleet"
             */
            private int COLLECTION_HSF2018_COMPLETED = 0;

            /**
             * Ingame name: True Survivalist
             */
            private int EV_POST_APOCALYPSE2019_WINNER = 0;

            /**
             * Ingame name: The Great Eight
             */
            private int COLLECTION_OVECHKIN_COMPLETED = 0;

            /**
             * Ingame name: "Battle of the North Cape"
             */
            private int COLLECTION_HAPPYNEWYEAR2018_COMPLETED = 0;

            /**
             * Ingame name: Shark Among Shrimps
             */
            private int PVE_HON_FRAG_OBJ = 0;

            /**
             * Ingame name: Unsinkable
             */
            private int UNSINKABLE = 0;

            /**
             * Ingame name: Naval Constructor
             */
            private int ENGINEER = 0;

            /**
             * Ingame name: "Hit Hard! Hit Fast! Hit Often!"
             */
            private int CAMPAIGN_HALSEY_COMPLETED = 0;

            /**
             * Ingame name: "Battle of the North Cape"
             */
            private int CAMPAIGN_NEWYEAR2018BASIC_COMPLETED = 0;

            /**
             * Ingame name: Go Navy!
             */
            private int AVACOMMON = 0;

            /**
             * Ingame name: Junior Naval Designer
             */
            private int JUNIOR_PLANNER = 0;

            /**
             * Ingame name: Paint It Gold!
             */
            private int BD2_CAMO = 0;

            /**
             * Ingame name: Will to Win
             */
            private int PVE_HERO_WIN_ONE = 0;

            /**
             * Ingame name: Devastating Strike
             */
            private int INSTANT_KILL = 0;

            /**
             * Ingame name: Veteran
             */
            private int VETERAN = 0;

            /**
             * Ingame name: Crash Tester
             */
            private int PVE_HON_FRAG_WAY = 0;

            /**
             * Ingame name: "Vive la France"
             */
            private int COLLECTION_VIVELAFRANCE_COMPLETED = 0;

            /**
             * Ingame name: Tactical Expertise
             */
            private int PVE_HERO_WIN_SUR = 0;

            /**
             * Ingame name: Legend of the Seas
             */
            private int NO_PRICE_FOR_HEROISM = 0;

            /**
             * Ingame name: Eat It, Toaster!
             */
            private int BD2_PVE = 0;

            /**
             * Ingame name: Dreadnought
             */
            private int DREADNOUGHT = 0;

            /**
             * Ingame name: Exorcist
             */
            private int HALLOWEEN_2017 = 0;

            /**
             * Ingame name: Ghostbuster
             */
            private int HALLOWEEN_2016 = 0;

            /**
             * Ingame name: Terror of the Deep
             */
            private int HALLOWEEN_2018 = 0;

            /**
             * Ingame name: Queue Jumper
             */
            private int BD2016_PARTY_CHECK_IN = 0;

            /**
             * Ingame name: Guardian
             */
            private int PVE_HON_PR_SAVE_2 = 0;

            /**
             * Ingame name: Assistant
             */
            private int PVE_HERO_DAM_ENEM = 0;

            /**
             * Ingame name: Warrior
             */
            private int FIGHTER = 0;

            /**
             * Ingame name: An Epic Journey
             */
            private int NY17_500_LEAGUES = 0;

            /**
             * Ingame name: Moneybags
             */
            private int MILLIONAIR = 0;

            /**
             * Ingame name: "Yamamoto Isoroku"
             */
            private int CAMPAIGN_YAMAMOTO_COMPLETED = 0;

            /**
             * Ingame name: Torpedoproof
             */
            private int EV1APR19_TORPEDO1 = 0;

            /**
             * Ingame name: Universal Seaman
             */
            private int PVE_HON_DONE_CLASS = 0;

            /**
             * Ingame name: Weather Beaten
             */
            private int PVE_HON_PR_DONE_1 = 0;

            /**
             * Ingame name: Master Blaster
             */
            private int EV1APR19_TORPEDO5 = 0;

            /**
             * Ingame name: Before Zee Germans Get There!
             */
            private int BD2_BISMARCK = 0;

            /**
             * Ingame name: Junior Supply Officer
             */
            private int MESSENGER_L = 0;

            /**
             * Ingame name: Battle Hero
             */
            private int BATTLE_HERO = 0;

            /**
             * Ingame name: A Shot in the Dark
             */
            private int BD2016_WRONG_SOW = 0;

            /**
             * Ingame name: Sea Star
             */
            private int PVE_HON_WIN_ALL_DONE = 0;

            /**
             * Ingame name: "Science of Victory"
             */
            private int CAMPAIGN1_COMPLETED = 0;

            /**
             * Ingame name: Not a Pyramid Scheme!
             */
            private int BD2_CREDITS = 0;

            /**
             * Ingame name: Naval Warfare. Ramming
             */
            private int SCIENCE_OF_WINNING_HARD_EDGED = 0;

            /**
             * Ingame name: Azur Lane
             */
            private int FILLALBUM_AZURLANE_COMPLETED = 0;

            /**
             * Ingame name: It's Just a Flesh Wound!
             */
            private int RETRIBUTION = 0;

            /**
             * Ingame name: Foes Way Past Their Prime
             */
            private int BD2_HW2016 = 0;

            /**
             * Ingame name: "The Hunt for Graf Spee"
             */
            private int CAMPAIGN_NY17B_COMPLETED = 0;

            /**
             * Ingame name: Twitch Welcome
             */
            private int TWITCH_WG = 0;

            /**
             * Ingame name: Life and Soul of the Party
             */
            private int BD2016_PARTY_ANIMAL = 0;

            /**
             * Ingame name: Naval Warfare. Flooding
             */
            private int SCIENCE_OF_WINNING_TO_THE_BOTTOM = 0;

            /**
             * Ingame name: Four-Goal Haul
             */
            private int GREATEEIGHT = 0;

            /**
             * Ingame name: The Hunt for Bismarck
             */
            private int COLLECTION_BISMARCK_COMPLETED = 0;

            /**
             * Ingame name: "In the service of the Motherland"
             */
            private int FILLALBUM_SOVIETBB_COMPLETED = 0;

            /**
             * Ingame name: Break the Bank
             */
            private int NY17_BREAK_THE_BANK = 0;

            /**
             * Ingame name: Warrior of the Light
             */
            private int ALL_THREE_HALLOWEEN_COMPLETE = 0;

            /**
             * Ingame name: Initial Capital
             */
            private int CAPITAL = 0;

            /**
             * Ingame name: Naval Warfare. Weaponry Basics
             */
            private int SCIENCE_OF_WINNING_BOMBARDIER = 0;

            /**
             * Ingame name: "Resolute and Rapid"
             */
            private int FILLALBUM_ITCA_COMPLETED = 0;

            /**
             * Ingame name: Operation Dynamo
             */
            private int PVE_DUNKERQUE_OPERATION_DYNAMO = 0;

            /**
             * Ingame name: First Blood
             */
            private int FIRST_BLOOD = 0;

            /**
             * Ingame name: "2 Years of World of Warships"
             */
            private int COLLECTION_WOWSBIRTHDAY_COMPLETED = 0;

            /**
             * Ingame name: AA Defense Expert
             */
            private int AIRDEFENSEEXPERT = 0;

            /**
             * Ingame name: Good Start
             */
            private int NY17_WIN_AT_LEAST_ONE = 0;

            /**
             * Ingame name: "The Hunt for Graf Spee" with Honors
             */
            private int CAMPAIGN_NY17B_COMPLETED_EXCELLENT = 0;

            /**
             * Ingame name: Amateur
             */
            private int AMAUTEUR = 0;

            @Override
            public String toString() {
                return IRequestAction.GSON.toJson(this);
            }
        }

        @Getter
        public static class Progress {

            private int FIGHTER = 0;

            private int MILLIONAIR = 0;

            private int MERCENARY = 0;

            private int MESSENGER = 0;

            private int PVE_HON_PR_SAVE_3 = 0;

            private int PVE_HON_PR_SAVE_1 = 0;

            private int ENGINEER = 0;

            private int BATTLE_HERO = 0;

            private int SEA_LEGEND = 0;

            private int WORKAHOLIC_L = 0;

            private int PVE_HON_PR_SAVE_2 = 0;

            private int JUNIOR_PLANNER = 0;

            private int MESSENGER_L = 0;

            private int MERCENARY_L = 0;

            private int VETERAN = 0;

            private int NEVER_ENOUGH_MONEY = 0;

            private int NO_PRICE_FOR_HEROISM = 0;

            private int ALL_THREE_HALLOWEEN_COMPLETE = 0;

            private int CAPITAL = 0;

            private int SCIENCE_OF_WINNING_BOMBARDIER = 0;

            private int PVE_HON_PR_DONE_ALL_1 = 0;

            private int CHIEF_ENGINEER = 0;

            private int NO_DAY_WITHOUT_ADVENTURE_L = 0;

            private int PVE_HON_PR_DONE_1 = 0;

            private int AMAUTEUR = 0;

            @Override
            public String toString() {
                return IRequestAction.GSON.toJson(this);
            }
        }

        @Override
        public String toString() {
            return IRequestAction.GSON.toJson(this);
        }
    }

    @Override
    public String toString() {
        return IRequestAction.GSON.toJson(this);
    }
}
