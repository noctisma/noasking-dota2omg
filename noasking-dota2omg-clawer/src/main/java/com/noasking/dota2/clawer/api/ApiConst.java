package com.noasking.dota2.clawer.api;

/**
 * Created by MaJing on 2017/11/9.
 */
public class ApiConst {

    public static final String BASE_URL = "http://api.steampowered.com/";
    public static final String GET_MATCH_HISTORY = "IDOTA2Match_570/GetMatchHistory/v001/";
    public static final String GET_MATCH_HISTORY_BY_SEQ_NUM = "IDOTA2Match_570/GetMatchHistoryBySequenceNum/v0001/";
    public static final String GET_MATCH_DETAILS = "IDOTA2Match_570/GetMatchDetails/v001/";
    public static final String GET_LEAGUE_LISTING = "IDOTA2Match_570/GetLeagueListing/v0001/";
    public static final String GET_LIVE_LEAGUE_GAMES = "IDOTA2Match_570/GetLiveLeagueGames/v0001/";
    public static final String GET_TEAM_INFO_BY_TEAM_ID = "IDOTA2Match_570/GetTeamInfoByTeamID/v001/";
    public static final String GET_PLAYER_SUMMARIES = "ISteamUser/GetPlayerSummaries/v0002/";
    public static final String GET_HEROES = "IEconDOTA2_570/GetHeroes/v0001/";
    public static final String GET_GAME_ITEMS = "IEconDOTA2_570/GetGameItems/v0001/";
    public static final String GET_TOURNAMENT_PRIZE_POOL = "IEconDOTA2_570/GetTournamentPrizePool/v1/";
    public static final String GET_TOP_LIVE_GAME = "IDOTA2Match_570/GetTopLiveGame/v1/";
    public static final String BASE_ITEMS_IMAGES_URL = "http://cdn.dota2.com/apps/dota2/images/items/";
    public static final String BASE_HERO_IMAGES_URL = "http://cdn.dota2.com/apps/dota2/images/heroes/";

    public static class MatchState {
        public static final String CREATE = "C";
        public static final String USE = "U";
        public static final String OVER = "O";
    }

    public static class DicState {
        public static final String ERROR = "E";
        public static final String USE = "U";
        public static final String OVER = "O";
        public static final String CREATE = "C";
    }

}
