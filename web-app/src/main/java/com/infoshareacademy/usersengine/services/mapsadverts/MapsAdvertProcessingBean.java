package com.infoshareacademy.usersengine.services.mapsadverts;

import com.infoshareacademy.usersengine.services.PropertiesService;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Stateless
public class MapsAdvertProcessingBean implements MapsAdvertProcessing{

    private Long buildedAdvertId;
    private List<String> summary;

    @Inject
    private MapsAdvertVerifier verifier;

    @Inject
    private MapsAdvertBuilder builder;

    @Override
    public void processMapsAdvertCreation(Map<String, String[]> parameters) {
        summary = new ArrayList<>();
        verifier.verifyParameters(parameters);
        if (verifier.areParametersCorrect()) {
            builder.buildMapsAdvert(parameters);
            buildedAdvertId = builder.getBuildedAdvertId();
            summary = Collections.singletonList(PropertiesService.getMsgAdvertAddOk());
        } else {
            summary = verifier.getErrorMessages();
        }
    }

    @Override
    public Long getBuildedAdvertId() {
        return buildedAdvertId;
    }

    @Override
    public List<String> getSummary() {
        return summary;
    }
}
