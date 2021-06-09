/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.m1.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;

/**
 *
 * @author Valentin
 */
@Entity
@NamedQueries({
    @NamedQuery(name="Quai.getAllQuais", query="SELECT q FROM Quai q "
            + "WHERE q.station.id = :vstation"),
    @NamedQuery(name="Quai.getQuaisDispo", query="SELECT q FROM Quai q "
            + "WHERE q.station = :vstation "
            + "AND q.estLibre = true")
})
public class Quai implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private int noQuai;
    private boolean estLibre;
    
    @OneToOne
    public Station station;

    public Quai() {
    }

    public Quai(Long id, int noQuai, boolean estLibre, Station station) {
        this.id = id;
        this.noQuai = noQuai;
        this.estLibre = estLibre;
        this.station = station;
    }

    public int getNoQuai() {
        return noQuai;
    }

    public void setNoQuai(int noQuai) {
        this.noQuai = noQuai;
    }

    public boolean isEstLibre() {
        return estLibre;
    }

    public void setEstLibre(boolean estLibre) {
        this.estLibre = estLibre;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Quai)) {
            return false;
        }
        Quai other = (Quai) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fr.miage.m1.entities.Quai[ id=" + id + " ]";
    }
    
}
