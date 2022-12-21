//package com.example.demo.dao;
//
//import com.example.demo.domain.Author;
//import org.springframework.stereotype.Component;
//
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//
//
//@Component
//public class AuthorDaoImpl implements AuthorDao{
//
//    private final DataSource source;
//
//    public AuthorDaoImpl(DataSource source) {
//        this.source = source;
//    }
//
//    @Override
//    public Long getById(Long id) {
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = source.getConnection();
//            ps = connection.prepareStatement("SELECT * FROM author where id = ?");
//            ps.setLong(1, id);
//            resultSet = ps.executeQuery();
//
//            if (resultSet.next()) {
//                return getAuthorFromRS(resultSet);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                closeAll(resultSet, ps, connection);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public Long findAuthorByName(String firstName, String lastName) {
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = source.getConnection();
//            ps = connection.prepareStatement("SELECT * FROM author where first_name = ? and last_name = ?");
//            ps.setString(1, firstName);
//            ps.setString(2, lastName);
//            resultSet = ps.executeQuery();
//
//            if (resultSet.next()) {
//                return getAuthorFromRS(resultSet);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                closeAll(resultSet, ps, connection);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public Long saveNewAuthor(Author author) {
//        Connection connection = null;
//        PreparedStatement ps = null;
//        ResultSet resultSet = null;
//
//        try {
//            connection = source.getConnection();
//            ps = connection.prepareStatement("INSERT INTO  author (first_name,last_name) values (?,?)");
//            ps.setString(1, author.getFirstName ());
//            ps.setString(2, author.getFirstName ());
//            ps.execute ();
//
//            Statement statement = connection.createStatement();
//            resultSet = statement.executeQuery ("SELECT id FROM author ORDER BY id DESC LIMIT 1");
//
//            if (resultSet.next()) {
//                Long savedId = resultSet.getLong(1);
//                return this.getById (savedId);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                closeAll(resultSet, ps, connection);
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
//        }
//
//        return null;
//    }
//
//    @Override
//    public Long updatedAuthor(Long saved) {
//        return null;
//    }
//
////    @Override
////    public Long updatedAuthor(Author author) {
////        Connection connection = null;
////        PreparedStatement ps = null;
////        ResultSet resultSet = null;
////
////        try {
////            connection = source.getConnection();
////            ps = connection.prepareStatement("UPDATE author set first_name = ?, last_name = ? where author.id = ? ");
////            ps.setString(1, author.getFirstName ());
////            ps.setString(2, author.getLastName ());
////            ps.setLong (3,author.getId ());
////            ps.execute ();
////
////        } catch (SQLException e) {
////            e.printStackTrace();
////        } finally {
////            try {
////                closeAll(resultSet, ps, connection);
////            } catch (SQLException e) {
////                e.printStackTrace();
////            }
////        }
////
////        return this.getById (author.getId ());
////    }
//
//    @Override
//    public void deleteAuthorById(Long id) {
//
//        Connection connection = null;
//
//        PreparedStatement ps = null;
//
//        try {
//            connection = source.getConnection ();
//            ps = connection.prepareStatement ("DELETE from author where id = ?");
//            ps.setLong (1,id);
//            ps.execute ();
//
//        }
//         catch (SQLException e) {
//            e.printStackTrace ();
//        }
//        finally {
//            try {
//                closeAll (null,ps,connection);
//            }
//            catch (SQLException e) {
//              e.printStackTrace ();
//            }
//        }
//
//    }
//
////    private Long getAuthorFromRS(ResultSet resultSet) throws SQLException {
////        Long author = new Author();
////        author.setId(resultSet.getLong("id"));
////        author.setFirstName(resultSet.getString("first_name"));
////        author.setLastName(resultSet.getString("last_name"));
////        return author;
////    }
////
////    private void closeAll(ResultSet resultSet, PreparedStatement ps, Connection connection) throws SQLException {
////        if (resultSet != null) {
////            resultSet.close();
////        }
////
////        if (ps != null){
////            ps.close();
////        }
////
////        if (connection != null){
////            connection.close();
////        }
////    }
//
//}