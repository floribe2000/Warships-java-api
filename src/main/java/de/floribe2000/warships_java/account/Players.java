package de.floribe2000.warships_java.account;

import de.floribe2000.warships_java.RequestAction;
import de.floribe2000.warships_java.common.Meta;
import lombok.Getter;

import java.util.List;

public class Players implements RequestAction<Players> {

    private String status;

    private Meta meta;

    private List<PlayerElement> data;

    //TODO
    @Override
    public Players fetch() {
        return null;
    }

    @Getter
    public class PlayerElement {
        private String nickname;

        private int account_id;
    }
}