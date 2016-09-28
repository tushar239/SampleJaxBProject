package jaxbannotations;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Tushar Chokshi @ 4/26/15.
 */
@XmlRootElement(name = "BClass")
public class B {

    private String city;

    @XmlElement(required = true)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }


}
