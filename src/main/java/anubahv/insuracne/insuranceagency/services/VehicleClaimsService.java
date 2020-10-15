package anubahv.insuracne.insuranceagency.services;

import anubahv.insuracne.insuranceagency.models.VehicleClaims;

import java.util.List;

public interface VehicleClaimsService {
    public VehicleClaims getClaim(int id);
    public void add(VehicleClaims vehicleClaims);
    public void changeStatus(String status,int id);
    public List<String> getRelatedDocuments(int id);
    public void delete(int id);
}
