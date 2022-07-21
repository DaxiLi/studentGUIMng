package com.studentGUI;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;


public class XMLUtils {
    public static String list2XML(List<Student> list) {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        // 创建DocumentBuilder
        DocumentBuilder builder = null;
        try {
            builder = factory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
        // 创建Document
        org.w3c.dom.Document document = builder.newDocument();
        // 设置XML声明中standalone为yes，即没有dtd和schema作为该XML的说明文档，且不显示该属性
        document.setXmlStandalone(true);
        // 创建根节点
        org.w3c.dom.Element student = document.createElement("student");
        // 创建子节点，并设置属性
        for (Student val : list) {
            Element id = document.createElement("studentID");
            id.setAttribute("id", val.getStudentID());
            // 添加子节点
            Element name = document.createElement("name");
            name.setTextContent(val.getName());
            id.appendChild(name);
            Element gender = document.createElement("gender");
            gender.setTextContent(val.getGender());
            id.appendChild(gender);
            Element school = document.createElement("school");
            school.setTextContent(val.getSchool());
            id.appendChild(school);
            Element birth = document.createElement("birth");
            birth.setTextContent(val.getBirth());
            id.appendChild(birth);
            Element studentClass = document.createElement("class");
            studentClass.setTextContent(val.getStudentClass());
            id.appendChild(studentClass);
            Element others = document.createElement("others");
            others.setTextContent(val.getOthers());
            id.appendChild(others);
            // 为根节点添加子节点
            student.appendChild(id);
        }
        // 将根节点添加到Document下
        document.appendChild(student);

        /*
         * 下面开始实现： 生成XML文件
         */
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        // 创建TransformerFactory对象
        TransformerFactory transformerFactory = TransformerFactory.newInstance();

        // 创建Transformer对象
        Transformer transformer = null;
        try {
            transformer = transformerFactory.newTransformer();
            // 设置输出数据时换行
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            // 使用Transformer的transform()方法将DOM树转换成XML
            transformer.transform(new DOMSource(document), new StreamResult(outStream));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }

        System.out.println(outStream.toString());
        return outStream.toString();
    }

    public static List<Student> XML2list(String bf) {
        List<Student> list = new ArrayList<Student>();
        Document document = null;
        try {
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            StringBuffer sb = new StringBuffer();
            sb.append(bf);
            InputStream inputStream = new ByteArrayInputStream(bf.getBytes("UTF8"));
            document = documentBuilder.parse(inputStream);
            NodeList students = document.getElementsByTagName("studentID");
            for (int i = 0; i < students.getLength(); i++) {
                Node node = students.item(i);
                System.out.println("find ID:" +node.getAttributes().getNamedItem("id").getNodeValue());
                Student retStudent = new Student();
                retStudent.setStudentID(node.getAttributes().getNamedItem("id").getNodeValue());
                NodeList childNodes = node.getChildNodes();
                for (int j = 0; j < childNodes.getLength(); j++) {
                    String name = "";
                    String nodeVal = "";
                    if (childNodes.item(j).hasChildNodes()){
                        name = childNodes.item(j).getNodeName();
                        nodeVal = childNodes.item(j).getFirstChild().getNodeValue();
                    }
                    switch (name) {
                        case "name":
                            retStudent.setName(nodeVal);
                            System.out.println("set Name:" + nodeVal);
                            break;
                        case "gender":
                            retStudent.setGender(nodeVal);
                            System.out.println("gender set:" + nodeVal);
                            break;
                        case "school":
                            retStudent.setSchool(nodeVal);
                            System.out.println("school set:" + nodeVal);
                            break;
                        case "birth":
                            retStudent.setBirth(nodeVal);
                            System.out.println("birth set:" + retStudent.getBirth());
                            break;
                        case "class":
                            retStudent.setStudentClass(nodeVal);
                            System.out.println("class set:" + nodeVal);
                            break;
                        case "others":
                            retStudent.setOthers(nodeVal);
                            System.out.println("others set:" + nodeVal);
                            break;
                        default:System.out.println("unExpect value:" + name);
                    }

                }
                list.add(retStudent);
            }

        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return list;
        //302690e3-b524-42fd-f876-1f41cb509403
        //85c03991-f95a-43df-b05d-b743c9c0d42f
    }

    public static void main(String[] args) {
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("yuanjie", "12123135", new MyDate(2000, 2, 2), "xsxsxsx", "Xsxsxs", "xsxsxs", "scscscs"));
        Student b = new Student("cdcsc", "scscsjncijsd", new MyDate(2000, 2, 2), "xsxsxsx", "Xsxsxs", "xsxsxs", "scscscs");
        b.setOthers("this is others");
        list.add(b);

        String a = list2XML(list);
        System.out.println(a);
        List<Student> list1 = XML2list(a);
        System.out.println(list1);
    }
}
