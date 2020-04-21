package de.floribe2000.warships_java.utilities;

import de.floribe2000.warships_java.direct.api.typeDefinitions.Region;
import de.floribe2000.warships_java.direct.encyclopedia.Warships;
import de.floribe2000.warships_java.direct.encyclopedia.WarshipsRequest;

/**
 * A class that offers a collection of utility methods for api encyclopedia requests.
 * <p>Make sure to initialize the ApiBuilder instance before sending requests! You can either do this by using {@link #initialize(String apiKey)}
 * or by manually creating an ApiBuilder instance using {@link de.floribe2000.warships_java.direct.api.ApiBuilder#createInstance(String apiKey)}</p>
 *
 * @author floribe2000
 */
public class EncyclopediaRequestService extends AbstractRequestService {

    /**
     * Allows to request a full list of all ships currently in the game.
     * <p>Ignore the page number of the meta object of the returned {@link Warships} instance!</p>
     *
     * @param region the region for the request
     * @return a {@link Warships} instance containing the data
     */
    public static Warships requestFullWarshipsList(Region region) {
        //Prepare basic request
        WarshipsRequest request = WarshipsRequest.createRequest().region(region);
        //Send first request
        Warships result = request.fetch();
        //Check response status
        if (!result.getStatus().get()) {
            throw new IllegalStateException("Invalid result state!");
        }

        int page = 1;
        while (page < result.getMeta().getPage_total()) {
            page++;
            Warships tmp = request.pageNo(page).fetch();
            if (tmp.getStatus().get()) {
                result.getData().putAll(tmp.getData());
            } else {
                throw new IllegalStateException("Invalid result state!");
            }
        }
        return result;
    }
}
