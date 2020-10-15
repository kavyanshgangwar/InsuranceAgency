package anubahv.insuracne.insuranceagency.repository;

import anubahv.insuracne.insuranceagency.models.LifeInsuranceClaim;


public interface LifeInsuranceClaimsRepository {
    public void save(LifeInsuranceClaim lifeInsuranceClaim);
    public void delete(int id);
    public void updateStatus(String status, int id);
    public LifeInsuranceClaim findById(int id);
    public String findDocuments(int id);
}
