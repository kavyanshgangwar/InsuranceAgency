package anubahv.insuracne.insuranceagency.services;

import anubahv.insuracne.insuranceagency.models.PolicyRecord;
import anubahv.insuracne.insuranceagency.repository.PolicyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class PolicyRecordServiceImpl implements PolicyRecordService {
    PolicyRecordRepository policyRecordRepository;

    @Autowired
    public PolicyRecordServiceImpl(PolicyRecordRepository policyRecordRepository) {
        this.policyRecordRepository = policyRecordRepository;
    }

    @Override
    public List<PolicyRecord> getAllOfUser(int userId) {
        return policyRecordRepository.findAllOfUser(userId);
    }

    @Override
    public List<PolicyRecord> getHealthRecordsOfUser(int userId) {
        return policyRecordRepository.findAllOfUserOfCategory(userId,"health");
    }

    @Override
    public List<PolicyRecord> getVehicleRecordsOfUser(int userId) {
        return policyRecordRepository.findAllOfUserOfCategory(userId,"vehicle");
    }

    @Override
    public List<PolicyRecord> getPropertyRecordsOfUser(int userId) {
        return policyRecordRepository.findAllOfUserOfCategory(userId,"property");
    }

    @Override
    public List<PolicyRecord> getLifeRecordsOfUser(int userId) {
        return policyRecordRepository.findAllOfUserOfCategory(userId,"life");
    }

    @Override
    public void changeStatus(String status, int id) {
        policyRecordRepository.changeStatus(status,id);
    }

    @Override
    public void changeExpiryDate(Date date, int id) {
        policyRecordRepository.changeExpiryDate(date,id);
    }

    @Override
    public void addRecord(PolicyRecord policyRecord) {
        policyRecordRepository.save(policyRecord);
    }

    @Override
    public PolicyRecord getPolicyRecord(int id) {
        return policyRecordRepository.getPolicyRecord(id);
    }

    @Override
    public PolicyRecord getPolicyRecord(int userId, int policyId) {
        return policyRecordRepository.getPolicyRecord(userId,policyId);
    }

    @Override
    public void deleteRecord(int id) {
        policyRecordRepository.delete(id);
    }

    @Override
    public Map<PolicyRecord, String> getAllOfUserWithCategory(int userId) {
        Map<PolicyRecord,String> policyRecordsWithCategory = new HashMap<>();
        List<PolicyRecord> healthPolicyRecords = policyRecordRepository.findAllOfUserOfCategory(userId,"health");
        for(int i=0;i<healthPolicyRecords.size();i++){
            policyRecordsWithCategory.put(healthPolicyRecords.get(i),"health");
        }
        List<PolicyRecord> vehiclePolicyRecords = policyRecordRepository.findAllOfUserOfCategory(userId,"vehicle");
        for(int i=0;i<vehiclePolicyRecords.size();i++){
            policyRecordsWithCategory.put(vehiclePolicyRecords.get(i),"vehicle");
        }
        List<PolicyRecord> propertyPolicyRecords = policyRecordRepository.findAllOfUserOfCategory(userId,"property");
        for(int i=0;i<propertyPolicyRecords.size();i++){
            policyRecordsWithCategory.put(propertyPolicyRecords.get(i),"property");
        }
        List<PolicyRecord> policyRecords = policyRecordRepository.findAllOfUserOfCategory(userId,"life");
        for(int i=0;i<policyRecords.size();i++){
            policyRecordsWithCategory.put(policyRecords.get(i),"life");
        }
        return policyRecordsWithCategory;
    }
}
