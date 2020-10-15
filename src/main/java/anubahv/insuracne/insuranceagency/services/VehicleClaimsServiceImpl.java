package anubahv.insuracne.insuranceagency.services;

import anubahv.insuracne.insuranceagency.models.VehicleClaims;
import anubahv.insuracne.insuranceagency.repository.VehicleClaimsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VehicleClaimsServiceImpl implements VehicleClaimsService {
    VehicleClaimsRepository vehicleClaimsRepository;

    @Autowired
    public VehicleClaimsServiceImpl(VehicleClaimsRepository vehicleClaimsRepository) {
        this.vehicleClaimsRepository = vehicleClaimsRepository;
    }

    @Override
    public VehicleClaims getClaim(int id) {
        return vehicleClaimsRepository.findClaim(id);
    }

    @Override
    public void add(VehicleClaims vehicleClaims) {
        vehicleClaimsRepository.save(vehicleClaims);
    }

    @Override
    public void changeStatus(String status, int id) {
        vehicleClaimsRepository.changeStatus(status,id);
    }

    @Override
    public List<String> getRelatedDocuments(int id) {
        return vehicleClaimsRepository.getRelatedDocuments(id);
    }

    @Override
    public void delete(int id) {
        vehicleClaimsRepository.delete(id);
    }
}
