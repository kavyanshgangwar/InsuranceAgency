package anubahv.insuracne.insuranceagency.services;

import anubahv.insuracne.insuranceagency.models.PropertyClaim;
import anubahv.insuracne.insuranceagency.repository.PropertyClaimsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropertyClaimsServiceImpl implements PropertyClaimsServices {
    PropertyClaimsRepository propertyClaimsRepository;

    @Autowired
    public PropertyClaimsServiceImpl(PropertyClaimsRepository propertyClaimsRepository) {
        this.propertyClaimsRepository = propertyClaimsRepository;
    }

    @Override
    public PropertyClaim getClaim(int id) {
        return propertyClaimsRepository.findClaim(id);
    }

    @Override
    public void add(PropertyClaim propertyClaim) {
        propertyClaimsRepository.save(propertyClaim);
    }

    @Override
    public void changeStatus(String status, int id) {
        propertyClaimsRepository.changeStatus(status,id);
    }

    @Override
    public List<String> getRelatedDocuments(int id) {
        return propertyClaimsRepository.getRelatedDocuments(id);
    }

    @Override
    public void delete(int id) {
        propertyClaimsRepository.delete(id);
    }
}
