package org.scsmksn.npl.auction.logic.services.impl;

import org.scsmksn.npl.auction.logic.repositories.CricketProfileRepository;
import org.scsmksn.npl.auction.logic.services.ProfileService;
import org.scsmksn.npl.auction.model.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import java.nio.charset.Charset;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Component
public class ProfileServiceImpl implements ProfileService {

    @Autowired
    private CricketProfileRepository profileRepository;

    @Override
    public Profile get(final Long id) {
        return profileRepository.findById(id)
                .orElseThrow(() -> new HttpClientErrorException("Cricket Profile with id('" + id + "') not found!"
                        , NOT_FOUND, NOT_FOUND.getReasonPhrase(), null, null, Charset.defaultCharset()));
    }

    @Override
    public Profile save(final Profile profile) {
        final Profile fromDB = profileRepository.saveAndFlush(profile);
        return profileRepository.findById(fromDB.getId()).orElse(fromDB);
    }

    @Override
    public Profile delete(final Long id) {
        final Profile profile = get(id);
        profileRepository.delete(profile);
        return profile;
    }
}
