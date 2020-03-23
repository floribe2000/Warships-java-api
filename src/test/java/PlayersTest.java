import de.floribe2000.warships_java.account.AccountRequestBuilder;
import de.floribe2000.warships_java.account.PlayersAchievmentsRequest;
import de.floribe2000.warships_java.account.PlayersPersonalDataFullRequest;
import de.floribe2000.warships_java.account.PlayersRequest;
import de.floribe2000.warships_java.api.ApiBuilder;
import de.floribe2000.warships_java.api.Region;
import org.junit.Test;

public class PlayersTest {

    private String apiKey = "";

    @Test
    public void testPlayersRequest() {
        //TODO
        ApiBuilder.createInstance(apiKey, true);
        System.out.println(PlayersRequest.builder().region(Region.EU).searchText("floribe").build().fetch());
        //System.out.println(AccountRequestBuilder.playersRequest(Region.EU, "floribe").fetch());

    }

    @Test
    public void testPlayerPersonalDataRequest() {
        //TODO
        ApiBuilder.createInstance(apiKey, true);
        System.out.println(AccountRequestBuilder.playersPersonalDataFullRequest(Region.EU, "537376379")
                .addExtraField(PlayersPersonalDataFullRequest.ExtraField.PVE)
                .addExtraField(PlayersPersonalDataFullRequest.ExtraField.RANK_SOLO).fetch());
    }

    @Test
    public void testPlayersAchievments() {
        ApiBuilder.createInstance(apiKey, true);
        System.out.println(AccountRequestBuilder.playersAchievmentsRequest(Region.EU, "537376379").fetch());
        System.out.println(PlayersAchievmentsRequest.AchievmentElement.retrieveElement("Solo Warrior"));
    }

}
