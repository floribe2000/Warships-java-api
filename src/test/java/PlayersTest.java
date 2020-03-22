import de.floribe2000.warships_java.account.PlayersRequest;
import de.floribe2000.warships_java.api.ApiBuilder;
import de.floribe2000.warships_java.api.Region;
import org.junit.Test;

public class PlayersTest {

    @Test
    public void testPlayersRequest() {
        //TODO
        ApiBuilder.createInstance("placeholder");
        System.out.println(new PlayersRequest(Region.EU, "floribe").fetch());
    }
}
