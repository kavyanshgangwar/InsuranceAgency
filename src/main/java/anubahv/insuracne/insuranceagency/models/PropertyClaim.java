package anubahv.insuracne.insuranceagency.models;

import java.util.Date;
import java.util.List;

public class PropertyClaim {
    private int id;
    private int damage;
    private int amount;
    private String status;
    private Date dateOfLoss;
    private int propertyId;
    private int recordId;
    private List<String> linkToDocuments;
}
