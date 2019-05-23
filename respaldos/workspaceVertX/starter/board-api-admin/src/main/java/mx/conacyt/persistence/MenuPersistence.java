package mx.conacyt.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.conacyt.board.connection.ConnectionH2;
import mx.conacyt.board.model.Menu;

public class MenuPersistence {
	
	public List<Menu> getMenuByRole(int idRol) throws SQLException {

		List<Menu> response = new ArrayList<Menu>();

		String sql = "SELECT ID_MENU, ID_ROL, NOMBRE, ICONO, ID_USUARIO_CREACION FROM MENU WHERE ID_ROL = ?";

		PreparedStatement pstmt = ConnectionH2.getConnection().prepareStatement(sql);

		pstmt.setInt(1, idRol);
		

		ResultSet rs = pstmt.executeQuery();

		while (rs.next()) {
			Menu menu = new Menu();

			menu.setIdMenu(rs.getInt("ID_MENU"));
			menu.setIdRol(rs.getInt("ID_ROL"));
			menu.setIcono(rs.getString("ICONO"));
			menu.setIdUsuarioCreacion(rs.getInt("ID_USUARIO_CREACION"));
			menu.setNombre(rs.getString("NOMBRE"));
			
			response.add(menu);
		}
		return response;
	}
}
