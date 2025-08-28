package com.kkuk.member.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.kkuk.member.dto.MemberDto;

@Repository
public class MemberDao {

	@Autowired // 자동으로 DI 주입 => 컨테이너에 생성된 Bean(객체)를 자동 주입
	JdbcTemplate jdbcTemplate;
	
	//INSERT문 구현- 회원추가 
	public void insertMember(String memberid, String memberpw, String membername, int memberage){ // DB에 회원 추가
		String sql = "INSERT INTO membertbl VALUES (?,?,?,?)";
		jdbcTemplate.update(sql, memberid, memberpw, membername, memberage);
		
	}
	
	//DELETE문 구현 - 회원 삭제
	public int dlelteMember(String memberid) {
		String sql = "DLELTE FROM membertbl WHERE memberid = ?";
		return jdbcTemplate.update(sql, memberid);
		//return result;
	} //삭제 성공시 1, 실패시 0
	
	//UPDATE문 구현 - 회원 정보 수정
	public void updateMember(String memberid, String memberpw, String membername, int memberage ) {
		String sql = "UPDATE membertbl SET memberpw=?, membername=?, memberage=? WHERE memberid =?";
		jdbcTemplate.update(sql, memberid, memberpw, membername, memberage);
		//정보 수정한 레코드 수를 반환(기본키로 검색시 성공-1 / 실패-0
	}
	
	//SELECT 문 구현 - 회원 1명만 가져오기
	public MemberDto searchMember(String memberid) {
		String sql = "SELECT * FROM membertbl WHERE memberid = ?";
		return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<MemberDto>(MemberDto.class), memberid);
		//return mDto;
	}
	
	//SELECT문 구현 - 전체 회원 가져오기
	public List<MemberDto> searchMembers() {
		String sql = "SELECT * FROM membertbl";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<MemberDto>(MemberDto.class));
		//return mDto;
	}
}
