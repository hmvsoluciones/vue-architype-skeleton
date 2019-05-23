package mx.conacyt.board.model;

import java.io.Serializable;
import java.util.List;

public class MenuDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3682281388066433586L;
	private long idMenu;
	private String nombre;
	private String icono;
	private List<SubMenuDTO> subMenus;

	public List<SubMenuDTO> getSubMenus() {
		return subMenus;
	}

	public void setSubMenus(List<SubMenuDTO> subMenus) {
		this.subMenus = subMenus;
	}

	public long getIdMenu() {
		return idMenu;
	}

	public void setIdMenu(long idMenu) {
		this.idMenu = idMenu;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}

}
