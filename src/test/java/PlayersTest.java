import de.floribe2000.warships_java.account.PlayersPersonalDataFullRequest;
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

    @Test
    public void testPlayerPersonalDataRequest() {
        //TODO
        ApiBuilder.createInstance("placeholder");
        System.out.println(new PlayersPersonalDataFullRequest(Region.EU, "537376379")
                .addExtraField(PlayersPersonalDataFullRequest.ExtraField.PVE)
                .addExtraField(PlayersPersonalDataFullRequest.ExtraField.RANK_SOLO).fetch());
    }
}
