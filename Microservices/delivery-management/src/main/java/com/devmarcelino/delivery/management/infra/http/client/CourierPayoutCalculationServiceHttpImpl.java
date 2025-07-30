package com.devmarcelino.delivery.management.infra.http.client;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;

import com.devmarcelino.delivery.management.domain.service.CourierPayoutCalculationService;

import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CourierPayoutCalculationServiceHttpImpl implements CourierPayoutCalculationService{
    private final CourierAPIClient courierAPIClient;

    @Override
    public BigDecimal calculatePayout(Double distanceInKm) {
        try {
            var payoutCalculationRequest = courierAPIClient.payoutCalculation(new CourierPayoutCalculationRequest(distanceInKm));
            return payoutCalculationRequest.getPayoutFee();
        } catch (ResourceAccessException ex) {
            throw new GatewayTimeoutException(ex);
        } catch (HttpServerErrorException | IllegalArgumentException | CallNotPermittedException ex) {
            throw new BadGatewayException(ex);
        }
    } 
    
}
