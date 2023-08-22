package org.scsmksn.npl.auction.logic.services;

import org.scsmksn.npl.auction.model.Profile;

public interface ProfileService {

    Profile get(Long id);
    Profile save(Profile profile);
    Profile delete(Long id);
}
