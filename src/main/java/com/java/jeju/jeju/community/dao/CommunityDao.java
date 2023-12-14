package com.java.jeju.jeju.community.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.java.jeju.jeju.community.domain.Community;


@Repository
public class CommunityDao {
	@Autowired
	JdbcTemplate jdbcTemplate;

	private RowMapper<Community> mapper = new RowMapper<Community>() {

		@Override
		public Community mapRow(ResultSet rs, int rowNum) throws SQLException {
			// TODO Auto-generated method stub
			return new Community(rs.getInt("id"), rs.getString("title"), rs.getString("content"), rs.getInt("views"), 0, 0,
					rs.getTimestamp("created_at"), rs.getInt("is_withdrew") == 1, rs.getInt("user_id"),
					rs.getString("name"), rs.getString("git_address"));
		}
	};

	public void add(Community Community) {
		jdbcTemplate.update("insert into Communitys (title, content, is_withdrew, user_id) values (?, ?, ?, ?)",
				Community.getTitle(), Community.getContent(), Community.isWithdrew() ? 1 : 0, Community.getUserId());
	}

	public Community get(int id) {
		return jdbcTemplate.queryForObject(
				"select Communitys.*, users.name, users.git_address from Communitys join users on Communitys.user_id=users.id where Communitys.id=?",
				mapper, id);
	}

	public List<Community> getAll(int idx, int count) {
		return jdbcTemplate.query(
				/*
				 * "select Communitys.*, users.name, users.git_address from Communitys join users on Communitys.user_id=users.id order by Communitys.id desc offset ? rows fetch first ? rows only"
				 * ,
				 */
				"select Communitys.*, users.name, users.git_address from Communitys join users on Communitys.user_id=users.id order by Communitys.id desc limit ?, ?",
				mapper, idx, count);
	}

	public int getCount() {
		return jdbcTemplate.queryForObject("select count(*) from Communitys", Integer.class);
	}
}