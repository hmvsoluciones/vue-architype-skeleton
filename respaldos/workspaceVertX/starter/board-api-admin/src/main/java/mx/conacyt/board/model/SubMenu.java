package mx.conacyt.board.model;

public class SubMenu {
	private long idSubMenu;
	private String nombre;
	private String recurso;
	private int idMenu;
	private int idUsuarioCreacion;

	public long getIdSubMenu() {
		return idSubMenu;
	}

	public void setIdSubMenu(long idSubMenu) {
		this.idSubMenu = idSubMenu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRecurso() {
		return recurso;
	}

	public void setRecurso(String recurso) {
		this.recurso = recurso;
	}

	public int getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(int idMenu) {
		this.idMenu = idMenu;
	}

	public int getIdUsuarioCreacion() {
		return idUsuarioCreacion;
	}

	public void setIdUsuarioCreacion(int idUsuarioCreacion) {
		this.idUsuarioCreacion = idUsuarioCreacion;
	}

}
