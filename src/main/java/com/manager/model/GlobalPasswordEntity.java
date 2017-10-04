package com.manager.model;

import javax.persistence.*;


@Entity
@Table(name = "GLOBAL", schema = "PUBLIC", catalog = "TEST")
public class GlobalPasswordEntity {
    private Integer idglobal;
    private String globalpassword;

    @Id
    @Column(name = "IDGLOBAL", nullable = false)
    public Integer getIdglobal() {
        return idglobal;
    }

    public void setIdglobal(Integer idglobal) {
        this.idglobal = idglobal;
    }

    @Basic
    @Column(name = "GLOBALPASSWORD", nullable = true, length = 255)
    public String getGlobalpassword() {
        return globalpassword;
    }

    public void setGlobalpassword(String globalpassword) {
        this.globalpassword = globalpassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GlobalPasswordEntity that = (GlobalPasswordEntity) o;

        if (idglobal != null ? !idglobal.equals(that.idglobal) : that.idglobal != null) return false;
        if (globalpassword != null ? !globalpassword.equals(that.globalpassword) : that.globalpassword != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idglobal != null ? idglobal.hashCode() : 0;
        result = 31 * result + (globalpassword != null ? globalpassword.hashCode() : 0);
        return result;
    }
}
