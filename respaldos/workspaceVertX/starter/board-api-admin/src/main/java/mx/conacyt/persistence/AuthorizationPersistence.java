package mx.conacyt.persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mx.conacyt.board.connection.ConnectionH2;
import mx.conacyt.board.model.Rol;
import mx.conacyt.board.model.User;

public class AuthorizationPersistence {
	public User login(User user) throws SQLException {

		User response = null;

		String sql = "SELECT ID_USUARIO,NOMBRE,APELLIDO_PATERNO,APELLIDO_MATERNO,USUARIO FROM USUARIO WHERE USUARIO LIKE ? AND CREDENCIAL LIKE ?";

		PreparedStatement pstmt = ConnectionH2.getConnection().prepareStatement(sql);

		pstmt.setString(1, user.getUsuario());
		pstmt.setString(2, user.getCredencial());

		ResultSet rs = pstmt.executeQuery();

		if (rs.next()) {
			response = new User();

			response.setIdUsuario(rs.getInt("ID_USUARIO"));
			response.setNombre(rs.getString("NOMBRE"));
			response.setApellidoPaterno(rs.getString("APELLIDO_PATERNO"));
			response.setApellidoMaterno(rs.getString("APELLIDO_MATERNO"));
			response.setUsuario(rs.getString("USUARIO"));
		}
		return response;
	}

	public List<Rol> getAllRoles() throws SQLException {

		String sql = "SELECT ID_ROL, NOMBRE, FECHA_CREACION, ID_USUARIO, URL, IMG FROM ROL";

		PreparedStatement pstmt = ConnectionH2.getConnection().prepareStatement(sql);

		ResultSet rs = pstmt.executeQuery();
		List<Rol> response = new ArrayList<Rol>();
		while (rs.next()) {
			Rol rol = new Rol();
			rol.setIdRol(rs.getInt("ID_ROL"));
			rol.setNombre(rs.getString("NOMBRE"));
			rol.setFechaCreacion(rs.getDate("FECHA_CREACION"));
			rol.setIdUsuarioCreacion(rs.getInt("ID_USUARIO_CREACION"));
			rol.setUrl(rs.getString("URL"));
			rol.setImg(rs.getString("IMG"));

			response.add(rol);
		}
		return response;
	}

	public List<Rol> getRolesByUser(int idUsuario) throws SQLException {

		String sql = "SELECT r.ID_ROL, r.NOMBRE, r.FECHA_CREACION, r.ID_USUARIO_CREACION, r.URL, r.IMG FROM ROL r "
				+ "INNER JOIN ROL_USUARIO ru ON ru.ID_ROL = r.ID_ROL WHERE ru.ID_USUARIO = ? ORDER BY r.NOMBRE";

		PreparedStatement pstmt = ConnectionH2.getConnection().prepareStatement(sql);
		pstmt.setInt(1, idUsuario);

		ResultSet rs = pstmt.executeQuery();
		List<Rol> response = new ArrayList<Rol>();
		while (rs.next()) {
			Rol rol = new Rol();
			rol.setIdRol(rs.getInt("ID_ROL"));
			rol.setNombre(rs.getString("NOMBRE"));
			rol.setFechaCreacion(rs.getDate("FECHA_CREACION"));
			rol.setIdUsuarioCreacion(rs.getInt("ID_USUARIO_CREACION"));
			rol.setUrl(rs.getString("URL"));
			rol.setImg(rs.getString("IMG"));

			response.add(rol);
		}
		return response;
	}
}
