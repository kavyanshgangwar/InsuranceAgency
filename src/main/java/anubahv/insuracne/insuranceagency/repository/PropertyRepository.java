package anubahv.insuracne.insuranceagency.repository;

import anubahv.insuracne.insuranceagency.models.Property;

import java.util.List;

public interface PropertyRepository {
    public List<Property> findByUser(int userId);
    public Property findById(int id);
    public int findRecordOnProperty(int id);
    public void save(Property property);
    public void changeRecord(int recordId,int id);
    public void delete(int id);
}