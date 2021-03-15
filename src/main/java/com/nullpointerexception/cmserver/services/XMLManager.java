package com.nullpointerexception.cmserver.services;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class XMLManager {

    private static final String xmlDocumentPath = "D:\\FileZillaServer\\FileZilla Server\\FileZilla Server.xml";

    public void addGroup(String groupName) throws Exception {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlDocumentPath);
        NodeList childrenOfRoot = document.getDocumentElement().getChildNodes();  //Getting children of <FileZillaServer>
        Element groups = null;
        for (int i = 0; i < childrenOfRoot.getLength(); i++) {
            if (childrenOfRoot.item(i).getNodeName().equals("Groups")) {
                groups = (Element) childrenOfRoot.item(i);
            }
        }
        //Create the new group
        Element newGroup = document.createElement("Group");
        newGroup.setAttribute("Name", groupName);


        // Create the first set of options for the group
        String[] optionAttributeNames = {"Bypass server userlimit", "User Limit", "IP Limit", "Enabled", "Comments", "ForceSsl"};
        int[] optionAttributeValues = {0, 5, 0, 1, -1, 0};

        //Create children of the new group
        for(int i = 0; i < optionAttributeNames.length; i++){
            Element option1 = document.createElement("Option");
            option1.setAttribute("Name", optionAttributeNames[i]);
            if(optionAttributeValues[i] != -1) {
                option1.appendChild(document.createTextNode(Integer.toString(optionAttributeValues[i])));
            }else{
                option1.appendChild(document.createTextNode(""));
            }
            newGroup.appendChild(option1);
        }

        Element ipFilter = document.createElement("IpFilter");
        Element ipFilterAllowed = document.createElement("Allowed");
        Element ipFilterDisallowed = document.createElement("Disallowed");
        ipFilter.appendChild(ipFilterDisallowed);
        ipFilter.appendChild(ipFilterAllowed);
        newGroup.appendChild(ipFilter);

        Element permissions = document.createElement("Permissions");
        Element permission = document.createElement("Permission");
        permission.setAttribute("Dir", "D:\\FileZillaServer\\FTPServerFiles\\" + groupName);
        String[] permissionAttributes = {"FileRead", "FileWrite", "FileDelete", "FileAppend", "DirCreate","DirDelete", "DirList", "DirSubdirs", "IsHome", "AutoCreate"};
        for (String permAttr : permissionAttributes) {
            Element option1 = document.createElement("Option");
            option1.setAttribute("Name", permAttr);
            option1.appendChild(document.createTextNode(Integer.toString(1)));
            permission.appendChild(option1);
            }
        permissions.appendChild(permission);
        newGroup.appendChild(permissions);

        String[] speedLimitAttributeNames = {"DlType", "DlLimit", "ServerDlLimitBypass", "UlType", "UlLimit", "ServerUlLimitBypass"};
        int[] speedLimitAttributeValues = {1, 10, 0, 1, 10, 0};
        Element speedLimits = document.createElement("SpeedLimits");
        for (int i = 0; i < speedLimitAttributeNames.length; i++){
            speedLimits.setAttribute(speedLimitAttributeNames[i], Integer.toString(speedLimitAttributeValues[i]));
        }
        Element speedLimitsDownload = document.createElement("Download");
        Element speedLimitsUpload = document.createElement("Upload");
        speedLimits.appendChild(speedLimitsDownload);
        speedLimits.appendChild(speedLimitsUpload);
        newGroup.appendChild(speedLimits);

        if (groups != null) {
            groups.appendChild(newGroup);
        } else {
            throw new NullPointerException("Groups node not found!");
        }

        DOMSource source = new DOMSource(document);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        StreamResult result = new StreamResult(xmlDocumentPath);
        transformer.transform(source, result);
    }

    public void addUser(String groupName, String userName, String password, String salt) throws Exception {
        Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(xmlDocumentPath);
        NodeList childrenOfRoot = document.getDocumentElement().getChildNodes();  //Getting children of <FileZillaServer>
        Element users = null;
        for (int i = 0; i < childrenOfRoot.getLength(); i++) {
            if (childrenOfRoot.item(i).getNodeName().equals("Users")) {
                users = (Element) childrenOfRoot.item(i);
            }
        }
        //Create the new group
        Element newGroup = document.createElement("User");
        newGroup.setAttribute("Name", userName);

        Element passwordElement = document.createElement("Option");
        passwordElement.setAttribute("Name", "Pass");
        passwordElement.appendChild(document.createTextNode(password));
        newGroup.appendChild(passwordElement);

        Element saltElement = document.createElement("Option");
        saltElement.setAttribute("Name", "Group");
        saltElement.appendChild(document.createTextNode(groupName));
        newGroup.appendChild(saltElement);

        Element grpElement = document.createElement("Option");
        grpElement.setAttribute("Name", "Salt");
        grpElement.appendChild(document.createTextNode(salt));
        newGroup.appendChild(grpElement);

        // Create the first set of options for the group
        String[] optionAttributeNames = {"Bypass server userlimit", "User Limit", "IP Limit", "Enabled", "Comments", "ForceSsl"};
        int[] optionAttributeValues = {2, 0, 0, 2, -1, 2};

        //Create children of the new group
        for(int i = 0; i < optionAttributeNames.length; i++){
            Element option1 = document.createElement("Option");
            option1.setAttribute("Name", optionAttributeNames[i]);
            if(optionAttributeValues[i] != -1) {
                option1.appendChild(document.createTextNode(Integer.toString(optionAttributeValues[i])));
            }else{
                option1.appendChild(document.createTextNode(""));
            }
            newGroup.appendChild(option1);
        }

        Element ipFilter = document.createElement("IpFilter");
        Element ipFilterAllowed = document.createElement("Allowed");
        Element ipFilterDisallowed = document.createElement("Disallowed");
        ipFilter.appendChild(ipFilterDisallowed);
        ipFilter.appendChild(ipFilterAllowed);
        newGroup.appendChild(ipFilter);

        String[] speedLimitAttributeNames = {"DlType", "DlLimit", "ServerDlLimitBypass", "UlType", "UlLimit", "ServerUlLimitBypass"};
        int[] speedLimitAttributeValues = {0, 10, 2, 0, 10, 2};
        Element speedLimits = document.createElement("SpeedLimits");
        for (int i = 0; i < speedLimitAttributeNames.length; i++){
            speedLimits.setAttribute(speedLimitAttributeNames[i], Integer.toString(speedLimitAttributeValues[i]));
        }
        Element speedLimitsDownload = document.createElement("Download");
        Element speedLimitsUpload = document.createElement("Upload");
        speedLimits.appendChild(speedLimitsDownload);
        speedLimits.appendChild(speedLimitsUpload);
        newGroup.appendChild(speedLimits);

        if (users != null) {
            users.appendChild(newGroup);
        } else {
            throw new NullPointerException("Groups node not found!");
        }

        DOMSource source = new DOMSource(document);
        Transformer transformer = TransformerFactory.newInstance().newTransformer();
        StreamResult result = new StreamResult(xmlDocumentPath);
        transformer.transform(source, result);
    }
    
    public void reloadConfiguration() {
		try {
			Process p = Runtime.getRuntime().exec(new String[]{"D:\\FileZillaServer\\FileZilla Server\\FileZilla server.exe","/reload-config"});
			System.out.println(p.waitFor());
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }

}
