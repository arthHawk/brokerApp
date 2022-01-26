//package ru.pcs.graduatework.repositories;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.jdbc.core.RowMapper;
//import org.springframework.stereotype.Component;
//import ru.pcs.graduatework.model.Client;
//
//import javax.sql.DataSource;
//import java.math.BigDecimal;
//import java.util.List;
//
//@Component
//public class ClientsRepositoryImpl implements ClientsRepository {
//
//    private final JdbcTemplate jdbcTemplate;
//
//    @Autowired
//    public ClientsRepositoryImpl(DataSource dataSource) {
//        this.jdbcTemplate = new JdbcTemplate(dataSource);
//    }
//
//    // language=SQL
//    private static final String SQL_SELECT_ALL = "select * from clients";
//
//    // language=SQL
//    private static final String SQL_INSERT = "insert into clients(login, password, surname, name) values (?, ?, ?, ?)";
////    private static final String SQL_INSERT = "insert into clients(login, password, surname, name, cash) values ('newUser', 'qwerty100', 'Stark', 'Tony', 40000)";
//
//    // language=SQL
//    private static final String SQL_DELETE_BY_ID = "delete from clients where id = ?; ";
//
//    // language=SQL
//    private static final String SQL_SELECT_BY_ID = "select * from clients where id = ?; ";
//
//    // language=SQL
//    private static final String SQL_ADD_CASH = "update clients set cash = ? where id = ?";
//
//
//    ClientsTableMapping clientsTableMapping = new ClientsTableMapping();
//
//    private static final RowMapper<Client> clientsRowMapper = (rs, rowNum) -> {
//        Client client = new Client();
//        client.setId(rs.getInt("id"));
//        client.setLogin(rs.getString("login"));
//        client.setPassword(rs.getString("password"));
//        client.setSurname(rs.getString("surname"));
//        client.setName(rs.getString("name"));
//        client.setCash(rs.getBigDecimal("cash"));
//        return client;
//    };
//
//    @Override
//    public List<Client> findAll() {
//        return jdbcTemplate.query(SQL_SELECT_ALL, clientsRowMapper);
//    }
//
//    @Override
//    public void save(Client client) {
//        jdbcTemplate.update(SQL_INSERT, client.getLogin(),
//                client.getPassword(), client.getSurname(), client.getName());
//    }
//
//    @Override
//    public void delete(Integer userId) {
//        jdbcTemplate.update(SQL_DELETE_BY_ID, userId);
//    }
//
//    @Override
//    public Client findById(Integer clientId) {
//           return jdbcTemplate.queryForObject(SQL_SELECT_BY_ID, clientsRowMapper, clientId);
//    }
//
//    @Override
//    public void addCash(Integer clientId, BigDecimal newCashBalance) {
//        jdbcTemplate.update(SQL_ADD_CASH, newCashBalance, clientId);
//    }
//}
