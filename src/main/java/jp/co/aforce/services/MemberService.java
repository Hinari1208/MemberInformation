package jp.co.aforce.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jp.co.aforce.models.Member;
import jp.co.aforce.utils.DatabaseUtils;

public class MemberService {

    public boolean isMemberExists(String loginId) {
        try (Connection connection = DatabaseUtils.getConnection()) {
            String sql = "SELECT COUNT(*) FROM ログインテーブル WHERE ログインID = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, loginId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean registerMember(Member member) {
        try (Connection connection = DatabaseUtils.getConnection()) {
            connection.setAutoCommit(false);
            try {
                String loginSql = "INSERT INTO ログインテーブル (ログインID, パスワード) VALUES (?, ?)";
                PreparedStatement loginStatement = connection.prepareStatement(loginSql);
                loginStatement.setString(1, member.getLoginId());
                loginStatement.setString(2, member.getPassword());
                loginStatement.executeUpdate();

                String memberSql = "INSERT INTO 会員情報 (会員番号, ログインID, 名前_姓, 名前_名, 性別, 生年月日_年, 生年月日_月, 生年月日_日, 職業, 電話番号, メールアドレス) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement memberStatement = connection.prepareStatement(memberSql);
                memberStatement.setString(1, member.getMemberId());
                memberStatement.setString(2, member.getLoginId());
                memberStatement.setString(3, member.getLastName());
                memberStatement.setString(4, member.getFirstName());
                memberStatement.setString(5, member.getSex());
                memberStatement.setInt(6, member.getBirthYear());
                memberStatement.setInt(7, member.getBirthMonth());
                memberStatement.setInt(8, member.getBirthDay());
                memberStatement.setString(9, member.getJob());
                memberStatement.setString(10, member.getPhoneNumber());
                memberStatement.setString(11, member.getMailAddress());
                memberStatement.executeUpdate();

                connection.commit();
                return true;
            } catch (SQLException e) {
                connection.rollback();
                e.printStackTrace();
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
