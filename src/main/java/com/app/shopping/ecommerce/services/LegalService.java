package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.entity.Legal;
import com.app.shopping.ecommerce.payload.LegalDto;

import java.util.List;

public interface LegalService {
    LegalDto addLegal(LegalDto newLegal);

    LegalDto getLegalById(Long id);
    List<Legal> getAllLegal();
    LegalDto updateLegal(Long id, LegalDto updatedLegal);
    boolean deleteLegal(Long id);
}
