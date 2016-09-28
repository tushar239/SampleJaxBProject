package jaxbannotations;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.SchemaOutputResolver;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;

/*
http://www.mytechnotes.biz/2012/08/jaxb-annotation-tutorial-with-code.html
http://www.caucho.com/resin-3.1/doc/jaxb-annotations.xtp#XmlElements
 */
public class Application {
    public static void main(String[] args) throws Exception{
        {
            //System.out.println("XML Schema for Class A");
            //generateSchema(A.class);

            A a = new A();
            a.setA1(1);
            a.setName("Tushar");
            A.staticField = "i am static";

            B b = new B();
            b.setCity("Sacramento");
            a.setB(b);

            final Order o1 = new Order();
            o1.setA1(1);
            o1.setA2(2);
            final Order o2 = new Order();
            o2.setA1(1);
            o2.setA2(2);
            a.setOrders(new ArrayList<Order>() {{
                add(o1);
                add(o2);
            }});


            final GeoCriteria geoCriteria = new GeoCriteria();
            geoCriteria.setRegion("western");
            final VehicleCriteria vehicleCriteria = new VehicleCriteria();
            vehicleCriteria.setVin("v123");
            a.setCriterias(new ArrayList<Criteria>(){{ add(geoCriteria); add(vehicleCriteria); }});

            System.out.println("POJO to XML for Class A");
            String xml = createXML(a);

            System.out.println("XML to POJO for Class A");
            A  deserializedAObj = (A) createObject(xml, A.class);
            System.out.println(deserializedAObj.getA1());
        }

        {
            InheritsA inheritsA = new InheritsA();
            inheritsA.setA1(1);
            inheritsA.setName("Tushar");
            inheritsA.setB1(2);
            System.out.println("POJO to XML for Class InheritsA");
            createXML(inheritsA);
        }

    }
    public static String createXML(Object obj) throws Exception {
        // Creating a Marshaller
        JAXBContext jaxbContext = JAXBContext.newInstance(obj.getClass());
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

        StringWriter result = new StringWriter();
        jaxbMarshaller.marshal(obj, result);

        // Printing XML
        String xml = result.toString();
        System.out.println(xml);
        return xml;
    }

    public static Object createObject(String xml, Class cls) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(cls);
        Unmarshaller jaxBUnmarshaller = jaxbContext.createUnmarshaller();
        Object obj = jaxBUnmarshaller.unmarshal(new StringReader(xml));
        return obj;
    }
    public static void generateSchema(Class cls) throws IOException, JAXBException {
        JAXBContext jc = JAXBContext.newInstance(cls);

        jc.generateSchema(new SchemaOutputResolver() {
            @Override
            public Result createOutput(String namespaceUri,
                                       String suggestedFileName) throws IOException {
                StreamResult result = new StreamResult(System.out);
                result.setSystemId(suggestedFileName);
                return result;
            }
        });
    }
}
