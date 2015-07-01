package dirceubelem.GamesStats.to;

import java.util.List;

/**
 * Created by joaop_000 on 26/06/2015.
 */
public class TOfeedGames extends TOBase {
    private  int game_count;

    public int getGame_count() {
        return game_count;
    }

    public void setGame_count(int game_count) {
        this.game_count = game_count;
    }

    public List<TOrecentes> getGames() {
        return games;
    }

    public void setGames(List<TOrecentes> games) {
        this.games = games;
    }

    private List<TOrecentes> games;

}
