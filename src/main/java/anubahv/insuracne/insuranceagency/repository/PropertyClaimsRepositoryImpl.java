package anubahv.insuracne.insuranceagency.repository;

import anubahv.insuracne.insuranceagency.models.PropertyClaim;
import anubahv.insuracne.insuranceagency.models.PropertyClaim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class PropertyClaimsRepositoryImpl implements PropertyClaimsRepository {
    JdbcTemplate jdbcTemplate;

    private RowMapper<PropertyClaim> propertyClaimRowMapper = new RowMapper<PropertyClaim>() {
        @Override
        public PropertyClaim mapRow(ResultSet resultSet, int i) throws SQLException {
            PropertyClaim propertyClaim = new PropertyClaim();
            propertyClaim.setId(resultSet.getInt("id"));
            propertyClaim.setDamage(resultSet.getInt("damage"));
            propertyClaim.setAmount(resultSet.getInt("amount"));
            propertyClaim.setStatus(resultSet.getString("status"));
            propertyClaim.setDateOfLoss(resultSet.getDate("date_of_loss"));
            propertyClaim.setPropertyId(resultSet.getInt("property_id"));
            propertyClaim.setRecordId(resultSet.getInt("record_id"));
            return propertyClaim;
        }
    };
    @Autowired
    public PropertyClaimsRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public PropertyClaim findClaim(int id) {
        String sqlQuery = "select * from property_claims where id='"+id+"'";
        PropertyClaim propertyClaim = jdbcTemplate.queryForObject(sqlQuery,propertyClaimRowMapper);
        String sqlQueryForDocs = "select document from property_claim_docs where property_claim_id = '"+id+"'";
        propertyClaim.setLinkToDocuments(jdbcTemplate.query(sqlQueryForDocs, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("document");
            }
        }));
        return propertyClaim;
    }

    @Override
    public void save(PropertyClaim propertyClaim) {
        String sqlQuery = "insert into property_claims(damage,amount,status,date_of_loss,property_id,record_id) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sqlQuery);
        String sqlQueryForId = "select id from property_claims where property_id = '"+propertyClaim.getPropertyId()+"'";
        int id = jdbcTemplate.queryForObject(sqlQueryForId,Integer.class);
        String sqlQueryForDocs = "insert into property_claim_docs(document,property_claim_id) values(?,?)";
        for(int i=0;i<propertyClaim.getLinkToDocuments().size();i++){
            jdbcTemplate.update(sqlQueryForDocs,propertyClaim.getLinkToDocuments().get(i),id);
        }
    }

    @Override
    public void changeStatus(String status,int id) {
        String sqlQuery = "update property_claims set status='"+status+"' where id ='"+id+"'";
        jdbcTemplate.update(sqlQuery);
    }

    @Override
    public List<String> getRelatedDocuments(int id) {
        String sqlQuery = "select document from property_claim_docs where property_claim_id='"+id+"'";
        return jdbcTemplate.query(sqlQuery, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("document");
            }
        });
    }

    @Override
    public void delete(int id) {
        String sqlQuery = "delete from property_claims where id='"+id+"'";
        String sqlQueryForDocs = "delete from property_claim_docs where property_claim_id ='"+id+"'";
        jdbcTemplate.update(sqlQueryForDocs);
        jdbcTemplate.update(sqlQuery);
    }
}
