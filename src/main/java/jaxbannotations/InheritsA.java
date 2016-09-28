package jaxbannotations;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement // it is not inherited from super class.
// @XmlAccessorType(XmlAccessType.NONE) // inherited from super class.
public class InheritsA extends A {

    @XmlElement
    private int b1 = 0;

    public int getB1() {
        return b1;
    }

    public void setB1(int b1) {
        this.b1 = b1;
    }

}
