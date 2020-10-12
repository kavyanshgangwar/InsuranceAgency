package anubahv.insuracne.insuranceagency.services;

import anubahv.insuracne.insuranceagency.models.PolicyRecord;
import anubahv.insuracne.insuranceagency.models.Property;
import anubahv.insuracne.insuranceagency.repository.PolicyRecordRepository;
import anubahv.insuracne.insuranceagency.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyServiceImpl implements PropertyService {
    PropertyRepository propertyRepository;
    PolicyRecordRepository policyRecordRepository;

    @Autowired
    public PropertyServiceImpl(PropertyRepository propertyRepository, PolicyRecordRepository policyRecordRepository) {
        this.propertyRepository = propertyRepository;
        this.policyRecordRepository = policyRecordRepository;
    }

    @Override
    public List<Property> getByUser(int userId) {
        return propertyRepository.findByUser(userId);
    }

    @Override
    public Property getById(int id) {
        return propertyRepository.findById(id);
    }

    @Override
    public PolicyRecord getRecordOnProperty(int id) {
        return policyRecordRepository.getPolicyRecord(propertyRepository.findRecordOnProperty(id));
    }

    @Override
    public void addProperty(Property property) {
        propertyRepository.save(property);
    }

    @Override
    public void changeRecord(int recordId, int id) {
        propertyRepository.changeRecord(recordId,id);
    }

    @Override
    public void delete(int id) {
        propertyRepository.delete(id);
    }
}
