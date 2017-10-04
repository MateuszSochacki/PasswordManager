package com.manager.model;

import javax.persistence.*;

@Entity
@Table(name = "PASSWORD", schema = "PUBLIC", catalog = "TEST")
public class PasswordEntity {
    private Integer idpassword;
    private String password;
    private String name;
    private String link;

    @Id
    @GeneratedValue
    @Column(name = "IDPASSWORD", nullable = false)
    public Integer getIdpassword() {
        return idpassword;
    }

    public void setIdpassword(Integer idpassword) {
        this.idpassword = idpassword;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = true, length = 255)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PasswordEntity that = (PasswordEntity) o;

        if (idpassword != null ? !idpassword.equals(that.idpassword) : that.idpassword != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idpassword != null ? idpassword.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

    @Basic
    @Column(name = "NAME", nullable = true, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "LINK", nullable = true, length = 255)
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
