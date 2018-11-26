package com.infoshareacademy;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public enum State {
    SHOW_ADVERTS_LIST(1),
    ADD_ADVERT(2),
    FILTER(3),
    RATING(4),
    EXIT(0);

    private int stateNumber;
    private static final Map enumMap = new HashMap();
    State(int stateNumber) {
        this.stateNumber = stateNumber;
    }
    static{

        for (State s: EnumSet.allOf(State.class)
                ) {
            enumMap.put(s.getStateNumber(), s);
        }

    }
    public int getStateNumber() {
        return stateNumber;
    }

    public static State get(int stateNumber){
        return (State) enumMap.get(stateNumber);
    }
}
