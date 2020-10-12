package anubahv.insuracne.insuranceagency.services;

import anubahv.insuracne.insuranceagency.models.PolicyRecord;
import anubahv.insuracne.insuranceagency.repository.PolicyRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

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
    public void deleteRecord(int id) {
        policyRecordRepository.delete(id);
    }
}