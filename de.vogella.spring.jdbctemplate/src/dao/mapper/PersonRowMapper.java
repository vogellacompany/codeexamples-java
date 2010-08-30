package dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class PersonRowMapper implements RowMapper {

	@Override
	public Object mapRow(ResultSet rs, int line) throws SQLException {
		PersonResultSetExtractor extractor = new PersonResultSetExtractor();
		return extractor.extractData(rs);
	}

}
