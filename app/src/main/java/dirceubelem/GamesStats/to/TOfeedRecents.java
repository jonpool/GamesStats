package dirceubelem.GamesStats.to;

import java.util.List;

/**
 * Created by joaop_000 on 25/06/2015.
 */
public class TOfeedRecents extends TOBase {
        private int total_count;
        private List<TOrecentes> games;

    public int getTotal_count() {
        return total_count;
    }

    public void setTotal_count(int total_count) {
        this.total_count = total_count;
    }

    public List<TOrecentes> getGames() {
        return games;
    }

    public void setGames(List<TOrecentes> games) {
        this.games = games;
    }
}
