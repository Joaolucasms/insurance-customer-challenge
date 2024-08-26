package br.com.challenge.insurance.application;

import br.com.challenge.insurance.application.dto.SimulateInsuranceRequest;
import br.com.challenge.insurance.application.dto.SimulateInsuranceResponse;
import br.com.challenge.insurance.domain.contract.InsuranceRequestService;
import br.com.challenge.insurance.domain.entity.InsuranceRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/insurante-request")
public class InsuranceRequestController {

    private final InsuranceRequestService insuranceRequestService;

    public InsuranceRequestController(InsuranceRequestService insuranceRequestService) {
        this.insuranceRequestService = insuranceRequestService;
    }

    @PostMapping
    public ResponseEntity<SimulateInsuranceResponse> simulateInsurance(
            @RequestBody SimulateInsuranceRequest simulateInsuranceRequest
    ){
        InsuranceRequest insuranceRequest = insuranceRequestService.simulateInsurance(
                simulateInsuranceRequest.customerId(),
                simulateInsuranceRequest.insuranceType(),
                simulateInsuranceRequest.amountToReceive()
        );

        return ResponseEntity.ok(new SimulateInsuranceResponse(
                insuranceRequest.getExternalId(),
                insuranceRequest.getCustomer().getExternalId(),
                insuranceRequest.getType(),
                insuranceRequest.getAmountToReceive(),
                insuranceRequest.getAmountToBePaid(),
                insuranceRequest.getValidUntil())
        );
    }

    @PostMapping("/{id}")
    public ResponseEntity<Object> formalizeRequest(@PathVariable UUID id){
        insuranceRequestService.formalizeRequest(id);
        return ResponseEntity.ok().build();
    }
}
