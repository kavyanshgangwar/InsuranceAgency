package anubahv.insuracne.insuranceagency.services;

import anubahv.insuracne.insuranceagency.models.PolicyRecord;
import anubahv.insuracne.insuranceagency.models.Vehicle;

import java.util.List;

public interface VehicleService {
    public List<Vehicle> getByUser(int userId);
    public Vehicle getById(int id);
    public PolicyRecord getRecordOnVehicle(int id);
    public void addVehicle(Vehicle vehicle);
    public void changeRecord(int recordId,int id);
    public void delete(int id);
    public List<Vehicle> getVehicleForBuyingPolicy(int userId);
}
