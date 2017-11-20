//@author Bozzay, Ádám
package hu.kleatech.bead1.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="UserTable")
public class User implements Serializable {
	@Column(name="name")
	private String name;
	@Column(name="uniqueAlias")
	private String uniqueAlias;
	@Column(name="passwd")
	private String passwd;
	@OneToMany(mappedBy = "owner")
	private List<PencilCase> pencilCases;
	@Id
	@GeneratedValue
	@Column(name="id")
	private Long id;

	protected User() {}

	public User(String name, String uniqueAlias, String passwd) {
		this.name = name;
		this.uniqueAlias = uniqueAlias;
		this.passwd = passwd;
	}
	public String getName() {
		return name;
	}
	public String getUniqueAlias() {
		return uniqueAlias;
	}
	public String getPasswd() {
		return passwd;
	}
	public List<PencilCase> getPencilCases() {
		return Collections.unmodifiableList(pencilCases);
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	@Override
	public int hashCode() {
		int hash = 7;
		hash = 53 * hash + Objects.hashCode(this.uniqueAlias);
		return hash;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final User other = (User) obj;
		if (Objects.equals(this.id, other.id))
			return true;
		return Objects.equals(this.uniqueAlias, other.uniqueAlias);
	}
	@Override
	public String toString() {
		return "User{" + "name=" + name + ", uniqueAlias=" + uniqueAlias + '}';
	}

}
