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
    void returnsListOfAdvertsWithAddedAdvert() {
        // arrange

        Advert advertToAdd = new Advert(0, Date.from(Instant.now()), new Driver(), testRoute);
        // act
        advertsManager.addAdvert(advertToAdd, adverts);
        // assert
        assertThat(adverts).contains(advertToAdd);
    }

    @Test
    @DisplayName("Should return the highest of adverts id, increased by 1.")
    void returnsHighestOfAdvertsIdIncreasedBy1() {
        // arrange
        final Integer lowestId = 2;
        final Integer middleId = 5;
        final Integer highestId = 7;
        adverts.add(new Advert(lowestId, Date.from(Instant.now()), new Driver(), testRoute));
        adverts.add(new Advert(highestId, Date.from(Instant.now()), new Driver(), testRoute));
        adverts.add(new Advert(middleId, Date.from(Instant.now()), new Driver(), testRoute));
        // act
        Integer nextId = advertsManager.getNextAdvertId(adverts);
        // assert
        assertThat(nextId).isEqualTo(highestId + ID_INCREASE_VALUE);
    }

    @Test
    @DisplayName("Should return the highest of routes id, increased by 1.")
    void returnsHighestOfRoutesIdIncreasedBy1() {
        // arrange
        final Integer lowestId = 2;
        final Integer middleId = 5;
        final Integer highestId = 7;
        Route lowestIdRoute = testRoute;
        lowestIdRoute.setId(lowestId);
        Route middleIdRoute = testRoute;
        middleIdRoute.setId(middleId);
        Route highestIdRoute = testRoute;
        highestIdRoute.setId(highestId);
        adverts.add(new Advert(lowestId, Date.from(Instant.now()), new Driver(), highestIdRoute));
        adverts.add(new Advert(highestId, Date.from(Instant.now()), new Driver(), middleIdRoute));
        adverts.add(new Advert(middleId, Date.from(Instant.now()), new Driver(), lowestIdRoute));
        // act
        Integer nextId = advertsManager.getNextRouteId(adverts);
        // assert
        assertThat(nextId).isEqualTo(highestId + ID_INCREASE_VALUE);
    }

}