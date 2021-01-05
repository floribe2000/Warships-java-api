package de.floribe2000.warships_java.direct.account

import de.floribe2000.warships_java.direct.api.ErrorContainer
import de.floribe2000.warships_java.direct.api.IApiResponse
import de.floribe2000.warships_java.direct.api.IRequestAction.Companion.GSON
import de.floribe2000.warships_java.direct.api.Meta
import de.floribe2000.warships_java.direct.api.typeDefinitions.Status
import kotlinx.serialization.Serializable

/**
 *
 * A representation of the api results for player achievments.
 * Contains a list of all currently available achievments and the amount of times a player has achieved them.
 *
 *
 * **Important: Field names can be different that display names ingame!** For details see the javadoc for each field.
 */
@Serializable
data class PlayersAchievements(
    /**
     * The api response status
     */
    override val status: Status = Status.ERROR,

    /**
     * Details about errors in case of a failed request.
     *
     * Field is null if no errors occurred during the request!
     */
    override val error: ErrorContainer? = null,

    /**
     * The api meta information
     */
    override val meta: Meta = Meta(),

    /**
     * A map holding data separated by player ids as string
     */
    val data: Map<String, Details> = mapOf(),

    ) : IApiResponse {
    /**
     * Achievment details for a single player
     */
    @Serializable
    data class Details(
        /**
         * A list of all battle achievments
         */
        val battle: Battle,

        /**
         * A list of all progress achievments
         */
        val progress: Progress,
    ) {
        @Serializable
        data class Battle(
            /**
             * Ingame name: Not Overly Assaultive
             */
            val FOOLSDAY_TROOPER: Int = 0,

            /**
             * Ingame name: 3 Years of World of Warships
             */
            val COLLECTION_HAPPY_BIRTHDAY2018_COMPLETED: Int = 0,

            /**
             * Ingame name: Ho-Ho-Ho!
             */
            val BD2_NY2016: Int = 0,

            /**
             * Ingame name: Solo Warrior
             */
            val ONE_SOLDIER_IN_THE_FIELD: Int = 0,

            /**
             * Ingame name: "New Year Raid"
             */
            val CAMPAIGN_NEWYEAR2018ELITE_COMPLETED: Int = 0,

            /**
             * Ingame name: Gale
             */
            val CLAN_SEASON_1_LEAGUE_3: Int = 0,

            /**
             * Ingame name: Bane of the Oceans
             */
            val SEA_LEGEND: Int = 0,

            /**
             * Ingame name: Typhoon
             */
            val CLAN_SEASON_1_LEAGUE_1: Int = 0,

            /**
             * Ingame name: Squall
             */
            val CLAN_SEASON_1_LEAGUE_4: Int = 0,

            /**
             * Ingame name: "Royal Navy Destroyers"
             */
            val COLLECTION_BRITISHARC_COMPLETED: Int = 0,

            /**
             * INGAME NAME: NAval WARFARE. TACTICS
             */
            val SCIENCE_OF_WINNING_TACTICIAN: Int = 0,

            /**
             * INGAME NAME: DOUBLE STRIKE
             */
            val DOUBLE_KILL: Int = 0,

            /**
             * INGAME NAME: DUNKIRK
             */
            val COLLECTION_DUNKIRK_COMPLETED: Int = 0,

            /**
             * INGAME NAME: HIGH CALIBER
             */
            val MAIN_CALIBER: Int = 0,

            /**
             * INGAME NAME: LIKE A PRO
             */
            val BD2_RANKS: Int = 0,

            /**
             * INGAME NAME: HOARDER 2016
             */
            val NY17_WIN_ALL: Int = 0,

            /**
             * INGAME NAME: "YAMAMOTO ISOROKU"
             */
            val COLLECTION_YAMAMOTO_COMPLETED: Int = 0,

            /**
             * INGAME NAME: AMERICAN CRUISERS
             */
            val COLLECTION_AMERICANARC_COMPLETED: Int = 0,

            /**
             * INGAME NAME: RUN! ADMIRAL! RUN!
             */
            val BD2016_RUN_FOREST: Int = 0,

            /**
             * INGAME NAME: AIMING? TOO MUCH EFFORT
             */
            val NY17_AIMING: Int = 0,

            /**
             * INGAME NAME: "MIGHTY PRINZ"
             */
            val CAMPAIGN_NEWYEAR2019PEF_COMPLETED: Int = 0,

            /**
             * INGAME NAME: SHARKS
             */
            val AVASHARKS: Int = 0,

            /**
             * INGAME NAME: POEKHALI!
             */
            val FOOLSDAY_POEKHALI: Int = 0,

            /**
             * INGAME NAME: "BELLE ÉPOQUE"
             */
            val COLLECTION_HAPPYNEWYEAR2019_COMPLETED: Int = 0,

            /**
             * INGAME NAME: LIQUIDATOR
             */
            val LIQUIDATOR: Int = 0,

            /**
             * INGAME NAME: STORM
             */
            val CLAN_SEASON_1_LEAGUE_2: Int = 0,

            /**
             * INGAME NAME: WITHERER
             */
            val WITHERING: Int = 0,

            /**
             * INGAME NAME: FIRE SHOW
             */
            val BD2016_FIRESHOW: Int = 0,

            /**
             * INGAME NAME: FIREPROOF
             */
            val FIREPROOF: Int = 0,

            /**
             * INGAME NAME: YOU'VE GOT A PARCEL WAITING FOR YOU!
             */
            val BD2_CONTAINERS: Int = 0,

            /**
             * INGAME NAME: GUARDIAN OF THE GALAXY
             */
            val EV1APR19_ATTDEF1: Int = 0,

            /**
             * INGAME NAME: TIN CAN
             */
            val NY17_SAFECRACKER: Int = 0,

            /**
             * INGAME NAME: CONFEDERATE
             */
            val SUPPORT: Int = 0,

            /**
             * INGAME NAME: WORKHORSE
             */
            val MERCENARY: Int = 0,

            /**
             * INGAME NAME: IN THE THICK OF IT
             */
            val MESSENGER: Int = 0,

            /**
             * INGAME NAME: PROTECTOR
             */
            val PVE_HON_PR_SAVE_3: Int = 0,

            /**
             * INGAME NAME: SHIELD
             */
            val PVE_HON_PR_SAVE_1: Int = 0,

            /**
             * INGAME NAME: NAval WARFARE. ARSON
             */
            val SCIENCE_OF_WINNING_ARSONIST: Int = 0,

            /**
             * INGAME NAME: "THE HUNT FOR BISMARCK"
             */
            val CAMPAIGN_BISMARCK_COMPLETED: Int = 0,

            /**
             * INGAME NAME: TWITCH PRIME
             */
            val TWITCH_PRIME: Int = 0,

            /**
             * INGAME NAME: FEELING GOOD
             */
            val NY17_DRESS_THE_TREE: Int = 0,

            /**
             * INGAME NAME: SENIOR SUPPLY OFFICER
             */
            val WORKAHOLIC_L: Int = 0,

            /**
             * INGAME NAME: DIE-HARD
             */
            val HEADBUTT: Int = 0,

            /**
             * INGAME NAME: WILL NOW HAVE STORIES
             */
            val BD2_CAMPAIGNS: Int = 0,

            /**
             * INGAME NAME: SKIPPER ON DECK!
             */
            val BD2_CREW: Int = 0,

            /**
             * INGAME NAME: CLOSE QUARTERS EXPERT
             */
            val ATBA_CALIBER: Int = 0,

            /**
             * INGAME NAME: SUPPLY OFFICER
             */
            val MERCENARY_L: Int = 0,

            /**
             * INGAME NAME: LOOK WHO WE HAVE HERE!
             */
            val BD2_ARP: Int = 0,

            /**
             * INGAME NAME: NAval AVIATION
             */
            val FILLALBUM_BRIT_CVARC_COMPLETED: Int = 0,

            /**
             * INGAME NAME: BUSINESS MAGNATE
             */
            val NEVER_ENOUGH_MONEY: Int = 0,

            /**
             * INGAME NAME: FESTIVE SOUP
             */
            val BD2016_FESTIV_SOUP: Int = 0,

            /**
             * INGAME NAME: ARSONIST
             */
            val ARSONIST: Int = 0,

            /**
             * INGAME NAME: KRAKEN UNLEASHED!
             */
            val WARRIOR: Int = 0,

            /**
             * INGAME NAME: IMPORTANT MISSIONS
             */
            val PVE_HON_PR_DONE_ALL_1: Int = 0,

            /**
             * INGAME NAME: DETONATION
             */
            val DETONATED: Int = 0,

            /**
             * INGAME NAME: CHIEF NAval ARCHITECT
             */
            val CHIEF_ENGINEER: Int = 0,

            /**
             * INGAME NAME: NATURAL SELECTION
             */
            val PVE_HON_FRAG_CLASS: Int = 0,

            /**
             * INGAME NAME: SMOOTH SUPPLY
             */
            val NO_DAY_WITHOUT_ADVENTURE_L: Int = 0,

            /**
             * INGAME NAME: LEGION OF HONOR
             */
            val FILLALBUM_FRENCHDD_COMPLETED: Int = 0,

            /**
             * INGAME NAME: NAval WARFARE. LUCKY SHOT
             */
            val SCIENCE_OF_WINNING_LUCKY: Int = 0,

            /**
             * INGAME NAME: "HONORABLE SERVICE"
             */
            val CAMPAIGN_SB_COMPLETED: Int = 0,

            /**
             * INGAME NAME: STREAM DROP!
             */
            val STREAM: Int = 0,

            /**
             * INGAME NAME: "HIGH SCHOOL FLEET"
             */
            val COLLECTION_HSF2018_COMPLETED: Int = 0,

            /**
             * INGAME NAME: TRUE SURVIvalIST
             */
            val EV_POST_APOCALYPSE2019_WINNER: Int = 0,

            /**
             * INGAME NAME: THE GREAT EIGHT
             */
            val COLLECTION_OVECHKIN_COMPLETED: Int = 0,

            /**
             * INGAME NAME: "BATTLE OF THE NORTH CAPE"
             */
            val COLLECTION_HAPPYNEWYEAR2018_COMPLETED: Int = 0,

            /**
             * INGAME NAME: SHARK AMONG SHRIMPS
             */
            val PVE_HON_FRAG_OBJ: Int = 0,

            /**
             * INGAME NAME: UNSINKABLE
             */
            val UNSINKABLE: Int = 0,

            /**
             * INGAME NAME: NAval CONSTRUCTOR
             */
            val ENGINEER: Int = 0,

            /**
             * INGAME NAME: "HIT HARD! HIT FAST! HIT OFTEN!"
             */
            val CAMPAIGN_HALSEY_COMPLETED: Int = 0,

            /**
             * INGAME NAME: "BATTLE OF THE NORTH CAPE"
             */
            val CAMPAIGN_NEWYEAR2018BASIC_COMPLETED: Int = 0,

            /**
             * INGAME NAME: GO NAVY!
             */
            val AVACOMMON: Int = 0,

            /**
             * INGAME NAME: JUNIOR NAval DESIGNER
             */
            val JUNIOR_PLANNER: Int = 0,

            /**
             * INGAME NAME: PAINT IT GOLD!
             */
            val BD2_CAMO: Int = 0,

            /**
             * INGAME NAME: WILL TO WIN
             */
            val PVE_HERO_WIN_ONE: Int = 0,

            /**
             * INGAME NAME: DEVASTATING STRIKE
             */
            val INSTANT_KILL: Int = 0,

            /**
             * INGAME NAME: VETERAN
             */
            val VETERAN: Int = 0,

            /**
             * INGAME NAME: CRASH TESTER
             */
            val PVE_HON_FRAG_WAY: Int = 0,

            /**
             * INGAME NAME: "VIVE LA FRANCE"
             */
            val COLLECTION_VIVELAFRANCE_COMPLETED: Int = 0,

            /**
             * INGAME NAME: TACTICAL EXPERTISE
             */
            val PVE_HERO_WIN_SUR: Int = 0,

            /**
             * INGAME NAME: LEGEND OF THE SEAS
             */
            val NO_PRICE_FOR_HEROISM: Int = 0,

            /**
             * INGAME NAME: EAT IT, TOASTER!
             */
            val BD2_PVE: Int = 0,

            /**
             * INGAME NAME: DREADNOUGHT
             */
            val DREADNOUGHT: Int = 0,

            /**
             * INGAME NAME: EXORCIST
             */
            val HALLOWEEN_2017: Int = 0,

            /**
             * INGAME NAME: GHOSTBUSTER
             */
            val HALLOWEEN_2016: Int = 0,

            /**
             * INGAME NAME: TERROR OF THE DEEP
             */
            val HALLOWEEN_2018: Int = 0,

            /**
             * INGAME NAME: QUEUE JUMPER
             */
            val BD2016_PARTY_CHECK_IN: Int = 0,

            /**
             * INGAME NAME: GUARDIAN
             */
            val PVE_HON_PR_SAVE_2: Int = 0,

            /**
             * INGAME NAME: ASSISTANT
             */
            val PVE_HERO_DAM_ENEM: Int = 0,

            /**
             * INGAME NAME: WARRIOR
             */
            val FIGHTER: Int = 0,

            /**
             * INGAME NAME: AN EPIC JOURNEY
             */
            val NY17_500_LEAGUES: Int = 0,

            /**
             * INGAME NAME: MONEYBAGS
             */
            val MILLIONAIR: Int = 0,

            /**
             * INGAME NAME: "YAMAMOTO ISOROKU"
             */
            val CAMPAIGN_YAMAMOTO_COMPLETED: Int = 0,

            /**
             * INGAME NAME: TORPEDOPROOF
             */
            val EV1APR19_TORPEDO1: Int = 0,

            /**
             * INGAME NAME: UNIVERSAL SEAMAN
             */
            val PVE_HON_DONE_CLASS: Int = 0,

            /**
             * INGAME NAME: WEATHER BEATEN
             */
            val PVE_HON_PR_DONE_1: Int = 0,

            /**
             * INGAME NAME: MASTER BLASTER
             */
            val EV1APR19_TORPEDO5: Int = 0,

            /**
             * INGAME NAME: BEFORE ZEE GERMANS GET THERE!
             */
            val BD2_BISMARCK: Int = 0,

            /**
             * INGAME NAME: JUNIOR SUPPLY OFFICER
             */
            val MESSENGER_L: Int = 0,

            /**
             * INGAME NAME: BATTLE HERO
             */
            val BATTLE_HERO: Int = 0,

            /**
             * INGAME NAME: A SHOT IN THE DARK
             */
            val BD2016_WRONG_SOW: Int = 0,

            /**
             * INGAME NAME: SEA STAR
             */
            val PVE_HON_WIN_ALL_DONE: Int = 0,

            /**
             * INGAME NAME: "SCIENCE OF VICTORY"
             */
            val CAMPAIGN1_COMPLETED: Int = 0,

            /**
             * INGAME NAME: NOT A PYRAMID SCHEME!
             */
            val BD2_CREDITS: Int = 0,

            /**
             * INGAME NAME: NAval WARFARE. RAMMING
             */
            val SCIENCE_OF_WINNING_HARD_EDGED: Int = 0,

            /**
             * INGAME NAME: AZUR LANE
             */
            val FILLALBUM_AZURLANE_COMPLETED: Int = 0,

            /**
             * INGAME NAME: IT'S JUST A FLESH WOUND!
             */
            val RETRIBUTION: Int = 0,

            /**
             * INGAME NAME: FOES WAY PAST THEIR PRIME
             */
            val BD2_HW2016: Int = 0,

            /**
             * INGAME NAME: "THE HUNT FOR GRAF SPEE"
             */
            val CAMPAIGN_NY17B_COMPLETED: Int = 0,

            /**
             * INGAME NAME: TWITCH WELCOME
             */
            val TWITCH_WG: Int = 0,

            /**
             * INGAME NAME: LIFE AND SOUL OF THE PARTY
             */
            val BD2016_PARTY_ANIMAL: Int = 0,

            /**
             * INGAME NAME: NAval WARFARE. FLOODING
             */
            val SCIENCE_OF_WINNING_TO_THE_BOTTOM: Int = 0,

            /**
             * INGAME NAME: FOUR-GOAL HAUL
             */
            val GREATEEIGHT: Int = 0,

            /**
             * INGAME NAME: THE HUNT FOR BISMARCK
             */
            val COLLECTION_BISMARCK_COMPLETED: Int = 0,

            /**
             * INGAME NAME: "IN THE SERVICE OF THE MOTHERLAND"
             */
            val FILLALBUM_SOVIETBB_COMPLETED: Int = 0,

            /**
             * INGAME NAME: BREAK THE BANK
             */
            val NY17_BREAK_THE_BANK: Int = 0,

            /**
             * INGAME NAME: WARRIOR OF THE LIGHT
             */
            val ALL_THREE_HALLOWEEN_COMPLETE: Int = 0,

            /**
             * INGAME NAME: INITIAL CAPITAL
             */
            val CAPITAL: Int = 0,

            /**
             * INGAME NAME: NAval WARFARE. WEAPONRY BASICS
             */
            val SCIENCE_OF_WINNING_BOMBARDIER: Int = 0,

            /**
             * INGAME NAME: "RESOLUTE AND RAPID"
             */
            val FILLALBUM_ITCA_COMPLETED: Int = 0,

            /**
             * INGAME NAME: OPERATION DYNAMO
             */
            val PVE_DUNKERQUE_OPERATION_DYNAMO: Int = 0,

            /**
             * INGAME NAME: FIRST BLOOD
             */
            val FIRST_BLOOD: Int = 0,

            /**
             * INGAME NAME: "2 YEARS OF WORLD OF WARSHIPS"
             */
            val COLLECTION_WOWSBIRTHDAY_COMPLETED: Int = 0,

            /**
             * INGAME NAME: AA DEFENSE EXPERT
             */
            val AIRDEFENSEEXPERT: Int = 0,

            /**
             * INGAME NAME: GOOD START
             */
            val NY17_WIN_AT_LEAST_ONE: Int = 0,

            /**
             * INGAME NAME: "THE HUNT FOR GRAF SPEE" WITH HONORS
             */
            val CAMPAIGN_NY17B_COMPLETED_EXCELLENT: Int = 0,

            /**
             * INGAME NAME: AMATEUR
             */
            val AMAUTEUR: Int = 0,

            val FILLALBUM_GF097_COMPLETED: Int = 0,

            val FILLALBUM_CAPT_COMPLETED: Int = 0,

            val FILLALBUM_USABB_0910_COMPLETED: Int = 0,
        ) {


            override fun toString(): String {
                return GSON.toJson(this)
            }
        }

        @Serializable
        data class Progress(
            val FIGHTER: Int = 0,
            val MILLIONAIR: Int = 0,
            val MERCENARY: Int = 0,
            val MESSENGER: Int = 0,
            val PVE_HON_PR_SAVE_3: Int = 0,
            val PVE_HON_PR_SAVE_1: Int = 0,
            val ENGINEER: Int = 0,
            val BATTLE_HERO: Int = 0,
            val SEA_LEGEND: Int = 0,
            val WORKAHOLIC_L: Int = 0,
            val PVE_HON_PR_SAVE_2: Int = 0,
            val JUNIOR_PLANNER: Int = 0,
            val MESSENGER_L: Int = 0,
            val MERCENARY_L: Int = 0,
            val VETERAN: Int = 0,
            val NEVER_ENOUGH_MONEY: Int = 0,
            val NO_PRICE_FOR_HEROISM: Int = 0,
            val ALL_THREE_HALLOWEEN_COMPLETE: Int = 0,
            val CAPITAL: Int = 0,
            val SCIENCE_OF_WINNING_BOMBARDIER: Int = 0,
            val PVE_HON_PR_DONE_ALL_1: Int = 0,
            val CHIEF_ENGINEER: Int = 0,
            val NO_DAY_WITHOUT_ADVENTURE_L: Int = 0,
            val PVE_HON_PR_DONE_1: Int = 0,
            val AMAUTEUR: Int = 0,
        ) {
            override fun toString(): String {
                return GSON.toJson(this)
            }
        }

        override fun toString(): String {
            return GSON.toJson(this)
        }
    }

    override fun toString(): String {
        return GSON.toJson(this)
    }
}