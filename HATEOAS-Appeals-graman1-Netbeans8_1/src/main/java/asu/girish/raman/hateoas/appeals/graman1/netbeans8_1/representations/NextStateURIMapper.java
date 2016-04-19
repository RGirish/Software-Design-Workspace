
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asu.girish.raman.hateoas.appeals.graman1.netbeans8_1.representations;

import javax.xml.bind.annotation.XmlElement;

/**
 *
 * @author Girish
 */
public class NextStateURIMapper {

    @XmlElement(name = "URIType")
    public String key;
    @XmlElement(name = "URI")
    public String value;

    private NextStateURIMapper() {
    }

    public NextStateURIMapper(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
