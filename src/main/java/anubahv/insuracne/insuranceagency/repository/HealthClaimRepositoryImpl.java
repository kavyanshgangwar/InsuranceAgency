package anubahv.insuracne.insuranceagency.repository;

import anubahv.insuracne.insuranceagency.models.HealthClaim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class HealthClaimRepositoryImpl implements HealthClaimRepository {
    JdbcTemplate jdbcTemplate;
    private RowMapper<HealthClaim> healthClaimRowMapper = new RowMapper<HealthClaim>() {
        @Override
        public HealthClaim mapRow(ResultSet resultSet, int i) throws SQLException {
            HealthClaim healthClaim = new HealthClaim();
            healthClaim.setId(resultSet.getInt("id"));
            healthClaim.setDamage(resultSet.getInt("damage"));
            healthClaim.setAmount(resultSet.getInt("amount"));
            healthClaim.setStatus(resultSet.getString("status"));
            healthClaim.setDateOfLoss(resultSet.getDate("date_of_loss"));

            healthClaim.setRecordId(resultSet.getInt("record_id"));
            return healthClaim;
        }
    };

    @Autowired
    public HealthClaimRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public HealthClaim findClaim(int id) {
        String sqlQuery = "select * from health_claims where id='"+id+"'";
        HealthClaim healthClaim = jdbcTemplate.queryForObject(sqlQuery,healthClaimRowMapper);
        String sqlQueryForDocs = "select document from health_claim_docs where health_claim_id = '"+id+"'";
        healthClaim.setLinkToDocuments(jdbcTemplate.query(sqlQueryForDocs, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("document");
            }
        }));
        return healthClaim;
    }

    @Override
    public void save(HealthClaim healthClaim) {
        String sqlQuery = "insert into health_calims(damage,amount,status,date_of_loss,record_id) values(?,?,?,?,?)";
        jdbcTemplate.update(sqlQuery);
        String sqlQueryForId = "select id from health_claims where record_id = '"+healthClaim.getRecordId()+"'";
        int id = jdbcTemplate.queryForObject(sqlQueryForId,Integer.class);
        String sqlQueryForDocs = "insert into health_claim_docs(document,health_claim_id) values(?,?)";
        for(int i=0;i<healthClaim.getLinkToDocuments().size();i++){
            jdbcTemplate.update(sqlQueryForDocs,healthClaim.getLinkToDocuments().get(i),id);
        }
    }

    @Override
    public void changeStatus(String status,int id) {
        String sqlQuery = "update health_claims set status='"+status+"' where id ='"+id+"'";
        jdbcTemplate.update(sqlQuery);
    }

    @Override
    public List<String> getRelatedDocuments(int id) {
        String sqlQuery = "select document from health_claim_docs where health_claim_id='"+id+"'";
        return jdbcTemplate.query(sqlQuery, new RowMapper<String>() {
            @Override
            public String mapRow(ResultSet resultSet, int i) throws SQLException {
                return resultSet.getString("document");
            }
        });
    }

    @Override
    public void delete(int id) {
        String sqlQuery = "delete from health_claims where id='"+id+"'";
        String sqlQueryForDocs = "delete from health_claim_docs where health_claim_id ='"+id+"'";
        jdbcTemplate.update(sqlQueryForDocs);
        jdbcTemplate.update(sqlQuery);
    }
}