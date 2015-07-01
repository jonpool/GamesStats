package dirceubelem.GamesStats.fw;

/**
 * Created by dirceubelem on 16/05/15.
 */
public class Constant {

    public static String key = "8D816C5575B94710AF2B30AAA7B00DA2";

    public static final class API {
        public static final String URL = "http://api.steampowered.com/";
        public static final String OBTER_IDUSUARIO = URL + "ISteamUser/ResolveVanityURL/v0001/?key="+key+"&vanityurl=";
        public static final String OBTER_DADOSUSUARIO = URL +"ISteamUser/GetPlayerSummaries/v0002/?key="+key+"&steamids=";
        public static final String OBTER_RECENTEGAMES = URL +"IPlayerService/GetRecentlyPlayedGames/v0001/?key="+key+"&steamid=";
        public static final String URLMEDIA = "http://api.steampowered.com/";
        public static final String OBTER_IMAGEMRECENTE = URLMEDIA+"steamcommunity/public/images/apps/";
        public static final String OBTER_JOGOS = URL+"IPlayerService/GetOwnedGames/v1?key="+key+"&steamid=";
        public static final String Obter_AMIGOS = URL+"ISteamUser/GetFriendList/v0001/?key="+key+"&steamid=";
    }

}
