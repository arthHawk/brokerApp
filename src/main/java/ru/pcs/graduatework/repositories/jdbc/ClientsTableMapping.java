//package ru.pcs.graduatework.repositories;
//
//import org.springframework.jdbc.core.RowMapper;
//import ru.pcs.graduatework.model.Client;
//
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//public class ClientsTableMapping implements RowMapper<Client> {
//
//    @Override
//    public Client mapRow(ResultSet rs, int rowNum) throws SQLException {
//        Client client = new Client();
//        client.setId(rs.getInt("id"));
//        client.setLogin(rs.getString("login"));
//        client.setPassword(rs.getString("password"));
//        client.setSurname(rs.getString("surname"));
//        client.setName(rs.getString("name"));
//        client.setCash(rs.getBigDecimal("cash"));
//        return client;
//    }
//}
