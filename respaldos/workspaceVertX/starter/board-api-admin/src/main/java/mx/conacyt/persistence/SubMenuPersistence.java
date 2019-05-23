package mx.conacyt.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.conacyt.board.connection.ConnectionH2;
import mx.conacyt.board.model.SubMenu;

public class SubMenuPersistence {
	
	public List<SubMenu> getSubByMenu(long idMenu) throws SQLException {

		List<SubMenu> response = new ArrayList<SubMenu>();

		String sql = "SELECT ID_SUB_MENU, NOMBRE, RECURSO, ID_MENU, ID_USUARIO_CREACION FROM SUB_MENU WHERE ID_MENU = ?";

		PreparedStatement pstmt = ConnectionH2.getConnection().prepareStatement(sql);

		pstmt.setLong(1, idMenu);
		

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			SubMenu subMenu = new SubMenu();

			subMenu.setIdSubMenu(rs.getInt("ID_SUB_MENU"));
			subMenu.setNombre(rs.getString("NOMBRE"));
			subMenu.setRecurso(rs.getString("RECURSO"));
			subMenu.setIdMenu(rs.getInt("ID_MENU"));
			subMenu.setIdUsuarioCreacion(rs.getInt("ID_USUARIO_CREACION"));
			
			response.add(subMenu);
		}
		return response;
	}
}
