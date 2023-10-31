package com.app.shopping.ecommerce.services;

import com.app.shopping.ecommerce.payload.ShowDetailsDto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;
public interface ShowDetailsService {
    ShowDetailsDto getShowDetails(HttpServletRequest request);



}
