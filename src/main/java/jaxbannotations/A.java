package jaxbannotations;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.XmlType;
import java.util.Collection;


@XmlRootElement(name = "root" , namespace = "http://www.myexample.com/jaxb")
// By default, JaxB will consider elements having getters for serialization(marshalling).
// If you want to provide annotations, you have to provide it on getter/setter methods. You cannot do it on Field level.
// To change this default behaviour, use @XmlAccessorType

//@XmlAccessorType(XmlAccessType.FIELD) // accept field level annotations. If you provide getter/setter level annotations, then it will throw IllegalAnnotationsException
@XmlAccessorType(XmlAccessType.NONE) // Field level or getter/setter level annotations are mandatory to serialize a property.
@XmlType(propOrder={"a1", "name", "staticField", "nillableField", "requiredField", "orders", "criterias" , "b"}) // defines an order in serialized xml
public class A {

    @XmlElement(name = "a1")
    private int a1;

    @XmlElement(name = "name")
    private String name;

    @XmlTransient // transient fields are not considered for serialization
    private String transientField;

    @XmlElement(required = true)
    public B b;

    @XmlElement(name = "staticField")
    public static String staticField; // static fields can also be serialized

    // nillable=true --- True enables an instance of the element to have the null attribute set to true. If value is null, then serialized xml contains this element with an attribute xsi:nil="true"
    @XmlElement(name = "nillableField", nillable = true)
    public String nillableField;

    // requried=true should force to set the value, but it is not. It is not working???
    @XmlElement(name = "requiredField", required = true) // default is required=false, means minOccurs="0" will be added to xml schema. If required=true, then minOccurs="1" won't be added because it is default in xml schema.
    private String requiredField;

    @XmlElementWrapper(name = "orders") // creates <orders> <order>...</order> <order>...</order> </orders>. Without a wrapper, <order>...</order> <order>...</order>
    @XmlElement(name = "order")
    private Collection<Order> orders;

    @XmlElementWrapper(name = "criterias") // creates <orders> <order>...</order> <order>...</order> </orders>. Without a wrapper, <order>...</order> <order>...</order>
    @XmlElements({
            @XmlElement(name="geo",type=GeoCriteria.class),
            @XmlElement(name="vehicle",type=VehicleCriteria.class)
    })
    private Collection<Criteria> criterias;

    public int getA1() {
        return a1;
    }

    //@XmlElement(name = "a1", required = true)
    public void setA1(int a1) {
        this.a1 = a1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTransientField() {
        return transientField;
    }

    public void setTransientField(String transientField) {
        this.transientField = transientField;
    }

    public String getRequiredField() {
        return requiredField;
    }

    public void setRequiredField(String requiredField) {
        this.requiredField = requiredField;
    }

    public B getB() {
        return b;
    }

    public void setB(B b) {
        this.b = b;
    }

    public Collection<Order> getOrders() {
        return orders;
    }

    public void setOrders(Collection<Order> orders) {
        this.orders = orders;
    }

    public Collection<Criteria> getCriterias() {
        return criterias;
    }

    public void setCriterias(Collection<Criteria> criterias) {
        this.criterias = criterias;
    }
}
