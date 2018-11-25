package com.infoshareacademy.usersengine.adverts;

import com.infoshareacademy.usersengine.api.googlemaps.PlaceCompatibilityVerifier;
import com.infoshareacademy.usersengine.dao.MapsAddressDao;
import com.infoshareacademy.usersengine.dao.MapsAdvertDao;
import com.infoshareacademy.usersengine.dao.MapsDriverDao;
import com.infoshareacademy.usersengine.model.MapsAddress;
import com.infoshareacademy.usersengine.model.MapsAdvert;
import com.infoshareacademy.usersengine.model.MapsDriver;

import javax.ejb.Singleton;
import javax.inject.Inject;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Singleton
public class MapsAdvertPreparationBean implements MapsAdvertPreparation{

    @Inject
    private MapsAdvertParametersReader parametersReader;

    @Inject
    private MapsAdvertValidation validation;

    @Inject
    private PlaceCompatibilityVerifier verifier;
    
    @Inject
    private MapsDriverDao mapsDriverDao;
    
    @Inject
    private MapsAddressDao mapsAddressDao;

    private List<String> validationSummary;

    @Override
    public MapsAdvert buildMapsAdvert(Map<String, String[]> parameters) {
        AdvertData advertData = parametersReader.prepareMapsAdvertData(parameters);
        validation.checkAdvertDataCorrection(advertData);
        if (isDataValidAndCompatible(advertData)) {
            return prepareMapsAdvert(advertData);
        } else {
            buildValidationSummary();
            return null;
        }
    }

    @Override
    public List<String> getValidationSummary() {
        return validationSummary;
    }

    private void buildValidationSummary() {
        validationSummary = new ArrayList<>();
        validationSummary = validation.getCheckingMessages();
    }

    private Boolean isDataValidAndCompatible(AdvertData advertData) {
        Boolean isValidAndCompatible = false;
        try {
            if (isDataValid()) {
//                isValidAndCompatible = verifier.arePlacesCompatible(advertData);
                isValidAndCompatible = true;
            }
        } catch (NullPointerException e) {
            System.out.println("Niby jest null");
            e.getMessage();
        }
        return isValidAndCompatible;
    }

    private Boolean isDataValid() {
        Boolean isDataValid = validationSummary.isEmpty();
        System.out.println("Data Valid: " + isDataValid);
        return isDataValid;
    }
    
    private MapsAdvert prepareMapsAdvert(AdvertData advertData) {
        MapsDriver driver = mapsDriverDao.findById(Long.valueOf(advertData.getDriverId()));
        MapsAddress startAddress = buildMapsStartAddress(advertData);
        MapsAddress endAddress = buildMapsEndAddress(advertData);
        return new MapsAdvert(driver, startAddress, endAddress,
                LocalTime.parse(advertData.getStartTime()),
                LocalTime.parse(advertData.getEndTime()),
                LocalDate.parse(advertData.getDate()));
    }
    
    private MapsAddress buildMapsStartAddress(AdvertData advertData) {
        MapsAddress mapsAddress = new MapsAddress();
        mapsAddress.setAddressMapsPointId(advertData.getStartMapsPointId());
        mapsAddress.setStreetName(advertData.getStartStreet());
        mapsAddress.setStreetNumber(advertData.getStartStreetNumber());
        mapsAddress.setCity(advertData.getStartCity());
        mapsAddress.setLatitude(Double.valueOf(advertData.getStartLatitude()));
        mapsAddress.setLongitude(Double.valueOf(advertData.getStartLongitude()));
        mapsAddress.setInfo(advertData.getStartInfo());
        mapsAddressDao.save(mapsAddress);
        return mapsAddress;
    }
    
    private MapsAddress buildMapsEndAddress(AdvertData advertData) {
        MapsAddress mapsAddress = new MapsAddress();
        mapsAddress.setAddressMapsPointId(advertData.getEndMapsPointId());
        mapsAddress.setStreetName(advertData.getEndStreet());
        mapsAddress.setStreetNumber(advertData.getEndStreetNumber());
        mapsAddress.setCity(advertData.getEndCity());
        mapsAddress.setLatitude(Double.valueOf(advertData.getEndLatitude()));
        mapsAddress.setLongitude(Double.valueOf(advertData.getEndLongitude()));
        mapsAddress.setInfo(advertData.getEndInfo());
        mapsAddressDao.save(mapsAddress);
        return mapsAddress;
    }
}
