package dirceubelem.GamesStats.to;

import java.util.List;

/**
 * Created by joaop_000 on 23/06/2015.
 */
public class TOPerfil extends TOBase {
    private List<TOUsuario> players;

    public List<TOUsuario> getPlayers() {
        return players;
    }

    public void setPlayers(List<TOUsuario> players) {
        this.players = players;
    }
}
