package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.Advert;
import com.infoshareacademy.Driver;
import com.infoshareacademy.Route;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("AdvertsManager Test")
class AdvertsManagerBeanTest {

    private static final Integer ID_INCREASE_VALUE = 1;
    private static final Integer LOWEST_ID = 2;
    private static final Integer MIDDLE_ID = 5;
    private static final Integer HIGHEST_ID = 7;

    private AdvertsManager advertsManager;
    private List<Advert> adverts;
    private Route testRoute = new Route(0, Date.from(Instant.now()),
            "Start City", "Start Street", "End City", "End Street",
            "Pickup City", "Pickup Street", "08:00", "10:00",
            "09:00");

    @BeforeEach
    void setUp() {
        advertsManager = new AdvertsManagerBean();
        adverts = new ArrayList<>();
    }

    @Test
    @DisplayName("Should return list of adverts with added advert.")
    void returnsListOfAdvertsWithAddedAdvertInAddAdvertMethod() {

        // arrange
        Advert advertToAdd = new Advert(0, Date.from(Instant.now()), new Driver(), testRoute);

        // act
        advertsManager.addAdvert(advertToAdd, adverts);

        // assert
        assertThat(adverts).contains(advertToAdd);
    }

    @Test
    @DisplayName("Should return the highest of adverts id, increased by 1.")
    void returnsHighestOfAdvertsIdIncreasedBy1InGetNextAdvertIdMethod() {

        // arrange
        adverts.add(new Advert(LOWEST_ID, Date.from(Instant.now()), new Driver(), testRoute));
        adverts.add(new Advert(HIGHEST_ID, Date.from(Instant.now()), new Driver(), testRoute));
        adverts.add(new Advert(MIDDLE_ID, Date.from(Instant.now()), new Driver(), testRoute));

        // act
        Integer nextId = advertsManager.getNextAdvertId(adverts);

        // assert
        assertThat(nextId).isEqualTo(HIGHEST_ID + ID_INCREASE_VALUE);
    }

    @Test
    @DisplayName("Should return the highest of routes id, increased by 1.")
    void returnsHighestOfRoutesIdIncreasedBy1InGetNextRouteIdMethod() {

        // arrange
        Route lowestIdRoute = new Route();
        lowestIdRoute.setId(LOWEST_ID);
        Route highestIdRoute = new Route();
        highestIdRoute.setId(HIGHEST_ID);
        Route middleIdRoute = new Route();
        middleIdRoute.setId(MIDDLE_ID);
        adverts.add(new Advert(0, Date.from(Instant.now()), new Driver(), highestIdRoute));
        adverts.add(new Advert(0, Date.from(Instant.now()), new Driver(), middleIdRoute));
        adverts.add(new Advert(0, Date.from(Instant.now()), new Driver(), lowestIdRoute));

        // act
        Integer nextId = advertsManager.getNextRouteId(adverts);

        // assert
        assertThat(nextId).isEqualTo(HIGHEST_ID + ID_INCREASE_VALUE);
    }

}