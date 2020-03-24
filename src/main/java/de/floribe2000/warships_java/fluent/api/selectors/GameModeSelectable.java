package de.floribe2000.warships_java.fluent.api.selectors;

import de.floribe2000.warships_java.fluent.api.GameMode;
import de.floribe2000.warships_java.fluent.api.Request;
import java.util.Collection;

public interface GameModeSelectable<T extends Request> {

	T ofGameMode(GameMode mode);

	T ofGameModes(GameMode... modes);

	T ofGameModes(Collection<GameMode> modes);

	T clearGameModes();
}
